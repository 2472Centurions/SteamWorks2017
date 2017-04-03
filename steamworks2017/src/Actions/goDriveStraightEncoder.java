package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class goDriveStraightEncoder extends Action {
	
	private double speed = 0.5;
	
	/**sets time*/
	public goDriveStraightEncoder(double time) {

		timeout = time;

	}
	/**sets time and speed (in that order)*/
	public goDriveStraightEncoder(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {
		Robot.motorEnc1.reset();
		Robot.motorEnc2.reset();
		super.startAction();

	}

	public void periodic() {
		//if encoder 2 is less than encoder 1, correct.
		 if(Robot.motorEnc1.getDistance()-Robot.motorEnc2.getDistance()>0.25){
			 
			 Robot.d.turn(Lspeed, Rspeed);
			 
		 }
		//if encoder 1 is less than encoder 2, correct.
		 if(Robot.motorEnc1.getDistance()-Robot.motorEnc2.getDistance()>0.25){
			 
			 Robot.d.turn(Lspeed, Rspeed);
			 
		 }
		 
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
