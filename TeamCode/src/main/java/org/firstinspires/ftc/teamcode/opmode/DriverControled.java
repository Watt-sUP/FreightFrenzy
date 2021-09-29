package org.firstinspires.ftc.teamcode.opmode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.gamepad.Axis;
import org.firstinspires.ftc.teamcode.gamepad.Button;
import org.firstinspires.ftc.teamcode.gamepad.GamepadEx;

@TeleOp(name="Driver Controled" , group="Linear Opmode")
//@Disabled
public class DriverControled extends LinearOpMode {

    public ElapsedTime runtime = new ElapsedTime();
    private org.firstinspires.ftc.teamcode.hardware.Mugurel robot;
    private boolean changed1 = false;
    private boolean changed2 = false;
    private boolean isPressed1 = false;
    private boolean isPressed2 = false;
    private boolean isPressed3 = false;
    private double posi = 0.5;

    private GamepadEx gaju, andrei;

    @Override
    public void runOpMode()  {

        robot = new org.firstinspires.ftc.teamcode.hardware.Mugurel(hardwareMap, telemetry, this, runtime);


        gaju = new GamepadEx(gamepad1);
        andrei = new GamepadEx(gamepad2);

       // robot.setOpmode(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Status", "Waiting in init");
            telemetry.update();
        }
        runtime.reset();

        robot.runner.setFace(0);
        robot.runner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();
        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()){
            gaju.update();
            andrei.update();

            telemetry.addData("Status", "Run Time: " + runtime.toString());

            setFace(gaju.y, gaju.a, gaju.x, gaju.b);
            move(gaju.left_x, gaju.left_y, gaju.right_x, gaju.left_trigger.toButton(0.3), gaju.right_trigger.toButton(0.3), gaju.dpad_left, gaju.dpad_right);
            brat(andrei.left_x, andrei.right_y, andrei.a);
            if (andrei.x.raw && !isPressed1) {
                isPressed1 = true;
                if (changed2) {
                    robot.rul.setPower(0.0);
                } else {
                    robot.rul.setPower(-1.0);
                }
                changed2 = !changed2;
            } else if (!andrei.x.raw && isPressed1) {
                isPressed1 = false;
            }
            if (andrei.y.raw && !isPressed3) {
                isPressed3 = true;
                robot.brat.clapeta();
            } else if (!andrei.y.raw && isPressed3) {
                isPressed3 = false;
            }
            telemetry.update();
        }

    }

    private void setFace(Button front, Button back, Button left, Button right) {
        if (front != null && front.pressed()) robot.runner.setFace(0);
        else if (back != null && back.pressed()) robot.runner.setFace(Math.PI);
        else if (left != null && left.pressed()) robot.runner.setFace(Math.PI / 2.0);
        else if (right != null && right.pressed()) robot.runner.setFace(-Math.PI / 2.0);
    }

    private void move(Axis lx, Axis ly, Axis rx, Button smallPower, Button mediumPower, Button dl, Button dr) {
        double modifier = 1.0;
        if (smallPower != null && smallPower.raw) modifier = 0.23;
        if (mediumPower != null && mediumPower.raw)  modifier = 0.5;

        final double drive_y = robot.runner.scalePower(ly.raw);
        final double drive_x = robot.runner.scalePower(lx.raw);
        final double turn = -robot.runner.scalePower(rx.raw);

        if (dr != null && dr.raw) robot.runner.moveWithAngle(-1,0,0, modifier);
        else if (dl != null && dl.raw) robot.runner.moveWithAngle(1,0,0, modifier);
        else robot.runner.moveWithAngle(drive_x, drive_y, turn, modifier);
    }

    private void brat(Axis lx, Axis ly, Button cutie) {
        if (cutie.raw && !isPressed2) {
            robot.brat.cutie();
            isPressed2 = true;
        } else if (!cutie.raw && isPressed2) {
            isPressed2 = false;
        }


        final double brat_x = lx.raw;
        final double brat_y = ly.raw;

        robot.brat.move(brat_x, brat_y);
    }

}
