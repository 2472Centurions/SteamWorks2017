package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;
import Subsystem.Flywheel;
import Subsystem.drive;

public class goDriveStraight extends Action{
	
	private double speed = .75;
	
	drive d = new drive(Const.FL, Const.FR, Const.BL, Const.BR);

	public goDriveStraight(double time) {
		
		timeout = time;

	}

	public goDriveStraight(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {
		
		super.startAction();

	}

	public void periodic() {
		
		  if(Robot.imu.getYaw() <5.0 && Robot.imu.getYaw() >180.0){
			 
			 d.turnleft();
			 
		 }
		 else if(Robot.imu.getYaw() >180.0){
			 
			 d.turnright();
			 
		 }
		 else {
			 
			 d.goForward(1.0);
			 
		 }
		 
		 
		 
		}
	

	public void endAction() {

		 d.stopMotors();;
	}
	
	public boolean isFinished(){
		
		if(isTimedOut()){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}
		
	}
		
	





}
