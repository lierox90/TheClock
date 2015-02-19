package Engine;

import Display.WindowShaker;

public class Device implements Runnable
{
	private static Alarm alarm;
	private static Clock firstClock;
	private static Clock secondClock;
	private static Stoper stoper;
	private static ClockDate date;
	private Thread AlarmThread;
	private Thread FirstClockThread;
	private Thread SecondClockThread;
	private Thread StoperThread;
	private Thread DateThread;
	
	
	public Device()
	{
		firstClock = new Clock(this);
		alarm = new Alarm(firstClock);
		stoper = new Stoper();
		date = new ClockDate();
		AlarmThread = new Thread(alarm);
		FirstClockThread = new Thread(firstClock);
		SecondClockThread = new Thread(secondClock);
		StoperThread = new Thread(stoper);
		DateThread = new Thread(date);
	}
	
	public void increaseDayDueToHours()
	{
		date.incrementTime();
	}

	public Clock getClock()
	{
		return Device.firstClock;
	}
	
	public ClockDate getDate()
	{
		return Device.date;
	}
	
	public Stoper getStoper()
	{
		return Device.stoper;
	}
	
	public Alarm getAlarm()
	{
		return Device.alarm;
	}
	
	public void isAlarmActive()
	{
		Device.alarm.isAlarmActive();
	}
	
	public void stopAlarm()
	{
		Device.alarm.stopAlarm();
	}
	
	public void passShaker(WindowShaker shaker)
	{
		Device.alarm.passShaker(shaker);
	}

	@Override
	public void run()
	{
		AlarmThread.start();
		FirstClockThread.start();
		//SecondClockThread.start();
		StoperThread.start();
		DateThread.start();
	}
}
