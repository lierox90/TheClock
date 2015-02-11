package Engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class Date implements Runnable
{
	
	private Calendar calendar;
	private SimpleDateFormat formatter;
	private Date currentDate;
	private Formatter fmt;

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
	public String getFullDate(){
	    fmt = new Formatter();
		calendar = Calendar.getInstance();
		return fmt.format("%1$tA %1$td %1$tB %1$tY",calendar).toString();
	  }

}
