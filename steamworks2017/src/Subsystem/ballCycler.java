package Subsystem;

import com.ctre.CANTalon;

public class ballCycler {
	
	CANTalon cycle;

	public ballCycler(int CAN) {

		cycle = new CANTalon(CAN);
		

	}

	//runs cycler at .5 power
	public void cycleIt() {


		cycle.set(.5);

	}

	//breathe
	//set cycle power to a set number
	public void cycleSpeed(double spped) {

		cycle.set(spped);
		

	}

	//stops cycler
	public void stop() {

		cycle.set(0.0);

	}

}
