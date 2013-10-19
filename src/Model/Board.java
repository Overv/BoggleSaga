package Model;

import java.util.Collections;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:00 PM
 */
public abstract class Board {

    Random rand;
    
    protected int boardSizeX;
    protected int boardSizeY;
    protected int boardSize;
    protected char[][] board;
    
    public Board(int boardSizeX, int boardSizeY) {
        this.rand = new Random();
        
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.boardSize = boardSizeX * boardSizeY;
        this.board = new char[boardSizeX][boardSizeY];
        generateBoard();
    }
    
    // Varies for each board
    public abstract void generateBoard();

    protected static String[] shuffleArray(String[] arIn) {
        String[] ar = arIn;
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
    
    public String toString() {
        String res = "";
        for(int i=0; i<boardSizeX; i++) {
            for(int j=0; j<boardSizeY; j++) {
                res += board[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }

}
