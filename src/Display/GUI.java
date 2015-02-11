package Display;

import java.io.IOException;

import javax.swing.JFrame;

public class GUI extends JFrame implements Runnable
{
	//Inits
	private int width=300;
	private int heigh=200;
	//Constructor
    public GUI() throws IOException 
    {
    	//Window init
        super("TheClock");
        setSize(width, heigh);
        setResizable( false );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
    }
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}

}
