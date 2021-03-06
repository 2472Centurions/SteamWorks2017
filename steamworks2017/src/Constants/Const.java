package Constants;

public class Const {
	public static final String spped = "FAST";//we need this i promise
	/**drive motors*/
	public static final int BL = 14;
	public static final int FL = 15;
	public static final int BR = 2;
	public static final int FR = 3;
	/**other motors*/
	public static final int climber = 0;
	public static final int FWheel = 1;
	public static final int Intake = 13;
	public static final int Cycler = 12;
	/**Full Speed on motor*/
	public static final double fullSpeedForward = 1.0;
	public static final int shooterSpeed = 5000;
	/**encoder channels*/
	public static final int motorEncChanA = 0;
	public static final int motorEncChanB = 1;
	public static final int shooterEncChanA = 3;
	public static final int shooterEncChanB = 4;
	/**controller ports*/
	public static final int gpad = 2;
	public static final int joyl = 1;
	public static final int joyr = 0;
	public static final int box = 3;
	/**other controller stuffs*/
	public static final int  boxButton1 = 0;
	public static final int boxButton2 = 0;
	public static final int buttonA = 1;
	public static final int buttonB = 2;
	public static final int buttonX = 3;
	public static final int buttonY = 4;
	public static final int buttonL = 5;
	public static final int buttonR = 6;
	public static final int buttonBack = 7;
	public static final int buttonStart = 8;
	/**Camera Values*/
	public static final int cameraCenter = 159;
	public static final int finalDistance = 2000;
	/**IMU Values*/
	public static final double yawFactor = 210.0;
	
	public static final double roboDiameter = 27.5;
}