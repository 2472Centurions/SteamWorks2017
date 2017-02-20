package Subsystem;

import com.ctre.CANTalon;

public class ballCycler {
	CANTalon cycleA,cycleB;

	public ballCycler(int CAN,int CANB) {

		cycleA = new CANTalon(CAN);
		cycleB = new CANTalon(CANB);

	}

	public void cycleIt() {

		cycleA.set(1.0);
		cycleB.set(1.0);

	}

	public void cycleSpeed(double spped) {

		cycleA.set(spped);
		cycleB.set(spped);

	}

	public void stop() {

		cycleA.set(0.0);
		cycleB.set(0.0);

	}

}
