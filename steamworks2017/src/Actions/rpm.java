package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;

public class rpm extends Action {

	private double speed = 0.5;
	private double rpm;

	public rpm(double time, double rpmTarget) {

		timeout = time;

		rpm = rpmTarget;

	}

	public void startAction() {

		super.startAction();

	}

	public void periodic() {
		Robot.f.flywhlGo(speed);
		if (Robot.shooterEnc.getRate() < rpm) {

			speed += 0.01;
			System.out.println(Robot.shooterEnc.getRate() + "      " + speed);
		}
		if (Robot.shooterEnc.getRate() > rpm) {

			speed -= 0.01;
			System.out.println(Robot.shooterEnc.getRate() + "      " + speed);

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
