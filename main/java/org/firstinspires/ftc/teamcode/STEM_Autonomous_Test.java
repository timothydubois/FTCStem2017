package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

//import org.firstinspires.ftc.robotcontroller.external.samples.Flipper;

/**
 * Created by jdubois on 11/14/2016.
 */

@Autonomous(name = "STEM Autonomous Test")


public class STEM_Autonomous_Test extends LinearOpMode

{

    private DcMotor motorLeft;
    private DcMotor motorRight;

//    private Servo flipperLeft;
//    private Servo flipperRight;
//
//    private TouchSensor touch;
//
//    private static final double LEFT_RETRACTED_POSITION = 0.4;
//    private static final double LEFT_EXTENDED_POSITION = 0.6;
//    private static final double RIGHT_RETRACTED_POSITION = 0.6;
//    private static final double RIGHT_EXTENDED_POSITION = 0.4;
//    private static final double ARM_CENTER_POSITION = 0.5;


    @Override
    public void runOpMode() throws InterruptedException

    {
        motorLeft = hardwareMap.dcMotor.get("left_drive");
        motorRight = hardwareMap.dcMotor.get("right_drive");

        motorRight.setDirection(DcMotor.Direction.REVERSE);

        //flipperLeft = hardwareMap.servo.get("left_hand");
        //flipperRight = hardwareMap.servo.get("right_hand");

        //touch = hardwareMap.touchSensor.get("touchey");

        //flipperLeft.setPosition(ARM_CENTER_POSITION);
        //flipperRight.setPosition(ARM_CENTER_POSITION);

        waitForStart();


       // while (opModeIsActive()) {

         //   if (touch.isPressed()) {

                Thread.sleep(22500);

                motorLeft.setPower(0.2);
                motorRight.setPower(0.2);
                Thread.sleep(2800);
          //  } else {
                motorLeft.setPower(0.0);
                motorRight.setPower(0.0);
          //  }


//            flipperLeft.setPosition(LEFT_EXTENDED_POSITION);
//            flipperRight.setPosition(RIGHT_EXTENDED_POSITION);
//            Thread.sleep(1000);
//
//            flipperLeft.setPosition(LEFT_RETRACTED_POSITION);
//            flipperRight.setPosition(RIGHT_RETRACTED_POSITION);
//            Thread.sleep(1000);

        }


    }


