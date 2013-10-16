package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 9:06 PM
 * Pattern: Signleton
 */
public class Dictionary {
    
    private static Dictionary instance = null;
    
    private static String pathToDictionaryFile = "src/Resources/wordlist_english.txt";
    private static Set<String> wordList = null;

    // Initializer for the Singleton
    public static Dictionary getInstance() {
        if(instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }
    
    // Creates a new dictionary and fills it with the words form the wordlist file specified above
    protected Dictionary() {
        // Try to fill the wordlist
        if(fillWordList()) {
            System.out.println("Filled wordlist from: " + pathToDictionaryFile);
        } else {
            System.out.println("Could not fill wordlist.");
        }
    }
    
    private boolean fillWordList() {
        File f = new File(pathToDictionaryFile);
        BufferedReader br = null;
        List words = new ArrayList<String>();
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(f));
            while ((sCurrentLine = br.readLine()) != null) {
                 words.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        wordList = new HashSet<String>(words);
        return true;
    }
    
    public String toString() {
        if(wordList != null) {
            return wordList.toString();
        } else {
            System.out.println("Wordlist is empty.");
            return null;
        }
    }
}
