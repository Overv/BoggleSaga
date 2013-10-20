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
    private boolean isPaused = true;
    
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
    
    public synchronized void registerObserver(Observable observer) {
        observers.add(observer);
    }

    public synchronized void removeObserver(Observable observer) {
        observers.remove(observer);
    }

    public synchronized void notifyObservers() {
        // Code sent to observers about state changes
    }

    public synchronized void start() {
        gameStarted = true;
        isPaused = false;
        time.startTime();
    }

    public synchronized Game newGame(int boardSizeX, int boardSizeY) {
        return new Game(boardSizeX, boardSizeY);
    }

    public synchronized Game restart() {
        return new Game(this.board.getBoardSizeX(), this.board.getBoardSizeY());
    }

    public synchronized int getTimeLeft() {
        return this.time.getTimeLeft();
    }

    public synchronized void pause() {
        this.time.pause();
        isPaused = true;
    }
    
    public synchronized void resume() {
        this.time.resume();
        isPaused = false;
    }

    public synchronized boolean isPaused() {
        return this.isPaused;
    }
    
    public synchronized boolean checkWord(String word) {
        return this.dictionary.checkWord(word);
    }

    public synchronized void addWord(String word) {
        this.foundWords.add(word);
        this.updateScore();
    }

    public synchronized void updateScore() {
        this.score.updateScore(foundWords);
    }
    
    public synchronized int getCurrentScore() {
        return this.score.getCurrentScore();
    }
    
    public synchronized char[][] getDice() {
        return this.board.getBoard();
    }
    
    public synchronized ArrayList<String> getFoundWords() {
        return this.foundWords;
    }
    
    public synchronized String toString() {
        return this.board.toString();
    }
    
    public void setTimeListener(Time.TimeListener listener) {
        this.time.setTimeListener(listener);
    }
}
