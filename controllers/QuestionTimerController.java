package controllers;

import animation.GameState;
import gui_panels.PlayGamePanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import question.QuestionTimer;

/**
 * This class is used to control how the timer for each question works...
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class QuestionTimerController implements ActionListener {

    private final QuestionTimer questionTimerModel;
    private PlayGamePanel gameView;
    private final GameState gameState;

    private final Color INTIAL_TIMER_COLOR = new Color(63, 255, 202);

    public QuestionTimerController(GameState gameState) {
        this.gameView = null;
        this.gameState = gameState;
        questionTimerModel = new QuestionTimer(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (questionTimerModel.getCounter() > 0) {
            questionTimerModel.decrementCounter();
        }

        if (questionTimerModel.getCounter() <= 0) {
            questionTimerModel.stopTimer();
            questionTimerModel.resetCounter();
            gameState.updateRecords();
            gameState.setLifeLineUsedThisRound(false);
            gameState.goToMainMenu();
        }
    }

    public QuestionTimer getQuestionTimerModel() {
        return this.questionTimerModel;
    }
    
    public void setGameView(PlayGamePanel gameView) {
        this.gameView = gameView;
    }
}
