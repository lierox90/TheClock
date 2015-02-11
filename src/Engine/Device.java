package Engine;

public class Device implements Runnable
{
	private static Alarm alarm;
	private static Clock firstClock;
	private static Clock secondClock;
	private static Stoper stoper;
	private static Date date;
	private Thread AlarmThread;
	private Thread FirstClockThread;
	private Thread SecondClockThread;
	private Thread StoperThread;
	private Thread DateThread;
	
	
	public Device()
	{
		alarm = new Alarm();
		firstClock = new Clock();
		secondClock = new Clock();
		stoper = new Stoper();
		date = new Date();
		AlarmThread = new Thread(alarm);
		FirstClockThread = new Thread(firstClock);
		SecondClockThread = new Thread(secondClock);
		StoperThread = new Thread(stoper);
		DateThread = new Thread(date);
	}

	@Override
	public void run()
	{
		AlarmThread.start();
		FirstClockThread.start();
		SecondClockThread.start();
		StoperThread.start();
		DateThread.start();
	}

}
