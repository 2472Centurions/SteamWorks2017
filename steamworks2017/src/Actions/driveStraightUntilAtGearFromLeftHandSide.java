package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

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
			if (Robot.enc.getDistance() < gearDistance) {

				Robot.d.setAllMotors(speed);

			} else {

				Robot.d.stopMotors();
				part=2;
			}
		}
		if(part==2){
			if(Robot.imu.getYaw()>gearAngle){
				Robot.d.turnright();
			}
			else{
				Robot.d.stopMotors();
				part=3;
			}
		}

	}
}