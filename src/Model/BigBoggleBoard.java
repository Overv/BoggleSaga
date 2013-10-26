package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/19/13
 * Time: 12:42 PM
 */
public class BigBoggleBoard extends Board{
    
    private String dice[] = {"aaafrs", "aaeeee", "aafirs", "adennn", "aeeeem", 
            "aeegmu", "aegmnn", "afirsy", "bjkqxz", "ccenst",
            "ceiilt", "ceilpt", "ceipst", "ddhnot", "dhhlor",
            "dhlnor", "dhlnor", "eiiitt", "emottt", "ensssu",
            "fiprsy", "gorrvw", "iprrry", "nootuw", "ooottu"};
    
    public BigBoggleBoard() {
        super(5, 5);
        generateBoard(dice);
    }
}
