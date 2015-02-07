package Engine;

import java.util.Calendar;

public class Clock
{
	public String showTime(){
		Calendar curTime = Calendar.getInstance();
		curTime.setTimeInMillis(System.currentTimeMillis());
		double hour = curTime.get(Calendar.HOUR_OF_DAY);
		double min = curTime.get(Calendar.MINUTE);
		double sec = curTime.get(Calendar.SECOND);
    
		String time = hour + ":" + min + ":" + sec;
		return time;
	}
	
	/*public change24to12(){
		if(hour >= 12.0)
			hour -= 12;
	}*/
}
