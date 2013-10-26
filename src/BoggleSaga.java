import Controller.GameController;
import Model.Settings;
import View.GameFrame;

import java.util.ArrayList;

public class BoggleSaga {
    public static void main(String[] args) {
        // Load settings
        Settings.loadSettings();

        // Create components
        GameController controller = new GameController();

        // Start game
        controller.startGame();
    }
}
