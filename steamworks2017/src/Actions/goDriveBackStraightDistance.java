package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Objects.Action;
import edu.wpi.first.wpilibj.Encoder;

public class goDriveBackStraightDistance extends Action {
	
	private double speed = 0.5;
	private int distance = 159;
	private boolean done;
	IMUAdvanced imu;
	Encoder motorEnc;
	public goDriveBackStraightDistance(double time,IMUAdvanced i,Encoder e,int d, double s) {
		imu=i;
		distance = -d;
		motorEnc = e;
		speed = s;
		timeout = time;

	}

	public goDriveBackStraightDistance(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public goDriveBackStraightDistance(double time, double speeed, int distanceInches) {

		timeout = time;

		speed = speeed;

		distance = distanceInches;

	}

	public void startAction() {

		super.startAction();
		imu.zeroYaw();
		motorEnc.reset();
	}

	public void periodic() {
		if (motorEnc.getDistance() > distance) {
			if (imu.getYaw() > 0 ||imu.getYaw() == 0) {
				/*
				 * This formula takes the absolute value of yaw then subtracts
				 * 180. Next this number is put in a fraction of N/210. Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn((Math.abs(imu.getYaw() - 180) / 210.0) * speed, speed);
				System.out.println(motorEnc.getDistance());
			}

			if (imu.getYaw() < 0) {
				/*
				 * This formula takes the abslote value of yaw than adds 180.
				 * Next this number is put in a fracion of N/210. Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn(speed, Math.abs(imu.getYaw() + 180) / 210.0 * speed);
				System.out.println(motorEnc.getDistance());
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
