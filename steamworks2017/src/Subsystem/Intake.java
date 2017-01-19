package Subsystem;

import com.ctre.CANTalon;

import Constants.Const;
import Objects.Action;

public class Intake extends Action {
static CANTalon intakeMotor;
	
    public Intake(){
    	
    	intakeMotor = new CANTalon(Const.Intake);
    	
    }
    
    public static void intakeGo(double spped){
    	
    	intakeMotor.set(spped);
    	
    }
    
    public static void intakeStop(){
    	intakeMotor.set(0.0);
    }
}
