package Engine;

import java.util.Calendar;

public class Date
{
	public String showDate(){
		Calendar date = Calendar.getInstance();
		
		int year = date.get(date.YEAR);
        int month = date.get(date.MONTH);
        int day = date.get(date.DATE);
        
		String dateShow = year + " " + month + " " + day;
		
		return dateShow;
	}
}
