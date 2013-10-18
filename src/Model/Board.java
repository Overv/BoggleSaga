package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:00 PM
 */
public class Board {

    private int boardSizeX;
    private int boardSizeY;
    private int[][] board;

    public Board(int boardSizeX, int boardSizeY) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.board = new int[boardSizeX][boardSizeY];
    }

}
