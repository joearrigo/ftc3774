package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MotorController {

    //Assumes a 4 motor drive
    public DcMotor frontLeft, frontRight, backLeft, backRight;
    public DcMotor pulley;
    public Servo handLeft, handRight;

    public MotorController(HardwareMap hwMap){
        frontLeft = hwMap.dcMotor.get("frontLeft");
        frontRight = hwMap.dcMotor.get("frontRight");
        backLeft = hwMap.dcMotor.get("backLeft");
        backRight = hwMap.dcMotor.get("backRight");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        pulley = hwMap.dcMotor.get("pulley");

        handLeft = hwMap.servo.get("handLeft");
        handRight = hwMap.servo.get("handRight");
    }

    public void forward(float speed){
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
    }

    public void backward(float speed){
        forward(-speed);
    }

    public void left(float speed){
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
    }

    public void right(float speed){
        left(-speed);
    }

    public void counterClockwise(float speed){
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
    }

    public void clockwise(float speed){
        counterClockwise(-speed);
    }

    public void gamepadToDrive(float lx, float ly, float rx){
        //This code takes the left stick values and the right stick x value
        //and transforms it into motor instructions using Trigonometry.

        //FL === sin(x-pi/4)
        //FR === cos(x-pi/4)
        //BL === cos(x-pi/4)
        //BR === sin(x-pi/4)

        //Converts ly to a positive value because gamepad forces up to be negative for some reason
        ly *= -1;

        //Flips lx AND ly because the robot drives in reverse for some reason
        //lx has to be flipped because if only ly is flipped, lx will be reversed negatively

        lx *= -1;
        ly *= -1;

        //Gets the angle that the left stick is positioned at
        double r = Math.hypot(lx, ly);
        double theta = Math.atan2(ly,lx);
        double thetaConverted = theta - Math.PI / 4;

        float FL = (float) (r*Math.sin(thetaConverted));
        float FR = (float) (r*Math.cos(thetaConverted));
        float BL = (float) (r*Math.cos(thetaConverted));
        float BR = (float) (r*Math.sin(thetaConverted));

        //Factors in the value given by the right stick x for turning
        //One side will always be added, and one will always be subtracted
        FL -= rx;
        BL -= rx;
        FR += rx;
        BR += rx;

        /*
        //Finally, constricts all values to the range of -1 to 1
        clamp(FL, -1, 1);
        clamp(FR, -1, 1);
        clamp(BL, -1, 1);
        clamp(BR, -1, 1);
        */

        //Sets motor powers
        frontLeft.setPower(FL);
        frontRight.setPower(FR);
        backLeft.setPower(BL);
        backRight.setPower(BR);

    }

    double clamp(double input, double min, double max){
        if(input > max){
            input = max;
        }else if(input < min){
            input = min;
        }

        return input;
    }

    public void pulleyControl(double speed){
        pulley.setPower(speed);
    }

    float openPosL = .2f;
    float openPosR = .65f;

    public void openHands(){
        handLeft.setPosition(openPosL);
        handRight.setPosition(openPosR);
    }

    float closedPosL = 0.5f;
    float closedPosR = 0.5f;

    public void closeHands(){
        handLeft.setPosition(closedPosL);
        handRight.setPosition(closedPosR);
    }

    public float leftServoAngle(){
        return (float) handLeft.getPosition();
    }

    public float rightServoAngle(){
        return (float) handRight.getPosition();
    }
}


