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
		
		flywheelMotor.reverseSensor(false);
		flywheelMotor.configNominalOutputVoltage(+0.0f,-0.0f);
		flywheelMotor.configPeakOutputVoltage(12.0f, -12.0f);
		flywheelMotor.setProfile(0);
		flywheelMotor.setF(.1097);
		flywheelMotor.setP(.22);
		flywheelMotor.setI(0);
		flywheelMotor.setD(0);		
		flywheelMotor.set(0.0);
		
		

	}

	public void flywhlGo(double spped) {
		System.out.println(flywheelMotor.getEncVelocity());
		//SmartDashboard.putDouble("speed of talon",flywheelMotor.getSpeed());
		flywheelMotor.changeControlMode(TalonControlMode.Speed);
		flywheelMotor.set(spped*600);
		

	}

	public void flywhlStop() {
		flywheelMotor.set(0.0);
	}
}
