package Objects;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Action{
	private long startTime,endTime;
	
	public double timeout = 0;
	
	public void startAction(){
		
		startTime = System.currentTimeMillis();
		
		endTime = startTime + (long)timeout*1000;
		
	}
	
	private void endAction(){
		
	}
	
	public boolean isFinished(){
		
		if(isTimedOut()){
			
			endAction();
			
			return true;

		}else{
			
			return false;
			
		}
		
	}
	
	protected boolean isTimedOut(){
		
		return endTime <= System.currentTimeMillis();
		
	}

	public void periodic(){
		
	}
	
	//protected void setTimeOut(double timeoutvalue){
		
		//startTime = System.currentTimeMillis();
		
		//endTime = startTime + (long)timeoutvalue*1000;
		
		//SmartDashboard.putNumber("Action Timeout Time", endTime);
		
	//}

}
