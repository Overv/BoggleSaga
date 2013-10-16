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
            System.out.println("Ammount of words: " + dts.split(",").length);
            System.out.println(dts);
        }
        
        // test validWord
        Boolean a = d.checkWord("hello");
        Boolean b = d.checkWord("aLiGatOR"); //TODO: This gives false --> wordlist is not good enough
        Boolean c = d.checkWord("fuck"); // This gives false --> good, swear words not included
        System.out.println(a.toString() + b.toString() + c.toString());
    }
}
