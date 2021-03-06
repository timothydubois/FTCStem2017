/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

    @TeleOp(name="Scissor Lift Test", group="Linear Opmode")  // @Autonomous(...) is the other common choice
  //  @Disabled
    public class Scissor_Lift_Test extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftMotor = null;   // uncommented
    DcMotor rightMotor = null;  // uncommented
    DcMotor leftLiftMotor = null;
    DcMotor rightLiftMotor = null;
    Servo leftLiftServo = null;
    Servo rightLiftServo = null;

    public static final double leftGateOpen = 0.25;
    public static final double leftGateClosed = 0.75;
    public static final double rightGateOpen = 0.75;
    public static final double rightGateClosed = 0.25;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
    /* Declare OpMode members. */
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        leftMotor = hardwareMap.dcMotor.get("left_drive");   // uncommented
        rightMotor = hardwareMap.dcMotor.get("right_drive");  // uncommented
        leftLiftMotor = hardwareMap.dcMotor.get("left_lift");
        rightLiftMotor = hardwareMap.dcMotor.get("right_lift");

        leftLiftServo = hardwareMap.servo.get("left_hand");
        rightLiftServo = hardwareMap.servo.get("right_hand");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors  // uncommented
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors  // uncommented

        leftLiftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors  // uncommented
        rightLiftMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors  // uncommented

        leftLiftServo.setPosition(leftGateClosed);
        rightLiftServo.setPosition(rightGateClosed);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            double left_power = -gamepad1.left_stick_y;
            double right_power = -gamepad1.right_stick_y;

            //left_power = Math.pow(left_power, 3);
            left_power = left_power * left_power * left_power;

            //right_power = Math.pow(right_power, 3);
            right_power = right_power * right_power * right_power;

            /*
            if (left_power < 0.0)  {
                left_power = -left_power * left_power;
            }

            else  {
                left_power = left_power * left_power;
            }
            */

            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            leftMotor.setPower(left_power);  // negative sign needed
            rightMotor.setPower(right_power);  //negative sign needed

            if (gamepad1.right_trigger > 0.1 && gamepad1.left_trigger < 0.1) {
                // When right trigger is pressed, run both lift motors up
                leftLiftMotor.setPower(gamepad1.right_trigger * gamepad1.right_trigger);
                rightLiftMotor.setPower(gamepad1.right_trigger * gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0.1 && gamepad1.right_trigger < 0.1) {
                // When left trigger is pressed, run both lift motors down
                leftLiftMotor.setPower(-gamepad1.left_trigger * gamepad1.left_trigger);
                rightLiftMotor.setPower(-gamepad1.left_trigger * gamepad1.left_trigger);
            } else if (gamepad1.right_bumper && !gamepad1.left_bumper) {
                // When right bumper is pressed nudge the right motor
                rightLiftMotor.setPower(-0.15);
            } else if (gamepad1.left_bumper && !gamepad1.right_bumper) {
                // When left bumper is pressed nudge the left motor
                leftLiftMotor.setPower(-0.15);
            } else {
                // If more than one is pressed, then don't run motors
                leftLiftMotor.setPower(0.0);
                rightLiftMotor.setPower(0.0);
            }

            if (gamepad1.a) {
                leftLiftServo.setPosition(leftGateOpen);
                rightLiftServo.setPosition(rightGateOpen);
                telemetry.addData("Text", "*** Button A ***");
            }

            if (gamepad1.y) {
                leftLiftServo.setPosition(leftGateClosed);
                rightLiftServo.setPosition(rightGateClosed);
                telemetry.addData("Text", "*** Button Y ***");
            }
        }    // while (opModeIsActive())

    }    // public void runOpMode()
}