package Subsystem;

import com.ctre.CANTalon;

import Objects.Action;
import Constants.Const;

public class Flywheel extends Action{
	
	 CANTalon flywheelMotor;
	
    public Flywheel(int number){
    	
    	flywheelMotor = new CANTalon(number);
    	
    }
    
    public void flywhlGo(double spped){
    	
    	flywheelMotor.set(spped);
    	
    }
    
    public void flywhlStop(){
    	flywheelMotor.set(0.0);
    }
}
