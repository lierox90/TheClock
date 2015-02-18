package Engine;

public class Alarm implements Runnable
{
	private int hours = 0, minutes = 0, seconds = 0;
	private boolean isOn = false;
	private boolean isSound = false;
	private Clock soloClock;
	
	public Alarm(Clock clock)
	{
		this.soloClock = clock;
	}
	
	public void incSeconds()
	{
		this.seconds++;
		if(this.seconds == 60)
		{ 
			this.seconds = 0;
		}
	}
	
	public void incMinutes()
	{
		this.minutes++;
		if(this.minutes == 60)
		{ 
			this.minutes = 0;
		}
	}
	
	public void incHours()
	{
		this.hours++;
		if(this.hours == 24)
	    { 
	    	  this.hours = 0;
	    }
	}
	
	public void setAlarmOn()
	{
		this.isOn = true;
	}
	
	public void setAlarmOff()
	{
		this.isOn = false;
	}
	
	public boolean isAlarmOn()
	{
		return this.isOn;
	}
	
	public void setSoundOn()
	{
		this.isOn = true;
	}
	
	public void setSoundOff()
	{
		this.isOn = false;
	}
	
	public boolean isSoundOn()
	{
		return this.isSound;
	}
	
	@Override
	public void run()
	{
		
	}
}