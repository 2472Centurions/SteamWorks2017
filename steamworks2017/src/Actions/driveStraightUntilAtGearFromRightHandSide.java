package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

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
			if (Robot.enc.getDistance() < gearDistance) {

				Robot.d.setAllMotors(spped);

			} else {

				Robot.d.stopMotors();
				part=2;
			}
		}
		if(part==2){
			if(Robot.imu.getYaw()<gearAngle){
				Robot.d.turnleft();
			}
			else{
				Robot.d.stopMotors();
				part=3;
			}
		}

	}
}