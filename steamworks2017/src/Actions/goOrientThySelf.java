package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class goOrientThySelf extends Action {

	private double speed = 0.5;
	private double angle = 45.0;

	public goOrientThySelf(double time) {

		timeout = time;

	}

	public goOrientThySelf(double time, double turnAngle) {

		timeout = time;

		angle = turnAngle;

	}

	public void startAction() {

		super.startAction();
		Robot.imu.zeroYaw();
	}

	public void periodic() {

		if (angle > 0) {

			Robot.d.turn(((angle - Robot.imu.getYaw())) * speed, ((angle - Robot.imu.getYaw())) * -speed);
			System.out.println(Robot.imu.getYaw()+"     "+((angle - Robot.imu.getYaw())) * speed);
		}
		if (angle < 0) {
			
			Robot.d.turn(((angle + Robot.imu.getYaw())) * speed, ((angle - Robot.imu.getYaw())) * -speed);
			System.out.println(Robot.imu.getYaw()+"     "+((angle + Robot.imu.getYaw())) * speed);
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
