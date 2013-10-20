package Model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:05 PM
 */
public class Time {
    
    private Timer timer;
    private Timer secondsTimer;
    private int gameDuration = 60; // in seconds
    private int timeLeft;
    private TimeListener timeListener;
    
    public Time() {
        timer = new Timer();
        secondsTimer = new Timer();
    }

    public int getTimeLeft() {
        return this.timeLeft;
    }
    
    public void startTime() {
        timer.schedule(new TimeIsUp(), gameDuration*1000);
        secondsTimer.scheduleAtFixedRate(new TimeLeft(), 0, 1*1000); // Callbacks every second
        timeLeft = gameDuration;
    }
    
    public void pause() {
        // Unimplemented as of now
    }
    
    public void resume() {
        // Unimplemented as of now
    }

    // Register an observer, the observer will get notified each second through the onTimePassed() callback 
    // to update the view's time display. When the time is up the timesUp() callback is fired.
    public void setTimeListener(TimeListener listener) {
        timeListener = listener;
    }

    public interface TimeListener {
        public void timesUp();
        public void onTimePassed(int timeLeft); // call the model for the current time
    }
    
    // Inline class, specifies the callback used to notify Observer when time is up
    class TimeIsUp extends TimerTask {
        public void run() {
            secondsTimer.cancel();

            if(timeListener != null) {
                timeListener.timesUp();
            } else {
                System.err.println("No listeners registered for time");
            }
        }
    }
    
    // Inline class, specifies the callback used to notify the Observer when a specified time has passed
    class TimeLeft extends TimerTask {
        public void run() {
            if(timeListener != null) {
                timeListener.onTimePassed(timeLeft); // Send time left to the Observer every second
            } else {
                System.err.println("No listeners registered for time");
            }
            timeLeft -= 1; // each callback reduce timeleft by 1
        }
    }
}


