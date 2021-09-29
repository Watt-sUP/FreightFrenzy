package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Brat {

    public DcMotor ext, rot;
    public CRServo ser;
    public Servo clap;
    public boolean matura = false, clapeta = false;

    public Brat(DcMotor ext, DcMotor rot, CRServo ser, Servo clap) {
        this.ext = ext;
        this.rot = rot;
        this.ser = ser;
        this.clap = clap;

        this.ext.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.clap.setPosition(0.1);
    }

    public void cutie() {
        if (matura)
            ser.setPower(0.1);
        else
            ser.setPower(-1.0);
        matura = !matura;
    }

    public void move(double xPow, double yPow) {
        rot.setPower(xPow);
        ext.setPower(yPow);
    }

    public void clapeta() {
        if (clapeta)
            clap.setPosition(0.5);
        else
            clap.setPosition(0.1);
        clapeta = !clapeta;
    }

}
