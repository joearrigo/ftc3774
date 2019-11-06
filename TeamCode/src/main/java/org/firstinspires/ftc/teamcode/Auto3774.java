package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto3774")
public class Auto3774 extends LinearOpMode {

    MotorController motors;
    VuforiaHandler vuforia;

    ElapsedTime et;

    @Override
    public void runOpMode() throws InterruptedException {

        motors = new MotorController(hardwareMap);
        vuforia = new VuforiaHandler(telemetry);
        et = new ElapsedTime();

        waitForStart();

        et.reset();

        vuforia.initialize();
        vuforia.enableTracking();

        //This empty loop body forces the program to wait for the second play push to begin
        while(!opModeIsActive());

        //The autonomous program is only allowed to run for 26 seconds AT MAX.
        //This helps us end it in the last 4 seconds of autonomous.
        while(et.seconds() < 26){

        }

        vuforia.disableTracking();


    }
}
