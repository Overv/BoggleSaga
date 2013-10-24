package Controller;

import Model.Dictionary;
import Model.Sound;
import Model.Time;
import View.GameFrame;
import Model.Game;

import javax.swing.*;

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
        this.gameView.setScore(0);

        gameModel = new Game(x, y);
        gameModel.setTimeListener(this);
    }

    public void startGame(){
        gameModel.start();
        Sound.playGameSound();
        gameView.setDice(gameModel.getDice());
    }

    public void pauseGame(){
        gameModel.pause();
    }

    public void resumeGame(){
        gameModel.resume();
    }

    @Override
    public void onWord(String word) {
        if(gameModel.checkWord(word)){
            gameModel.addWord(word);
            Sound.playFoundWordSound();

            gameView.setScore(gameModel.getCurrentScore());
            gameView.setWordsFound(gameModel.getFoundWords());
        }
    }

    @Override
    public void timesUp(){
        JOptionPane.showMessageDialog(null, "You found " + gameModel.getFoundWords().size() + " words with a total score of " 
                + gameModel.getCurrentScore() + "!\n" + "Your best word was: " + gameModel.getBestWord() + "!", "Game finished!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    public void onTimePassed(int timeLeft){
        gameView.setTimeLeft(timeLeft);
    }
}
