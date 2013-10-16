package Tests;

import Model.Dictionary;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 9:45 PM
 */
public class DictionaryTester {
    
    public static void main(String args[]) {
        Dictionary d = Dictionary.getInstance();
        
        // Test toString
        String dts = d.toString();
        if(dts != null) {
            System.out.println("Ammount of words: " + dts.length());
            System.out.println(dts);
        }
    }
}
