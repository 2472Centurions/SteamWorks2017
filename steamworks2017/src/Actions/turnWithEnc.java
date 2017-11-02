package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;
import edu.wpi.first.wpilibj.Encoder;

public class turnWithEnc extends Action{
	
	double arc, speed, angle;
	Encoder Enc;
	boolean done;
	public turnWithEnc(double time) {

		timeout = time;

	}

	public turnWithEnc(double time, double speeed, double ang, Encoder e) {

		timeout = time;

		speed = speeed;

		angle = ang;
		
		Enc = e;
	}

	public void startAction() {
		
		super.startAction();
		Enc.reset();
		arc = (angle/360)*89.5;
		System.out.println("STARTING"+Enc.getDistance());
		
	}
	//Move backwards at negative desired speed
	public void periodic() {
		System.out.println("Destination"+arc);
		System.out.println("Dist:"+Enc.getDistance());
		if(angle>0){
			if(Enc.getDistance()>-arc){
				System.out.println("left");
				Robot.d.turn(speed, -speed);
			}
			else{
				done = true;
			}
		}
		else if(angle<0){
			if(Enc.getDistance()<-arc){
				System.out.println("right");
				Robot.d.turn(-speed, speed);
				
			}
			else{
				done = true;
			}
		}
		else{
			System.out.println("stop");
			done = true;
		}
		}
	

	public void endAction() {

		 Robot.d.stopMotors();

	}
	
	public boolean isFinished(){
		
		if(isTimedOut()||done){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}
		
	}
		
	

}
