package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Overv
 * Date: 10-11-13
 * Time: 3:03
 * To change this template use File | Settings | File Templates.
 */
public class NoSelectListRenderer extends DefaultListCellRenderer {
    public int getHorizontalAlignment() {
        return CENTER;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, false, false);
        return this;
    }
}
