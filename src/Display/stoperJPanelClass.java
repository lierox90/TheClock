package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Engine.*;

public class stoperJPanelClass extends JPanel implements Runnable
{
	private GUI parent;
	private Stoper soloStoper;
	private JButton buttonA;
	private JButton buttonB;
	private JLabel hoursLabel;
	private JLabel minutesLabel;
	private JLabel secondsLabel;
	private boolean doOnce = false;
	private Timer pressTimer = new Timer(500, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			doOnce=true;
			soloStoper.reset();
			playBeepSound();
			pressTimer.stop();
	    }
	});
	
	public static synchronized void playBeepSound() 
	{
	    new Thread(new Runnable() 
	    {
	    	public void run() 
	    	{
		        try 
		        {
		        	Clip clip = AudioSystem.getClip();
		        	AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/res/beep.wav"));
		        	clip.open(inputStream);
		        	clip.start(); 
		        } 
		        catch (Exception e) 
		        {
		        	System.err.println(e.getMessage());
		        }
	    	}
	    }).start();
	}
	
	public stoperJPanelClass(GUI gui,Stoper stoper)
	{
		this.setLayout(null);
		this.parent = gui;
		this.soloStoper = stoper;
		this.setBounds(0, 0, 294, 171);
		this.setBackground(new Color(123,153,23));
		//Button A
		this.buttonA = new JButton("A");
		this.buttonA.setBounds(160, 121, 60, 40);
		this.buttonA.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
            	parent.swapSelectedPanel();
            }
        });
		this.buttonA.setFocusable(false);
		this.add(buttonA);
		//Button B
		this.buttonB = new JButton("B");
		this.buttonB.setBounds(224, 121, 60, 40);
		this.buttonB.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
            	if(soloStoper.isRunning())
            	{
            		System.out.println("Stoper dziala");
            		playBeepSound();
            		soloStoper.stop();
            	}
            	else
            	{
    		    	if(!doOnce)
    		    	{
    		    		System.out.println("Stoper nie dziala");
    		    		playBeepSound();
    		    		soloStoper.start();
    		    	}
    		    	doOnce=false;
            	}
            }
        });
		this.buttonB.addMouseListener(new MouseAdapter() 
		{
			@Override
		    public void mousePressed(MouseEvent e) 
			{
            	pressTimer.start();
		    }
		 
		    @Override
		    public void mouseReleased(MouseEvent e) 
		    {
		    	pressTimer.stop();
		    }
		});
		this.buttonB.setFocusable(false);
		this.add(buttonB);
		//Hours Label
		hoursLabel = new JLabel();
		hoursLabel.setBounds(10, 10, 90, 90);
		hoursLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		hoursLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hoursLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
		this.add(hoursLabel);
		//Minutes Label
		minutesLabel = new JLabel();
		minutesLabel.setBounds(100, 10, 90, 90);
		minutesLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		minutesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minutesLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
		this.add(minutesLabel);
		//Seconds Label
		secondsLabel = new JLabel();
		secondsLabel.setBounds(190, 10, 90, 90);
		secondsLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		secondsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		secondsLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(secondsLabel);
	}

	@Override
	public void run()
	{
		while(true)
		{
			if(soloStoper.getMinutes()>=30)
			{
				if(soloStoper.getHours()<10)
				{
					hoursLabel.setText("0"+Integer.toString(soloStoper.getHours()));
				}
				else
				{
					hoursLabel.setText(Integer.toString(soloStoper.getHours()));
				}
				
				if(soloStoper.getMinutes()<10)
				{
					minutesLabel.setText("0"+Integer.toString(soloStoper.getMinutes()));
				}
				else
				{
					minutesLabel.setText(Integer.toString(soloStoper.getMinutes()));
				}

				if(soloStoper.getSeconds()<10)
				{
					secondsLabel.setText("0"+Integer.toString(soloStoper.getSeconds()));
				}
				else
				{
					secondsLabel.setText(Integer.toString(soloStoper.getSeconds()));
				}
			}
			else
			{
				if(soloStoper.getMinutes()<10)
				{
					hoursLabel.setText("0"+Integer.toString(soloStoper.getMinutes()));
				}
				else
				{
					hoursLabel.setText(Integer.toString(soloStoper.getMinutes()));
				}
				
				if(soloStoper.getSeconds()<10)
				{
					minutesLabel.setText("0"+Integer.toString(soloStoper.getSeconds()));
				}
				else
				{
					minutesLabel.setText(Integer.toString(soloStoper.getSeconds()));
				}
				
				if(soloStoper.getCentysecunds()<10)
				{
					secondsLabel.setText("00"+Integer.toString(soloStoper.getCentysecunds()));
				}
				else
				{
					if(soloStoper.getCentysecunds()<100)
					{
						secondsLabel.setText("0"+Integer.toString(soloStoper.getCentysecunds()));
					}
					else
					{
						secondsLabel.setText(Integer.toString(soloStoper.getCentysecunds()));
					}
				}
			}
		}
	}
}
