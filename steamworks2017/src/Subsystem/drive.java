package Subsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class drive {
	CANTalon l1,l2,r1,r2;
	int left1,left2,right1,right2;
	RobotDrive d;
	public drive(int leftmotor1,int leftmotor2,int rightmotor1,int rightmotor2){
			l1=new CANTalon(leftmotor1);
			l2=new CANTalon(leftmotor2);
			r1=new CANTalon(rightmotor1);
			r2=new CANTalon(rightmotor2);
			d=new RobotDrive(l1,l2,r1,r2);
			left1=leftmotor1;
			left2=leftmotor2;
			right1=rightmotor1;
			right2=rightmotor2;
	}
	public void runMotor(int motor){
		if (motor==left1 || motor==left2){
			l1.setInverted(false);
			l1.set(1.0);
			l1.setInverted(false);
			l2.set(1.0);
		}
		else if (motor==right1 || motor==right2){
			r1.set(1.0);
			r1.setInverted(false);
			r2.set(1.0);
			r2.setInverted(false);
		}
	}
	public void runMotorReverse(int motor){
		if(motor==left1 || motor==left2){
			l1.setInverted(true);
			l1.set(1.0);
			l2.setInverted(true);
			l2.set(1.0);
		}
		else if(motor==right1 || motor==right2){
			r1.setInverted(true);
			r1.set(1.0);
			r2.setInverted(true);
			r2.set(1.0);
		}
	}
	public void stopMotors(){
		l1.set(0.0);
		l2.set(0.0);
		r1.set(0.0);
		r2.set(0.0);
	}
	public void tankDrive(Joystick ljoy,Joystick rjoy){
		if(ljoy.getY()<0){
			l1.setInverted(true);
			l2.setInverted(true);
		}
		if(rjoy.getY()<0){
			r1.setInverted(true);
			r2.setInverted(true);
		}
		l1.set(Math.abs(ljoy.getY()));
		l2.set(Math.abs(ljoy.getY()));
		r1.set(Math.abs(rjoy.getY()));
		r2.set(Math.abs(rjoy.getY()));
	}
	
}