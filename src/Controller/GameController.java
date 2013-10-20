package Controller;

import Model.Dictionary;
import Model.Time;
import View.GameFrame;
import Model.Game;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 18/10/13
 * Time: 08:39
 */
public class GameController implements GameFrame.OnWordListener, Time.TimeListener {
    GameFrame gameView;
    Game gameModel;

    public GameController(GameFrame gameView, int x, int y) {
        this.gameView = gameView;
        this.gameView.setOnWordListener(this);
        gameModel = new Game(x, y);
        gameModel.setTimeListener(this);
    }

    public void restartGame(){
        gameModel = gameModel.restart();
        gameModel.setTimeListener(this);
        gameView.setDices(gameModel.getDice());
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

    @Override
    public void onWord(String word) {
        if(Dictionary.getInstance().checkWord(word)){
            gameModel.addWord(word);
            gameView.setScore(gameModel.getCurrentScore());
        }
    }

    @Override
    public void timesUp(){
        gameView.setScore(gameModel.getCurrentScore());
        gameView.setWordsFound((String[])gameModel.getFoundWords().toArray());
        gameView.setTimeLeft(gameModel.getTimeLeft());
    }

    @Override
    public void onTimePassed(int timeLeft){
        gameView.setTimeLeft(timeLeft);
    }
}
