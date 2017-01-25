package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Constants.Const;
import Objects.Action;
import Subsystem.drive;

public class goForward extends Action{
	
	private double speed = .75;
	
	drive d = new drive(Const.FL, Const.FR, Const.BL, Const.BR);

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
		// goForward method needs a double for speed. 
	    // It takes the absloute value of the double you give.
		
		 d.goForward(1.0);	
		
		}
	

	public void endAction() {

		 d.stopMotors();

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
