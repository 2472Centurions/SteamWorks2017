package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Constants.Const;
import Objects.Action;

public class driveStraightUntilAtGearFromCenter extends Action {
	private double gearDistance = 109;// The gear dropoff is 112 in. away.
	private double speed = 0.75;

	public driveStraightUntilAtGearFromCenter(double time) {

		timeout = time;

	}

	public driveStraightUntilAtGearFromCenter(double time, double giveSpeed) {

		timeout = time;
		speed = giveSpeed;

	}

	public void startAction() {

		super.startAction();

	}

	public void periodic() {
		if (Robot.motorEnc.getDistance() < gearDistance) {
			
			if (Robot.imu.getYaw() > Const.deadZone && Robot.imu.getYaw() < 180.0) {

				Robot.d.turnleft();

			} else if (Robot.imu.getYaw() > 180.0 && Robot.imu.getYaw() < 360.0 - Const.deadZone) {
				
				Robot.d.turnright();

			} else {

				Robot.d.setAllMotors(speed);

			}

		} else {

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
