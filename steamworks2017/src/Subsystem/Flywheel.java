package Subsystem;

import com.ctre.CANTalon;

import Constants.Const;

public class Flywheel{
	
	static CANTalon flywheelMotor;
	
    public Flywheel(){
    	
    	flywheelMotor = new CANTalon(Const.FWheel);
    	
    }
    
    public static void flywhlGo(double spped){
    	
    	flywheelMotor.set(spped);
    	
    }
    
    public static void flywhlStop(){
    	flywheelMotor.set(0.0);
    	
    }
}
