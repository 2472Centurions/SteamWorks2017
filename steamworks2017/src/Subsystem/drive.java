package Subsystem;

import com.ctre.CANTalon;

import Objects.Action;
import Constants.Const;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class drive extends Action {
	CANTalon l1,l2,r1,r2;
	RobotDrive d;
	public drive(){
			l1=new CANTalon(Const.BL);
			l2=new CANTalon(Const.FL);
			r1=new CANTalon(Const.BR);
			r2=new CANTalon(Const.FR);
			d=new RobotDrive(l1,l2,r1,r2);
	}
	public void runMotor(int motor,double spped){
		if (motor==Const.BL || motor==Const.FL){
			l1.setInverted(false);
			l1.set(spped);
			l1.setInverted(false);
			l2.set(spped);
		}
		else if (motor==Const.BR || motor==Const.FR){
			r1.set(spped);
			r1.setInverted(false);
			r2.set(spped);
			r2.setInverted(false);
		}
	}
	public void runMotorReverse(int motor,double spped){
		if(motor==Const.BL || motor==Const.FL){
			l1.setInverted(true);
			l1.set(spped);
			l2.setInverted(true);
			l2.set(spped);
		}
		else if(motor==Const.BR || motor==Const.FR){
			r1.setInverted(true);
			r1.set(spped);
			r2.setInverted(true);
			r2.set(spped);
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