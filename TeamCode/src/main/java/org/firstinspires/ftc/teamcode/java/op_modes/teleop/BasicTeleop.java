package org.firstinspires.ftc.teamcode.java.op_modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.java.util.RobotHardware;



@TeleOp(name = "BasicTeleop")


public class BasicTeleop extends LinearOpMode {

    RobotHardware robot = new RobotHardware();

    private final double MAX_SPEED = 1;


    @Override
    public void runOpMode() throws InterruptedException
    {


        robot.init(hardwareMap);
        
         // defining motors
        DcMotor leftMotor = robot.leftMotor;
        DcMotor rightMotor = robot.rightMotor;

         // creating an array for the motor speeds
        double[] motorSpeeds = new double[2];

        waitForStart();

        while (opModeIsActive())
        {

             // correlating gamepad sticks to driving states
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;


             // creating array for motor speeds that takes a value from the different driving states
            motorSpeeds[0] = drive + turn;
            motorSpeeds[1] = drive - turn;


             // defining max speed for motors
            double max = Math.max(Math.abs(motorSpeeds[0]),Math.abs(motorSpeeds[1]));



             // creating a stable range for the motor max speed
            if (max > 1)
            {
                for(int i = 0 ; i < 2; i++)
                {
                    motorSpeeds[i] = motorSpeeds[i] / max;
                }
            }


             // setting power to the motors based the values of the speeds from the
             // gamepad and max speed
            leftMotor.setPower(motorSpeeds[0] * MAX_SPEED);
            rightMotor.setPower(motorSpeeds[1] * MAX_SPEED);
        }
    }
}
