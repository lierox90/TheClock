package Engine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Clock implements Runnable
{
	private int hours, minutes, seconds;

	private SimpleDateFormat formatter;
	private Date currentDate;
	private boolean twentyTOtwelve = true;
	
	

	@Override
	public void run() 
	{		
		seconds++;
		if(seconds == 60){ 
	      seconds = 0; 
	      minutes++; 
		  if(minutes == 60){ 
	        minutes = 0;
	        hours++;		
		  }	
	      if(hours == 24){ 
	        hours = 0;
	      }		 
		}
	}
	
	private void getTime()
	{
		formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.getDefault());
		
		formatter.applyPattern("s");
	    try{
	      seconds = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      seconds = 25;
	    }
	    formatter.applyPattern("m");
	    try{
	      minutes = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      minutes = 10;
	    }    
	    formatter.applyPattern("h");
	    try{
	      hours = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      hours = 21;
	    }  
	}
}
