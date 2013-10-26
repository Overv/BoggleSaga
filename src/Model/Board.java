package Model;

import java.util.ArrayList;
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
    }
    
    public char[][] getBoard() {
        return this.board;
    }
    
    public int getBoardSizeX() {
        return this.boardSizeX;
    }

    public int getBoardSizeY() {
        return this.boardSizeY;
    }
    
    // Generates a board, independent of the size of the board
    public void generateBoard(String[] dice) {
        // shuffle dice
        String[] shuffled4x4 = shuffleArray(dice);
        // fill the board with the generated letters
        int counter = 0;
        for(int i=0; i<boardSizeX; i++) {
            for(int j=0; j<boardSizeY; j++) {
                board[i][j] = shuffled4x4[counter].charAt(rand.nextInt(5)); // Convert the String[] to a char[]
                counter ++;
            }
        }
    }

    private ArrayList<String> allWords;

    // Computationally intensive (up to 100 ms), don't run in UI thread
    // Result is cached
    public ArrayList<String> findAllWords() {
        if (allWords == null) {
            allWords = new ArrayList<String>();

            // Check for every word if it's contained in the board
            for (String word : Dictionary.getInstance().getWords()) {
                if (word.length() >= 3 && containsWord(word)) {
                    allWords.add(word);
                }
            }
        }

        return allWords;
    }

    public boolean containsWord(String word) {
        // Check for every occurence of the first letter if the word can be found from there
        for (int x = 0; x < boardSizeX; x++) {
            for (int y = 0; y < boardSizeY; y++) {
                if (board[x][y] == word.charAt(0) && wordStarts(word, new DiceCoord(x, y), new ArrayList<DiceCoord>())) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean wordStarts(String word, DiceCoord coord, ArrayList<DiceCoord> visited) {
        visited = new ArrayList<DiceCoord>(visited);
        visited.add(coord);

        // If we've reached the end, the word is contained
        if (word.length() == 0) {
            return true;
        }

        // Check if given word starts here
        if (board[coord.x][coord.y] != word.charAt(0)) {
            return false;
        }

        // Look for next letter in surrounding dices
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                DiceCoord neighbour = new DiceCoord(coord.x + dx, coord.y + dy);

                if (neighbour.x < 0 || neighbour.y < 0 || neighbour.x >= boardSizeX || neighbour.y >= boardSizeY) {
                    continue;
                }

                if (!visited.contains(neighbour) && wordStarts(word.substring(1), neighbour, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    // We chose to reimplement a shuffle array function because even though there is a 
    // Collections.shuffle function, it only shuffles lists. If we would have to convert 
    // the array to a list and back, this takes more time then doing it this way. 
    // The shuffle algorithm is taken from Stack Overflow: http://tinyurl.com/onake63
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
    
    // Returns a matrix-like representation of the board
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
