package Tests;

import Model.Dictionary;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Overv
 * Date: 20-10-13
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class DictionaryTest {
    @Test
    public void testCheckWord() throws Exception {
        Dictionary dictionary = Dictionary.getInstance();

        assert dictionary.checkWord("car");
        assert !dictionary.checkWord("foo");
        assert !dictionary.checkWord("fuck");
    }
}
