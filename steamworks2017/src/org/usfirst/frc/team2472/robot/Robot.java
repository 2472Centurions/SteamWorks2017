package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;
import com.kauailabs.nav6.frc.IMUAdvanced;

import Actions.GearPlace;
import Actions.goDriveStraightDistance;
import Actions.goOrientThySelf;
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
	public String tt[];
	public static boolean pixyBlind=true;
	BoxInfo BiL;
	BoxInfo BiR;
	AnalogInput distance;
	int springPos;
	String str;
	UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture();
	String Finals;
<<<<<<< HEAD

	SerialPort serial=new SerialPort(9600, SerialPort.Port.kUSB);

=======
	SerialPort serial=new SerialPort(9600, Port.kUSB1);
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
	public static drive d = new drive(Const.FL, Const.FR, Const.BL, Const.BR);
	public static Intake i = new Intake(Const.Intake);
	public static Flywheel f = new Flywheel(Const.FWheel);
	public Encoder motorEnc;
	public static Encoder shooterEnc;
	public static Climber climber;
	public static ballCycler cycler;
	//public IMUAdvanced imu;
	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> stepSecondary = new ArrayList<Action>();
	int currentAction = 0;
	public boolean OBJECT=true;
	Joystick gamepadController = new Joystick(Const.gpad);
	Joystick joyl = new Joystick(Const.joyl);
	Joystick joyr = new Joystick(Const.joyr);
	Joystick box = new Joystick(Const.box);
	SerialPort serial_port;
	byte update_rate_hz = 50;
<<<<<<< HEAD
	CamToSdash camera;
=======
	
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		//cam0=CameraServer.getInstance().startAutomaticCapture();
		//cam0.setResolution(1600, 900);
		//cam0.setFPS(30);
		SmartDashboard.putBoolean("IMU", true);
		SmartDashboard.putBoolean("Motor Encoder", true);
		SmartDashboard.putBoolean("Shooter Encoder", true);
		SmartDashboard.putBoolean("Climber", true);
		SmartDashboard.putBoolean("Cycler", true);

		try {
			//serial_port = new SerialPort(57600, Port.kUSB);
			System.out.print("Up 1");
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("IMU", false);

		}
		try {
			//imu = new IMUAdvanced(serial_port, update_rate_hz);
			System.out.print("Up 2");
		} catch (Exception e) {
			System.out.print(e);
			SmartDashboard.putBoolean("IMU", false);

		}

		try {
			motorEnc = new Encoder(Const.motorEncChanA, Const.motorEncChanB, false, Encoder.EncodingType.k4X);
			motorEnc.setReverseDirection(true);
			motorEnc.setDistancePerPulse(.053);
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
			cycler = new ballCycler(Const.CyclerA, Const.CyclerB);
		} catch (Exception e) {
			SmartDashboard.putBoolean("Cycler", false);
		}

	}

	@Override
	public void autonomousInit() {
		step.add(new GearPlace(BiL, BiR,.5,distance));
		stepSecondary.add(new Action());
		// if (box.getRawButton(Const.boxButton1)) {

		// } else if (box.getRawButton(Const.boxButton2)) {

		// }

		// else {
<<<<<<< HEAD
	//	step.add(new goDriveStraightDistance(5.0));
		//stepSecondary.add(new Action());
		//step.add(null);
	//	stepSecondary.add(null);

=======
	/*	if(box.getRawButton(1)){
			step.add(new goDriveStraightDistance(5.0,imu,motorEnc,79,.75));
			stepSecondary.add(new Action());
			step.add(new goOrientThySelf(5.0,imu,60.0));
			stepSecondary.add(new Action());
			step.add(new goDriveStraightDistance(5.0,imu,motorEnc,24,.75));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}
		else if(box.getRawButton(2)){
			step.add(new goOrientThySelf(5.0,imu,60.0));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}
		else if(box.getRawButton(3)){
			step.add(new goDriveStraightDistance(5.0,imu,motorEnc,79,.75));
			stepSecondary.add(new Action());
			step.add(new goDriveStraightDistance(5.0,imu,motorEnc,24,.75));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}
		else if(box.getRawButton(4)){
			step.add(new goDriveStraightDistance(10.0,imu,motorEnc,79,.5));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}
		else{
			step.add(new goDriveStraightDistance(10.0,imu,motorEnc,79,.25));
			stepSecondary.add(new Action());
			step.add(null);
			stepSecondary.add(null);
		}
		
*/
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
		// }

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
<<<<<<< HEAD
		
			getIt();
			getIt();
			getIt();
			getIt();
			getIt();
			getIt();
		SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
=======
		//for(int i=0;i<6;i++){
		//	getIt();
		//}
		System.out.println(motorEnc.getDistance());
		//SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		//SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
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
	public void teleopInit(){
		
//		imu.zeroYaw();
		CameraServer.getInstance().addCamera(cam0);
	}
	public void teleopPeriodic() {
<<<<<<< HEAD
		//for(int i=0;i<6;i++){
		//	getIt();
		//}
		SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
=======
		
		//SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		//SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
		SmartDashboard.putNumber("Motor Speed", motorEnc.getRate());
		SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());
		//System.out.println(imu.getPitch());
		//System.out.println(motorEnc.getDistance());
		
		d.tankDrive(joyl, joyr);
<<<<<<< HEAD
		if (gamepadController.getRawButton(5)) {

			f.flywhlGo(1.0);
=======
		if (gamepadController.getRawButton(Const.buttonL)) {
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5

			f.flywhlGo(.8);
			System.out.println("running");
		}
		else {

			f.flywhlStop();
		}
		if (gamepadController.getRawButton(Const.buttonA)) {

			i.intakeGo(1.0);

		}
		if (gamepadController.getRawButton(Const.buttonB)) {

			i.intakeStop();

		}
<<<<<<< HEAD
		if (gamepadController.getRawButton(3)) {
			cycler.cycleSpeed(.5);
			
		}
		else if (gamepadController.getRawButton(4)) {
			cycler.cycleSpeed(-.5);
			
		}else {
=======
		if (gamepadController.getRawButton(Const.buttonR)) {

			cycler.cycleSpeed(-.15);

		} else if (gamepadController.getRawButton(Const.buttonStart)){
			cycler.cycleSpeed(.15);
		}
		else {
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5

			cycler.stop();

		}
		if (gamepadController.getAxis(AxisType.kTwist)>=.1){
			climber.setSpeed(gamepadController.getAxis(AxisType.kTwist));
		} else {
			climber.stop();
		}
	}

	@Override
	public void testPeriodic() {
<<<<<<< HEAD
		camera.addCamera();
		SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
=======
//		SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
//		SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
		SmartDashboard.putNumber("Motor Speed", motorEnc.getRate());
		SmartDashboard.putNumber("Shooter Speed", shooterEnc.getRate());
		
		if (gamepadController.getAxis(AxisType.kTwist)>=.1){
			climber.setSpeed(gamepadController.getAxis(AxisType.kTwist));
			//speeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed #fyast
		}

		if (gamepadController.getRawButton(Const.buttonA)) {

			d.runBL();

		} else if (gamepadController.getRawButton(Const.buttonB)) {

			d.runFL();

		} else if (gamepadController.getRawButton(Const.buttonX)) {

			d.runBR();

		} else if (gamepadController.getRawButton(Const.buttonY)) {

			d.runFR();

		} else if (gamepadController.getRawButton(Const.buttonL)) {

			f.flywhlGo(4000.0);

		} else if (gamepadController.getRawButton(Const.buttonR)) {

			i.intakeGo(1.0);

		} else if (gamepadController.getRawButton(Const.buttonBack)) {

			climber.setSpeed(1.0);
		} else if (gamepadController.getRawButton(Const.buttonStart)) {
			
			cycler.cycleSpeed(.001);
		}
			else {

			d.stopMotors();
			f.flywhlStop();
			i.intakeStop();
			climber.stop();
			cycler.stop();
		}

		// WHOA TECHNOLOGY
	}
	public void getIt(){
<<<<<<< HEAD
	str=serial.readString(1);
=======
		
		
		
		//str=serial.readString(1);
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
		//System.out.println("Trying to Read");
		if(str!=null&&!str.equals("^")){
			Finals += str;
		//	System.out.println("Collecting");
		}
		else if(Finals.split(":").length==9){
			//System.out.println(finalS);
			tt = Finals.split(":");
			try{
				
				
				tt[0]=tt[0].substring(2);
				
				if(Integer.parseInt(tt[0])>Integer.parseInt(tt[4])){
					BiL = new BoxInfo(tt[4],tt[5],tt[6],tt[7]);
					BiR = new BoxInfo(tt[0],tt[1],tt[2],tt[3]);
				}
				else if(Integer.parseInt(tt[0])<Integer.parseInt(tt[4])){
					BiR = new BoxInfo(tt[4],tt[5],tt[6],tt[7]);
					BiL = new BoxInfo(tt[0],tt[1],tt[2],tt[3]);
				}
				
				System.out.println("Left Box X:" + BiL.getX());
				System.out.println("Right Box X:" + BiR.getX());
				springPos=(BiL.getX()+BiR.getX())/2;
				//System.out.println("Width:" + tt[2]);
				Finals = "";
			}
			catch(Exception e){
				System.out.println(tt[0]);
				System.out.println(tt[4]);
				System.out.println(e);
			}
<<<<<<< HEAD
		}else if(Finals.split(":").length==1){
			OBJECT=false;
=======
		}else if(Finals.split(":").length==2){
			pixyBlind=false;
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5
			
		}
		else{
			Finals = "";
		}
			
		
	}
}
