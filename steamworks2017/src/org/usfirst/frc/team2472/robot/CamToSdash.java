package org.usfirst.frc.team2472.robot;



import edu.wpi.first.wpilibj.CameraServer;

public class CamToSdash {
	
	public void addCamera(){
		
		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().putVideo("Rectangle", 640, 480);
		
	}
}
