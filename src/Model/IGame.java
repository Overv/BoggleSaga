package Model;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 11:35 PM
 */
public interface IGame {
    
    abstract void registerObserver(Observable observable); 
    abstract void removeObserver(Observable observable);
    abstract void notifyObservers(Observable[] observers);
    
    abstract void startGame(); // Use to start a newly created game
    abstract IBoard getBoard(); // Gets the board instance in the current game
    abstract void restartGame(); // Restart a currently active game
    abstract int getTimeLeft(); // Get time left on the timer in seconds
    abstract void pauseTime(); // Stop the timer
    
    abstract IScore getScore(); // Get the score object regarding the current game
    
}
