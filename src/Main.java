import java.awt.EventQueue;
import java.io.IOException;

import Display.*;
import Engine.*;
//
public class Main 
{
	static private GUI visualisation;
	static private Device clock;
    public static void main(String[] args) throws IOException 
    {
    	clock = new Device();
    	visualisation = new GUI(clock);
		Thread FrameThread = new Thread(visualisation);
		Thread ClockThread = new Thread(clock);
		FrameThread.start();
		ClockThread.start();
    }
}
