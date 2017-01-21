package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMU;

import Objects.Action;
import Subsystem.Flywheel;

public class goShoot extends Action{
	
	private double speed = .75;

	public goShoot(double time) {

		timeout = time;

	}

	public goShoot(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {
		
		super.startAction();

	}

	public void periodic() {
		
		 Flywheel.flywhlGo(1.0);
		
		}
	

	public void endAction() {

		 Flywheel.flywhlStop();
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
