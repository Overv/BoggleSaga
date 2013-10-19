package View;

/**
 * Created with IntelliJ IDEA.
 * User: Overv
 * Date: 20-10-13
 * Time: 1:16
 * To change this template use File | Settings | File Templates.
 */
public class DiceCoord {
    public int x;
    public int y;

    public DiceCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object other) {
        if (other instanceof DiceCoord) {
            DiceCoord that = (DiceCoord) other;
            return this.x == that.x && this.y == that.y;
        } else {
            return false;
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
