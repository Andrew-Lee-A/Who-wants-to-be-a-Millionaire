package controllers;

import animation.GameState;
import gui_panels.PlayGamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import life_lines.AbstractPlayerGameHelp;
import life_lines.AskTheAudience;
import life_lines.FiftyFifty;

/**
 * this class is used to represent the relationship between lifelines and the
 * game.
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class LifeLinesController implements ActionListener {

    private final GameState gameState;
    private final PlayGamePanel gameView;

    public LifeLinesController(PlayGamePanel gameView, GameState gameState) {
        this.gameState = gameState;
        this.gameView = gameView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton lifeLineBtn = (JButton) e.getSource();
        AbstractPlayerGameHelp lifeLine = null;
        
        if(gameView.getFiftyFiftyButton() == lifeLineBtn) {
            lifeLine = gameView.getLifeLines()[0];
        } else if (gameView.getAskTheAudienceButton() == lifeLineBtn) {
            lifeLine = gameView.getLifeLines()[1];
        } else if (gameView.getPhoneAFriendButton() == lifeLineBtn) {
            lifeLine = gameView.getLifeLines()[2];
        }
        
        if (!gameState.isLifeLineUsedThisRound()) {
            if (!lifeLine.isUsed()) {
                lifeLine.lifeLineUsed();
                gameState.setLifeLineUsedThisRound(true);
            }
        }
    }

}
