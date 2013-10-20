package View;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 20/10/13
 * Time: 00:01
 */
public interface GameViewInterfaceStub {
    public void showGameElements();
    public void hideGameElements();

    //hightlight for lettercubes (or connect with lines)?
    void unhighlight(Integer cubeIndex);
    void highlight(Integer cubeIndex);
    void unhighlightAll();
    void highlightAll();
}
