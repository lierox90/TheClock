package Engine;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;


public class Alarm extends JFrame{
	  private Device panelClock;
	  private Clock digit;
	  private Signal panelChoiceSignal;
	  private JLabel labelClock; 
	  private JLabel currentTimeLabel;
	  private JLabel timeSignal;
	  private SpinnerTimerPannel timerClock;
	  private SystemTray systemTray = SystemTray.getSystemTray(); 
	  private TrayIcon trayIcon; 
	  public void javaStart() throws IOException{
	    String command = "java "+getClass().getName();
		BufferedReader buf = new BufferedReader(new StringReader(command));
		PrintWriter out = null;
		out = new PrintWriter(new BufferedWriter(new FileWriter("JavaAlarmClock.bat")));
		String str;
		while( (str = buf.readLine()) != null){
		  out.println(command);
		}	
		buf.close();
		out.close();
	  }
	  private void removeTrayIcon(){ 
	    systemTray.remove(trayIcon); 
	  } 
	  private void addTrayIcon(){ 
	    try{ 
	      systemTray.add(trayIcon); 
	    } 
	    catch(AWTException ex){ 
	      ex.printStackTrace(); 
	    } 
	  } 
	  public Alarm() throws IOException{
	    setLayout(null);
		Container content = getContentPane();
		getContentPane().setBackground(Color.cyan);
		
		panelClock = new Device(80);
	    panelClock.startClock();
		panelClock.setBounds(10,25,170,170);
		
		labelClock = new JLabel(""+panelClock.getFullDate());
		labelClock.setForeground(Color.blue);
		labelClock.setBounds(15,5,150,15);
		
		currentTimeLabel = new JLabel("Aktualny czas");
		currentTimeLabel.setBounds(235,5,100,15);
		currentTimeLabel.setForeground(Color.blue);
		
		panelChoiceSignal = new Signal();
		panelChoiceSignal.setBounds(10,195,350,85);
		
		timeSignal = new JLabel("Czas aktywacji");
		timeSignal.setBounds(220,130,130,15);
		timeSignal.setForeground(Color.blue);
		
		timerClock = new SpinnerTimerPannel();
		timerClock.setBounds(215,145,133,38);
		
		digit = new Clock();
		digit.setBounds(200,20,160,100);
		
		content.add(panelClock);
		content.add(labelClock);
		content.add(currentTimeLabel);
		content.add(timeSignal);
		content.add(timerClock);
		content.add(digit);
		content.add(panelChoiceSignal);
		
		getContentPane().add(new JColorChooser()); 
	    setDefaultCloseOperation(EXIT_ON_CLOSE); 
	 
	    trayIcon = new TrayIcon(ImageIO.read(new File("ico.gif")), "JavaAlarmClock"); 
	    trayIcon.addActionListener(new ActionListener(){ 
	      public void actionPerformed(ActionEvent e){ 
	        setVisible(true); 
	        setState(JFrame.NORMAL); 
	        removeTrayIcon(); 
	      } 
	    }); 
	    addWindowStateListener(new WindowStateListener(){ 
	      public void windowStateChanged(WindowEvent e){ 
	       if(e.getNewState() == JFrame.ICONIFIED){ 
	         setVisible(false); 
	         addTrayIcon(); 
	       } 
	      } 
	    }); 
		PopupMenu popupMenu = new PopupMenu(); 
		MenuItem showItem = new MenuItem("Przywroc");
	    MenuItem exitItem = new MenuItem("Zamknac"); 
	    exitItem.addActionListener(new ActionListener() { 
	      public void actionPerformed(ActionEvent e) { 
	        dispose(); 
	        System.exit(0); 
	      } 
	    }); 
		showItem.addActionListener(new ActionListener() { 
	      public void actionPerformed(ActionEvent e) { 
	        setVisible(true); 
	        setState(JFrame.NORMAL); 
	        removeTrayIcon(); 
	      } 
	    });
	    popupMenu.add(exitItem); 
		popupMenu.add(showItem);
	    trayIcon.setPopupMenu(popupMenu);
	  }
	  public static void main(String[] args) throws IOException{
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    try{
	      UIManager.setLookAndFeel(new MetalLookAndFeel());
	    }  
	    catch ( UnsupportedLookAndFeelException e ) {
	      System.exit(0);
	    }
	    Alarm frame = new Alarm();
		frame.javaStart();
		frame.setTitle("JavaAlarmClock");
		frame.setSize(375,320);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	  }
	}