package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class Runner {

    public DcMotor left_front , right_front ;
    public DcMotor left_back , right_back ;

    public double face_orientation;
    public final double runner_angle = Math.PI / 4.0;

    public double scalePower(final double speed) {

        return Math.pow(speed, 3);
    }


    public class MotorPowers {
        public double lf, lb, rf, rb;

        MotorPowers(double dLf, double dLb, double dRf, double dRb) {
            lf = dLf;
            lb = dLb;
            rf = dRf;
            rb = dRb;
        }

        public void normalize() {
            double mx = Math.max(Math.max(Math.abs(lf), Math.abs(lb)), Math.max(Math.abs(rf), Math.abs(rb)));
            if (mx > 1.0) {
                lf /= mx;
                lb /= mx;
                rf /= mx;
                rb /= mx;
            }
        }

        public void speed(double spd) {
            spd = Math.abs(spd);

            if (spd > 1.0) spd = 1.0;

            double mx = Math.max(Math.max(Math.abs(lf), Math.abs(lb)), Math.max(Math.abs(rf), Math.abs(rb)));

            if (spd <= 1.0 && mx < spd) {
                double newSpeed = spd / mx;

                lf *= newSpeed;
                lb *= newSpeed;
                rf *= newSpeed;
                rb *= newSpeed;
            }
        }

        public void driveSpeed (double ds) {
            lf *= ds;
            lb *= ds;
            rf *= ds;
            rb *= ds;
        }
    }

    public Runner(DcMotor lf, DcMotor rf, DcMotor lb, DcMotor rb) {
        left_front = lf;
        right_front = rf;
        left_back = lb;
        right_back = rb;

        face_orientation = 0;

        left_front.setDirection(DcMotorSimple.Direction.REVERSE);
        left_back.setDirection(DcMotorSimple.Direction.REVERSE);
        right_front.setDirection(DcMotorSimple.Direction.FORWARD);
        right_back.setDirection(DcMotorSimple.Direction.FORWARD);

        left_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setMode(DcMotor.RunMode mode) {
        left_front.setMode(mode);
        left_back.setMode(mode);
        right_front.setMode(mode);
        right_back.setMode(mode);
    }

    public void setPower(MotorPowers pw) {
        setPower(pw.lf, pw.lb, pw.rf, pw.rb);
    }
    public void setPower (double pw) {setPower(pw, pw, pw, pw); }


    public void setPower(double lf, double lb, double rf, double rb) {
        left_front.setPower(lf);
        left_back.setPower(lb);
        right_front.setPower(rf);
        right_back.setPower(rb);
    }

    public MotorPowers angleDriveFromAxes(double x, double y, double t) {

        double angle = Math.atan2(x, y);
        double speed = Math.min(1.0, Math.sqrt(x * x + y * y));

        return angleDrive(speed, angle, t);
    }

    public MotorPowers angleDrive(double speed, double angle, double turn) {
        angle += face_orientation;
        angle += runner_angle;

        double lf = speed * Math.sin(-angle) - turn;
        double rf = speed * Math.cos(-angle) + turn;
        double lb = speed * Math.cos(-angle) - turn;
        double rb = speed * Math.sin(-angle) + turn;

        MotorPowers mpw = new MotorPowers(lf, lb, rf, rb);

        mpw.normalize();
        mpw.speed(speed);

        return mpw;
    }

    public MotorPowers directDrive (double x, double y, double r){

        double lf = Range.clip(x + y - r,-1.0,1.0);
        double rf = Range.clip(x - y + r,-1.0,1.0);
        double lb = Range.clip(x - y - r,-1.0,1.0);
        double rb = Range.clip(x + y + r,-1.0,1.0);

        MotorPowers dpw = new MotorPowers(lf, lb, rf, rb);

        dpw.normalize();
        dpw.speed(x);

        return dpw;

    }

    public void moveWithAngle (double x, double y, double r, double ds) {
        MotorPowers pw = angleDriveFromAxes(x, y, r);
        pw.driveSpeed(ds);
        setPower(pw);

    }

    public void directMove (double x, double y, double r, double ds){
        MotorPowers pw = directDrive(x, y, r);
        pw.driveSpeed(ds);
        setPower(pw);

    }



    public void setFace(double angle) {
        face_orientation = angle;
    }
    public void stop (){
      setPower(0.0);
      setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void reset (DcMotor.RunMode mode){
       setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      setMode(mode);
    }
    public void setTargetPosition (int lf, int lb, int rf, int rb ){
    left_front.setTargetPosition(lf);
    left_back.setTargetPosition(lb);
    right_front.setTargetPosition(rf);
    right_back.setTargetPosition(rb);
    }
    public int getTicksDistance (){
     int sum = 0;
     sum += Math.abs(left_front.getCurrentPosition() - left_front.getTargetPosition());
     sum += Math.abs(left_back.getCurrentPosition() - left_back.getTargetPosition() );
     sum += Math.abs(right_front.getCurrentPosition() - right_front.getTargetPosition());
     sum += Math.abs(right_back.getCurrentPosition() - right_back.getTargetPosition());
     return sum / 4;
    }

   public boolean isBusy (){
      int limit = 10;
      if ( getTicksDistance() <= limit)
        return false;
      return true;
    }








}
