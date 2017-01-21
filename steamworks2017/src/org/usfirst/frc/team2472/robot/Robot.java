package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;

import Actions.goFoward;
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
	ArrayList<Action> stepSecondary = new ArrayList<Action>();
	int currentAction = 0;
	
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
		
		
		//autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		//System.out.println("Auto selected: " + autoSelected);
		if (step.size() > 0) {

			currentAction = 0;

			step.get(currentAction).startAction();

			stepSecondary.get(currentAction).startAction();

		}
		//currentAction = 0;
		//step.get(currentAction).startAction();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	
	
	@Override
	public void autonomousPeriodic() {

		if (step.size() > 0 && step.get(currentAction) != null) {

			if (!step.get(currentAction).isFinished()) {
				step.get(currentAction).periodic();
			}

			if (!stepSecondary.get(currentAction).isFinished()) {
				stepSecondary.get(currentAction).periodic();
			}

			if (step.get(currentAction).isFinished() && stepSecondary.get(currentAction).isFinished()) {

				currentAction++;

				if (step.get(currentAction) != null) {

					step.get(currentAction).startAction();
					stepSecondary.get(currentAction).startAction();

				}

			}

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

