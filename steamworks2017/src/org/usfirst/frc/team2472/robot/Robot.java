package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;
import com.kauailabs.nav6.frc.IMUAdvanced;

import Actions.GearPlace;
import Actions.goDriveStraightDistance;
import Actions.goTurnAngle;
import Actions.newTurn;
import Actions.turnWithEnc;
import Constants.Const;
import Objects.Action;
import Subsystem.Climber;
import Subsystem.Flywheel;
import Subsystem.Intake;
import Subsystem.ballCycler;
import Subsystem.drive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Steamworks2017

public class Robot extends IterativeRobot {
	public String tt[];//Pixy buffer string
	public static boolean noBoxes = true;//see if pixy can see
	BoxInfo BiL;//pixy camera box information
	BoxInfo BiR;
	BoxInfo ShootAttempt;
	int springPos;//pixy midpoint for boxes
	String serialFeed;//initial string for pixy info
	String Finals;//Final string for pixy info
	//SerialPort arduinoSerial = new SerialPort(9600, Port.kUSB);//Serial port for the arduino
	
	AnalogInput dist=new AnalogInput(0);//distance sensor
	
	UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture();//Runs a picture on usb camera

	//Creates Subsystems
	public static drive d;
	public static Intake i;
	public static Flywheel f;
	public static Climber climber;
	
	public static ballCycler cycler;
	
	//Creates Encoders for the drive wheel and shooter
	public Encoder motorEnc,motorEncREVERSE;
	public static Encoder shooterEnc;
	
	//Creates IMU
	//IMUAdvanced imu;
	
	//Auto ArrayList
	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> stepSecondary = new ArrayList<Action>();
	int currentAction = 0;
	
	//Joysticks
	Joystick gamepadController = new Joystick(Const.gpad);
	Joystick joyl = new Joystick(Const.joyl);
	Joystick joyr = new Joystick(Const.joyr);
	Joystick box = new Joystick(Const.box);
	
	//SerialPort IMUserial_port = new SerialPort(57600, Port.kUSB);//IMU serial port
	byte IMUupdate_rate_hz = 50;//IMU update rate
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//Camera set-up
		cam0.setResolution(160, 90);
		cam0.setFPS(30);
		
		//SmartDashboard Booleans
		SmartDashboard.putBoolean("IMU", true);
		SmartDashboard.putBoolean("Motor Encoder", true);
		SmartDashboard.putBoolean("Shooter Encoder", true);
		SmartDashboard.putBoolean("Climber", true);
		SmartDashboard.putBoolean("Cycler", true);
		SmartDashboard.putBoolean("Drive", true);
		SmartDashboard.putBoolean("Intake", true);
		SmartDashboard.putBoolean("Flywheel", true);

		//try catch field to set-up Subsystems, encoders, and IMU
		try {
		//	IMUserial_port = new SerialPort(57600,Port.kUSB);
			System.out.print("Up 1");
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("IMU", false);
		}
		try {
	//		imu = new IMUAdvanced(IMUserial_port, IMUupdate_rate_hz);
			System.out.print("Up 2");
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("IMU", false);
		}
		try {
			motorEnc = new Encoder(Const.motorEncZERO, Const.motorEncONE, false, Encoder.EncodingType.k4X);
			motorEnc.setReverseDirection(true);
			motorEnc.setDistancePerPulse(.05357);
		//	motorEncREVERSE = new Encoder(Const.motorEncTWO,Const.motorEncTHREE, false, Encoder.EncodingType.k4X);
		//	motorEncREVERSE.setReverseDirection(true);
		//	motorEncREVERSE.setDistancePerPulse(.05357);
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("Motor Encoder", false);
		}
		try {
			shooterEnc = new Encoder(Const.shooterEncChanA, Const.shooterEncChanB, false, Encoder.EncodingType.k4X);
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("Shooter Encoder", false);
		}
		try {
			climber = new Climber(Const.climber);
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("Climber", false);
		}
		try {
			cycler = new ballCycler(Const.Cycler);
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("Cycler", false);
		}
		try {
			d = new drive(Const.FL, Const.FR, Const.BL, Const.BR);
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("Drive", false);
		}
		try {
			i = new Intake(Const.Intake);
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("Intake", false);
		}
		try {
			f = new Flywheel(Const.FWheel);
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("Flywheel", false);
		}

	}

	@Override
	public void autonomousInit() {
		//Fills auto with step according to which switch is active
		if (box.getRawButton(Const.boxButton1)) {
			step.add(new goDriveStraightDistance(5.0,motorEnc,80,.2));
			stepSecondary.add(new Action());
			step.add(new turnWithEnc(2.25,.35,60,motorEnc));
			stepSecondary.add(new Action());
			step.add(new goDriveStraightDistance(5.0,motorEnc,47,.15));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}else if (box.getRawButton(Const.boxButton2)) {
			//center gear
			System.out.println("2nd auto");
			step.add(new goDriveStraightDistance(5.0,motorEnc,80,.2));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}else if(box.getRawButton(Const.boxButton3)){
			//right gear
			step.add(new goDriveStraightDistance(5.0,motorEnc,85,.2));
			stepSecondary.add(new Action());
			step.add(new turnWithEnc(2.25,.35,-80,motorEnc));
			stepSecondary.add(new Action());
			step.add(new goDriveStraightDistance(5.0,motorEnc,50,.15));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}
		else{
			step.add(new goDriveStraightDistance(5.0,motorEnc,80,.2));
			stepSecondary.add(new Action());
			step.add(new goTurnAngle(5.0,-60.0,motorEnc,0.5));
			stepSecondary.add(new Action());
			step.add(new goDriveStraightDistance(5.0,motorEnc,47,.2));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}

		//Starts the first steps if there is one
		if (step.size() > 0) {

			currentAction = 0;

			step.get(currentAction).startAction();

			stepSecondary.get(currentAction).startAction();

		}

	}

	

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {
		System.out.println(motorEnc.getDistance());
		//Plls one char out of pixy
	
		
		//SmartDashboard Display Values
		//SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		//SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
		SmartDashboard.putNumber("Motor Speed", motorEnc.getDistance());
	///	SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());

		//Cycles through the steps and stops when it finds a null
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
	public void teleopInit() {
		//sets IMU Yaw to 0
		// imu.zeroYaw();
		
		//adds camera to SmartDasboard
	//	CameraServer.getInstance().addCamera(cam0);
	}

	public void teleopPeriodic() {
		//SmartDashboard and Console Values 
	
		SmartDashboard.putNumber("Motor Speed", motorEnc.getRate());
		//SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());
		System.out.println(motorEnc.getDistance());
		//System.out.println(motorEncREVERSE.getDistance());
		
		//Controls Drive with Joysticks
		// d.tankDrive(joyl , joyr);
		
		//New drive.
		d.XBoxDrive(gamepadController);
		
	/*	
		if (gamepadController.getRawButton(Const.buttonL)) {
			f.flywhlGo(Const.shooterSpeed);
			
			System.out.println("running");
		}
		else {
			f.flywhlStop();
		}
		if (gamepadController.getRawButton(Const.buttonA)) {
			i.intakeGo(Const.fullSpeedForward);
		}
		if (gamepadController.getRawButton(Const.buttonB)) {
			i.intakeStop();
		}

		if (gamepadController.getRawButton(Const.buttonX)) {
			cycler.cycleSpeed(Const.fullSpeedForward);
		}
		else {
			cycler.stop();
		}
		if (gamepadController.getAxis(AxisType.kTwist)>=.1){
			climber.setSpeed(gamepadController.getAxis(AxisType.kTwist));
		}
		else if(gamepadController.getRawAxis(3)>=.1){
			climber.setSpeed(-gamepadController.getRawAxis(3));
		}
		else if(gamepadController.getRawButton(Const.buttonY)){
			climber.setSpeed(.3);
		}
		else {
			climber.stop();
		}
		*/
	
	}
	@Override
	public void testPeriodic() {
		//System.out.println(imu.getYaw());
		//SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		//SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
		SmartDashboard.putNumber("Motor Speed", motorEnc.getRate());
		SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());
		//System.out.println(motorEnc.getDistance());
		System.out.println(motorEncREVERSE.getDistance());
		if (gamepadController.getAxis(AxisType.kTwist) >= .1) {
			climber.setSpeed(gamepadController.getAxis(AxisType.kTwist));
		}
		else if (gamepadController.getRawButton(Const.buttonA)) {
			d.runBL();
		} else if (gamepadController.getRawButton(Const.buttonB)) {
			d.runFL();
		} else if (gamepadController.getRawButton(Const.buttonX)) {
			d.runBR();
		} else if (gamepadController.getRawButton(Const.buttonY)) {
			d.runFR();
		} else if (gamepadController.getRawButton(Const.buttonL)) {
			f.flywhlGo(Const.shooterSpeed);
		} else if (gamepadController.getRawButton(Const.buttonR)) {
			i.intakeGo(Const.fullSpeedForward);
		} else if (gamepadController.getRawButton(Const.buttonBack)) {
			climber.setSpeed(Const.fullSpeedForward);
		} else if (gamepadController.getRawButton(Const.buttonStart)) {
			cycler.cycleSpeed(.001);
		} else {
			d.stopMotors();
			f.flywhlStop();
			i.intakeStop();
			climber.stop();
			cycler.stop();
		}

		// WHOA TECHNOLOGY
	}

	//Collects Info From pixy Cam
	public void getIt() {

	//serialFeed = arduinoSerial.readString(1);
		
		

		System.out.println("Trying to Read");
		if (serialFeed != null && !serialFeed.equals("^")) {
			Finals += serialFeed;
			System.out.println("Collecting");
		} else if (Finals.split(":").length == 9) {
			// System.out.println(finalS);
			tt = Finals.split(":");
			try {

				tt[0] = tt[0].substring(2);

				if (Integer.parseInt(tt[0]) > Integer.parseInt(tt[4])) {
					BiL = new BoxInfo(tt[4], tt[5], tt[6], tt[7]);
					BiR = new BoxInfo(tt[0], tt[1], tt[2], tt[3]);
				} else if (Integer.parseInt(tt[0]) < Integer.parseInt(tt[4])) {
					BiR = new BoxInfo(tt[4], tt[5], tt[6], tt[7]);
					BiL = new BoxInfo(tt[0], tt[1], tt[2], tt[3]);
				}

				System.out.println("Left Box X:" + BiL.getX());
				System.out.println("Right Box X:" + BiR.getX());
				springPos = (BiL.getX() + BiR.getX()) / 2;
				System.out.println("Width:" + tt[2]);
				Finals = "";
				noBoxes = false;
			} catch (Exception e) {
				System.out.println(tt[0]);
				System.out.println(tt[4]);
				System.out.println(e);
			}
		} else if (Finals.split(":").length != 9&&Finals.split(":").length!=3||BiL==null||BiR==null) {
			noBoxes = true;

		}else if(Finals.split(":").length == 2){
			ShootAttempt=new BoxInfo(tt[0],tt[1]);
			
		}
		
		else {
			Finals = "";
		}

	}
}
