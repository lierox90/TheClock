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
	private JLabel amLabel;
	private JLabel pmLabel;
	private boolean settingMode = false;
	private boolean amMode = true;
	private int clockSettingsPosition = 0;
	private int clockModePosition = 0;
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
	        	case 3:
	    		{
	    			if(!hoursLabel.isVisible())
		        	{
	        			hoursLabel.setVisible(true);
		        	}
	    			if(soloClock.getHourFormat())
	    			{
	    				pmLabel.setVisible(false);
    					amLabel.setVisible(false);
	    			}
	    			else
	    			{
	    				if(amMode)
	    				{
	    					pmLabel.setVisible(false);
	    					if(amLabel.isVisible())
	    					{
	    						amLabel.setVisible(false);
	    					}
	    					else
	    					{
	    						amLabel.setVisible(true);
	    					}
	    				}
	    				else
	    				{
	    					amLabel.setVisible(false);
	    					if(pmLabel.isVisible())
	    					{
	    						pmLabel.setVisible(false);
	    					}
	    					else
	    					{
	    						pmLabel.setVisible(true);
	    					}
	    				}
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
	    			if(!soloClock.getHourFormat())
	    			{
	    				if(amMode)
	    				{
	    					amLabel.setVisible(true);
	    					pmLabel.setVisible(false);
	    				}
	    				else
	    				{
	    					pmLabel.setVisible(true);
	    					amLabel.setVisible(false);
	    				}
	    			}
	    			else
	    			{
	    				pmLabel.setVisible(false);
    					amLabel.setVisible(false);
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
            		if(clockSettingsPosition == 3)
            		{
            			clockModePosition++;
            			if(clockModePosition == 3)
            			{
            				clockModePosition = 0;
            			}
            		}
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
		//AM Label
		amLabel = new JLabel("AM");
		amLabel.setBounds(130, 50, 60, 60);
		amLabel.setVisible(false);
		this.add(amLabel);
		//PM Label
		pmLabel = new JLabel("PM");
		pmLabel.setBounds(130, 70, 60, 60);
		pmLabel.setVisible(false);
		this.add(pmLabel);
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
        	case 3:
    		{
    			switch (clockModePosition)
    	    	{
    	        	case 0:
    	        	{	
    	        		soloClock.set24();
    	        		break;
    	        	}
    	        	case 1:
    	    		{
    	    			amMode = true;
    	    			soloClock.set12();
    	    			break;
    	    		}
    	        	case 2:
    	    		{
    	    			amMode = false;
        	    		soloClock.set12();
    	    			break;
    	    		}
    	    	}
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
			if(soloClock.getMode())
			{
				hoursLabel.setText(Integer.toString(soloClock.getHours()));
			}
			else
			{
				hoursLabel.setText(Integer.toString(soloClock.getHours()-12));
			}
			minutesLabel.setText(Integer.toString(soloClock.getMinutes()));
			secondsLabel.setText(Integer.toString(soloClock.getSeconds()));
		}
	}
}
