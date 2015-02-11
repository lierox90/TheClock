package Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import javax.swing.Timer;

public class Clock implements Runnable
{
	private int hours, minutes, seconds;
	private String today;
	private Calendar calendar;
	private Formatter fmt;
	
	

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
	
	  public String twelveHourFormat(){
	    fmt = new Formatter();
	    calendar = Calendar.getInstance();
		today = fmt.format("%tr",calendar).toString();
		return today;
	  }
	  
	  public String twentyFourthHourFormat(){
	    fmt = new Formatter();
	    calendar = Calendar.getInstance();
		today = fmt.format("%tT",calendar).toString();
		return today;
	  }
}
