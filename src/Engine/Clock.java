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
	private SimpleDateFormat formatter;
	private Date currentDate;
	
	

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
	
	  private String twelveHourFormat(){
	    fmt = new Formatter();
	    calendar = Calendar.getInstance();
		today = fmt.format("%tr",calendar).toString();
		return today;
	  }
	  
	  private String twentyFourthHourFormat(){
	    fmt = new Formatter();
	    calendar = Calendar.getInstance();
		today = fmt.format("%tT",calendar).toString();
		return today;
	  }
}
