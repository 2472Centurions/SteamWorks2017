package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Constants.Const;
import Objects.Action;
import Subsystem.drive;

public class goForward extends Action{
	
	private double speed = 0.5;
	

	public goForward(double time) {

		timeout = time;

	}

	public goForward(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {
		
		super.startAction();

	}

	public void periodic() {
		
		 Robot.d.setAllMotors(speed);
		 
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
