package controllers;

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
    private final PlayGamePanel gameView;

    private final Color INTIAL_TIMER_COLOR = new Color(63, 255, 202);

    public QuestionTimerController(PlayGamePanel gameView) {
        this.gameView = gameView;
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

            gameView.getGameState().updateRecords();
            gameView.getGameState().setLifeLineUsedThisRound(false);
            gameView.getGameState().goToMainMenu();
        }
    }

    public QuestionTimer getQuestionTimerModel() {
        return this.questionTimerModel;
    }
}
