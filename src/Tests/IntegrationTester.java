package Tests;

import Model.Game;
import View.GameFrame;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/20/13
 * Time: 3:12 PM
 */
public class IntegrationTester {

    private static GameFrame view;
    private static Game game;
    
    public static void main(String args[]) {
        // Create components
        view = new GameFrame();
        game = new Game(4, 4);

        // Temporary setup code
        view.setTimeLeft(0);
        view.setScore(0);

        view.setDices(game.getDice());

        view.setWordsFound(new String[]{"wench", "once"});

        view.setOnWordListener(new GameFrame.OnWordListener() {
            @Override
            public void onWord(String word) {
                System.out.println("Potential word selected: " + word);
            }
        });
    }
    
}
