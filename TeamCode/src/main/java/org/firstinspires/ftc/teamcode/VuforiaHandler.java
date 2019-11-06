package org.firstinspires.ftc.teamcode;

import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraManager;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

public class VuforiaHandler {

    String VuforiaID = "AQgjYOj/////AAAAGR2Uh98iMU61klzG27h8xcUkLTLhkuNh48xUnAf1eql/MSIMUmTXn2gddWVicdTYpIqCEACP3U/o1oSwZNzV0q3JFaSKfqW7rW3vaASNDJNjvexQ/qVKeKnyEtzzU0oUWjX2dqKjjjAbpnIp/5bFCYlkf/3uQfl3K5Ota7a9CKGpjK6YZAm+yMFpnfiJBsLXfmin+RjCwrYUTIx5flHy2RRdy0dBXG9AyuzOnG9MmWLQ8DiWGvJtK/90pF0uOSgWZgZgZXzG8z81HGehMRCfgX6k1j/jrEwhLt3ZQf0LdJOeIvKWIBhWIySW7WyoIsi9/BXayBEgWq84Tpr7N9pcwJkhXJn0HOg3cfVxXW9m5S57";
    VuforiaLocalizer vuforia;
    VuforiaTrackables targetsSkyStone;

    Telemetry telemetry;

    public VuforiaHandler(Telemetry m_telemetry){
        telemetry = m_telemetry;
    }

    public void initialize(){
        addTelemetry("Initializing...");

        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        params.vuforiaLicenseKey = VuforiaID;
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        vuforia = ClassFactory.getInstance().createVuforia(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        targetsSkyStone = vuforia.loadTrackablesFromAsset("Skystone");

        //region //REGION: Makes vuforia image names (EXPAND TO SEE)

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");

        VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
        blueRearBridge.setName("Blue Rear Bridge");

        VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
        redRearBridge.setName("Red Rear Bridge");

        VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
        redFrontBridge.setName("Red Front Bridge");

        VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
        blueFrontBridge.setName("Blue Front Bridge");

        VuforiaTrackable red1 = targetsSkyStone.get(5);
        red1.setName("Red Perimeter 1");

        VuforiaTrackable red2 = targetsSkyStone.get(6);
        red2.setName("Red Perimeter 2");

        VuforiaTrackable front1 = targetsSkyStone.get(7);
        front1.setName("Front Perimeter 1");

        VuforiaTrackable front2 = targetsSkyStone.get(8);
        front2.setName("Front Perimeter 2");

        VuforiaTrackable blue1 = targetsSkyStone.get(9);
        blue1.setName("Blue Perimeter 1");

        VuforiaTrackable blue2 = targetsSkyStone.get(10);
        blue2.setName("Blue Perimeter 2");

        VuforiaTrackable rear1 = targetsSkyStone.get(11);
        rear1.setName("Rear Perimeter 1");

        VuforiaTrackable rear2 = targetsSkyStone.get(12);
        rear2.setName("Rear Perimeter 2");

        //endregion //REGION: Makes vuforia image names (EXPAND TO SEE)

        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);

        addTelemetry("Initialized");
    }

    public void enableTracking(){
        targetsSkyStone.activate();
        addTelemetry("Tracking enabled");
    }

    public void disableTracking(){
        targetsSkyStone.deactivate();
        addTelemetry("Tracking disabled");
    }

    void addTelemetry(String msg){
        telemetry.addData("Vuforia: ", msg);
        telemetry.update();
    }
}
