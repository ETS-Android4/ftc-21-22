package org.firstinspires.ftc.teamcode.java.op_modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.java.subsystems.Capping;
import org.firstinspires.ftc.teamcode.java.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.java.subsystems.Intake;
import org.firstinspires.ftc.teamcode.java.subsystems.Lift;
import org.firstinspires.ftc.teamcode.java.util.AutoDrive;
import org.firstinspires.ftc.teamcode.java.util.RobotHardware;

@Autonomous(name = "redcubeducks", group = "auto")
public class redcubeducks extends LinearOpMode {
	/* Declare OpMode members. */
	RobotHardware robot   = new RobotHardware();   // Use a Pushbot's hardware
	private ElapsedTime runtime = new ElapsedTime();
	/**
	 * Override this method and place your code here.
	 * <p>
	 * Please do not swallow the InterruptedException, as it is used in cases
	 * where the op mode needs to be terminated early.
	 *
	 *
	 */
	@Override
	public void runOpMode() {
		robot.init(hardwareMap);

		// Send telemetry message to signify robot waiting;
		telemetry.addData("Status", "Resetting Encoders");    //
		telemetry.update();

		robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		DcMotor carouselMotor = robot.carousel;
		robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		AutoDrive ad = new AutoDrive(robot.leftMotor, robot.rightMotor, robot.imu, telemetry);
		Lift lift = new Lift(robot.elevator);
		Intake intake = new Intake(robot.intake);
		Carousel carousel = new Carousel(carouselMotor);
		Capping capping=new Capping(robot.armServo, robot.cappingServo);
		capping.lift();
		waitForStart();

		ad.gyroDrive(AutoDrive.DRIVE_SPEED, 450, 0);
		ad.gyroTurn(AutoDrive.TURN_SPEED, -40);
		ad.gyroDrive(AutoDrive.DRIVE_SPEED, -480, 0);


		carousel.spin(2);

		carousel.stop();
		ad.gyroTurn(AutoDrive.TURN_SPEED, -0.1);
		ad.gyroDrive(AutoDrive.DRIVE_SPEED, 930, 0);
		ad.gyroTurn(AutoDrive.TURN_SPEED, -84);
		ad.gyroDrive(AutoDrive.DRIVE_SPEED, 450, -90);
	    lift.lift(2);
		ad.gyroDrive(AutoDrive.DRIVE_SPEED, 450, -90);

		intake.outtake(5);
	    intake.stop();

	  ad.gyroDrive(AutoDrive.DRIVE_SPEED, -850, -90);

		lift.lower(3);
    	ad.gyroTurn(AutoDrive.TURN_SPEED, -1);
		ad.gyroDrive(AutoDrive.DRIVE_SPEED, -430, 0);


















		//while (true) {
		//telemetry.addData("left", robot.leftMotor.getCurrentPosition());
			//telemetry.addData("right", robot.rightMotor.getCurrentPosition());
			//telemetry.update();
		}




		}


