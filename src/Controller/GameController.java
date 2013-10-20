package Controller;

import Model.Dictionary;
import Model.IGameModel;
import Model.TimeObserverStub;
import View.GameViewInterfaceStub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 18/10/13
 * Time: 08:39
 */
public class GameController implements GameControllerInterface, TimeObserverStub {
    GameViewInterfaceStub gameView;
    IGameModel gameModel;

    ArrayList<Integer> dragList;

    public GameController(IGameModel gameModel, GameViewInterfaceStub gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

        dragList = new ArrayList<Integer>();
        //register as observer for time
        //gameModel.registerObserver(this);  //disabled until able to register
    }

    @Override
    public void submitWord(String word) {
        if(Dictionary.getInstance().checkWord(word)){
            //gameModel.addToWordlist(word);
        }
    }

    @Override
    public void newBoggleGame() {
        gameModel.newGame(4,4);
        startGame();
    }

    @Override
    public void newBigBoggleGame() {
        gameModel.newGame(5,5);
        startGame();
    }

    private void startGame() {
        gameView.showGameElements();
        gameModel.startGame();
    }

    @Override
    public void pauseGame() {
        /*if(!gameModel.isPaused()) {
            gameModel.pauseTime();
            gameView.hideGameElements();
        }*/
    }

    @Override
    public void resumeGame() {
        /*if(gameModel.isPaused()){
            gameView.showGameElements();
            gameModel.resumeTime();
        }*/
    }

    @Override
    public void stopGame() {
        //gameModel.restart;? gameView.hideGameElements();
    }

    @Override
    public void startDrag() {
        dragList.clear();
    }

    @Override
    public void drag(int cubeIndex) {
        int len = dragList.size();
        if(dragList.contains(cubeIndex)){ // cubeIndex already in dragList
            if(len > 1 && dragList.get(len-2)==cubeIndex){ //same as second to last
                gameView.unhighlight(dragList.get(len-1)); //unhightlight last
                dragList.remove(len-1); //remove last
            }
            else if(dragList.get(len-1) == cubeIndex){
                //do nothing since its repeat of last cubeIndex
            }
            else {  //failed to connect to last lettercube so remove all
                dragList.clear();
                gameView.unhighlightAll();
            }
        }
        else { // cubeIndex not yet in dragList
            //add width!
            if(len == 0 /*|| adjacent(cubeIndex, dragList.get(len-1), width)*/) {
                dragList.add(cubeIndex);
                gameView.highlight(cubeIndex);
            }
            else { //failed to connect to last lettercube so remove all
                dragList.clear();
                gameView.unhighlightAll();
            }
        }
    }

    private boolean adjacent(int cubeIndex, int lastCubeIndex, int width){
        int x = cubeIndex % width;
        int y = cubeIndex / width;
        int lastx = lastCubeIndex % width;
        int lasty = lastCubeIndex / width;
        return Math.abs(x-lastx)<=1 && Math.abs(y-lasty)<=1;
    }

    @Override
    public void stopDrag() {
        String word = "";
        Iterator<Integer> it = dragList.iterator();
        while(it.hasNext()){
            //word += gameModel.getGame().getBoard().getLetter(it.next());
        }

        submitWord(word);
        dragList.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(gameModel.getTimeLeft() <= 0){ //stop game
            //model stops itself?
            //gameView.hideGameElements();
            //gameView.showScoreScreen();
            dragList.clear();
        }
    }
}
