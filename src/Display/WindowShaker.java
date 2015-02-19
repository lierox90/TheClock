package Display;


public class WindowShaker implements Runnable
{
	private GUI parent;
	private boolean shaking = false;
	private int VIBRATION_LENGTH = 8;
	private int VIBRATION_VELOCITY = 5;
	
	public WindowShaker(GUI gui)
	{
		this.parent = gui;
	}
	
	public boolean isShaking()
	{
		return this.shaking;
	}
	
	public void startShake()
	{
		shaking = true;
	}
	
	public void stopShake()
	{
		shaking = false;
	}
	
	private void makeShake()
	{
		try 
		{ 
			final int originalX = this.parent.getLocationOnScreen().x; 
			final int originalY = this.parent.getLocationOnScreen().y; 
			for(int i = 0; i < VIBRATION_LENGTH; i++) 
			{ 
				Thread.sleep(10); 
				this.parent.setLocation(originalX, originalY + VIBRATION_VELOCITY); 
				Thread.sleep(10); 
				this.parent.setLocation(originalX, originalY - VIBRATION_VELOCITY);
				Thread.sleep(10); 
				this.parent.setLocation(originalX + VIBRATION_VELOCITY, originalY);
				Thread.sleep(10);
				this.parent.setLocation(originalX - VIBRATION_VELOCITY, originalY);
				Thread.sleep(10);
				this.parent.setLocation(originalX, originalY); 
			} 
		} 
		catch (Exception err)
		{ 
			err.printStackTrace(); 
		} 
	}

	@Override
	public void run()
	{
		while(true)
		{	
			if(isShaking())
			{
				makeShake();
			}
			try 
			{ 
				Thread.sleep(500);
			} 
			catch (Exception err)
			{ 
				err.printStackTrace(); 
			} 
		}
	}
}
