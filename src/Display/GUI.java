package Display;

import java.io.IOException;

import javax.swing.JFrame;

import Engine.*;

public class GUI extends JFrame implements Runnable
{
	//Inits
	private int width=300;
	private int heigh=200;
	private alarmJPanelClass alarmPane;
	private Thread alarmPaneThread;
	private clockJPanelClass clockPane;
	private Thread clockPaneThread;
	private twoClocksJPanelClass twoClockPane;
	private Thread twoClockPaneThread;
	private dateJPanelClass datePane;
	private Thread datePaneThread;
	private stoperJPanelClass stoperPane;
	private Thread stoperPaneThread;
	private int currentSelectedPanel;
	private Device device;
	private WindowShaker shaker;
	private Thread shakerThread;
	
	//Constructor
    public GUI(Device clock) throws IOException 
    {
    	//Window init
        super("TheClock");
        setSize(width, heigh);
        setResizable( false );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        device = clock;
        //Var init
        currentSelectedPanel = 0;
        //Used Panels Init
    	alarmPane = new alarmJPanelClass(this,device.getClock(),device.getAlarm());
    	clockPane = new clockJPanelClass(this,device.getClock());
    	twoClockPane = new twoClocksJPanelClass(this,device.getClock(),device.get2ndClock());
    	datePane = new dateJPanelClass(this,device.getDate());
    	stoperPane = new stoperJPanelClass(this,device.getStoper());
    	//Shaker
    	shaker = new WindowShaker(this);
    	//Threads
    	clockPaneThread = new Thread(clockPane);
    	datePaneThread = new Thread(datePane);
    	stoperPaneThread = new Thread(stoperPane);
    	alarmPaneThread = new Thread(alarmPane);
    	twoClockPaneThread = new Thread(twoClockPane);
    	shakerThread = new Thread(shaker);
    	//Init visibility
    	alarmPane.setVisible(false);
    	clockPane.setVisible(true);
    	twoClockPane.setVisible(false);
    	datePane.setVisible(false);
    	stoperPane.setVisible(false);
    	//Init addition
    	this.add(clockPane);
    	this.add(datePane);
    	this.add(stoperPane);
    	this.add(alarmPane);
    	this.add(twoClockPane);
    }
    
    public void swapSelectedPanel()
    {
    	currentSelectedPanel++;
    	if(currentSelectedPanel == 5)
    	{
    		currentSelectedPanel = 0;
    	}
    	switch (currentSelectedPanel)
    	{
        	case 0:
        	{
        		alarmPane.setVisible(false);
            	clockPane.setVisible(true);
            	twoClockPane.setVisible(false);
            	datePane.setVisible(false);
            	stoperPane.setVisible(false);
        		break;
        	}
        	case 1:
    		{
        		alarmPane.setVisible(false);
            	clockPane.setVisible(false);
            	twoClockPane.setVisible(false);
            	datePane.setVisible(true);
            	stoperPane.setVisible(false);
    			break;
    		}
        	case 2:
    		{
        		alarmPane.setVisible(false);
            	clockPane.setVisible(false);
            	twoClockPane.setVisible(false);
            	datePane.setVisible(false);
            	stoperPane.setVisible(true);
    			break;
    		}
        	case 3:
    		{
        		alarmPane.setVisible(true);
            	clockPane.setVisible(false);
            	twoClockPane.setVisible(false);
            	datePane.setVisible(false);
            	stoperPane.setVisible(false);
    			break;
    		}
        	case 4:
    		{
        		alarmPane.setVisible(false);
            	clockPane.setVisible(false);
            	twoClockPane.setVisible(true);
            	datePane.setVisible(false);
            	stoperPane.setVisible(false);
    			break;
    		}
        	default:
    		{
    			break;
    		}
    	}
    }

	@Override
	public void run()
	{
		clockPaneThread.start();
		datePaneThread.start();
		stoperPaneThread.start();
		alarmPaneThread.start();
		shakerThread.start();
		twoClockPaneThread.start();
		device.passShaker(shaker);
		while(true)
		{
			repaint();
			try 
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
