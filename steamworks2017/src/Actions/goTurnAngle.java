
package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import Objects.Action;
import edu.wpi.first.wpilibj.Encoder;

public class goTurnAngle extends Action {
	private double speed = 0.8;
	private double angle = 180.0;
	double turnFactor;
	int speedFactor=10;
	
	double roboDiameter=89.53275;
	double arcLength;
	Encoder enc;
	/**sets time and angle(int) (in that order)*/
	public goTurnAngle(double time,int turnAngle) {

		timeout = time;
		angle = turnAngle;
		
	}
	
	public goTurnAngle(double time, double turnAngle, Encoder e,double sped) {
		enc=e;
		speed=sped;
		timeout = time;

		angle = turnAngle;

	}

	public void startAction() {
		
		super.startAction();
		
		enc.reset();
		arcLength=roboDiameter*(Math.abs(angle/360));
	}

	public void periodic() {
		 if(Math.abs(arcLength-enc.getDistance())>speedFactor){
		turnFactor=Math.pow((1/(arcLength-enc.getDistance())),1/2);
		if(angle>0)Robot.d.turn(-(speed-(turnFactor*speed)),speed-(turnFactor*speed));
		if(angle<0)Robot.d.turn(speed-(turnFactor*speed),-(speed-(turnFactor*speed)));
		 }
		else if(Math.abs(arcLength-enc.getDistance())<speedFactor&&speedFactor>0)
		{
			
			
			speed=speed*.9;
			speedFactor--;
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