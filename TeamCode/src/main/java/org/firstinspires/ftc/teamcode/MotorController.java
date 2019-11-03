package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorController {

    //Assumes a 4 motor drive
    public DcMotor frontLeft, frontRight, backLeft, backRight;

    public MotorController(HardwareMap hwMap){
        frontLeft = hwMap.dcMotor.get("frontLeft");
        frontRight = hwMap.dcMotor.get("frontRight");
        backLeft = hwMap.dcMotor.get("backLeft");
        backRight = hwMap.dcMotor.get("backRight");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
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

        //Gets the angle that the left stick is positioned at
        double theta = Math.atan(ly/lx);
        double thetaConverted = theta - Math.PI / 4;

        float FL = (float) Math.sin(thetaConverted);
        float FR = (float) Math.cos(thetaConverted);
        float BL = (float) Math.cos(thetaConverted);
        float BR = (float) Math.sin(thetaConverted);

        //Factors in the value given by the right stick x for turning
        //One side will always be added, and one will always be subtracted
        FL -= rx;
        BL -= rx;
        FR += rx;
        BR += rx;

        //Finally, constricts all values to the range of -1 to 1
        clamp(FL, -1, 1);
        clamp(FR, -1, 1);
        clamp(BL, -1, 1);
        clamp(BR, -1, 1);

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
}


