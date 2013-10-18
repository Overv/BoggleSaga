package Controller;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 18/10/13
 * Time: 09:08
 */
public interface IGameController {
    public void submitWord(String word);    // Word from typing

    public void startGame();    // Starts the time and enables the user to play.
    public void pauseGame();    // Pauses timer
    public void stopGame();     // Stops and resets game

    public void startDrag();    // Indicate mousedown for start drag
    public void drag(int cubeIndex);    // Indicate mouseover lettercube
    public void stopDrag();     // Indicate mouseup for end drag
}
