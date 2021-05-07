/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import animation.GameState;
import life_lines.PhoneAFriend;
import life_lines.AskTheAudience;
import life_lines.FiftyFifty;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import gui_styling.OnHoverButton;
import life_lines.AbstractPlayerGameHelp;

/**
 * This class is used to represent the lifeline buttons the class takes into
 * account that the button should no longer be able to be clicked / used after
 * it has already been used
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class LifeLinePanel extends JPanel {

    private final FiftyFifty fiftyFifty;
    private final AskTheAudience askTheAudience;
    private final PhoneAFriend phoneAFriend;
    private final Color defaultBackgroundColor;
    private final Color onHoverColor;
    private final Color panelBackgroundColor;
    private final Color usedButtonColor;
    private OnHoverButton fiftyFiftyHover;
    private OnHoverButton phoneAFriendHover;
    private OnHoverButton askTheAudienceHover;
    private final GameState gameState;

    public LifeLinePanel(Dimension d, GameState gameState, Color defaultBackgroundColor, Color onHoverColor, Color panelBackgroundColor, Color usedButtonColor) {
        this.gameState = gameState;
        this.defaultBackgroundColor = defaultBackgroundColor;
        this.onHoverColor = onHoverColor;
        this.panelBackgroundColor = panelBackgroundColor;
        this.usedButtonColor = usedButtonColor;

        //Html used to create new line in button
        fiftyFifty = new FiftyFifty(new JButton("<html>Fifty<br>Fifty<html/>"));
        phoneAFriend = new PhoneAFriend(new JButton("<html>Phone<br>A Friend<html/>"));
        askTheAudience = new AskTheAudience(new JButton("<html>Ask the<br> Audience<html/>"));

        //Remove focusable
        fiftyFifty.getButton().setFocusable(false);
        phoneAFriend.getButton().setFocusable(false);
        askTheAudience.getButton().setFocusable(false);

        //Set size of each button
        fiftyFifty.getButton().setMaximumSize(new Dimension(100, 60));
        phoneAFriend.getButton().setMaximumSize(new Dimension(100, 60));
        askTheAudience.getButton().setMaximumSize(new Dimension(100, 60));

        //Set color information
        setUnclickedColor();

        //Add the hover for the button note, the hover action events need to be
        //tracked for removal when a button is pressed
        initialiseLifeLineHoverStyling();

        //Add the on click even for the button
//        fiftyFifty.getButton().addActionListener(new OnClick());
//        phoneAFriend.getButton().addActionListener(new OnClick());
//        askTheAudience.getButton().addActionListener(new OnClick());

        // pack panel with buttons and style panel
        super.setSize(d);
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        super.add(fiftyFifty.getButton(), BoxLayout.X_AXIS);
        super.add(Box.createRigidArea(new Dimension(20, 0)), BoxLayout.X_AXIS);
        super.add(phoneAFriend.getButton(), BoxLayout.X_AXIS);
        super.add(Box.createRigidArea(new Dimension(20, 0)), BoxLayout.X_AXIS);
        super.add(askTheAudience.getButton(), BoxLayout.X_AXIS);
        super.setBackground(panelBackgroundColor);
    }

    public FiftyFifty getFiftyFiftyHelper() {
        return fiftyFifty;
    }

    public AskTheAudience getAskTheAudienceHelper() {
        return askTheAudience;
    }

    public PhoneAFriend getPhoneAFriendHelper() {
        return phoneAFriend;
    }

    /**
     * Bind the on hover styling for the buttons
     */
    private void initialiseLifeLineHoverStyling() {

        fiftyFiftyHover = new OnHoverButton(fiftyFifty.getButton(), defaultBackgroundColor, onHoverColor);
        fiftyFifty.getButton().addMouseListener(fiftyFiftyHover);

        phoneAFriendHover = new OnHoverButton(phoneAFriend.getButton(), defaultBackgroundColor, onHoverColor);
        phoneAFriend.getButton().addMouseListener(phoneAFriendHover);

        askTheAudienceHover = new OnHoverButton(askTheAudience.getButton(), defaultBackgroundColor, onHoverColor);
        askTheAudience.getButton().addMouseListener(askTheAudienceHover);
    }

    /**
     * This method adds the mouse listeners to the lifelines for changing the
     * lifelines colors on hover note the mouse listeners are tracked to be
     * removed later
     */
    private void addOnHoverLifeLineStyling() {
        if (fiftyFifty.isUsed()) {
            fiftyFiftyHover = new OnHoverButton(fiftyFifty.getButton(), defaultBackgroundColor, onHoverColor);
            fiftyFifty.getButton().addMouseListener(fiftyFiftyHover);
        }

        if (phoneAFriend.isUsed()) {
            phoneAFriendHover = new OnHoverButton(phoneAFriend.getButton(), defaultBackgroundColor, onHoverColor);
            phoneAFriend.getButton().addMouseListener(phoneAFriendHover);
        }

        if (askTheAudience.isUsed()) {
            askTheAudienceHover = new OnHoverButton(askTheAudience.getButton(), defaultBackgroundColor, onHoverColor);
            askTheAudience.getButton().addMouseListener(askTheAudienceHover);
        }
    }

    /**
     * This method is used to set the initial colors of the buttons
     */
    private void setUnclickedColor() {
        fiftyFifty.getButton().setBackground(defaultBackgroundColor);
        fiftyFifty.getButton().setForeground(new Color(255, 255, 255));
        phoneAFriend.getButton().setBackground(defaultBackgroundColor);
        phoneAFriend.getButton().setForeground(new Color(255, 255, 255));
        askTheAudience.getButton().setBackground(defaultBackgroundColor);
        askTheAudience.getButton().setForeground(new Color(255, 255, 255));
    }

    /**
     * This method resets the lifelines styling to the initial unclicked state
     */
    public void resetLifeLineStyling() {
        setUnclickedColor();
        addOnHoverLifeLineStyling();
    }

    /**
     * private helper class used to remove the action listener rendering the
     * button not usable after it has been used i.e. after its first click
     */
//    private class OnClick implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent event) {
//            if (!gameState.isLifeLineUsedThisRound()) {
//                if (event.getSource() == fiftyFifty.getButton()) {
//                    fiftyFifty.getButton().removeMouseListener(fiftyFiftyHover);
//                    fiftyFifty.getButton().setBackground(usedButtonColor);
//                } else if (event.getSource() == phoneAFriend.getButton()) {
//                    phoneAFriend.getButton().removeMouseListener(phoneAFriendHover);
//                    phoneAFriend.getButton().setBackground(usedButtonColor);
//                } else if (event.getSource() == askTheAudience.getButton()) {
//                    askTheAudience.getButton().removeMouseListener(askTheAudienceHover);
//                    askTheAudience.getButton().setBackground(usedButtonColor);
//                }
//            }
//        }
//    }

    public void buttonClicked(AbstractPlayerGameHelp lifeLine) {
        if (lifeLine instanceof FiftyFifty) {
            fiftyFifty.getButton().removeMouseListener(fiftyFiftyHover);
            fiftyFifty.getButton().setBackground(usedButtonColor);
        } else if (lifeLine instanceof PhoneAFriend) {
            phoneAFriend.getButton().removeMouseListener(phoneAFriendHover);
            phoneAFriend.getButton().setBackground(usedButtonColor);
        } else if (lifeLine instanceof AskTheAudience) {
            askTheAudience.getButton().removeMouseListener(askTheAudienceHover);
            askTheAudience.getButton().setBackground(usedButtonColor);
        }
    }
}
