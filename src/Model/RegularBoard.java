package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/19/13
 * Time: 12:42 PM
 */
public class RegularBoard extends Board{

    private String[] dice = {"arelsc", "tabiyl","ednswo","biofxr",
            "mcdpae","ihfyee","ktdnuo","moqajb",
            "eslupt","invtge","zndvae","ukgely",
            "ocatai","ulgwir","sphein","msharo"};

    public RegularBoard() {
        super(4,4);
        generateBoard(dice);
    }
}
