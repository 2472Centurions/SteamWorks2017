package Subsystem;

import Objects.Action;
import com.ctre.CANTalon;
import Constants.Const;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class drive extends Action {
	CANTalon l1, l2, r1, r2;
	
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
		l1.set(-ljoy.getY());
		l2.set(-ljoy.getY());
		r1.set(-rjoy.getY());
		r2.set(-rjoy.getY());
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
