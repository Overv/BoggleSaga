package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:05 PM
 */
public class Game {
    
    private boolean gameStarted = false;
    private BoardFactory boardFactory;
    private Board board;
    private Score score;
    private Time time;
    private Dictionary dictionary;
    private ArrayList<String> foundWords;
    private ArrayList<Observable> observers = new ArrayList<Observable>();

    public Game(int boardSizeX, int boardSizeY) {
        this.score = new Score();
        this.time = new Time();
        this.boardFactory = new BoardFactory();
        this.dictionary = new Dictionary();
        this.foundWords = new ArrayList<String>();
        this.board = boardFactory.createBoard(boardSizeX, boardSizeY);
    }
    
    
    public void registerObserver(Observable observer) {
        observers.add(observer);
    }

    public void removeObserver(Observable observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        // Code sent to observers about state changes
    }

    public void startGame() {
        gameStarted = true;
        // timer.start
    }

    public Game newGame(int boardSizeX, int boardSizeY) {
        return new Game(boardSizeX, boardSizeY);
    }

    public Game restartGame() {
        return new Game(this.board.getBoardSizeX(), this.board.getBoardSizeY());
    }

    public int getTimeLeft() {
        return this.time.timeLeft();
    }

    public void pauseTime() {
        this.time.pause();
    }

    public boolean checkWord(String word) {
        return this.dictionary.checkWord(word);
    }

    public void addWord(String word) {
        this.addWord(word);
        this.updateScore();
    }

    public void updateScore() {
        this.score.updateScore(foundWords);
    }
    
    public int getCurrentScore() {
        return this.score.getCurrentScore();
    }
}
