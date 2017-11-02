package Actions;

import org.usfirst.frc.team2472.robot.BoxInfo;
import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;
import edu.wpi.first.wpilibj.AnalogInput;

public class SHOOT extends Action {
	boolean imuON;
	BoxInfo center;
	int springPos;
	Robot robot;
	double speed;
	AnalogInput dist;


	public SHOOT(BoxInfo center1, double sped, AnalogInput d) {
		dist = d;
		speed = sped;
		
		
	
		center = center1;
	}

	public void startAction() {
		super.startAction();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		
	}

	public void periodic() {
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		robot.getIt();
		
		if(center.getX()<120){
			Robot.d.turn(-speed, 1.0 - (double) Math.pow(Const.cameraCenter - springPos /Const.cameraCenter, 1 / 2));
			
		}else if(center.getX()>125){
			Robot.d.turn(1.0 - (double) Math.pow(Const.cameraCenter - springPos / Const.cameraCenter, 1 / 2) * speed,-speed);
		
		}else if(center.getY()<120){
		Robot.d.turn(-speed, 1.0 - (double) Math.pow(Const.cameraCenter - springPos /Const.cameraCenter, 1 / 2));
		
	}else if(center.getY()>125)
		Robot.d.turn(1.0 - (double) Math.pow(Const.cameraCenter - springPos / Const.cameraCenter, 1 / 2) * speed,-speed);
	
	}
	

	public void endAction() {
		Robot.d.stopMotors();

	}

	public boolean isFinished() {

		if (isTimedOut() || speed == 0.0) {

			endAction();

			return true;

		}

		else {

			return false;

		}

	}
}
