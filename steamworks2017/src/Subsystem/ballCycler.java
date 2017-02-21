package Subsystem;

import com.ctre.CANTalon;

public class ballCycler {
	CANTalon cycleA,cycleB;

	public ballCycler(int CAN,int CANB) {

		cycleA = new CANTalon(CAN);
		cycleB = new CANTalon(CANB);

	}

	public void cycleIt() {

<<<<<<< HEAD
		cycle.set(.5);
=======
		cycleA.set(1.0);
		cycleB.set(1.0);
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5

	}

	public void cycleSpeed(double spped) {

		cycleA.set(spped);
		cycleB.set(spped);

	}

	public void stop() {

<<<<<<< HEAD
		cycle.set(0.0);
=======
		cycleA.set(0.0);
		cycleB.set(0.0);
>>>>>>> b217d0a9c4b8bce339f25aed85169be6a554bcc5

	}

}
