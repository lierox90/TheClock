package Engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;


public class Signal extends JPanel{
	  private Box choice;
	  private JPanel left;
	  private JPanel right;
	  private JPanel south;
	  private ButtonGroup choiceRadioGroup;
	  private JRadioButton simpleSignal;
	  private JRadioButton soundSignal; 
	  private JLabel songFileName;
	  private JCheckBox activeTimer;
	  private JFileChooser fileChooser;
	  private String fileName;
	  private SpinnerTimerPannel spinnerTime; 
	  private Device panelClock; 
	  private Clip clip;
	  public void playSong(final String fileName){
	   new Thread(){
	    public void run(){
		  try{
		    int start = fileName.lastIndexOf("\\")+1;
		    int end = fileName.length();
		    char buf[] = new char[end-start];
		    fileName.getChars(start, end, buf, 0);
		    String song = new String(buf);
		 
		    buf = new char[fileName.length()-song.length()];
		    fileName.getChars(0, buf.length, buf, 0);
		    String dirTemp = new String(buf);
		    String dir = dirTemp.replace("\\", "\\\\");
			
	        activeTimer.setEnabled(false);		
			File soundFile = new File(dir, song);
			AudioInputStream source = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info clipInfo = new DataLine.Info(Clip.class, source.getFormat());
			if(AudioSystem.isLineSupported(clipInfo)){
			  clip = (Clip) AudioSystem.getLine(clipInfo);
			  clip.open(source);
	          clip.loop(0);
			  int waitTime = (int)Math.ceil(clip.getMicrosecondLength()/1000.0);	  
	          Thread.sleep(waitTime); 		  
			}  
			activeTimer.setSelected(false);
			activeTimer.setEnabled(true);	  
		  } catch(UnsupportedAudioFileException ex){
		     ex.printStackTrace(); 
		   }
		   catch(LineUnavailableException ex){
		    ex.printStackTrace(); 
		   }
		   catch(InterruptedException ex){
		    ex.printStackTrace();
		   }
		   catch(IOException ex){
		    ex.printStackTrace();
		   }
		 }
	   }.start();
	  }
	  public Signal(){
	    setLayout(new BorderLayout());
		setBackground(Color.cyan);
		setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.blue, 1),
		                             BorderFactory.createBevelBorder(BevelBorder.RAISED)));
		left = new JPanel();
		left.setBackground(Color.cyan);
		left.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.blue, 1),
		                                  BorderFactory.createBevelBorder(BevelBorder.RAISED)));
		choice = Box.createVerticalBox();
		choiceRadioGroup = new ButtonGroup();
		simpleSignal = new JRadioButton("Sygnal dzwiekowy");
		simpleSignal.setSelected(true);
		simpleSignal.setBackground(Color.cyan);
		simpleSignal.setFocusable(false);
		soundSignal = new JRadioButton("Upload file");
		soundSignal.setBackground(Color.cyan);
		soundSignal.setFocusable(false);
		
		choiceRadioGroup.add(soundSignal);
		choiceRadioGroup.add(simpleSignal);
		choice.add(simpleSignal);
		choice.add(soundSignal);
		left.add(choice);
		
		south = new JPanel();
		south.setLayout(new BorderLayout());
		south.setBackground(Color.cyan);
		songFileName = new JLabel();
		songFileName.setText("");
		songFileName.setFont(new Font("Arial", Font.BOLD, 12));
		songFileName.setBackground(Color.cyan);
		south.add(songFileName);
		
		right = new JPanel();
		right.setLayout(null);
		right.setBackground(Color.cyan);
		right.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.blue, 1),
		                                  BorderFactory.createBevelBorder(BevelBorder.RAISED)));				   
		activeTimer = new JCheckBox("Активизировать");
		activeTimer.setBackground(Color.cyan);
		activeTimer.setFocusable(false);
		activeTimer.setBounds(25,5,150,30);
		right.add(activeTimer);
		
		spinnerTime = new SpinnerTimerPannel(); 
		panelClock = new Device(80);
		activeTimer.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
		   new Thread(){
		    public void run(){
			 for(;;){
			  try{
		       if(activeTimer.isSelected()){
			    String currentTime = panelClock.twentyFourthHourFormat();
	            String signalTime = spinnerTime.getTime();		
			    if(soundSignal.isSelected()){ 
				  if(signalTime.equals(currentTime)){ 
				    playSong(fileName);
				    break;		
				  }
			    }else{
				  if(signalTime.equals(currentTime)){  
				    activeTimer.setEnabled(false);
			      for(int i=0;i<10;i++){		
				    Toolkit.getDefaultToolkit().beep();
				    Thread.sleep(300);
				  }
				  activeTimer.setSelected(false);
				  activeTimer.setEnabled(true);
				  break;
	             }			  
			    }
			   }
			   Thread.sleep(500);
			  } catch(InterruptedException ex){
			     ex.printStackTrace();
			    }
			 }
			} 
		   }.start(); 
		  }
		});
	 
		fileChooser = new JFileChooser();
		
		fileChooser.setCurrentDirectory(new File("."));
		soundSignal.addActionListener(new ActionListener(){ 
		  public void actionPerformed(ActionEvent e){
		    int r = fileChooser.showOpenDialog(null);
	        if(r == JFileChooser.APPROVE_OPTION){
			 fileName = fileChooser.getSelectedFile().getPath();
			 String song = fileName.toLowerCase();
			 if( song.endsWith(".au") || song.endsWith(".aif") || song.endsWith(".wav") ){
	           fileName = fileChooser.getSelectedFile().getPath();
			   songFileName.setText(fileName);
			 }else{
			   songFileName.setText("");
	           Toolkit.getDefaultToolkit().beep();
			   simpleSignal.setSelected(true);
	           JOptionPane.showMessageDialog(null, 
			   "   Wybierz plik .au .aif lub .wav", 
			   "MessageFormatInformation", JOptionPane.INFORMATION_MESSAGE, null);
		     }
			}
	        else if(r == JFileChooser.CANCEL_OPTION){
			  songFileName.setText("");
	          simpleSignal.setSelected(true);
			}  		
		  } 
		});
		
		simpleSignal.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
		    songFileName.setText("");
		  }
		});
		
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
	  }
	}