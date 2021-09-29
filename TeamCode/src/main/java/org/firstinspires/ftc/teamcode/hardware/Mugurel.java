package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Mugurel {

    public Runner runner;
    public Brat brat;
    public DcMotor rul;

    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public LinearOpMode opMode;
    public ElapsedTime runtime;

     public void init() {
        runner = new Runner(
                hardwareMap.get(DcMotorEx.class, Config.left_back),
                hardwareMap.get(DcMotorEx.class, Config.left_front),
                hardwareMap.get(DcMotorEx.class, Config.right_back),
                hardwareMap.get(DcMotorEx.class, Config.right_front)
        );

        brat = new Brat(
                hardwareMap.get(DcMotor.class, Config.extinsBrat),
                hardwareMap.get(DcMotor.class, Config.rotBrat),
                hardwareMap.get(CRServo.class, Config.cutie),
                hardwareMap.get(Servo.class, Config.clap)
        );

        rul = hardwareMap.get(DcMotor.class, Config.ruleta);
        rul.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public Mugurel () {}

    public Mugurel (HardwareMap hm){
        hardwareMap = hm;
        init();
    }

    public Mugurel(HardwareMap hm, Telemetry t, LinearOpMode opmode, ElapsedTime _runtime) {
        hardwareMap = hm;
        telemetry = t;
        opMode = opmode;
        runtime = _runtime;
        init();
    }

    public void setTelemetry (Telemetry _t) { telemetry = _t; }
    public void setOpmode(LinearOpMode _o) { opMode = _o; }
}
