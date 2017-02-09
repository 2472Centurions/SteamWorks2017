package Subsystem;

import com.ctre.CANTalon;

public class Climber {
	CANTalon climb;

	public Climber(int HY) {
		climb = new CANTalon(HY);
	}

	public void extend() {

		climb.set(1.0);

	}

	public void retract() {

		climb.set(-1.0);

	}

	public void stop() {

		climb.set(0.0);

	}

	public void setSpeed(double spped) {

		climb.set(spped);

	}
}
