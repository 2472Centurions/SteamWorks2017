
package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Objects.Action;

public class goTurnAngle extends Action {
	IMUAdvanced imu;
	private double speed = 0.8;
	private double angle = 180.0;

	public goTurnAngle(double time,int turnAngle) {

		timeout = time;
		angle = turnAngle;

	}

	public goTurnAngle(double time, double turnAngle) {

		timeout = time;

		angle = turnAngle;

	}

	public void startAction() {

		super.startAction();
		imu.zeroYaw();
	}

	public void periodic() {
		//Creates a fraction of the (target angle-yaw)/target angle. and the other side is the reverse.
		//The closer the robot is to the target angle the slower it moves.
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