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
	private clockJPanelClass clockPane;
	private twoClocksJPanelClass twoClockPane;
	private dateJPanelClass datePane;
	private stoperJPanelClass stoperPane;
	private int currentSelectedPanel;
	private Device device;
	
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
    	alarmPane = new alarmJPanelClass(this);
    	clockPane = new clockJPanelClass(this,device.getSoloClock());
    	twoClockPane = new twoClocksJPanelClass(this);
    	datePane = new dateJPanelClass(this);
    	stoperPane = new stoperJPanelClass(this);
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
    	System.out.println("currentSelectedPanel = "+currentSelectedPanel);
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
		while(true)
		{
			//swapSelectedPanel();
			repaint();
			try 
			{
				Thread.sleep(2000);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
