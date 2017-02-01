package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Constants.Const;
import Objects.Action;
import Subsystem.drive;

public class goSpin extends Action{
	
	private double speed = 0.5;
	

	public goSpin(double time) {

		timeout = time;

	}

	public goSpin(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {
		
		super.startAction();

	}

	public void periodic() {
		
		 Robot.d.spin();
		 System.out.println(Robot.imu.getYaw());
		 
		}
	

	public void endAction() {

		 Robot.d.stopMotors();
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
