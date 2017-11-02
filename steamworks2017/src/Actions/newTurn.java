package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import Objects.Action;
import edu.wpi.first.wpilibj.Encoder;

public class newTurn extends Action{
	
	private double speed, arc;
	
	double roboDiameter=89.53275;
	Encoder enc;
	boolean done;
	/**sets time and angle(int) (in that order)*/
	public newTurn(double time, double a, double sped, Encoder e) {

		timeout = time;
		arc = a;
		speed = sped;
		enc = e;
		
		
	}

	public void startAction() {
		
		super.startAction();
		
		enc.reset();
	}

	public void periodic() {
		System.out.println(enc.getDistance());
		 if(arc<0){
			 if(enc.getDistance()<-arc){
					Robot.d.turn(-speed, speed);
				}
		 }
		else if(arc>0){
			if(enc.getDistance()>-arc){
				Robot.d.turn(-speed, speed);
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
