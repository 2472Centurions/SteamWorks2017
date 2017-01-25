package Subsystem;

import com.ctre.CANTalon;

import Constants.Const;
import Objects.Action;

public class Intake extends Action {
    CANTalon intakeMotor;
	
    public Intake(int stillMaybeAnumber){
    	
    	intakeMotor = new CANTalon(stillMaybeAnumber);
    	
    }
    
    public void intakeGo(double spped){
    	
    	intakeMotor.set(spped);
    	
    }
    
    public  void intakeStop(){
    	intakeMotor.set(0.0);
    }
}
