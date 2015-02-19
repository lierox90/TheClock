package Engine;

public class Alarm implements Runnable
{
	private int hours = 0, minutes = 0;
	private int soundDurationCounter=0;
	private boolean isOn = false;
	private boolean isSound = false;
	private boolean startAlarm = false;
	private boolean amMode = true;
	private Clock soloClock;
	
	public Alarm(Clock clock)
	{
		this.soloClock = clock;
	}
	
	public void incMinutes()
	{
		this.minutes++;
		if(this.minutes == 60)
		{ 
			this.minutes = 0;
		}
		setAlarmOn();
		setSoundOn();
	}
	
	public void incHours()
	{
		this.hours++;
		if(this.hours == 24)
	    { 
	    	  this.hours = 0;
	    }
		setAlarmOn();
		setSoundOn();
	}
	
	public boolean getAlarmMode()
	{
		return this.amMode;
	}
	
	public int getMinutes()
	{
		return this.minutes;
	}
	
	public int getHours()
	{
		return this.hours;
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
	
	private void checkTime()
	{
		if(checkHours())
		{
			if(checkMinutes())
			{
				startAlarm = true;
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
			if(soloClock.isInitialised())
			{
				checkTime();
			}
			if(!soloClock.getHourFormat())
			{
				if(this.hours<12)
				{
					this.amMode = true;
				}
				else
				{
					this.amMode = false;
				}
			}
			if(startAlarm)
			{
				if(soundDurationCounter%10 == 0)
				{
					if(isSoundOn())
					{
						//play sound
						System.out.println("Sound");
					}
					
					if(isAlarmOn())
					{
						//popup alarm
						System.out.println("Popup");
					}
				}
				soundDurationCounter++;
				if(soundDurationCounter > 600)
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