package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:05 PM
 */
public class Game {
    
    private boolean gameStarted = false;
    private Board board;
    private Score score;
    private Time time;

    public Game(Board board) {
        this.board = board;
        this.score = new Score();
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
    
    public Board getBoard() {
        return this.board;
    }
    
}
