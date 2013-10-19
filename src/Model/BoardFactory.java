package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/19/13
 * Time: 12:39 PM
 */
public class BoardFactory {
    
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
