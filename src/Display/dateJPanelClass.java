package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Engine.ClockDate;

public class dateJPanelClass extends JPanel implements Runnable
{
	private GUI parent;
	private JButton buttonA;
	private JButton buttonB;
	private JLabel yearsLabel;
	private JLabel monthsLabel;
	private JLabel daysLabel;
	private ClockDate soloDate;
	private boolean settingMode = false;
	private boolean doOnce = true;
	private int dateSettingsPosition = 0;
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
    		increaseSelectedByOne();
	    }
	});
	private Timer blinkTimer = new Timer(500, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			switch (dateSettingsPosition)
	    	{
	        	case 0:
	        	{	
	        		if(daysLabel.isVisible())
	        		{
	        			daysLabel.setVisible(false);
	        		}
	        		else
	        		{
	        			daysLabel.setVisible(true);
	        		}
	        		break;
	        	}
	        	case 1:
	    		{
	    			daysLabel.setVisible(true);
	        		if(monthsLabel.isVisible())
	        		{
	        			monthsLabel.setVisible(false);
	        		}
	        		else
	        		{
	        			monthsLabel.setVisible(true);
	        		}
	    			break;
	    		}
	        	default:
	    		{
	    			daysLabel.setVisible(true);
	    			monthsLabel.setVisible(true);
	    			settingMode = false;
	    			dateSettingsPosition=0;
	    			blinkTimer.stop();
	    			break;
	    		}
	    	}
	    }
	});
	
	public dateJPanelClass(GUI gui,ClockDate date)
	{
		this.setLayout(null);
		this.parent = gui;
		this.setBounds(0, 0, 294, 171);
		this.setBackground(new Color(123,53,123));
		this.soloDate = date;
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
                		dateSettingsPosition++;
    		    	}
    		    	doOnce=false;
            	}
            }
        });
		this.buttonB.setFocusable(false);
		this.add(buttonB);
		//years Label
		yearsLabel = new JLabel();
		yearsLabel.setBounds(190, 10, 90, 90);
		yearsLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		yearsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearsLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(yearsLabel);
		//months Label
		monthsLabel = new JLabel();
		monthsLabel.setBounds(100, 10, 90, 90);
		monthsLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		monthsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthsLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(monthsLabel);
		//days Label
		daysLabel = new JLabel();
		daysLabel.setBounds(10, 10, 90, 90);
		daysLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
		daysLabel.setHorizontalAlignment(SwingConstants.CENTER);
		daysLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		this.add(daysLabel);
	}
	
	private void increaseSelectedByOne()
	{
		switch (dateSettingsPosition)
    	{
        	case 0:
        	{	
        		soloDate.incDay();
        		break;
        	}
        	case 1:
    		{
    			soloDate.incMonth();
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
			yearsLabel.setText(Integer.toString(soloDate.getYears()));
			monthsLabel.setText(soloDate.getMonthsN());
			daysLabel.setText(Integer.toString(soloDate.getDays()));
		}
	}
}
