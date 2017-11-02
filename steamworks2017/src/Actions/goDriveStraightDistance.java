package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;
import edu.wpi.first.wpilibj.Encoder;

public class goDriveStraightDistance extends Action {

	private double speed = 0.5;
	private int distance = 159;
	private boolean done;
	IMUAdvanced imu;
	Encoder motorEnc;
	public goDriveStraightDistance(double time,Encoder e,int d, double s) {
		
		distance = d;
		motorEnc = e;
		speed = s;
		timeout = time;

	}

	public goDriveStraightDistance(double time, double speeed) {

		timeout = time;

		speed = speeed;

	}

	public goDriveStraightDistance(double time, double speeed, int distanceInches) {

		

	}


	public void startAction() {

		super.startAction();
		if(imu!=null)imu.zeroYaw();
		motorEnc.reset();
	}

	public void periodic() {
		
		if (motorEnc.getDistance() < distance) {
			if(imu==null){
				 Robot.d.setAllMotors(speed);
				 System.out.println("NO IMU");
				}
			else if (imu.getYaw() >= 0) {
				/*
				 * This formula takes the absolute value of yaw then subtracts
				 * 180. Next this number is put in a fraction of N/yaw factor (210.0). Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn((Math.abs(imu.getYaw() - 180) / Const.yawFactor) * speed, speed);
				System.out.println(motorEnc.getDistance());
			}else {
				/*
				 * This formula takes the absolute value of yaw than adds 180.
				 * Next this number is put in a fraction of N/yaw factor (210.0). Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn(speed, Math.abs(imu.getYaw() + 180) / Const.yawFactor * speed);
				System.out.println(motorEnc.getDistance());
			}
		}
		else
			done = true;
	}

	public void endAction() {

		Robot.d.stopMotors();
	}

	public boolean isFinished() {

		if (isTimedOut()||done) {

			endAction();

			return true;

		}

		else {

			return false;

		}

	}

}
