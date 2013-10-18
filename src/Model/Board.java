package Model;

import java.util.Collections;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:00 PM
 */
public class Board {

    Random rand;
    
    private int boardSizeX;
    private int boardSizeY;
    private int boardSize;
    private char[][] board;
    
    public static String[] dice4x4 = {"ARELSC", "TABIYL","EDNSWO","BIOFXR",
                                      "MCDPAE","IHFYEE","KTDNUO","MOQAJB",
                                      "ESLUPT","INVTGE","ZNDVAE","UKGELY",
                                      "OCATAI","ULGWIR","SPHEIN","MSHARO"};

    public Board(int boardSizeX, int boardSizeY) {
        this.rand = new Random();
        
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.boardSize = boardSizeX * boardSizeY;
        this.board = new char[boardSizeX][boardSizeY];
        generateBoard();
    }
    
    private void generateBoard() {
        if(boardSize == 16) {
            // shuffle dice
            String[] shuffled4x4 = shuffleArray(dice4x4); 
            // fill the board with the generated letters
            int counter = 0;
            for(int i=0; i<boardSizeX; i++) {
                for(int j=0; j<boardSizeY; j++) {
                    board[i][j] = shuffled4x4[counter].charAt(rand.nextInt(5)); // Convert the String array to a char array
                    counter ++;
                }
            }
        } else {
            System.out.println("This board size is not supported");
            return;
        }
    }

    private static String[] shuffleArray(String[] arIn) {
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
