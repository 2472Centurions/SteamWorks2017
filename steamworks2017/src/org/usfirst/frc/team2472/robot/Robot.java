package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;

import Constants.Const;
import Objects.Action;
import Subsystem.Flywheel;
import Subsystem.drive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Steamworks2017

public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	Flywheel flywhl= new  Flywheel();
	
	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> stepsecondary = new ArrayList<Action>();

	
	Joystick gamepadController = new Joystick (Const.gpad);
	Joystick joyl = new Joystick(Const.joyl);
	Joystick joyr = new Joystick(Const.joyr);
	public static drive d = new drive();
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	@Override
	public void teleopPeriodic() {
		d.tankDrive(joyl, joyr);
		if(gamepadController.getRawButton(1)) {
    		
    		Flywheel.flywhlGo(1.0);
    		
    	}
    	
    	else {
    		
    		Flywheel.flywhlStop();		
    	}
	}
	
	@Override
	public void testPeriodic() {
	}
}

