package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;

public class driveStraightUntilAtGearFromLeftHandSide extends Action {
	private double gearDistance = 109;// The gear dropoff is 112 in. away.
	private float gearAngle = -45f;
	private double speed = 0.75;
	private int part = 1;

	public driveStraightUntilAtGearFromLeftHandSide(double time) {

		timeout = time;

	}

	public driveStraightUntilAtGearFromLeftHandSide(double time, double giveSpped) {

		timeout = time;
		speed = giveSpped;
	}

	public void startAction() {
		super.startAction();

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

	public void periodic() {
		if (part == 1) {
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
				part = 2;
			}

		}
		if (part == 2) {
			if (Robot.imu.getYaw() > gearAngle) {
				Robot.d.turnright();
			} else {
				Robot.d.stopMotors();
				part = 3;
			}
		}

	}
}