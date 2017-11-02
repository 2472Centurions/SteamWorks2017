package org.usfirst.frc.team2472.robot;

public class BoxInfo {
	String x,y,w,h;
	public BoxInfo(String xx, String yy, String ww, String hh){
		x = xx;
		y = yy;
		h = hh;
		w = ww;
	
	}
	public BoxInfo(String xx, String yy){
		x = xx;
		y = yy;
		
	}
	public int getX(){
		return Integer.parseInt(x);
	}
	public int getY(){
		return Integer.parseInt(y);
	}
	public int getWidth(){
		return Integer.parseInt(w);
	}
	public int getHeight(){
		return Integer.parseInt(h);
	}

}

