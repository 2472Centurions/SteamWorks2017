package Subsystem;

import com.ctre.CANTalon;

import Constants.Const;
import Objects.Action;

public class Intake{
    CANTalon intakeMotor;
	
    public Intake(int stillMaybeAnumber){
    	
    	intakeMotor = new CANTalon(stillMaybeAnumber);
    	
    }
    //Runs intake a desired power
    public void intakeGo(double spped){
    	
    	intakeMotor.set(spped);
    	
    }
    //Stops intake motor
    public  void intakeStop(){
    	intakeMotor.set(0.0);
    }
}
