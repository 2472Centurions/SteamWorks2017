package Actions;
import org.usfirst.frc.team2472.robot.BoxInfo;
import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;
import edu.wpi.first.wpilibj.AnalogInput;


public class GearPlace extends Action {
    BoxInfo left,right;
	int springPos;
	Robot robot;
	double speed;
	AnalogInput dist;
	
	public GearPlace(BoxInfo l,BoxInfo r,double time,double sped,AnalogInput distance){
		timeout=time;
		speed=sped;
		try{
		left=l;
		right=r;}
		catch(Exception e){
			
			
		}

		}
		
	
	public void startAction() {
springPos=(left.getX()+right.getX())/2;
	}

	

	public void periodic() {
		if(dist.getAverageValue()<2000)speed=0;
		if(Integer.parseInt(robot.tt[8])==2){
		springPos=(left.getX()+right.getX())/2;
		
		if(springPos>159){
			Robot.d.turn(1.0-(double)Math.pow(159-springPos/159,1/2)*speed,1.0*speed);
			
		}
		if(springPos<=159){
			
			
			Robot.d.turn(1.0,1.0-(double)Math.pow(159-springPos/springPos,1/2));
		}
	}else{
		if (Robot.imu.getYaw() > 0 || Robot.imu.getYaw() == 0) {
			/*
			 * This formula takes the absloute value of yaw then subtratcs
			 * 180. Next this number is put in a fracion of N/210. Next this
			 * number * speed to get final speed. The final speed gets lower
			 * the nearer the yaw is to zero
			 */
			Robot.d.turn((Math.abs(Robot.imu.getYaw() - 180) / 210.0) * speed, speed);
			System.out.println("=>" + Robot.imu.getYaw() + "    " + (Math.abs(Math.abs(Robot.imu.getYaw()) - 180) / 210.0) * speed);
		}

		if (Robot.imu.getYaw() < 0) {
			/*
			 * This formula takes the abslote value of yaw than adds 180.
			 * Next this number is put in a fracion of N/210. Next this
			 * number * speed to get final speed. The final speed gets lower
			 * the nearer the yaw is to zero
			 */
			Robot.d.turn(speed, Math.abs(Robot.imu.getYaw() + 180) / 210.0 * speed);
			System.out.println("<=" + Robot.imu.getYaw() + "    " + (Math.abs(Math.abs(Robot.imu.getYaw()) - 180) / 210.0) * speed);
		}
		
			}
	}
		
		
	
	public void endAction() {
		Robot.d.stopMotors();
		
	}

}