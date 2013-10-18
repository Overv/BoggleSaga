package Controller;

import Model.IGameModel;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 18/10/13
 * Time: 08:39
 */
public class GameController implements IGameController { //implements TimeObserver oid

    IGameModel gameModel;

    public GameController(IGameModel gameModel) { // add view
        this.gameModel = gameModel;
    }

    @Override
    public void submitWord(String word) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startGame() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pauseGame() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stopGame() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startDrag() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void drag(int cubeIndex) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stopDrag() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
