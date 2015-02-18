package Engine;

public class Alarm implements Runnable
{
	private int hours = 0, minutes = 0, seconds = 0;
	private int soundDurationCounter=0;
	private boolean isOn = false;
	private boolean isSound = false;
	private boolean startAlarm = false;
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
		setAlarmOn();
	}
	
	public void incMinutes()
	{
		this.minutes++;
		if(this.minutes == 60)
		{ 
			this.minutes = 0;
		}
		setAlarmOn();
	}
	
	public void incHours()
	{
		this.hours++;
		if(this.hours == 24)
	    { 
	    	  this.hours = 0;
	    }
		setAlarmOn();
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
	
	private boolean checkHours()
	{
		if(this.hours == soloClock.getHours())
		{
			return true;
		}
		return false;
	}
	
	private boolean checkMinutes()
	{
		if(this.minutes == soloClock.getMinutes())
		{
			return true;
		}
		return false;
	}
	
	private boolean checkSeconds()
	{
		if(this.seconds == soloClock.getSeconds())
		{
			return true;
		}
		return false;
	}
	
	private void checkTime()
	{
		if(checkHours())
		{
			if(checkMinutes())
			{
				if(checkSeconds())
				{
					startAlarm = true;
				}
			}
		}
	}
	
	public boolean isAlarmActive()
	{
		return this.startAlarm;
	}
	
	public void stopAlarm()
	{
		startAlarm = false;
		soundDurationCounter = 0;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			checkTime();
			if(startAlarm)
			{
				//play sound
				System.out.println("SOUND");
				soundDurationCounter++;
				if(soundDurationCounter > 100)
				{
					startAlarm = false;
					soundDurationCounter = 0;
				}
			}
			try 
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}