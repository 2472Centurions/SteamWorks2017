package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class goTurnAngle extends Action {

	private double speed = 0.8;
	private double angle = 180.0;

	public goTurnAngle(double time) {

		timeout = time;

	}

	public goTurnAngle(double time, double turnAngle) {

		timeout = time;

		angle = turnAngle;

	}

	public void startAction() {

		super.startAction();
		Robot.imu.zeroYaw();
	}

	public void periodic() {
		// Set one side to (angle-yaw)/angle. It sets the other to
		if (angle > 0) {

			Robot.d.turn((((angle - Robot.imu.getYaw())/angle) * speed)+.01, (((angle - Robot.imu.getYaw())/angle) * -speed)-.01);
			System.out.println(Robot.imu.getYaw() + "     " + ((angle - Robot.imu.getYaw())/angle) * speed);
		}
		if (angle < 0) {

			Robot.d.turn((((angle - Robot.imu.getYaw())/angle) * -speed)-.01, (((angle - Robot.imu.getYaw())/angle) * speed)+.01);
			System.out.println(Robot.imu.getYaw() + "     " + ((angle + Robot.imu.getYaw())/angle) * speed);
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
