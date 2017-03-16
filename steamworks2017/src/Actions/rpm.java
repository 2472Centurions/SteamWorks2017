package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Constants.Const;
import Objects.Action;

public class rpm extends Action {

	private int speed = Const.shooterSpeed;
	private double rpm;

	/**sets time and rpm*/
	public rpm(double time, double rpmTarget) {

		timeout = time;

		rpm = rpmTarget;

	}

	public void startAction() {

		super.startAction();

	}
	//will print out current rpm and the current power sent to fly wheel to console
	//Works by adjusting power sent to motors until it matches target RPM
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
