package Engine;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class Date implements Runnable
{
	private int year, month, day; 
	private SimpleDateFormat formatter;
	private Date currentDate;
	

	public Date()
	{
		currentDate = new Date();
	}
	
	public Date getInstance()
	{
		return this;
	}
	
	@Override
	public void run()
	{
		
		
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
	    try{
	      day = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      day = 23;
	    }
	    formatter.applyPattern("MMM");
	    try{
	      month = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      month = 10;
	    }    
	    formatter.applyPattern("yyyy");
	    try{
	      year = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      year = 2015;
	    }  
	}
	
}
