import Controller.GameController;
import Model.Board;
import Model.Settings;
import View.GameFrame;
import View.SplashFrame;

import java.util.ArrayList;

public class BoggleSaga {
    public static void main(String[] args) {
        // Allow user to specify seed for board generation
        if (args.length == 1) {
            Board.setSeed(Integer.valueOf(args[0]));
        }

        // Load settings
        Settings.loadSettings();

        // Show splash screen
        SplashFrame splashFrame = new SplashFrame();

        // Add callback to start main game
        splashFrame.setOnStartGameListener(new SplashFrame.OnStartGameListener() {
            @Override
            public void onStart() {
                // Create components
                GameController controller = new GameController();

                // Start game
                controller.startGame();
            }
        });
    }
}
