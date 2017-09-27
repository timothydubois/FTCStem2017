package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by jdubois on 11/14/2016.
 */

@Autonomous(name = "STEM Autonomous Drive")


public class STEM_Autonomous_Drive extends LinearOpMode

{

    private DcMotor motorLeft;
    private DcMotor motorRight;

    //private Servo flipperLeft;
    //private Servo flipperRight;

    private TouchSensor touch;

    private static final double LEFT_RETRACTED_POSITION = 0.4;
    private static final double LEFT_EXTENDED_POSITION = 0.6;
    private static final double RIGHT_RETRACTED_POSITION = 0.6;
    private static final double RIGHT_EXTENDED_POSITION = 0.4;
    private static final double ARM_CENTER_POSITION = 0.5;


    @Override
    public void runOpMode() throws InterruptedException

    {
        motorLeft = hardwareMap.dcMotor.get("left_drive");
        motorRight = hardwareMap.dcMotor.get("right_drive");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        //flipperLeft = hardwareMap.servo.get("left_hand");
        //flipperRight = hardwareMap.servo.get("right_hand");

        touch = hardwareMap.touchSensor.get("touchey");

        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //THIS LINE USE LATER

        //flipperLeft.setPosition(ARM_CENTER_POSITION);
        //flipperRight.setPosition(ARM_CENTER_POSITION);

        waitForStart();

        motorLeft.setTargetPosition(2880);
        motorRight.setTargetPosition(2880);

        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


//        while (opModeIsActive()) {
//
//            if (touch.isPressed()) {
//                motorLeft.setPower(0.3);
//                motorRight.setPower(0.3);
//                Thread.sleep(2000);
//            } else {
//                motorLeft.setPower(-0.1);
//                motorRight.setPower(-0.1);
//            }


//            flipperLeft.setPosition(LEFT_EXTENDED_POSITION);
//            flipperRight.setPosition(RIGHT_EXTENDED_POSITION);
//            Thread.sleep(1000);
//
//            flipperLeft.setPosition(LEFT_RETRACTED_POSITION);
//            flipperRight.setPosition(RIGHT_RETRACTED_POSITION);
//            Thread.sleep(1000);

     //   }


    }

}
