package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;
import com.kauailabs.nav6.frc.IMUAdvanced;
import Actions.goDriveStraightDistance;
import Constants.Const;
import Objects.Action;
import Subsystem.Climber;
import Subsystem.Flywheel;
import Subsystem.Intake;
import Subsystem.ballCycler;
import Subsystem.drive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Steamworks2017

public class Robot extends IterativeRobot {
	public static drive d = new drive(Const.FL, Const.FR, Const.BL, Const.BR);
	public static Intake i = new Intake(Const.Intake);
	public static Flywheel f = new Flywheel(Const.FWheel);
	public static Encoder motorEnc;
	public static Encoder shooterEnc;
	public static Climber climber;
	public static ballCycler cycler;
	public static IMUAdvanced imu;
	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> stepSecondary = new ArrayList<Action>();
	int currentAction = 0;

	Joystick gamepadController = new Joystick(Const.gpad);
	Joystick joyl = new Joystick(Const.joyl);
	Joystick joyr = new Joystick(Const.joyr);
	Joystick box = new Joystick(Const.box);
	SerialPort serial_port;
	byte update_rate_hz = 50;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		SmartDashboard.putNumber("IMU Yaw", 360);
		SmartDashboard.putNumber("IMU Pitch", 180);
		SmartDashboard.putNumber("Motor Speed", 80000000);
		SmartDashboard.putNumber("Shooter Speed", 70000000);
		SmartDashboard.putBoolean("IMU Connected", true);

		try {
			serial_port = new SerialPort(57600, SerialPort.Port.kUSB);
		} catch (Exception e) {

			SmartDashboard.putBoolean("IMU Connected", false);

		}
		try {
			imu = new IMUAdvanced(serial_port, update_rate_hz);
		} catch (Exception e) {

			SmartDashboard.putBoolean("IMU Connected", false);

		}
		try {
			motorEnc = new Encoder(Const.motorEncChanA, Const.motorEncChanB, false, Encoder.EncodingType.k4X);
			motorEnc.setReverseDirection(true);
			motorEnc.setDistancePerPulse(.08);
		} catch (Exception e) {

			System.out.println("Motor Encoder is broken/Not connected.");

		}
		try {
			shooterEnc = new Encoder(Const.shooterEncChanA, Const.shooterEncChanB, false, Encoder.EncodingType.k4X);
		} catch (Exception e) {

			System.out.println("Shooter Encoder is broken/Not connected.");

		}
		try {
			climber = new Climber(Const.climber);
		} catch (Exception e) {

			System.out.println("Climber is broken/Not connected");

		}
		try {

		} catch (Exception e) {

		}

	}

	@Override
	public void autonomousInit() {
		// if (box.getRawButton(Const.boxButton1)) {

		// } else if (box.getRawButton(Const.boxButton2)) {

		// }

		// else {
		step.add(new goDriveStraightDistance(5.0));
		stepSecondary.add(new Action());
		step.add(null);
		stepSecondary.add(null);
		

		// }

		if (step.size() > 0) {

			currentAction = 0;

			step.get(currentAction).startAction();

			stepSecondary.get(currentAction).startAction();

		}
		// currentAction = 0;
		// step.get(currentAction).startAction();

	}

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {

		if (step.size() > 0 && step.get(currentAction) != null) {

			if (!step.get(currentAction).isFinished()) {
				step.get(currentAction).periodic();
			}

			if (!stepSecondary.get(currentAction).isFinished()) {
				stepSecondary.get(currentAction).periodic();
			}

			if (step.get(currentAction).isFinished() && stepSecondary.get(currentAction).isFinished()) {

				currentAction++;

				if (step.get(currentAction) != null) {

					step.get(currentAction).startAction();
					stepSecondary.get(currentAction).startAction();

				}

			}

		}
	}

	@Override
	public void teleopPeriodic() {
		d.tankDrive(joyl, joyr);
		if (gamepadController.getTrigger()) {

			f.flywhlGo(1.0);

		}

		else {

			f.flywhlStop();
		}
		if (gamepadController.getRawButton(1)) {

			i.intakeGo(1.0);

		}
		if (gamepadController.getRawButton(2)) {

			i.intakeStop();

		}
		if (gamepadController.getRawButton(3)) {

			climber.extend();

		} else if (gamepadController.getRawButton(4)) {

			climber.retract();

		} else {

			climber.stop();

		}
		if (gamepadController.getRawButton(6)) {

			cycler.cycleIt();

		} else {

			cycler.stop();

		}
	}

	@Override
	public void testPeriodic() {

		if (gamepadController.getRawButton(1)) {

			d.runBL();

		} else if (gamepadController.getRawButton(2)) {

			d.runFL();

		} else if (gamepadController.getRawButton(3)) {

			d.runBR();

		} else if (gamepadController.getRawButton(4)) {

			d.runFR();

		} else if (gamepadController.getRawButton(5)) {

			f.flywhlGo(1.0);

		} else if (gamepadController.getRawButton(6)) {

			i.intakeGo(1.0);

		} else {

			d.stopMotors();
			f.flywhlStop();
			i.intakeStop();
		}

		// WHOA TECHNOLOGY
	}
}
