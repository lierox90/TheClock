package Engine;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class ClockDate implements Runnable
{
	private int year, month, day; 
	private SimpleDateFormat formatter;
	private Date currentDate;
	

	public ClockDate()
	{
		currentDate = new Date();
	}
	
	public ClockDate getInstance()
	{
		return this;
	}
	
	private int checkMonths()
	{
		switch (this.month)
    	{
        	case 1:
        	{	
        		return 0;
        	}
        	case 2:
    		{
    			return 2;
    		}
        	case 3:
    		{
    			return 0;
    		}
        	case 4:
    		{
    			return 1;
    		}
        	case 5:
        	{	
        		return 0;
        	}
        	case 6:
    		{
    			return 1;
    		}
        	case 7:
    		{
    			return 0;
    		}
        	case 8:
    		{
    			return 0;
    		}
        	case 9:
        	{	
        		return 1;
        	}
        	case 10:
    		{
    			return 0;
    		}
        	case 11:
    		{
    			return 1;
    		}
        	case 12:
    		{
    			return 0;
    		}	
        	default:
    		{
    			return -1;
    		}
    	}
	}
	
	private void incrementTime()
	{
		this.day++;
		if(checkMonths() == 0)
		{ 
			if(this.day == 32)
			{
				this.day=1;
				this.month++;
			}
		}
		if(checkMonths() == 1)
		{ 
			if(this.day == 31)
			{
				this.day=1;
				this.month++;
			}		
		}
		if(checkMonths() == 2)
		{ 
			this.day=1;
			this.month++;	
		}	
	    if(this.month == 13)
	    { 
	    	  this.month = 1;
	    	  this.year++;
	    }
	}
	
	public int getDays()
	{
		return this.day;
	}
	
	public int getMonths()
	{
		return this.month;
	}
	
	public int getYears()
	{
		return this.year;
	}
	
	private void getDate()
	{
		formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.getDefault());
		
		formatter.applyPattern("dd");
	    try
	    {
	    	day = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	day = 23;
	    }
	    
	    formatter.applyPattern("MMM");
	    try
	    {
	    	month = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	month = 10;
	    }
	    
	    formatter.applyPattern("yyyy");
	    try
	    {
	    	year = Integer.parseInt(formatter.format(currentDate));
	    } 
	    catch (NumberFormatException n)
	    {
	    	year = 2015;
	    }  
	}
	
	@Override
	public void run()
	{
		getDate();
		while(true)
		{
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
