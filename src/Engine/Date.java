package Engine;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

public class Date implements Runnable
{
	private int year, month, day; 
	private Calendar calendar;
	private SimpleDateFormat formatter;
	private Formatter fmt;
	

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
	public String getFullDate(){
		formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.getDefault());
	  }

}
