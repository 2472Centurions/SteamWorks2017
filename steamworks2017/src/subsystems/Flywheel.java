package subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Joystick;

public class Flywheel{
	
	static CANTalon flywheelMotor;
	
    public Flywheel(int fwm){
    	
    	flywheelMotor = new CANTalon(fwm);
    	
    }
    
    public static void flywhlGo(){
    	
    	flywheelMotor.set(1.0);
    	
    }
    
    public static void flywhlStop(){
    	
    	flywheelMotor.set(0.0);
    	
    }
}
