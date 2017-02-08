package Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import Objects.Action;
import Constants.Const;

public class Flywheel {

	CANTalon flywheelMotor;

	public Flywheel(int number) {

		flywheelMotor = new CANTalon(number);
		//flywheelMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

	}

	public void flywhlGo(double spped) {

		flywheelMotor.set(spped);

	}

	public void flywhlStop() {
		flywheelMotor.set(0.0);
	}
}
