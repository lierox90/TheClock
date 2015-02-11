package Engine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Clock implements Runnable
{
	private int hours, minutes, seconds;
	private SimpleDateFormat formatter;
	private Date currentDate;
	private boolean twentyOrTwelve = true;

	public Clock()
	{
		currentDate = new Date();
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
	
	public int getSeconds()
	{
		return this.seconds;
	}
	
	public int getMinuts()
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
		formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.getDefault());
		
		formatter.applyPattern("s");
	    try
	    {
	    	seconds = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	seconds = 25;
	    }
	    formatter.applyPattern("m");
	    try
	    {
	    	minutes = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	minutes = 10;
	    }    
	    formatter.applyPattern("h");
	    try
	    {
	    	hours = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	hours = 21;
	    }  
	}
}
