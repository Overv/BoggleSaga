package Model;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 9:06 PM
 * Pattern: Signleton
 */
public class Dictionary {
    
    private static Dictionary instance = null;
    
    private static String pathToDictionaryFile = "Resources/wordlist_english.txt";
    private static HashSet<String> wordList;

    // Initializer for the Singleton
    public static Dictionary getInstance() {
        if(instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }
    
    protected Dictionary() {
        
        
    }

}
