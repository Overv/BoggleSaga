package Model;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Overv
 * Date: 20-10-13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class TimeTest {
    @Test
    public void testSetTimeListener() throws Exception {
        Time time = new Time();
        time.startTime();

        assert time.getTimeLeft() == 60;

        Thread.sleep(1000);

        assert time.getTimeLeft() == 59;
    }
}
