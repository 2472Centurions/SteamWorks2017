package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMU;

import Constants.Const;
import Objects.Action;
import Subsystem.Flywheel;

public class goShoot extends Action{
	
	private int speed = Const.shooterSpeed;

	/**sets time*/
	public goShoot(double time) {

		timeout = time;

	}
	/**sets time and speed (in that order)*/
	public goShoot(double time, int speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {
		
		super.startAction();

	}

	public void periodic() {
		
		 Robot.f.flywhlGo(speed);
		
		}
	

	public void endAction() {
		
		Robot.f.flywhlStop();
		 
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
