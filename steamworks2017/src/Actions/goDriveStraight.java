package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;
import Subsystem.Flywheel;
import Subsystem.drive;

public class goDriveStraight extends Action {
	IMUAdvanced imu;
	private double speed = 0.5;

	public goDriveStraight(double time,IMUAdvanced i) {
		imu=i;
		timeout = time;

	}

	public goDriveStraight(double time, double speeed) {
		
		timeout = time;

		speed = speeed;

	}

	public void startAction() {

		super.startAction();
		if(imu!=null)imu.zeroYaw();
	}

	public void periodic() {
		if(imu==null){
			 Robot.d.setAllMotors(speed);
			 return;
			}
		if (imu.getYaw() > 0 || imu.getYaw() == 0) {
		/*
		 * This formula takes the absloute value of yaw then subtratcs
		 * 180. Next this number is put in a fracion of N/yaw factor(210.0). Next this
		 * number * speed to get final speed. The final speed gets lower
		 * the nearer the yaw is to zero
		 */
		Robot.d.turn((Math.abs(imu.getYaw() - 180) / Const.yawFactor) * speed, speed);
		System.out.println("=>" + imu.getYaw() + "    " + (Math.abs(Math.abs(imu.getYaw()) - 180) / Const.yawFactor) * speed);
	}

	if (imu.getYaw() < 0) {
		/*
		 * This formula takes the abslote value of yaw than adds 180.
		 * Next this number is put in a fracion of N/yaw factor (210.0). Next this
		 * number * speed to get final speed. The final speed gets lower
		 * the nearer the yaw is to zero
		 */
		Robot.d.turn(speed, Math.abs(imu.getYaw() + 180) / Const.yawFactor * speed);
		System.out.println("<=" + imu.getYaw() + "    " + (Math.abs(Math.abs(imu.getYaw()) - 180) / Const.yawFactor) * speed);
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
