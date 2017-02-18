package Subsystem;

import com.ctre.CANTalon;

public class ballCycler {
	CANTalon cycle;

	public ballCycler(int CAN) {

		cycle = new CANTalon(CAN);

	}

	public void cycleIt() {

		cycle.set(1.0);

	}

	public void cycleSpeed(double spped) {

		cycle.set(spped);

	}

	public void stop() {

		cycle.set(1.0);

	}

}