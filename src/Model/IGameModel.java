package Model;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 11:35 PM
 */
public interface IGameModel {
    
    abstract void registerObserver(Observable observer); 
    abstract void removeObserver(Observable observer);
    abstract void notifyObservers();
    
    abstract void startGame(int boardSizeX, int boardSizeY); // Use to start a newly created game
    abstract Game getGame(); // Gets the game instance in the current game
    abstract void restartGame(); // Restart a currently active game
    abstract int getTimeLeft(); // Get time left on the timer in seconds
    abstract void pauseTime(); // Stop the timer
    
    abstract Score getScore(); // Get the score object regarding the current game
    
}
