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

		if (Robot.imu.getYaw() > 0 || Robot.imu.getYaw() == 0) {
			
			Robot.d.turn((Math.abs(Robot.imu.getYaw()-180)/200.0)* speed,speed);
			System.out.println("=>"+Robot.imu.getYaw()+"    "+(Math.abs(Math.abs(Robot.imu.getYaw())-180)/210.0)* speed);
		}

		if (Robot.imu.getYaw() < 0) {
				
				Robot.d.turn(speed,Math.abs(Robot.imu.getYaw()+180)/200.0* speed);
				System.out.println("<="+Robot.imu.getYaw()+"    "+(Math.abs(Math.abs(Robot.imu.getYaw())-180)/210.0)* speed);}
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
