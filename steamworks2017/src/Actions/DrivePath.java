package Actions;

import org.usfirst.frc.team2472.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import Objects.Action;


public class DrivePath extends Action {
	double pathIndex;
	double[] left,right;
	public DrivePath(double[] leftPath,double[] rightPath,int index) {
		left=leftPath;
		right=rightPath;
pathIndex=index;
	}

	public void startAction() {

		super.startAction();
	}

	public void periodic() {
		if(pathIndex<70) {
			Robot.d.leftTalon.set(ControlMode.Velocity, 40*(1440.*((left[((int)pathIndex)])/600)));
			rightTalon.set(ControlMode.Velocity, 40*(1008.*(right[((int)pathIndex)])/600));
			pathIndex=pathIndex+.2;
			}else {
				leftTalon.set(ControlMode.PercentOutput, 0);
				rightTalon.set(ControlMode.PercentOutput, 0);
				endAction();
			}

	
	}

	public void endAction() {
		
	}


}
