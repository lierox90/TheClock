package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Engine.Alarm;
import Engine.Clock;

public class alarmJPanelClass extends JPanel implements Runnable
{
	private GUI parent;
	private Alarm soloAlarm;
	private Clock soloClock;
	private JButton buttonA;
	private JButton buttonB;
	private JLabel hoursLabel;
	private JLabel minutesLabel;
	private JLabel amLabel;
	private JLabel alarmLabel;
	private JLabel soundLabel;
	private BufferedImage onAlarmMarker;
	private BufferedImage offAlarmMarker;
	private BufferedImage onSoundMarker;
	private BufferedImage offSoundMarker;
	private boolean doOnce = true;
	private boolean settingMode = false;
	private int alarmSettingsPosition = 0;
	private int alarmModePosition = 0;
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
			switch (alarmSettingsPosition)
	    	{
	        	case 0:
	    		{
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
	        	case 1:
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
	        	case 2:
	    		{
	    			if(!hoursLabel.isVisible())
		        	{
	        			hoursLabel.setVisible(true);
		        	}
	    			if(alarmLabel.isVisible())
	    			{
	    				alarmLabel.setVisible(false);
	    			}
	    			else
	    			{
	    				alarmLabel.setVisible(true);
	    			}
	    			if(soundLabel.isVisible())
	    			{
	    				soundLabel.setVisible(false);
	    			}
	    			else
	    			{
	    				soundLabel.setVisible(true);
	    			}
	    			break;
	    		}	
	        	default:
	    		{
	    			if(!minutesLabel.isVisible())
		        	{
	        			minutesLabel.setVisible(true);
		        	}
	    			if(!hoursLabel.isVisible())
		        	{
	        			hoursLabel.setVisible(true);
		        	}
	    			if(!alarmLabel.isVisible())
	    			{
	    				alarmLabel.setVisible(true);
	    			}
	    			if(!soundLabel.isVisible())
	    			{
	    				soundLabel.setVisible(true);
	    			}
	    			settingMode = false;
	    			alarmSettingsPosition=0;
	    			blinkTimer.stop();
	    			break;
	    		}
	    	}
	    }
	});
	
	public alarmJPanelClass(GUI gui,Clock clock,Alarm alarm)
	{
		this.setLayout(null);
		this.parent = gui;
		soloAlarm = alarm;
		soloClock = clock;
		this.setBounds(0, 0, 294, 171);
		this.setBackground(new Color(123,53,23));
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
		//AM Label
		amLabel = new JLabel("");
		amLabel.setBounds(110, 121, 40, 40);
		amLabel.setVisible(true);
		amLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		amLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(amLabel);
		//Button A
		this.buttonA = new JButton("A");
		this.buttonA.setBounds(160, 121, 60, 40);
		this.buttonA.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
            	if(soloAlarm.isAlarmActive())
            	{
            		soloAlarm.stopAlarm();
            	}
            	else
            	{
            		if(!settingMode)
                	{
                		parent.swapSelectedPanel();
                	}
                	else
                	{
                		if(alarmSettingsPosition == 2)
                		{
                			alarmModePosition++;
                			if(alarmModePosition == 4)
                			{
                				alarmModePosition = 0;
                			}
                		}
                		increaseSelectedByOne();
                	}
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
            	if(soloAlarm.isAlarmActive())
            	{
            		soloAlarm.stopAlarm();
            	}
            	else
            	{
                	if(!settingMode)
                	{
                		pressTimer.start();
                	}
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
            	if(soloAlarm.isAlarmActive())
            	{
            		soloAlarm.stopAlarm();
            	}
            	else
            	{
                	if(settingMode)
                	{
        		    	if(!doOnce)
        		    	{
                    		alarmSettingsPosition++;
        		    	}
        		    	doOnce=false;
                	}
            	}
            }
        });
		this.buttonB.setFocusable(false);
		this.add(buttonB);
		//Hours Label
		hoursLabel = new JLabel();
		hoursLabel.setBounds(30, 10, 90, 90);
		hoursLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		hoursLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hoursLabel.setFont(new Font("Calibri", Font.PLAIN, 70));
		this.add(hoursLabel);
		//Minutes Label
		minutesLabel = new JLabel();
		minutesLabel.setBounds(170, 10, 90, 90);
		minutesLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		minutesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minutesLabel.setFont(new Font("Calibri", Font.PLAIN, 70));
		this.add(minutesLabel);
	}
	
	private void increaseSelectedByOne()
	{
		switch (alarmSettingsPosition)
    	{
        	case 0:
    		{
    			soloAlarm.incMinutes();
    			break;
    		}
        	case 1:
    		{
    			soloAlarm.incHours();
    			break;
    		}
        	case 2:
    		{
    			switch (alarmModePosition)
    	    	{
    	        	case 0:
    	        	{	
    	        		System.out.println("1-1");
    	        		soloAlarm.setAlarmOn();
    	        		soloAlarm.setSoundOn();
    	        		break;
    	        	}
    	        	case 1:
    	    		{
    	    			System.out.println("0-1");
    	    			soloAlarm.setAlarmOff();
    	        		soloAlarm.setSoundOn();
    	    			break;
    	    		}
    	        	case 2:
    	    		{
    	    			System.out.println("1-0");
    	    			soloAlarm.setAlarmOn();
    	        		soloAlarm.setSoundOff();
    	    			break;
    	    		}
    	        	case 3:
    	    		{
    	    			System.out.println("0-0");
    	    			soloAlarm.setAlarmOff();
    	        		soloAlarm.setSoundOff();
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
			if(soloAlarm.isAlarmOn())
			{
				alarmLabel.setIcon(new ImageIcon(onAlarmMarker));
			}
			else
			{
				alarmLabel.setIcon(new ImageIcon(offAlarmMarker));
			}
			
			if(soloAlarm.isSoundOn())
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
				if(soloAlarm.getHours()<10)
				{
					hoursLabel.setText("0"+Integer.toString(soloAlarm.getHours()));
				}
				else
				{
					hoursLabel.setText(Integer.toString(soloAlarm.getHours()));
				}
			}
			else
			{
				if(soloAlarm.getHours() == 0)
				{
					hoursLabel.setText("12");
				}
				else
				{
					if(soloAlarm.getHours() == 12)
					{
						hoursLabel.setText("12");
					}
					else
					{
						if(soloAlarm.getHours()<12)
						{
							if(soloAlarm.getHours()<10)
							{
								hoursLabel.setText("0"+Integer.toString(soloAlarm.getHours()));
							}
							else
							{
								hoursLabel.setText(Integer.toString(soloAlarm.getHours()));
							}
						}
						else
						{
							if(soloAlarm.getHours()<22)
							{
								hoursLabel.setText("0"+Integer.toString(soloAlarm.getHours()-12));
							}
							else
							{
								hoursLabel.setText(Integer.toString(soloAlarm.getHours()-12));
							}
						}
					}
				}
			}
			
			if(soloAlarm.getMinutes()<10)
			{
				minutesLabel.setText("0"+Integer.toString(soloAlarm.getMinutes()));
			}
			else
			{
				minutesLabel.setText(Integer.toString(soloAlarm.getMinutes()));
			}
			if(soloClock.getHourFormat())
			{
				amLabel.setText("");
			}
			else
			{
				if(soloAlarm.getAlarmMode())
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
