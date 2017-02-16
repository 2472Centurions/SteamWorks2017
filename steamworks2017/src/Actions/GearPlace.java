package Actions;
import org.usfirst.frc.team2472.robot.BoxInfo;
import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;


public class GearPlace extends Action {
    BoxInfo left,right;
	int springPos;
	
	public GearPlace(BoxInfo l,BoxInfo r){
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
		springPos=(left.getX()+right.getX())/2;
		
		if(springPos>159){
			Robot.d.turn(1.0-(double)Math.pow(159-springPos/159,1/2),1.0);
			
		}
		if(springPos<=159){
			
			
			Robot.d.turn(1.0,1.0-(double)Math.pow(159-springPos/springPos,1/2));
		}
	}
	public void endAction() {
		Robot.d.stopMotors();
		
	}
}
