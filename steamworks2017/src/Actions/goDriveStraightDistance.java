package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class goDriveStraightDistance extends Action {

	private double speed = 0.5;
	private int distance = 159;
	private boolean done;

	public goDriveStraightDistance(double time) {

		timeout = time;

	}

	public goDriveStraightDistance(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public goDriveStraightDistance(double time, double speeed, int distanceInches) {

		timeout = time;

		speed = speeed;

		distance = distanceInches;

	}

	public void startAction() {

		super.startAction();
		Robot.imu.zeroYaw();
		Robot.motorEnc.reset();
	}

	public void periodic() {
		if (Robot.motorEnc.getDistance() < distance) {
			if (Robot.imu.getYaw() > 0 || Robot.imu.getYaw() == 0) {
				/*
				 * This formula takes the absolute value of yaw then subtracts
				 * 180. Next this number is put in a fraction of N/210. Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn((Math.abs(Robot.imu.getYaw() - 180) / 210.0) * speed, speed);
				System.out.println(Robot.motorEnc.getDistance());
			}

			if (Robot.imu.getYaw() < 0) {
				/*
				 * This formula takes the abslote value of yaw than adds 180.
				 * Next this number is put in a fracion of N/210. Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn(speed, Math.abs(Robot.imu.getYaw() + 180) / 210.0 * speed);
				System.out.println(Robot.motorEnc.getDistance());
			}
		}
		else
			done = true;
	}

	public void endAction() {

		Robot.d.stopMotors();
	}

	public boolean isFinished() {

		if (isTimedOut()||done) {

			endAction();

			return true;

		}

		else {

			return false;

		}

	}

}
