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
    
    abstract void startGame(); // Use to start a newly created game
    abstract Time getTime();
    abstract Game restartGame(); // Restart a currently active game
    abstract int getTimeLeft(); // Get time left on the timer in seconds
    abstract void pauseTime(); // Stop the timer
    abstract Game newGame(int boardSizeX, int boardSizeY); // return new game with size x, y
    
    abstract Score getScore(); // Get the score object regarding the current game
    abstract boolean checkWord(String word);
    abstract void addWord(String word);
    abstract int calculateScore(); // returns an int representing the score
    abstract int getCurrentScore();
    
}
