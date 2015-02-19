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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Engine.Clock;
import Engine.SecondClock;

public class twoClocksJPanelClass extends JPanel implements Runnable
{
	private GUI parent;
	private JLabel hoursLabel;
	private JLabel minutesLabel;
	private JLabel secondsLabel;
	private JLabel amLabel;
	private JLabel hoursLabel2;
	private JLabel minutesLabel2;
	private JLabel secondsLabel2;
	private JLabel amLabel2;
	private BufferedImage onAlarmMarker;
	private BufferedImage offAlarmMarker;
	private BufferedImage onSoundMarker;
	private BufferedImage offSoundMarker;
	private Clock soloClock;
	private boolean amMode = true;
	private boolean amMode2 = true;
	private boolean settingMode = false;
	private boolean doOnce = true;
	private int clockSettingsPosition = 0;
	private int clockModePosition = 0;
	private SecondClock duoClock;
	private JLabel alarmLabel;
	private JLabel soundLabel;
	private JButton buttonA;
	private JButton buttonB;
	private Timer pressTimer = new Timer(500, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			settingMode = true;
			doOnce=true;
			pressTimer.stop();
	    }
	});
	private Timer incrementTimer = new Timer(150, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(clockSettingsPosition == 3)
    		{
    			clockModePosition++;
    			if(clockModePosition == 2)
    			{
    				clockModePosition = 0;
    			}
    		}
    		increaseSelectedByOne();
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
	        		if(minutesLabel2.isVisible())
		        	{
	        			minutesLabel2.setVisible(false);
		        	}
	        		else
	        		{
	        			minutesLabel2.setVisible(true);
	        		}
	    			break;
	    		}
	        	case 1:
	    		{	
	    			minutesLabel2.setVisible(true);
	        		if(hoursLabel2.isVisible())
		        	{
	        			hoursLabel2.setVisible(false);
		        	}
	        		else
	        		{
	        			hoursLabel2.setVisible(true);
	        		}
	    			break;
	    		}
	        	default:
	    		{
	    			minutesLabel2.setVisible(true);
	    			hoursLabel2.setVisible(true);
	    			if(!duoClock.getHourFormat())
	    			{
	    				if(amMode2)
	    				{
	    					amLabel2.setText("AM");
	    				}
	    				else
	    				{
	    					amLabel2.setText("PM");
	    				}
	    			}
	    			else
	    			{
	    				amLabel2.setText("");
	    			}
	    			settingMode = false;
	    			clockSettingsPosition=0;
	    			blinkTimer.stop();
	    			break;
	    		}
	    	}
	    }
	});
	
	public twoClocksJPanelClass(GUI gui, Clock clock, SecondClock secondClock)
	{
		this.setLayout(null);
		this.parent = gui;
		this.soloClock = clock;
		this.duoClock = secondClock;
		this.setBounds(0, 0, 294, 171);
		this.setBackground(new Color(23,153,23));
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
            		increaseSelectedByOne();
            	}
            }
        });
		this.buttonA.addMouseListener(new MouseAdapter() 
		{
			@Override
		    public void mousePressed(MouseEvent e) 
			{
            	if(settingMode)
            	{
            		incrementTimer.start();
            	}
		    }
		 
		    @Override
		    public void mouseReleased(MouseEvent e) 
		    {
		    	incrementTimer.stop();
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
		//Hours Label
		hoursLabel = new JLabel();
		hoursLabel.setBounds(10, 10, 70, 45);
		hoursLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		hoursLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hoursLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(hoursLabel);
		//Minutes Label
		minutesLabel = new JLabel();
		minutesLabel.setBounds(80, 10, 70, 45);
		minutesLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		minutesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minutesLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(minutesLabel);
		//Seconds Label
		secondsLabel = new JLabel();
		secondsLabel.setBounds(150, 10, 70, 45);
		secondsLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		secondsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		secondsLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(secondsLabel);
		//Hours Label
		hoursLabel2 = new JLabel();
		hoursLabel2.setBounds(10, 65, 70, 45);
		hoursLabel2.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		hoursLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		hoursLabel2.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(hoursLabel2);
		//Minutes Label
		minutesLabel2 = new JLabel();
		minutesLabel2.setBounds(80, 65, 70, 45);
		minutesLabel2.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		minutesLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		minutesLabel2.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(minutesLabel2);
		//Seconds Label
		secondsLabel2 = new JLabel();
		secondsLabel2.setBounds(150, 65, 70, 45);
		secondsLabel2.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		secondsLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		secondsLabel2.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(secondsLabel2);
		//AM Label
		amLabel = new JLabel("");
		amLabel.setBounds(230, 15, 40, 35);
		amLabel.setVisible(true);
		amLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		amLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(amLabel);
		//AM Label
		amLabel2 = new JLabel("");
		amLabel2.setBounds(230, 70, 40, 35);
		amLabel2.setVisible(true);
		amLabel2.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		amLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(amLabel2);
	}
	
	private void increaseSelectedByOne()
	{
		switch (clockSettingsPosition)
    	{
        	case 0:
    		{
    			duoClock.incMinutes();
    			break;
    		}
        	case 1:
    		{
    			duoClock.incHours();
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
			if(duoClock.getHours()<12)
			{
				this.amMode2 = true;
			}
			else
			{
				this.amMode2 = false;
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
			
			if(soloClock.getHourFormat())
			{
				if(duoClock.getHours()<10)
				{
					hoursLabel2.setText("0"+Integer.toString(duoClock.getHours()));
				}
				else
				{
					hoursLabel2.setText(Integer.toString(duoClock.getHours()));
				}
			}
			else
			{
				if(duoClock.getHours() == 0)
				{
					hoursLabel2.setText("12");
				}
				else
				{
					if(duoClock.getHours() == 12)
					{
						hoursLabel2.setText("12");
					}
					else
					{
						if(duoClock.getHours()<12)
						{
							if(duoClock.getHours()<10)
							{
								hoursLabel2.setText("0"+Integer.toString(duoClock.getHours()));
							}
							else
							{
								hoursLabel2.setText(Integer.toString(duoClock.getHours()));
							}
						}
						else
						{
							if(duoClock.getHours()<22)
							{
								hoursLabel2.setText("0"+Integer.toString(duoClock.getHours()-12));
							}
							else
							{
								hoursLabel2.setText(Integer.toString(duoClock.getHours()-12));
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
			
			if(duoClock.getMinutes()<10)
			{
				minutesLabel2.setText("0"+Integer.toString(duoClock.getMinutes()));
			}
			else
			{
				minutesLabel2.setText(Integer.toString(duoClock.getMinutes()));
			}
			
			if(soloClock.getSeconds()<10)
			{
				secondsLabel.setText("0"+Integer.toString(soloClock.getSeconds()));
			}
			else
			{
				secondsLabel.setText(Integer.toString(soloClock.getSeconds()));
			}
			
			if(duoClock.getSeconds()<10)
			{
				secondsLabel2.setText("0"+Integer.toString(duoClock.getSeconds()));
			}
			else
			{
				secondsLabel2.setText(Integer.toString(duoClock.getSeconds()));
			}
			
			if(soloClock.getHourFormat())
			{
				amLabel.setText("");
				amLabel2.setText("");
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
				if(amMode2)
				{
					amLabel2.setText("AM");
				}
				else
				{
					amLabel2.setText("PM");
				}
			}
		}
	}
}
