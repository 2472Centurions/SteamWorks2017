package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;
import com.ctre.CANTalon;
import com.kauailabs.nav6.frc.IMUAdvanced;
import Actions.goBackward;
import Actions.goDriveStraight;
import Actions.goOrientThySelf;
import Actions.goSpin;
import Actions.goShoot;
import Constants.Const;
import Objects.Action;
import Subsystem.Flywheel;
import Subsystem.Intake;
import Subsystem.drive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Steamworks2017

public class Robot extends IterativeRobot {
	public static drive d = new drive(Const.FL, Const.FR, Const.BL, Const.BR);
	public static Intake i = new Intake(Const.Intake);
	public static Flywheel f = new Flywheel(Const.FWheel);
	public static Encoder motorEnc;
	public static Encoder shooterEnc;

	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> stepSecondary = new ArrayList<Action>();
	int currentAction = 0;

	Joystick gamepadController = new Joystick(Const.gpad);
	Joystick joyl = new Joystick(Const.joyl);
	Joystick joyr = new Joystick(Const.joyr);
	Joystick box = new Joystick(Const.box);
	SerialPort serial_port = new SerialPort(57600, SerialPort.Port.kUSB);
	byte update_rate_hz = 50;
	public static IMUAdvanced imu;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		try {
			imu = new IMUAdvanced(serial_port, update_rate_hz);
		} catch (Exception e) {

			System.out.println("IMU is broken.");

		}
		try {
			motorEnc = new Encoder(Const.motorEncChanA, Const.motorEncChanB, false, Encoder.EncodingType.k4X);
		} catch (Exception e) {

			System.out.println("Motor Encoder is broken.");

		}
		try {
			shooterEnc = new Encoder(Const.shooterEncChanA, Const.shooterEncChanB, false, Encoder.EncodingType.k4X);
		} catch (Exception e) {

			System.out.println("Shooter Encoder is broken");

		}
	}

	@Override
	public void autonomousInit() {
		if (box.getRawButton(Const.boxButton1)) {

		} else if (box.getRawButton(Const.boxButton2)) {

		}

		else {
			step.add(new goDriveStraight(5.0));
			stepSecondary.add(new Action());
			step.add(new goOrientThySelf(5.0));
			stepSecondary.add(new Action());
			step.add(new goDriveStraight(5.0));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);

		}

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
		if (gamepadController.getRawButton(1)) {

			f.flywhlGo(1.0);

		}

		else {

			f.flywhlStop();
		}
		if (gamepadController.getRawButton(2)) {

			i.intakeGo(1.0);

		}

		else {

			i.intakeStop();
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
