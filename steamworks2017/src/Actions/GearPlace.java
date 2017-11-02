package Actions;

import org.usfirst.frc.team2472.robot.BoxInfo;
import org.usfirst.frc.team2472.robot.Robot;


import Constants.Const;
import Objects.Action;
import edu.wpi.first.wpilibj.AnalogInput;

public class GearPlace extends Action {
	boolean imuON;
	BoxInfo left, right;
	int springPos;
	double speed;
	AnalogInput dist;


	public GearPlace(BoxInfo l, BoxInfo r, double sped,AnalogInput d) {
		dist = d;
		speed = sped;
		
		left = l;
		right = r;
	}

	public void startAction() {
		System.out.println("Starting Gear Place");
		super.startAction();
		springPos = (left.getX() + right.getX()) / 2;
		System.out.print("test");
	
	}

	// If gear spring not in center of vision, orient to spring, else drive
	// straight until at minimum distance
	public void periodic() {
		System.out.println("starting periodic");
		
		System.out.print(springPos);
			//If robot is at the final distance, it is done
			if (dist.getAverageValue() < Const.finalDistance)
				speed = 0.0;

			// If pixy not blind drive towards spring target
			if (!Robot.noBoxes) {

				springPos = (left.getX() + right.getX()) / 2;

				if (springPos < Const.cameraCenter) {
					Robot.d.turn(1.0 - (double) Math.pow(Const.cameraCenter - springPos / Const.cameraCenter, 1 / 2) * speed,speed);

				}
				if (springPos >= Const.cameraCenter) {

					Robot.d.turn(speed, 1.0 - (double) Math.pow(Const.cameraCenter - springPos /Const.cameraCenter, 1 / 2));
				}

			}else
			{
				// If pixy blind drive straight.
				if (dist.getAverageValue()<1500)Robot.d.setAllMotors(speed);
				} 
			

			if(dist.getAverageValue()<1500)endAction();
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
