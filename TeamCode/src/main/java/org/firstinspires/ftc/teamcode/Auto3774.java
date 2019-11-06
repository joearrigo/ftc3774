package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto3774")
public class Auto3774 extends LinearOpMode {

    MotorController motors;

    @Override
    public void runOpMode() throws InterruptedException {

        motors = new MotorController(hardwareMap);

        VuforiaHandler vuforia = new VuforiaHandler(telemetry);

        waitForStart();

        vuforia.initialize();
        vuforia.enableTracking();

        //This empty loop body forces the program to wait for the second play push to begin
        while(!opModeIsActive());



    }
}
