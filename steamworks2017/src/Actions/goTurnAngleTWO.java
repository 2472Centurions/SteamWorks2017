package Actions;





import Objects.Action;
import edu.wpi.first.wpilibj.Encoder;

public class goTurnAngleTWO extends Action {
Encoder left;
Encoder right;
double wheelDist=80.465;
double turnDistance;
	public goTurnAngleTWO(double time, double turnAngle,Encoder left1,Encoder right1) {
//diameter=25.626 inches
		//Circumference =80.465 inches
		turnDistance=wheelDist*(turnAngle/360);
		left1=left;
		right=right1;
	if(turnAngle>0)right.setReverseDirection(false);
	else
	{
	left.setReverseDirection(false);	
		}
	}
public void startAction(){
	
	
}
public void periodic(){
	
	
}
public void endAction(){
	
	
}
	}
