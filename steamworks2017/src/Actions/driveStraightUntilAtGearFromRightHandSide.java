package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;

public class driveStraightUntilAtGearFromRightHandSide extends Action {
	private double gearDistance = 109;// The gear dropoff is 112 in. away.
	private float gearAngle = 45f;
	private double spped = 0.75;
	private int part = 1;

	public driveStraightUntilAtGearFromRightHandSide(double time) {

		timeout = time;

	}

	public driveStraightUntilAtGearFromRightHandSide(double time, double giveSpped) {

		timeout = time;
		spped = giveSpped;
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

					Robot.d.setAllMotors(spped);

				}
			} else {

				Robot.d.stopMotors();
				part = 2;
			}
		}
		if (part == 2) {
			if (Robot.imu.getYaw() < gearAngle) {
				Robot.d.turnleft();
			} else {
				Robot.d.stopMotors();
				part = 3;
			}
		}

	}
}