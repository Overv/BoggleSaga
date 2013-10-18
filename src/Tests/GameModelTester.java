package Tests;

import Model.GameModel;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 9:58 PM
 */
public class GameModelTester {
    
    public static void main(String args[]) {
        GameModel gm = new GameModel();
        gm.startGame(4, 4);
        System.out.print(gm.getGame().getBoard().toString());
    }
}
