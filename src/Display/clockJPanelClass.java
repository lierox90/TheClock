package Display;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Engine.Clock;

public class clockJPanelClass extends JPanel implements Runnable
{
	private GUI parent;
	private Clock soloClock;
	private JButton buttonA;
	private JButton buttonB;
	private JLabel hoursLabel;
	private JLabel minutesLabel;
	private JLabel secondsLabel;
	private boolean settingMode = false;
	private int clockSettingsPosition = 0;
	private int updateCounter = 0;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Timer pressTimer = new Timer(500, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			settingMode = true;
			pressTimer.stop();
	    }
	});
	private Timer blinkTimer = new Timer(1000, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			switch (clockSettingsPosition)
	    	{
	        	case 0:
	        	{	
	        		if(secondsLabel.isVisible())
		        	{
	        			secondsLabel.setVisible(false);
		        	}
	        		else
	        		{
	        			secondsLabel.setVisible(true);
	        		}
	        		break;
	        	}
	        	case 1:
	    		{
	        		if(!secondsLabel.isVisible())
		        	{
	        			secondsLabel.setVisible(true);
		        	}
	        		if(minutesLabel.isVisible())
		        	{
	        			minutesLabel.setVisible(false);
		        	}
	        		else
	        		{
	        			minutesLabel.setVisible(true);
	        		}
	    			break;
	    		}
	        	case 2:
	    		{
	        		if(!minutesLabel.isVisible())
		        	{
	        			minutesLabel.setVisible(true);
		        	}
	        		if(hoursLabel.isVisible())
		        	{
	        			hoursLabel.setVisible(false);
		        	}
	        		else
	        		{
	        			hoursLabel.setVisible(true);
	        		}
	    			break;
	    		}
	        	default:
	    		{
	    			if(!secondsLabel.isVisible())
		        	{
	        			secondsLabel.setVisible(true);
		        	}
	    			if(!minutesLabel.isVisible())
		        	{
	        			minutesLabel.setVisible(true);
		        	}
	    			if(!hoursLabel.isVisible())
		        	{
	        			hoursLabel.setVisible(true);
		        	}
	    			settingMode = false;
	    			clockSettingsPosition=0;
	    			blinkTimer.stop();
	    			break;
	    		}
	    	}
	    }
	});
	
	public clockJPanelClass(GUI gui,Clock clock)
	{
        this.setLayout(null);
		this.parent = gui;
		this.soloClock = clock;
		this.setBounds(0, 0, 294, 171);
		this.setBackground(new Color(123,153,23));
		//Button A
		this.buttonA = new JButton("A");
		this.buttonA.setBounds(224, 10, 60, 40);
		this.buttonA.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
            	if(!settingMode)
            	{
            		parent.swapSelectedPanel();
            	}
            	else
            	{
            		increaseSelectedByOne();
            	}
            }
        });
		this.buttonA.setFocusable(false);
		this.add(buttonA);
		//Button B
		this.buttonB = new JButton("B");
		this.buttonB.setBounds(224, 121, 60, 40);
		this.buttonB.addMouseListener(new MouseAdapter() 
		{
			@Override
		    public void mousePressed(MouseEvent e) 
			{
            	if(!settingMode)
            	{
            		pressTimer.start();
            	}
		    }
		 
		    @Override
		    public void mouseReleased(MouseEvent e) 
		    {
		    	pressTimer.stop();
		    }
		});
		this.buttonB.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
            	if(settingMode)
            	{
            		clockSettingsPosition++;
            		System.out.println("CSP - "+clockSettingsPosition);
            	}
            }
        });
		this.buttonB.setFocusable(false);
		this.add(buttonB);
		//Hours Label
		hoursLabel = new JLabel();
		hoursLabel.setBounds(10, 10, 60, 60);
		this.add(hoursLabel);
		//Minutes Label
		minutesLabel = new JLabel();
		minutesLabel.setBounds(80, 10, 60, 60);
		this.add(minutesLabel);
		//Seconds Label
		secondsLabel = new JLabel();
		secondsLabel.setBounds(130, 10, 60, 60);
		this.add(secondsLabel);
	}

	private void increaseSelectedByOne()
	{
		switch (clockSettingsPosition)
    	{
        	case 0:
        	{	
        		soloClock.incSeconds();
        		break;
        	}
        	case 1:
    		{
    			soloClock.incMinutes();
    			break;
    		}
        	case 2:
    		{
    			soloClock.incHours();
    			break;
    		}
    	}
	}

	@Override
	public void run()
	{
		while(true)
		{
			if(settingMode)
			{
				blinkTimer.start();
			}
			hoursLabel.setText(Integer.toString(soloClock.getHours()));
			minutesLabel.setText(Integer.toString(soloClock.getMinutes()));
			secondsLabel.setText(Integer.toString(soloClock.getSeconds()));
		}
	}
}
