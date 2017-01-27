package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class driveStraightUntilAtGear extends Action {

	public driveStraightUntilAtGear() {

	}

	public void startAction() {

		super.startAction();

	}

	public void periodic() {

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
