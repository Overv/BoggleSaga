package Model;

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

    public Game(int boardSizeX, int boardSizeY) {
        this.score = new Score();
        this.time = new Time();
        this.boardFactory = new BoardFactory();
        this.dictionary = new Dictionary();
        this.board = boardFactory.createBoard(boardSizeX, boardSizeY);
    }
    
    public void start() {
        gameStarted = true;
    }
    
    public boolean restart() {
        return true;
    }
    
    public Time getTime() {
        return this.time;
    }
    
    public Score getScore() {
        return this.score;
    }
    
    public Dictionary getDictionary() {
        return this.dictionary;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
}
