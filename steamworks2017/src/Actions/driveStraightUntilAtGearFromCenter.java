package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class driveStraightUntilAtGearFromCenter extends Action {
private double gearDistance=109;//The gear dropoff is 112 in. away.
private double speed=0.75;
	public driveStraightUntilAtGearFromCenter(double time) {
	
	timeout=time;
	
	}
	public driveStraightUntilAtGearFromCenter(double time,double giveSpeed) {
		
		timeout=time;
		speed= giveSpeed;

	}

	public void startAction() {

		super.startAction();

	}

	public void periodic() {
		if(Robot.enc.getDistance()<gearDistance){
			
			Robot.d.setAllMotors(speed);
				
		}
		else {
			
			Robot.d.stopMotors();
		}
	}

	public void endAction() {

		Robot.d.stopMotors();
	}

	public boolean isFinished() {

		if (isTimedOut()) {

			endAction();

			return true;

		}

		else {

			return false;

		}

	}
}
