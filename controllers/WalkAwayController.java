package controllers;

import animation.GameState;
import gui_panels.PlayGamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import question.QuestionList;
import question.QuestionTimer;

/**
 * this class is used to represent walk away functionality of the game not this
 * also represents the model as when the button is fired it notifies the view
 * directly
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class WalkAwayController extends Observable implements ActionListener {

    private final QuestionTimer questionTimer; // doesnt dictate the model / view but must be reset and stopped
    private final GameState gameState;
    private final PlayGamePanel gameView;

    public WalkAwayController(PlayGamePanel gameView, GameState gameState, QuestionTimer questionTimer) {
        this.gameView = gameView;
        this.gameState = gameState;
        this.questionTimer = questionTimer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        questionTimer.resetCounter();
        questionTimer.stopTimer();
        gameState.updateRecords();
        gameState.getPlayer().setHighscore(0);
        QuestionList.reset();
        QuestionList questionList = QuestionList.getQuestionListInstance();
        gameView.setQuestionAsked(questionList.getQuestion(questionList.getIndex()));
        questionList.incrementIndex();
        setChanged();
        notifyObservers(this);
        gameState.setLifeLineUsedThisRound(false);
        gameState.goToMainMenu();
    }
}
