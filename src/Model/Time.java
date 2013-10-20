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
    private int gameDuration = 180; // in seconds
    private TimeListener timeListener;
    
    public Time() {
        timer = new Timer();
    }

    public int timeLeft() {
        return 0;
    }
    
    public void startTime() {
        timer.schedule(new TimeIsUp(), gameDuration*1000);
    }
    
    public void pause() {
        
    }
    
    public void resume() {
        
    }

    public void setTimeListener(TimeListener listener) {
        timeListener = listener;
    }

    public interface TimeListener {
        public void timesUp();
    }
    
    // Inline class, gets triggered when timer ends
    class TimeIsUp extends TimerTask {
        public void run() {
            timeListener.timesUp();
            timer.cancel(); //Terminate the timer thread
        }
    }
}


