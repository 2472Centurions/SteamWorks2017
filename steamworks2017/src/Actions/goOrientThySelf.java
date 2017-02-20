package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Objects.Action;

public class goOrientThySelf extends Action {
	IMUAdvanced imu;
	private double speed = 0.8;
	private double angle = 180.0;

	public goOrientThySelf(double time,IMUAdvanced i,double a) {
		imu=i;
		timeout = time;
		angle = a;

	}

	public goOrientThySelf(double time, double turnAngle) {

		timeout = time;

		angle = turnAngle;

	}

	public void startAction() {

		super.startAction();
		imu.zeroYaw();
	}

	public void periodic() {
		// Set one side to (angle-yaw)/angle. It sets the other to
		if (angle > 0) {

			Robot.d.turn((((angle - imu.getYaw())/angle) * speed)+.01, (((angle - imu.getYaw())/angle) * -speed)-.01);
			System.out.println(imu.getYaw() + "     " + ((angle - imu.getYaw())/angle) * speed);
		}
		if (angle < 0) {

			Robot.d.turn((((angle - imu.getYaw())/angle) * -speed)-.01, (((angle - imu.getYaw())/angle) * speed)+.01);
			System.out.println(imu.getYaw() + "     " + ((angle + imu.getYaw())/angle) * speed);
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
