package gui_library;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        String buttonText = (value != null && value.equals("Added")) ? "Added" : "Add";
        setText(buttonText);

        if (value != null && value.equals("Added")) {
            setBackground(new Color(0xB0A695));
        } else {
            setBackground(new Color(0xE5E1DA));
        }

        return this;
    }
}
