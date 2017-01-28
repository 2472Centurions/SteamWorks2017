package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Objects.Action;

public class driveStraightUntilAtGearFromRightHandSide extends Action {
	private double gearDistance = 109;// The gear dropoff is 112 in. away.
	private float gearAngle = 45f;
	private double speed = 0.75;
	private int part = 1;
	IMUAdvanced imu;
	public driveStraightUntilAtGearFromRightHandSide(double time, IMUAdvanced i) {

		imu = i;
		timeout = time;

	}

	public driveStraightUntilAtGearFromRightHandSide(double time, double giveSpeed, IMUAdvanced i) {

		imu = i;
		timeout = time;
		speed = giveSpeed;
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
			if (Robot.enc.getDistance() < gearDistance) {

				Robot.d.setAllMotors(speed);

			} else {

				Robot.d.stopMotors();
				part=2;
			}
		}
		if(part==2){
			if(imu.getYaw()<gearAngle){
				Robot.d.turnleft();
			}
			else{
				Robot.d.stopMotors();
				part=3;
			}
		}

	}
}