package gui_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 * This class is used to create a field for the username to be entered
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class UsernameField extends JPanel {

    private JFormattedTextField usernameEntry;
    private final JLabel usernameLabel;
    private final JButton submit;
    private final int USERNAME_LENGTH = 20;
    private String usernameMask;

    public UsernameField(int width, int height, Color backgroundColor, Color textColor) {

        try {
            createMask();
            MaskFormatter formatStyle = new MaskFormatter(usernameMask);
            usernameEntry = new JFormattedTextField(formatStyle);
        } catch (ParseException e) {
            System.err.println(e);
        }

        // Set username text field for entry
        usernameEntry.setColumns(USERNAME_LENGTH);
        usernameEntry.setSize(new Dimension(1000, 100));
        usernameEntry.setBackground(backgroundColor);
        usernameEntry.setForeground(textColor);
        usernameEntry.setCaretColor(Color.white);
        usernameEntry.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                usernameEntry.setCaretPosition(0);
            }
        });

        // Set JLabel for username input
        usernameLabel = new JLabel("Please enter username here (20 characters max):");
        usernameLabel.setSize(400, 100);
        usernameLabel.setForeground(textColor);
        usernameLabel.setFont(new Font("", Font.BOLD, 16));

        // create a submission button
        submit = new JButton("Submit");
        submit.setMaximumSize(new Dimension(200, 100));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check if info is what is expected
                //if it isnt we must display something
            }
        });
        submit.setBackground(textColor);
        submit.setForeground(Color.white);
        submit.setFocusable(false);

        super.setSize(width, height);
        super.add(usernameLabel);
        super.add(usernameEntry);
        super.add(submit);
        super.setOpaque(false);
    }

    /**
     * Helper class to create a mask for the amount of characters for a
     * username.
     */
    private void createMask() {
        usernameMask = "";
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            usernameMask += "*";
        }
    }
}
