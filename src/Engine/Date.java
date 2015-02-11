package Engine;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class Date implements Runnable
{
	private int year, month, day; 
	private SimpleDateFormat formatter;
	private Date currentDate;

	

	@Override
	public void run()
	{
		formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.getDefault());
		
		formatter.applyPattern("d");
	    try{
	      day = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      day = 23;
	    }
	    formatter.applyPattern("m");
	    try{
	      month = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      month = 10;
	    }    
	    formatter.applyPattern("y");
	    try{
	      year = Integer.parseInt(formatter.format(currentDate));
	    } catch (NumberFormatException n){
	      year = 2015;
	    }  
		
	}
}
