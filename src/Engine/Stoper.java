package Engine;

class Stoper implements Runnable
{
	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}


	  private void start() {
	    this.startTime = System.currentTimeMillis();
	    this.running = true;
	  }


	  private void stop() {
	    this.stopTime = System.currentTimeMillis();
	    this.running = false;
	  }


	  //czas ktory minal z 1 milisekunda
	  private long getElapsedTime() {
	    long elapsed;
	    if (running) {
	      elapsed = (System.currentTimeMillis() - startTime);
	    }
	    else {
	      elapsed = (stopTime - startTime);
	    }
	    return elapsed;
	  }


	  //czas ktory minal z krokiem sekunda
	  private long getElapsedTimeSecs() {
	    long elapsed;
	    if (running) {
	      elapsed = ((System.currentTimeMillis() - startTime) / 1000);
	    }
	    else {
	      elapsed = ((stopTime - startTime) / 1000);
	    }
	    return elapsed;
	  }
	
	
}

