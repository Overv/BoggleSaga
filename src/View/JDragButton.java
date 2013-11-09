package View;

import javax.swing.*;
import java.awt.*;

/**
    This is a modification of the standard button that doesn't draw a special background for pressing down.
 */
public class JDragButton extends JButton {
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        setContentAreaFilled(false);
        super.paintComponent(g);
    }
}
