package Engine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock implements Runnable
{
	private int hours, minutes, seconds;
	private DateFormat formatter;
	private Date currentDate;
	private boolean twentyOrTwelve = true;

	public Clock()
	{
		currentDate = new Date();
	}
	
	public Clock getInstance()
	{
		return this;
	}
	
	@Override
	public void run() 
	{	
		getTime();
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
	public void incSeconds()
	{
		this.seconds++;
		if(this.seconds == 60)
		{ 
			this.seconds = 0; 
			this.minutes++;
			if(this.minutes == 60)
			{ 
				this.minutes = 0;
				this.hours++;
				if(this.hours == 24)
				{ 
					this.hours = 0;
				}
			}
		}
	}
	
	public void incMinutes()
	{
		this.minutes++;
		if(this.minutes == 60)
		{ 
			this.minutes = 0;
			this.hours++;
			if(this.hours == 24)
			{ 
				this.hours = 0;
			}
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
	
	public boolean getMode()
	{
		return this.twentyOrTwelve;
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
	
	public void set24()
	{
		this.twentyOrTwelve = true;
	}
	
	public void set12()
	{
		this.twentyOrTwelve = false;
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
	
	private void getTime()
	{
		System.out.println();

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
}
