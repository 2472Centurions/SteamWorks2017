package Actions;

import org.usfirst.frc.team2472.robot.BoxInfo;
import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Constants.Const;
import Objects.Action;
import edu.wpi.first.wpilibj.AnalogInput;

public class GearPlace extends Action {
	boolean imuON;
	BoxInfo left, right;
	int springPos;
	Robot robot;
	double speed;
	AnalogInput dist;
	IMUAdvanced imu;
	public GearPlace(BoxInfo l,BoxInfo r,IMUAdvanced i,double sped,AnalogInput d){
		dist =d;
		speed=sped;
		imu=i;
		left=l;
		right=r;
	}

	public void startAction() {
		super.startAction();
		springPos = (left.getX() + right.getX()) / 2;
	}
	//If gear spring not in center of vision, orient to spring, else drive straight until at minimum distance
	public void periodic() {
		//If robot is at the final distance, it is done 
		if(dist.getAverageValue()<Const.finalDistance)speed=0.0;
		
		//If pixy not blind drive towards spring target
		if(!robot.pixyBlind){

			springPos=(left.getX()+right.getX())/2;
		
			if(springPos<Const.cameraCenter){
			Robot.d.turn(1.0-(double)Math.pow(Const.cameraCenter-springPos/Const.cameraCenter,1/2)*speed,1.0*speed);
			
			}
			if(springPos>=Const.cameraCenter){
			
			
			Robot.d.turn(1.0,1.0-(double)Math.pow(Const.cameraCenter-springPos/springPos,1/2));
			}
			
		}//If pixy blind drive straight. 
		else {
			
			if (imu.getYaw() > 0 || imu.getYaw() == 0) {
				/*
				 * This formula takes the absloute value of yaw then subtratcs
				 * 180. Next this number is put in a fracion of N/210. Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn((Math.abs(imu.getYaw() - 180) / 210.0) * speed, speed);
				System.out.println(
						"=>" + imu.getYaw() + "    " + (Math.abs(Math.abs(imu.getYaw()) - 180) / 210.0) * speed);
			}

			if (imu.getYaw() < 0) {
				/*
				 * This formula takes the abslote value of yaw than adds 180.
				 * Next this number is put in a fracion of N/210. Next this
				 * number * speed to get final speed. The final speed gets lower
				 * the nearer the yaw is to zero
				 */
				Robot.d.turn(speed, Math.abs(imu.getYaw() + 180) / 210.0 * speed);
				System.out.println(
						"<=" + imu.getYaw() + "    " + (Math.abs(Math.abs(imu.getYaw()) - 180) / 210.0) * speed);
			}

		}
	}

	public void endAction() {
		Robot.d.stopMotors();

	}
	public boolean isFinished(){
		
		if(isTimedOut()||speed==0.0){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}

	}
}
