package View;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Custom list renderer that colors words that were found green and others red.
 */
public class CompleteWordListRenderer extends NoSelectListRenderer {
    private ArrayList<String> foundWords;

    public CompleteWordListRenderer(ArrayList<String> foundWords) {
        setOpaque(false);

        this.foundWords = foundWords;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setFont(new Font("Arial", Font.PLAIN, 20));

        if (foundWords.contains(value.toString())) {
            setForeground(Color.decode("#56A739"));
            setText(value.toString().toUpperCase());
        } else {
            setForeground(Color.decode("#D72828"));
            setText(value.toString());
        }

        return this;
    }
}
