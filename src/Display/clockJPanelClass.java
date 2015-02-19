package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Engine.Clock;
import Engine.Device;

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
	private BufferedImage onAlarmMarker;
	private BufferedImage offAlarmMarker;
	private BufferedImage onSoundMarker;
	private BufferedImage offSoundMarker;
	private JLabel alarmLabel;
	private JLabel soundLabel;
	private boolean settingMode = false;
	private boolean amMode = true;
	private boolean doOnce = true;
	private int clockSettingsPosition = 0;
	private int clockModePosition = 0;
	private Timer pressTimer = new Timer(500, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			settingMode = true;
			doOnce=true;
			pressTimer.stop();
	    }
	});
	private Timer blinkTimer = new Timer(500, new ActionListener() 
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
    					amLabel.setText("");
	    			}
	    			else
	    			{
	    				if(amMode)
	    				{
	    					amLabel.setText("AM");
	    				}
	    				else
	    				{
	    					amLabel.setText("PM");
	    				}
	    			}
 					if(amLabel.isVisible())
					{
						amLabel.setVisible(false);
					}
					else
					{
						amLabel.setVisible(true);
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
	    			if(!amLabel.isVisible())
		        	{
	    				amLabel.setVisible(true);
		        	}
	    			if(!soloClock.getHourFormat())
	    			{
	    				if(amMode)
	    				{
	    					amLabel.setText("AM");
	    				}
	    				else
	    				{
	    					amLabel.setText("PM");
	    				}
	    			}
	    			else
	    			{
	    				amLabel.setText("");
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
        //Display Controls
		try 
	    {
			onAlarmMarker = ImageIO.read(new File("src/res/alarmOn.png"));
			offAlarmMarker = ImageIO.read(new File("src/res/alarmOff.png"));
			onSoundMarker = ImageIO.read(new File("src/res/soundOn.png"));
			offSoundMarker = ImageIO.read(new File("src/res/soundOff.png"));
	    } 
	    catch (IOException ex) 
	    {
	    }
		//Button A
		this.buttonA = new JButton("A");
		this.buttonA.setBounds(160, 121, 60, 40);
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
    		    	if(!doOnce)
    		    	{
                		clockSettingsPosition++;
    		    	}
    		    	doOnce=false;
            	}
            }
        });
		this.buttonB.setFocusable(false);
		this.add(buttonB);
		//alarm Label
		alarmLabel = new JLabel();
		alarmLabel.setBounds(10, 121, 40, 40);
		alarmLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		alarmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(alarmLabel);
		//sound Label
		soundLabel = new JLabel();
		soundLabel.setBounds(60, 121, 40, 40);
		soundLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		soundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(soundLabel);
		//Hours Label
		hoursLabel = new JLabel();
		hoursLabel.setBounds(10, 10, 90, 90);
		hoursLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		hoursLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hoursLabel.setFont(new Font("Calibri", Font.PLAIN, 70));
		this.add(hoursLabel);
		//Minutes Label
		minutesLabel = new JLabel();
		minutesLabel.setBounds(100, 10, 90, 90);
		minutesLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		minutesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minutesLabel.setFont(new Font("Calibri", Font.PLAIN, 70));
		this.add(minutesLabel);
		//Seconds Label
		secondsLabel = new JLabel();
		secondsLabel.setBounds(190, 10, 90, 90);
		secondsLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		secondsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		secondsLabel.setFont(new Font("Calibri", Font.PLAIN, 70));
		this.add(secondsLabel);
		//AM Label
		amLabel = new JLabel("");
		amLabel.setBounds(110, 121, 40, 40);
		amLabel.setVisible(true);
		amLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		amLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(amLabel);
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
    	    			soloClock.set12();
    	    			break;
    	    		}
    	    	}
    			break;
    		}
    	}
	}
	
	private void setAmOrPm()
	{
		if(!soloClock.getHourFormat())
		{
			if(soloClock.getHours()<12)
			{
				this.amMode = true;
			}
			else
			{
				this.amMode = false;
			}
		}
	}

	@Override
	public void run()
	{
		while(true)
		{
			setAmOrPm();
			if( soloClock.isAlarmOn())
			{
				alarmLabel.setIcon(new ImageIcon(onAlarmMarker));
			}
			else
			{
				alarmLabel.setIcon(new ImageIcon(offAlarmMarker));
			}
			
			if( soloClock.isAlarmOn())
			{
				soundLabel.setIcon(new ImageIcon(onSoundMarker));
			}
			else
			{
				soundLabel.setIcon(new ImageIcon(offSoundMarker));
			}
			
			if(settingMode)
			{
				blinkTimer.start();
			}
			if(soloClock.getHourFormat())
			{
				if(soloClock.getHours()<10)
				{
					hoursLabel.setText("0"+Integer.toString(soloClock.getHours()));
				}
				else
				{
					hoursLabel.setText(Integer.toString(soloClock.getHours()));
				}
			}
			else
			{
				if(soloClock.getHours() == 0)
				{
					hoursLabel.setText("12");
				}
				else
				{
					if(soloClock.getHours() == 12)
					{
						hoursLabel.setText("12");
					}
					else
					{
						if(soloClock.getHours()<12)
						{
							if(soloClock.getHours()<10)
							{
								hoursLabel.setText("0"+Integer.toString(soloClock.getHours()));
							}
							else
							{
								hoursLabel.setText(Integer.toString(soloClock.getHours()));
							}
						}
						else
						{
							if(soloClock.getHours()<22)
							{
								hoursLabel.setText("0"+Integer.toString(soloClock.getHours()-12));
							}
							else
							{
								hoursLabel.setText(Integer.toString(soloClock.getHours()-12));
							}
						}
					}
				}
			}
			
			if(soloClock.getMinutes()<10)
			{
				minutesLabel.setText("0"+Integer.toString(soloClock.getMinutes()));
			}
			else
			{
				minutesLabel.setText(Integer.toString(soloClock.getMinutes()));
			}
			
			if(soloClock.getSeconds()<10)
			{
				secondsLabel.setText("0"+Integer.toString(soloClock.getSeconds()));
			}
			else
			{
				secondsLabel.setText(Integer.toString(soloClock.getSeconds()));
			}
			
			if(soloClock.getHourFormat())
			{
				amLabel.setText("");
			}
			else
			{
				if(amMode)
				{
					amLabel.setText("AM");
				}
				else
				{
					amLabel.setText("PM");
				}
			}
		}
	}
}
