import Controller.GameController;
import View.GameFrame;

public class Main {
    private static GameFrame view;
    private static GameController controller;

    public static void main(String[] args) {
        // Create components
        view = new GameFrame();

        // Temporary setup code
        view.setTimeLeft(0);
        view.setScore(0);

        view.setDices(new char[][] {
            {'r', 'y', 'w', 't'},
            {'h', 'p', 'n', 'e'},
            {'r', 'c', 's', 'g'},
            {'e', 's', 'n', 'o'}
        });

        view.setWordsGuessed(new String[] {"wench", "once"});

        view.setOnWordListener(new GameFrame.OnWordListener() {
            @Override
            public void onWord(String word) {
                System.out.println("Potential word selected: " + word);
            }
        });

        controller = new GameController(view, 4, 4);

        controller.startGame();

    }
}
