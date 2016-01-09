package com.qualcomm.ftcrobotcontroller.opmodes;
 import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Miguel on 1/9/2016.
 */
public class RobotFunctions {
    static int  openTarget = 0;
    static int  closeTarget = 0;
    static int encToCentiK = 37;
    static float turnPower = .75f;
    static int degConstant = 8;

    public static void OpenClaw( Servo clawServo){
        clawServo.setPosition(openTarget);
    }
    public static void CloseClaw( Servo clawServo){
        clawServo.setPosition(closeTarget);
    }
    public static void Move(int forward, int targetCenti, DcMotor m1, DcMotor m2){
        int startEnc = m1.getCurrentPosition();
        int targetDiff = encToCentiK*targetCenti;
        while (Math.abs(m1.getCurrentPosition() - startEnc) < targetDiff ) {
            m1.setPower(forward);
            m2.setPower(forward);
        }
       stop(m1, m2);
    }
    public static void turn( DcMotor m1, DcMotor m2, int deg){
        int startEnc = m1.getCurrentPosition();
        int targetEnc = degConstant* + startEnc;
        while (m1.getCurrentPosition()  < targetEnc ) {
            m1.setPower(turnPower);
            m2.setPower(-turnPower);
        }
        stop(m1, m2);
    }
     public static void stop( DcMotor m1, DcMotor m2){
         m1.setPower(0);
         m2.setPower(0);
     }
}

