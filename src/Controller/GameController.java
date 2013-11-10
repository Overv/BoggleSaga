package Controller;

import Model.*;
import Model.Settings.*;
import Model.Achievements.*;
import View.EndFrame;
import View.GameFrame;
import sun.audio.AudioPlayer;

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

    public GameController() {

        // load model and view based on the size specified in the settings
        if(Settings.getGameType() == GameType.BIGBOGGLE){
            gameModel = new Game(5,5);
            gameView = new GameFrame(5, 5);
        }
        else {
            gameModel = new Game(4,4);
            gameView = new GameFrame(4,4);
        }
        
        this.gameView.setOnWordListener(this);
        this.gameView.setScore(0);
        gameModel.addTimeListener(this);
        gameModel.addAchievementListener(gameView);
    }

    public void startGame(){
        gameModel.start();

        if(Settings.isMusicEnabled())
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
        int timeLeft = gameModel.getTimeLeft();
        boolean wordCorrect = gameModel.checkWord(word);

        //Record statistics about game
        gameModel.addStatisticsEntry(new StatisticsEntry(timeLeft, word, wordCorrect, Settings.getGameType()));

        if(wordCorrect){
            gameModel.addWord(word);
            if(Settings.isSoundEnabled())
                Sound.playFoundWordSound();

            gameView.setScore(gameModel.getCurrentScore());
            gameView.setWordsFound(gameModel.getFoundWords());
        }
    }

    @Override
    public void timesUp(){
        // Add high score if player got points
        if (gameModel.getCurrentScore() > 0) {
            String s = (String) JOptionPane.showInputDialog(gameView, "Please enter your name if you'd like to add yourself to the highscore list:", "Highscore!", JOptionPane.PLAIN_MESSAGE, null, null, "");

            if (s != null && s.trim().length() > 0) {
                Highscore.getInstance().addHighscore(s.trim(), gameModel.getCurrentScore());
            }
        }

        // Show end screen
        new EndFrame(gameModel);

        Sound.stopMusic();

        gameView.setVisible(false);
        gameView.dispose();
    }

    @Override
    public void onTimePassed(int timeLeft){
        gameView.setTimeLeft(timeLeft);
    }
}
