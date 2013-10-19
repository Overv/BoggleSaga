package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 9:37 PM
 */
public class GameModel implements IGameModel {
    
    private Game game;
    private ArrayList<Observable> observers = new ArrayList<Observable>();

    public GameModel() {
        
    }

    @Override
    public void registerObserver(Observable observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observable observer) {
        observers.remove(observer); 
    }

    @Override
    public void notifyObservers() {
        // Code sent to observers about state changes
    }

    @Override
    public void startGame() {
        this.game.start();
    }
    
    @Override
    public void newGame(int boardSizeX, int boardSizeY) {
        this.game = new Game(boardSizeX, boardSizeY);
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public void restartGame() {
        game.restart();
    }
    
    @Override
    public Time getTime() {
        return this.game.getTime();
    }

    @Override
    public int getTimeLeft() {
        return this.game.getTime().timeLeft();
    }

    @Override
    public void pauseTime() {
        this.game.getTime().pause();
    }

    @Override
    public Score getScore() {
        return this.game.getScore();
    }
}
