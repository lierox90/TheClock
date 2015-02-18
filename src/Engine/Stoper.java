package Engine;

public class Stoper implements Runnable
{
	private int centyseconds = 0;
	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;
	private boolean running = false;

	@Override
	public void run()
	{
		while(true)
		{	
			if(isRunning())
			{
				incrementTime();
			}
			try 
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private void incrementTime()
	{
		this.centyseconds++;
		if(this.centyseconds == 100)
		{ 
			this.centyseconds = 0; 
			this.seconds++;
		}
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
	
	public int getHours()
	{
		return this.hours;
	}
	
	public int getMinutes()
	{
		return this.minutes;
	}
	
	public int getSeconds()
	{
		return this.seconds;
	}
	
	public int getCentysecunds()
	{
		return this.centyseconds;
	}
	
	public void start() 
	{
		this.running = true;
	}

	public void stop() 
	{
		this.running = false;
	}
	
	public boolean isRunning()
	{
		return this.running;
	}
	
	public void reset()
	{
		stop();
		centyseconds = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
	}
}

