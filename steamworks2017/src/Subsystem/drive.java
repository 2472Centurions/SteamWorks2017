package Subsystem;

import Objects.Action;
import com.ctre.CANTalon;
import Constants.Const;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class drive extends Action {
	CANTalon l1, l2, r1, r2;
	double  baseSpeed, leftSide,rightSide,leftTrigger,rightTrigger,leftStickY;
	public drive(int flm, int frm, int blm, int brm) {
		l1 = new CANTalon(blm);
		l2 = new CANTalon(flm);
		r1 = new CANTalon(brm);
		r2 = new CANTalon(frm);
		r1.setInverted(true);
		r2.setInverted(true);
		//setInverted causes positive numbers to move the robot forward
	}
	//Runs a side of the drivetrain forward at desired speed
	public void runMotor(int motor, double spped) {
		if (motor == Const.BL || motor == Const.FL) {
			l1.set(spped);
			l2.set(spped);
		} else if (motor == Const.BR || motor == Const.FR) {
			r1.set(spped);
			r2.set(spped);
		}
	}
	//Runs a side of the drivetrain backwards at desired speed.
	public void runMotorReverse(int motor, double spped) {
		if (motor == Const.BL || motor == Const.FL) {
			l1.set(spped);
			l2.set(spped);
		} else if (motor == Const.BR || motor == Const.FR) {
			r1.set(spped);
			r2.set(spped);
		}
	}
	//Stops all motors
	public void stopMotors() {
		l1.set(0.0);
		l2.set(0.0);
		r1.set(0.0);
		r2.set(0.0);
	}
	//Moves the robot based on joystick movement
	public void tankDrive(Joystick ljoy, Joystick rjoy) {
		l1.set(-ljoy.getY()*.7);
		l2.set(-ljoy.getY()*.7);
		r1.set(-rjoy.getY()*.7);
		r2.set(-rjoy.getY()*.7);
	}
	public void XBoxDrive(Joystick xbox){
		/* This is a control scheme inspired by rocket league.
		 * 
		 * Pressing left trigger is reverse and pressing right trigger is forward. 
		 * The left thumb stick controls turning. Pushing the stick left will do will
		 * turn left and pushing the stick right will turn left
		 * 
		 */
		if(xbox.getRawButton(2)){
			if(xbox.getRawAxis(0)<0) {
				rightSide= -xbox.getRawAxis(0);
				leftSide=-rightSide;
			}
			if(xbox.getRawAxis(0)>0) {
				leftSide= xbox.getRawAxis(0);
				rightSide=-rightSide;
			}
		} else {
			baseSpeed= -xbox.getRawAxis(2)+xbox.getRawAxis(3);
			if(xbox.getRawAxis(0)<0) {
				leftSide=baseSpeed*(1+xbox.getRawAxis(0));
				rightSide=baseSpeed;
			}
			if(xbox.getRawAxis(0)>0) {
				rightSide=baseSpeed*(1-xbox.getRawAxis(0));
				leftSide=baseSpeed;
			}
		}
		turn(leftSide,rightSide);
		//combines the ax'es of the triggers
		
		
	}
	public void SouthPawDrive(Joystick xbox){
		/* This is a control scheme that is the reverse of XBoxDrive.
		 * 
		 * Triggers will handle turning.
		 *  Pressing left trigger is will turn the robot left and pressing right trigger
		 *  will turn the robot right. 
		 * 
		 * The left and tight thumb sticks control speed.
		 *  Pushing the left stick forward will make the robot go 
		 *  forward and pushing the right stick forward will make 
		 *  the robot go forward.
		 *  
		 * The X button will activate super turning mode.
		 *  the turning controls will control both sides of the 
		 *  drivetrain to run opposite to each other
		 *  move in opsit
		 * 
		 */
		
		leftTrigger = xbox.getRawAxis(2);
		leftStickY = (1+xbox.getRawAxis(4));
		
		if(leftTrigger>0.1){
			if(xbox.getRawAxis(1)<0)
			{
			//if turning stick is left
				leftSide=baseSpeed*(1+xbox.getRawAxis(4));
				rightSide=-baseSpeed;
			}
			if(xbox.getRawAxis(1)>0)
			{
				rightSide=baseSpeed*(1-xbox.getRawAxis(4));
				leftSide=baseSpeed;
			}
		}
		baseSpeed= -xbox.getRawAxis(1);
		if(xbox.getRawAxis(0)<0)
		{
			leftSide=baseSpeed*(1+xbox.getRawAxis(4));
		}
		if(xbox.getRawAxis(0)>0)
		{
			rightSide=baseSpeed*(1-xbox.getRawAxis(4));
			leftSide=baseSpeed;
		}
		turn(leftSide,rightSide);
		
		
	}
	
	public void oneStick (Joystick xbox) {
		

		leftStickY = (1+xbox.getRawAxis(0));
		if(leftTrigger>0.1){
			if(xbox.getRawAxis(1)<0)
			{
			//if turning stick is left
				leftSide=baseSpeed*(1+xbox.getRawAxis(4));
				rightSide=-baseSpeed;
			}
			if(xbox.getRawAxis(1)>0)
			{
				rightSide=baseSpeed*(1-xbox.getRawAxis(4));
				leftSide=baseSpeed;
			}
		}
		baseSpeed= -xbox.getRawAxis(1);
		if(xbox.getRawAxis(0)<0)
		{
			leftSide=baseSpeed*(1+xbox.getRawAxis(4));
		}
		if(xbox.getRawAxis(0)>0)
		{
			rightSide=baseSpeed*(1-xbox.getRawAxis(4));
			leftSide=baseSpeed;
		}
		turn(leftSide,rightSide);
	}
	
	//Moves the robot at desired power
	public void setAllMotors(double spped) {

		l1.set(spped);
		l2.set(spped);
		r1.set(spped);
		r2.set(spped);

	}
	//Runs both sides of the drivetrain at desired power individually
	public void turn(double Lspeed, double Rspeed) {

		l1.set(Lspeed);
		l2.set(Lspeed);
		r1.set(Rspeed);
		r2.set(Rspeed);

	}
	//Causes the robot to spin by running both sides of the drive train in opposition
	public void spin() {

		l1.set(-1.0);
		l2.set(-1.0);
		r1.set(1.0);
		r2.set(1.0);

	}
	//Runs front right motor at full power
	public void runFR() {
		r2.set(1.0);
		
	}
	//Runs back right motor at full power
	public void runBR() {
		r1.set(1.0);

	}
	//Runs front left motor at full power 
	public void runFL() {
		l2.set(1.0);

	}
	//Runs back left motor at full power
	public void runBL() {
		l1.set(1.0);

	}
}
