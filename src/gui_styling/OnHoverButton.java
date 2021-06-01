package gui_styling;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * This class is used to change the color of buttons when
 * the mouse enter (hovers) and leaves (return back to original color)
 * mainly used to make buttons pop out more.
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class OnHoverButton extends MouseAdapter {

    private JButton button;
    private Color defaultColor;
    private Color onHoverColor;

    public OnHoverButton(JButton button, Color defaultColor, Color onHoverColor) {
        this.button = button;
        this.defaultColor = defaultColor;
        this.onHoverColor = onHoverColor;
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        button.setBackground(onHoverColor);
    }

    @Override
    public void mouseExited(MouseEvent event) {

        button.setBackground(defaultColor);

    }
}
