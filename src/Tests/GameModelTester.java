package Tests;

import Model.Game;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 9:58 PM
 */
public class GameModelTester {
    
    public static void main(String args[]) {
        Game game = new Game(4, 4);
        game.startGame();
        System.out.print(game.toString());
    }
}
