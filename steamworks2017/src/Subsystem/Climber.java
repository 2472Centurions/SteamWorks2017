package Subsystem;

import com.ctre.CANTalon;

public class Climber {
	CANTalon climb;

	public Climber(int HY) {
		climb = new CANTalon(HY);
	}

	//Extends the climber at 1.0/full power
	public void extend() {

		climb.set(1.0);

	}
	//Retracts the climber 1.0/full power
	public void retract() {

		climb.set(-1.0);

	}
	//Stops the climber
	public void stop() {

		climb.set(0.0);

	}
	//Moves the climber at desired power
	public void setSpeed(double spped) {

		climb.set(spped);

	}
}
