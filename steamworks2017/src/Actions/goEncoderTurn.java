package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Constants.Const;
import Objects.Action;

public class goEncoderTurn extends Action {
	
	private double turnAngle = 90.0;
	double arcSectorDistance;
	private boolean done = false;
	
	/**sets time*/
	public goEncoderTurn(double time) {

		timeout = time;

	}
	/**sets time and speed (in that order)*/
	public goEncoderTurn(double time, double whatAngle) {

		timeout = time;

		turnAngle = whatAngle;

	}

	public void startAction() {
		Robot.motorEnc1.reset();
		super.startAction();
		//Is the length of the sector that we want to turn
		arcSectorDistance = (Const.wheelBaseDiameter*3.1415926)*(turnAngle/360);
	}

	public void periodic() {
		
		if(Robot.motorEnc1.getDistance()<arcSectorDistance){
			
			Robot.d.setAllMotors(((arcSectorDistance-Robot.motorEnc1.getDistance())/arcSectorDistance)+0.01);
		
		}else done = true;
	}
	

	public void endAction() {

		 Robot.d.stopMotors();
	}
	
	public boolean isFinished(){
		
		if(isTimedOut()||done){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}
		
	}
		
	



}
