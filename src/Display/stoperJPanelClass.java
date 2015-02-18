package Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import Engine.*;

public class stoperJPanelClass extends JPanel implements Runnable
{
	private GUI parent;
	private Stoper soloStoper;
	private JButton buttonA;
	private JButton buttonB;
	private boolean runMode = false;
	private Timer pressTimer = new Timer(500, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			runMode  = true;
			pressTimer.stop();
	    }
	});
	
	public stoperJPanelClass(GUI gui,Stoper stoper)
	{
		this.setLayout(null);
		this.parent = gui;
		this.soloStoper = stoper;
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
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
}
