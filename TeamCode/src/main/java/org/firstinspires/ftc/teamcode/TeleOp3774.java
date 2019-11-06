package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Tele3774")
public class TeleOp3774 extends LinearOpMode {

    MotorController motors;

    @Override
    public void runOpMode() throws InterruptedException {

        //Takes care of the motor assignment just so that it stays consistent between different
        //opmodes. All motor names can be changed in here. Drive code need not be made in opmodes.
        motors = new MotorController(hardwareMap);

        waitForStart();
        motors.handLeft.setPosition(0.2);
        motors.handRight.setPosition(0.65);

        while(opModeIsActive()){
            //This is basically all of the drive code right here
            //See MotorController -> gamepadToDrive for an explanation on how it works
            motors.gamepadToDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if(gamepad1.left_trigger > gamepad1.right_trigger){
                motors.pulleyControl(0.6);
            }else if(gamepad1.right_trigger > gamepad1.left_trigger){
                motors.pulleyControl(-0.6);
            }else {
                motors.pulleyControl(0);
            }

            if(gamepad1.left_bumper){
                motors.openHands();
            }else if(gamepad1.right_bumper){
                motors.closeHands();
            }

            telemetry.addData("Left Servo Pos", motors.leftServoAngle());
            telemetry.addData("Left Servo Pos", motors.rightServoAngle());

            telemetry.update();

        }
    }
}
