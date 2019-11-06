package org.firstinspires.ftc.teamcode;

import com.vuforia.CameraDevice;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraManager;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

public class VuforiaHandler {

    String VuforiaID = "AQgjYOj/////AAAAGR2Uh98iMU61klzG27h8xcUkLTLhkuNh48xUnAf1eql/MSIMUmTXn2gddWVicdTYpIqCEACP3U/o1oSwZNzV0q3JFaSKfqW7rW3vaASNDJNjvexQ/qVKeKnyEtzzU0oUWjX2dqKjjjAbpnIp/5bFCYlkf/3uQfl3K5Ota7a9CKGpjK6YZAm+yMFpnfiJBsLXfmin+RjCwrYUTIx5flHy2RRdy0dBXG9AyuzOnG9MmWLQ8DiWGvJtK/90pF0uOSgWZgZgZXzG8z81HGehMRCfgX6k1j/jrEwhLt3ZQf0LdJOeIvKWIBhWIySW7WyoIsi9/BXayBEgWq84Tpr7N9pcwJkhXJn0HOg3cfVxXW9m5S57";
    VuforiaLocalizer vuforia;
    VuforiaTrackables targetsSkyStone;
    List<VuforiaTrackable> allTrackables;
    OpenGLMatrix pose;

    Telemetry telemetry;

    Boolean initialized = false;
    int indexFound = -1;

    public enum TARGET {
        STONE, BLUE_REAR_BRIDGE, RED_REAR_BRIDGE, RED_FRONT_BRIDGE, BLUE_FRONT_BRIDGE, RED_1, RED_2, FRONT_1, FRONT_2,
        BLUE_1, BLUE_2, REAR_1, REAR_2, NONE
    }

    public enum AXIS {
        X, Y, Z, NONE
    }

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

        boolean focusModeSet = CameraDevice.getInstance().setFocusMode(CameraDevice.FOCUS_MODE.FOCUS_MODE_CONTINUOUSAUTO);

        if (!focusModeSet) {
            addTelemetry("Failed to set focus mode (unsupported mode)");
        }

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

        allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);

        addTelemetry("Initialized");
        initialized = true;
    }


    public Boolean track(TARGET input){
        if(!initialized){
            addTelemetry("Vuforia not yet initialized. Cannot track.");
            return false;
        }

        int index = 0;

        //Determines INDEX value based on ENUM input (EXPAND TO SEE)
        switch(input.toString()){
            case "STONE":
                index = 0;
                break;
            case "BLUE_REAR_BRIDGE":
                index = 1;
                break;
            case "RED_REAR_BRIDGE":
                index = 2;
                break;
            case "RED_FRONT_BRIDGE":
                index = 3;
                break;
            case "BLUE_FRONT_BRIDGE":
                index = 4;
                break;
            case "RED_1":
                index = 5;
                break;
            case "RED_2":
                index = 6;
                break;
            case "FRONT_1":
                index = 7;
                break;
            case "FRONT_2":
                index = 8;
                break;
            case "BLUE_1":
                index = 9;
                break;
            case "BLUE_2":
                index = 10;
                break;
            case "REAR_1":
                index = 11;
                break;
            case "REAR_2":
                index = 12;
                break;
            case "NONE":
                index = -1;
                break;
            default:
                index = -1;
                break;
        }

        if(index == -1){
            addTelemetry("Unknown image name or index used. Cannot track.");
            return false;
        }

        addTelemetry("Selected " + input.toString() + " with index " + index + " to track");

        VuforiaTrackable img = targetsSkyStone.get(index);

        pose = ((VuforiaTrackableDefaultListener) img.getListener()).getPose();
        if(pose != null){
            indexFound = index;
            addTelemetry("Found image " + input.toString());
            return true;
        }else {
            indexFound = -1;
            addTelemetry("Image " + input.toString() + " not found");
            return false;
        }
    }

    public VectorF getLocation(TARGET input){
        int index = -1;
        //Determines INDEX value based on ENUM input (EXPAND TO SEE)
        switch(input){
            case STONE:
                index = 0;
                break;
            case BLUE_REAR_BRIDGE:
                index = 1;
                break;
            case RED_REAR_BRIDGE:
                index = 2;
                break;
            case RED_FRONT_BRIDGE:
                index = 3;
                break;
            case BLUE_FRONT_BRIDGE:
                index = 4;
                break;
            case RED_1:
                index = 5;
                break;
            case RED_2:
                index = 6;
                break;
            case FRONT_1:
                index = 7;
                break;
            case FRONT_2:
                index = 8;
                break;
            case BLUE_1:
                index = 9;
                break;
            case BLUE_2:
                index = 10;
                break;
            case REAR_1:
                index = 11;
                break;
            case REAR_2:
                index = 12;
                break;
            case NONE:
                index = -1;
                break;
            default:
                index = -1;
                break;
        }

        if(index != indexFound){
            addTelemetry("Indices from " + input.toString() + " and indexFound do not match.");
            return new VectorF(-999, -999, -999);
        }

        //X is 0, Y is 1, Z is 2
        addTelemetry("Found target at (" + pose.getTranslation().get(0) + ", " + pose.getTranslation().get(1) + ", " + pose.getTranslation().get(2) + ")");
        return pose.getTranslation();
    }

    public double getDist(VectorF input, AXIS axis){
        double output = -999;

        switch(axis){
            case X:
                output = input.get(0);
                break;
            case Y:
                output = input.get(1);
                break;
            case Z:
                output = input.get(2);
                break;
            case NONE:
                output = -999;
                break;
            default:
                output = -999;
                break;
        }

        return output;
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
