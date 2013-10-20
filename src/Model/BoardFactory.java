package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/19/13
 * Time: 12:39 PM
 * Pattern: Factory
 */
public class BoardFactory {
    
    // Returns an instance of the Board class, depending on the size
    // of the sizeX and sizeY, specified in the createBoard method
    public Board createBoard(int sizeX, int sizeY) {
        if(sizeX == 4 && sizeY == 4) {
            return new RegularBoard();
        } else if(sizeX == 5 && sizeY == 5) {
            return new BigBoggleBoard();
        } else {
            System.out.println("This board size is not supported.");
            return null;
        }
    }
}
