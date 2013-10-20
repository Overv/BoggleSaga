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
        // timer.
    }
    
    public void resume() {
        
    }

    // Inline class, gets triggered when timer ends
    class TimeIsUp extends TimerTask {
        public void run() {
            System.out.format("Time's up!");
            timer.cancel(); //Terminate the timer thread
        }
    }
}


