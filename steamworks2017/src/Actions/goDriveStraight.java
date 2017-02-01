package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;
import Subsystem.Flywheel;
import Subsystem.drive;

public class goDriveStraight extends Action {

	private double speed = 0.75;

	public goDriveStraight(double time) {

		timeout = time;

	}

	public goDriveStraight(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public void startAction() {

		super.startAction();
		Robot.imu.zeroYaw();
	}

	public void periodic() {

		if (Robot.imu.getYaw() > Const.deadZone) {

			Robot.d.turnleft();
			System.out.println("<="+Robot.imu.getYaw());
		} else if (Robot.imu.getYaw()>-Const.deadZone) {

			Robot.d.turnright();
			System.out.println("=>"+Robot.imu.getYaw());
		} else {

			Robot.d.setAllMotors(speed);
			System.out.println("||"+Robot.imu.getYaw());
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
