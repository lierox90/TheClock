package Engine;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;


public class Clock extends JPanel {
	  private JLabel labelDigitTime; 
	  private JRadioButton rbTwelveHourFormat; 
	  private JRadioButton rbTwentyFourthHourFormat;
	  private ButtonGroup choiceFormatRadioGroup;
	  private String time;
	  private Device panelClock; 
	  public Clock(){
	    setBackground(Color.cyan);
		setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.blue, 1),
		                             BorderFactory.createBevelBorder(BevelBorder.RAISED)));						
	    labelDigitTime = new JLabel();
		choiceFormatRadioGroup = new ButtonGroup();
		rbTwelveHourFormat = new JRadioButton("12-h tryb");
		choiceFormatRadioGroup.add(rbTwelveHourFormat);
		rbTwelveHourFormat.setBackground(Color.cyan);
		rbTwelveHourFormat.setFocusable(false);
		rbTwentyFourthHourFormat = new JRadioButton("24-h tryb");
		rbTwentyFourthHourFormat.setSelected(true);
		choiceFormatRadioGroup.add(rbTwentyFourthHourFormat);
		rbTwentyFourthHourFormat.setBackground(Color.cyan);
		rbTwentyFourthHourFormat.setFocusable(false);
		
		new Thread(){
		  public void run(){
		   for(;;){
		    try{
			 repaint();
			 panelClock = new Device(80); 
			  if(rbTwelveHourFormat.isSelected()){
			    time = panelClock.twelveHourFormat();
			  }
			  else{
		        time = panelClock.twentyFourthHourFormat();
			  }
			 labelDigitTime.setText(time);		 
			 Thread.sleep(500);
			 }catch(InterruptedException ex){
			  ex.printStackTrace();
			}
		   }		
		  }
		}.start();
		labelDigitTime.setFont(new Font("Arial", Font.BOLD, 24));
		labelDigitTime.setForeground(Color.white);
		add(labelDigitTime);
		add(rbTwelveHourFormat);
		add(rbTwentyFourthHourFormat);
	  }
	}