package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:05 PM
 */
public class Game implements IGameModel{
    
    private boolean gameStarted = false;
    private BoardFactory boardFactory;
    private Board board;
    private Score score;
    private Time time;
    private Dictionary dictionary;
    private ArrayList<String> foundWords;
    private Game game;
    private ArrayList<Observable> observers = new ArrayList<Observable>();

    public Game(int boardSizeX, int boardSizeY) {
        this.score = new Score();
        this.time = new Time();
        this.boardFactory = new BoardFactory();
        this.dictionary = new Dictionary();
        this.foundWords = new ArrayList<String>();
        this.board = boardFactory.createBoard(boardSizeX, boardSizeY);
    }
    
    
    public Dictionary getDictionary() {
        return this.dictionary;
    }
    
    public Board getBoard() {
        return this.board;
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
        gameStarted = true;
        // timer.start
    }

    @Override
    public Game restartGame() {
        return new Game(this.board.getBoardSizeX(), this.board.getBoardSizeY());
    }

    @Override
    public Time getTime() {
        return this.game.getTime();
    }

    @Override
    public int getTimeLeft() {
        return this.getTime().timeLeft();
    }

    @Override
    public void pauseTime() {
        this.game.getTime().pause();
    }

    @Override
    public Game newGame(int boardSizeX, int boardSizeY) {
        return new Game(boardSizeX, boardSizeY);
    }

    @Override
    public Score getScore() {
        return this.game.getScore();
    }

    @Override
    public boolean checkWord(String word) {
        return this.game.getDictionary().checkWord(word);
    }

    @Override
    public void addWord(String word) {
        this.game.addWord(word);
    }

    @Override
    public int calculateScore() {
        return this.game.calculateScore();
    }
    
    @Override
    public int getCurrentScore() {
        return this.getScore().getCurrentScore();
    }
}
