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

        while(opModeIsActive()){
            //This is basically all of the drive code right here
            //See MotorController -> gamepadToDrive for an explanation on how it works
            motors.gamepadToDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
    }
}
