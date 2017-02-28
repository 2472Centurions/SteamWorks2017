package Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import Objects.Action;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import Constants.Const;

public class Flywheel {

	CANTalon flywheelMotor;

	public Flywheel(int number) {

		flywheelMotor = new CANTalon(number);
		flywheelMotor.setFeedbackDevice(FeedbackDevice.EncRising);
		flywheelMotor.reverseSensor(false);
		flywheelMotor.configNominalOutputVoltage(0.0f, -0.0f);
		flywheelMotor.configPeakOutputVoltage(12.0f, -12.0f);
		flywheelMotor.setProfile(0);
		flywheelMotor.setF(.1097);
		flywheelMotor.setP(.22);
		flywheelMotor.setI(0);

		flywheelMotor.setD(0);
		flywheelMotor.set(0.0);
	}

	public void flywhlGo(double s) {
		flywheelMotor.changeControlMode(TalonControlMode.Speed);
		flywheelMotor.set(6000);
		System.out.println(flywheelMotor.getError());

	}


	public void flywhlStop() {
		flywheelMotor.set(0.0);
	}
}
