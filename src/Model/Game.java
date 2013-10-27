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
    private Statistics statistics;

    public Game(int boardSizeX, int boardSizeY) {
        this.score = new Score();
        this.time = new Time();
        this.boardFactory = new BoardFactory();
        this.dictionary = Dictionary.getInstance();
        this.foundWords = new ArrayList<String>();
        this.board = boardFactory.createBoard(boardSizeX, boardSizeY);
        this.statistics = new Statistics();
    }
    
    public void start() {
        gameStarted = true;
        isPaused = false;
        time.startTime();
    }

    public Game newGame(int boardSizeX, int boardSizeY) {
        return new Game(boardSizeX, boardSizeY);
    }

    public Game restart() {
        return new Game(this.board.getBoardSizeX(), this.board.getBoardSizeY());
    }

    public void pause() {
        this.time.pause();
        isPaused = true;
    }
    
    public void resume() {
        this.time.resume();
        isPaused = false;
    }

    public boolean isPaused() {
        return this.isPaused;
    }
    
    public boolean checkWord(String word) {
        return !getFoundWords().contains(word) && this.dictionary.checkWord(word);
    }

    public void addWord(String word) {
        this.foundWords.add(word);
        this.updateScore();
    }

    public void updateScore() {
        this.score.updateScore(foundWords);
    }
    
    public int getCurrentScore() {
        return this.score.getCurrentScore();
    }
    
    public char[][] getDice() {
        return this.board.getBoard();
    }

    public ArrayList<String> findAllWords() {
        return this.board.findAllWords();
    }
    
    public ArrayList<String> getFoundWords() {
        return this.foundWords;
    }
    
    public String toString() {
        return this.board.toString();
    }
    
    public void setTimeListener(Time.TimeListener listener) {
        this.time.setTimeListener(listener);
    }
    
    public String getBestWord() {
        return this.score.getBestWord();
    }

    public int getTimeLeft(){
        return time.getTimeLeft();
    }

    // Methods for Statistics access
    public void addStatisticsEntry(StatisticsEntry entry){
        statistics.addEntry(entry);
    }

    public ArrayList<Achievement> getAchievements(){
        return statistics.getAchievements();
    }

    public void printStatistics(){
        statistics.print();
    }

    public void printAchievements(){
        statistics.printAchievements();
    }
}
