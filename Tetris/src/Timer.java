import processing.core.PApplet;

public class Timer {
	private PApplet processing;

	private boolean running;
	private int startTime;
	private int stopTime;

	public Timer(PApplet processing) {
		this.processing = processing;
		this.running = false;
		this.startTime = 0;
		this.stopTime = 0;
	}

	// function stays open until the given time is over
	public void timer(int time) {
		reset();
		startTimer();
		while (getElapsedTimeMill() < time) {
		}
		stopTimer();
		reset();
	}

	public void startTimer() {
		if (running == false) {
			startTime = processing.millis();
			running = true;
		}
	}

	public void stopTimer() {
		if (running == true) {
			stopTime = processing.millis();
			running = false;
		}
	}

	// returns true or false (is given time reached or not)
	public boolean timeReachedMill(int time) {
		if (getElapsedTimeMill() >= time) {
			return true;
		} else {
			return false;
		}
	}

	// calculates and returns the elapsed time in milliseconds
	public int getElapsedTimeMill() {
		int elapsed;
		if (running) {
			elapsed = processing.millis() - startTime;
		} else {
			elapsed = stopTime - startTime;
		}
		return elapsed;
	}

	// resets the timer
	public void reset() {
		running = false;
		startTime = 0;
		stopTime = 0;
	}
}
