package Engine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondClock implements Runnable
{
	private int hours, minutes, seconds;
	private DateFormat formatter;
	private Date currentDate;
	private boolean twentyOrTwelve = true;
	private boolean initialized = false;
	private Device device;

	public SecondClock(Device overDevice)
	{
		currentDate = new Date();
		device = overDevice;
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
	
	public int getSeconds()
	{
		return this.seconds;
	}
	
	public int getMinutes()
	{
		return this.minutes;
	}
	
	public int getHours()
	{
		return this.hours;
	}
	
	public boolean getHourFormat()
	{
		return this.twentyOrTwelve;
	}
	
	private void incrementTime()
	{
		this.seconds++;
		if(this.seconds == 60)
		{ 
			this.seconds = 0; 
			this.minutes++;
		}
		if(this.minutes == 60)
		{ 
			  this.minutes = 0;
			  this.hours++;		
		}	
	    if(this.hours == 24)
	    { 
	    	  this.hours = 0;
	    }
	}
	
	public boolean isInitialised()
	{
		return this.initialized;
	}
	
	private void getTime()
	{
		try
	    {
	    	formatter = new SimpleDateFormat("ss");
	    	seconds = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	seconds = 25;
	    }
	    try
	    {
	    	formatter = new SimpleDateFormat("mm");
	    	minutes = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	minutes = 10;
	    }
	    try
	    {
	    	formatter = new SimpleDateFormat("HH");
	    	hours = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	hours = 21;
	    }  
	}
	
	@Override
	public void run() 
	{	
		getTime();
		this.initialized = true;
		while(true)
		{
			incrementTime();
			try 
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}