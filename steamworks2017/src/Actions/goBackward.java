package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class goBackward extends Action{
	
	private double speed = .75;

	public goBackward(double time) {

		timeout = time;

	}

	public goBackward(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {
		
		super.startAction();

	}

	public void periodic() {
		// goForward method needs a double for speed. 
	    // It takes the absloute value of the double you give.
		
		 Robot.d.goBackward(-1.0);
		
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
