package Model;

import java.util.ArrayList;
import java.util.Observable;

import Model.Settings.GameType;
import Model.Achievements.Achievement;
import Model.Achievements.AchievementListener;
import Model.Achievements.AchievementManager;
import Model.Achievements.Statistics;
import Model.Achievements.StatisticsEntry;

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
    private AchievementManager achievementManager;

    public Game(int boardSizeX, int boardSizeY) {
        this.score = new Score();
        this.time = new Time();
        this.boardFactory = new BoardFactory();
        this.dictionary = Dictionary.getInstance();
        this.foundWords = new ArrayList<String>();
        this.board = boardFactory.createBoard(boardSizeX, boardSizeY);
        this.statistics = new Statistics();
        this.addTimeListener(statistics);
        this.achievementManager = new AchievementManager(this.statistics, Settings.getGameType());
        this.time.addTimeListener(achievementManager);
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
    
    public void addTimeListener(Time.TimeListener listener) {
        this.time.addTimeListener(listener);
    }
    
    public String getBestWord() {
        return this.score.getBestWord();
    }

    public int getTimeLeft(){
        return time.getTimeLeft();
    }

    // Methods for Statistics access
    public void addStatisticsEntry(int timeLeft, String word, boolean wordCorrect, GameType type){
        statistics.addEntry(new StatisticsEntry(timeLeft, word, wordCorrect, type, statistics.isNew(word)));
    }
    
    public void addAchievementListener(AchievementListener listener){
    	achievementManager.addAchievementListener(listener);
    }

    public ArrayList<Achievement> getAchievements(){
        return achievementManager.getAchievements();
    }

    public void printStatistics(){
        statistics.print();
    }
}
