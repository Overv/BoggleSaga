package Controller;

import Model.Dictionary;
import View.GameFrame;
import Model.Game;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 18/10/13
 * Time: 08:39
 */
public class GameController implements GameFrame.OnWordListener {
    GameFrame gameView;
    Game gameModel;

    public GameController(GameFrame gameView) {
        this.gameView = gameView;
    }

    public void newGame(){
        gameModel = new Game(4,4);
    }

    public void restartGame(){
        gameModel = gameModel.restart();
    }

    public void startGame(){
        gameModel.start();
    }

    public void pauseGame(){
        gameModel.pause();
    }

    public void resumeGame(){
        gameModel.resume();
    }


    public void run() {

        gameModel = new Game(4, 4);

        for(;;){
            //if(!gameModel.isPaused()){
            gameView.setTimeLeft(gameModel.getTimeLeft());
            gameView.setDices(gameModel.getDice());
            gameView.setWordsFound((String[]) gameModel.getFoundWords().toArray());
            gameView.setScore(gameModel.getCurrentScore());
            //}

            try { //update 100 times a second
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //Do nothing?
            }
        }
    }


    @Override
    public void onWord(String word) {
        if(Dictionary.getInstance().checkWord(word)){
            gameModel.addWord(word);
            gameView.setScore(gameModel.getCurrentScore());
        }
    }
}
