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

    @TeleOp(name="Scissor Lift", group="Linear Opmode")  // @Autonomous(...) is the other common choice
  //  @Disabled
    public class Scissor_Lift extends LinearOpMode {

        private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftMotor = null;   // uncommented
    DcMotor rightMotor = null;  // uncommented
    DcMotor leftLiftMotor = null;
    // DcMotor rightLiftMotor = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
    /* Declare OpMode members. */
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        leftMotor  = hardwareMap.dcMotor.get("left_drive");   // uncommented
        rightMotor = hardwareMap.dcMotor.get("right_drive");  // uncommented
        leftLiftMotor = hardwareMap.dcMotor.get("left_lift");
        // rightLiftMotor = hardwareMap.dcMotor.get("right_lift");



        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors  // uncommented
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors  // uncommented

        leftLiftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors  // uncommented
        //rightLiftMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors  // uncommented

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            float left_power = -gamepad1.left_stick_y;
            float right_power = -gamepad1.right_stick_y;

            left_power = left_power * left_power * left_power;
            right_power = right_power * right_power * right_power;

            // left_power = Range.clip(left_power, -1,1);
            // right_power = Range.clip(right_power, -1,1);



            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            leftMotor.setPower(left_power);  // negative sign needed
            rightMotor.setPower(right_power);  //negative sign needed

            // if button pressed, turn both lift motors
            if (gamepad1.right_bumper && !gamepad1.left_bumper) {
                leftLiftMotor.setPower(0.3);
                // rightLiftMotor.setPower(0.3);
            }


             else if (gamepad1.left_bumper && !gamepad1.right_bumper) {
                leftLiftMotor.setPower(-0.3);
                // rightLiftMotor.setPower(0.3);
            }

            else {
                leftLiftMotor.setPower(0.0);
                //rightLiftMotor.setPower(0.0);
            }

            // leftLiftMotor.setPower(gamepad1.right_trigger);



        }
    }
}
