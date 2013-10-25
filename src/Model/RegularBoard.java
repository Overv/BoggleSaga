package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/19/13
 * Time: 12:42 PM
 */
public class RegularBoard extends Board{

    public static String[] dice = {"arelsc", "tabiyl","ednswo","biofxr",
            "mcdpae","ihfyee","ktdnuo","moqajb",
            "eslupt","invtge","zndvae","ukgely",
            "ocatai","ulgwir","sphein","msharo"};

    public RegularBoard() {
        super(4,4);
    }
    
    @Override
    public void generateBoard() { 
        // shuffle dice
        String[] shuffled4x4 = shuffleArray(dice);
        // fill the board with the generated letters
        int counter = 0;
        for(int i=0; i<boardSizeX; i++) {
            for(int j=0; j<boardSizeY; j++) {
                board[i][j] = shuffled4x4[counter].charAt(rand.nextInt(5)); // Convert the String[] to a char[]
                counter ++;
            }
        }
    }
}
