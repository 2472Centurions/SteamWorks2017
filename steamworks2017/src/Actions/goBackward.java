package Actions;

import org.usfirst.frc.team2472.robot.Robot;
import Constants.Const;
import Objects.Action;
import Subsystem.drive;

public class goBackward extends Action{
	
	private double speed = 0.75;
	
	/**set amount of time to backward (as dubs)*/
	public goBackward(double time) {

		timeout = time;

	}
	/**goes backward for a set time at a set speed*/
	public goBackward(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}
	
	public void startAction() {
		
		super.startAction();

	}
	/**Move backwards at negative desired speed*/
	public void periodic() {
		
		 Robot.d.setAllMotors(-speed);
		
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
