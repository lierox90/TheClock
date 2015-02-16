package Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class alarmJPanelClass extends JPanel
{
	private GUI parent;
	private JButton buttonA;
	private JButton buttonB;
	private boolean settingMode = false;
	
	public alarmJPanelClass(GUI gui)
	{
		this.setLayout(null);
		this.parent = gui;
		this.setBounds(0, 0, 294, 171);
		this.setBackground(new Color(123,53,23));
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
            		
            	}
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
	}
}
