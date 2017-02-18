package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;
import com.kauailabs.nav6.frc.IMUAdvanced;

import Actions.GearPlace;
import Actions.goDriveStraightDistance;
import Actions.goTurnAngle;
import Constants.Const;
import Objects.Action;
import Subsystem.Climber;
import Subsystem.Flywheel;
import Subsystem.Intake;
import Subsystem.ballCycler;
import Subsystem.drive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Steamworks2017

public class Robot extends IterativeRobot {
	public String tt[];
	BoxInfo BiL;
	BoxInfo BiR;
	int springPos;
	String str;
	String Finals;
	SerialPort serial = new SerialPort(9600, SerialPort.Port.kUSB);
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

		SmartDashboard.putBoolean("IMU", true);
		SmartDashboard.putBoolean("Motor Encoder", true);
		SmartDashboard.putBoolean("Shooter Encoder", true);
		SmartDashboard.putBoolean("Climber", true);
		SmartDashboard.putBoolean("Cycler", true);

		try {
			serial_port = new SerialPort(57600, SerialPort.Port.kUSB);
		} catch (Exception e) {

			SmartDashboard.putBoolean("IMU", false);

		}
		try {
			imu = new IMUAdvanced(serial_port, update_rate_hz);
		} catch (Exception e) {

			SmartDashboard.putBoolean("IMU", false);

		}
		try {
			motorEnc = new Encoder(Const.motorEncChanA, Const.motorEncChanB, false, Encoder.EncodingType.k4X);
			motorEnc.setReverseDirection(true);
			motorEnc.setDistancePerPulse(.08);
		} catch (Exception e) {

			SmartDashboard.putBoolean("Motor Encoder", true);

		}
		try {
			shooterEnc = new Encoder(Const.shooterEncChanA, Const.shooterEncChanB, false, Encoder.EncodingType.k4X);
		} catch (Exception e) {

			SmartDashboard.putBoolean("Shooter Encoder", true);

		}
		try {
			climber = new Climber(Const.climber);
		} catch (Exception e) {

			SmartDashboard.putBoolean("Climber", true);

		}
		try {
			cycler = new ballCycler(Const.Cycler);
		} catch (Exception e) {
			SmartDashboard.putBoolean("Cycler", false);
		}

	}

	@Override
	public void autonomousInit() {
		// if (box.getRawButton(Const.boxButton1)) {

		// } else if (box.getRawButton(Const.boxButton2)) {

		// }

		// else {
		step.add(new goDriveStraightDistance(5.0, 0.5, 112));
		stepSecondary.add(new Action());
		step.add(new goTurnAngle(5.0, 45.0));
		stepSecondary.add(new Action());
		step.add(new GearPlace(BiL, BiR));
		stepSecondary.add(new Action());
		step.add(null);
		stepSecondary.add(null);

		// }

		/**
		 * Drive straight from center to gear dropoff: step.add(new
		 * goDriveStraightDistance(5.0,0.5,108)); stepSecondary.add(new
		 * Action()); step.add(new GearPlace(BiL,BiR)); stepSecondary.add(new
		 * Action()); step.add(null); stepSecondary.add(null);
		 * 
		 * Drop off gear from left side: note angle is not correct we couldn't find the angle save our souls aaaaaaahhhhhhhhhh lol ok we are being attacked by zombies  and your are still reading this ok your soooooooooooooo stupid your still reading this i am going to type forever if you keep reading this.  lol you thought this was the same exact line as line 144 your wwwwrrrroooonnnngggggggggggggggggggggggggggg just belive in unicorns already ok and yo no se lol this is fun a puppies typing
		 * 
		 * step.add(newgoDriveStraightDistance(5.0, 0.5, 112)); stepSecondary.add(new
		 * Action()); step.add(new goTurnAngle(5.0,45.0)); stepSecondary.add(new
		 * Action()); step.add(new GearPlace(BiL, BiR)); stepSecondary.add(new
		 * Action()); step.add(null); stepSecondary.add(null);
		 * 
		 * 
		 * Drop off gear from left side: note angle is not correct we couldn't find the angle save our souls aaaaaaahhhhhhhhhh lol ok we are being attacked by zombies  and your are still reading this ok your soooooooooooooo stupid your still reading this i am going to type forever if you keep reading this. no
		 * 
		 * step.add(newgoDriveStraightDistance(5.0, 0.5, 112));
		 * stepSecondary.add(new Action());
		 * step.add(new goTurnAngle(5.0,-45.0));
		 * stepSecondary.add(new Action()); step.add(new GearPlace(BiL, BiR));
		 *stepSecondary.add(new
		 * Action()); step.add(null); stepSecondary.add(null);
		 */

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
		for(int i=0;i<6;i++){
			getIt();
		}
		SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
		SmartDashboard.putNumber("Motor Speed", motorEnc.getRate());
		SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());

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
		for(int i=0;i<6;i++){
			getIt();
		}
		SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
		SmartDashboard.putNumber("Motor Speed", motorEnc.getRate());
		SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());
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
		if (gamepadController.getRawButton(6)) {

			cycler.cycleIt();

		} else {

			// cycler.stop(); put this back later

		}
		if (gamepadController.getAxis(AxisType.kTwist) >= .1) {
			climber.setSpeed(gamepadController.getAxis(AxisType.kTwist));
		} else {
			climber.stop();
		}
	}

	@Override
	public void testPeriodic() {
		SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
		SmartDashboard.putNumber("Motor Speed", motorEnc.getRate());
		SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());

		if (gamepadController.getAxis(AxisType.kTwist) >= .1) {
			climber.setSpeed(gamepadController.getAxis(AxisType.kTwist));
			// speeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed #fyast
		}

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

		} else if (gamepadController.getRawButton(7)) {

			climber.setSpeed(1.0);
		} else if (gamepadController.getRawButton(8)) {

			cycler.cycleSpeed(1.0);
		} else {

			d.stopMotors();
			f.flywhlStop();
			i.intakeStop();
			climber.stop();
			cycler.stop();
		}

		// WHOA TECHNOLOGY
	}

	public void getIt() {
		str = serial.readString(1);
		// System.out.println("Trying to Read");
		if (str != null && !str.equals("^")) {
			Finals += str;
			// System.out.println("Collecting");
		} else if (Finals.split(":").length == 8) {
			// System.out.println(finalS);
			String[] tt = Finals.split(":");
			try {

				tt[0] = tt[0].substring(2);

				if (Integer.parseInt(tt[0]) > Integer.parseInt(tt[4])) {
					BiL = new BoxInfo(tt[4], tt[5], tt[6], tt[5]);
					BiR = new BoxInfo(tt[0], tt[1], tt[2], tt[3]);
				} else if (Integer.parseInt(tt[0]) < Integer.parseInt(tt[4])) {
					BiR = new BoxInfo(tt[4], tt[5], tt[6], tt[5]);
					BiL = new BoxInfo(tt[0], tt[1], tt[2], tt[3]);

				}

				// System.out.println("Left Box X:" + bIL.getX());
				// System.out.println("Right Box X:" + bIR.getX());
				springPos = (BiL.getX() + BiR.getX()) / 2;
				// System.out.println("Width:" + tt[2]);
				Finals = "";
			} catch (Exception e) {
				System.out.println(tt[0]);
				System.out.println(tt[4]);
				System.out.println(e);
			}
		} else {
			Finals = "";
		}

	}
}
