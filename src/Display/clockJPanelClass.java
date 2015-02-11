package Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Engine.Clock;

public class clockJPanelClass extends JPanel
{
	private GUI parent;
	private Clock soloClock;
	private JButton buttonA;
	private JButton buttonB;
	private JLabel hoursLabel;
	private JLabel minutesLabel;
	private JLabel secondsLabel;
	
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
            }
        });
		this.buttonB.setFocusable(false);
		this.add(buttonB);
		//Hours Label
		hoursLabel = new JLabel();
		
		//Minutes Label
		minutesLabel = new JLabel();
		//Seconds Label
		secondsLabel = new JLabel();
	}
}
