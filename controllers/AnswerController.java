package controllers;

import animation.GameState;
import gui_components.AnswerButtons;
import gui_panels.PlayGamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import player.Player;
import question.Question;
import question.QuestionList;

/**
 * This class is used to represent to control when a answer is clicked within
 * the game view, this class also acts as the model... thus it is also the
 * observer (if the button clicked was correct)
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class AnswerController extends Observable implements ActionListener {

    private final GameState gameState;
    private PlayGamePanel gameView;
    private AnswerButtons answerView;
    private QuestionList questionList;
    private boolean answerModel; // models if the answer was correct or not
    private final Question currentQuestion;

    public AnswerController(GameState gameState) {
        this.gameState = gameState;
        this.gameView = null;
        this.answerView = null;
        this.answerModel = true;
        questionList = QuestionList.getQuestionListInstance();
        currentQuestion = questionList.getQuestion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameState.setLifeLineUsedThisRound(false);
        gameView.setQuestionAsked(questionList.getQuestion());
        
        if (!(answerView.getCorrectButton() == e.getSource())) { // exit the game
            answerModel = false;
            questionList.reset();
            setChanged();
            notifyObservers(this);
            gameState.updateRecords();
            gameState.goToMainMenu();
            questionList.reset();
        } else { // increment score and change the question
            answerModel = true;
            setChanged();
            notifyObservers(this);
            Player p = gameState.getPlayer();
            p.setHighscore(p.getCurrentHighscore() + 1);
        }

        if (questionList.getIndex() > questionList.getLength()) {
            answerModel = false;
            questionList.reset();
            setChanged();
            notifyObservers(this);
            gameState.updateRecords();
            gameState.goToMainMenu();
        }
    }

    public boolean isCorrectChoice() {
        return answerModel;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setGameView(PlayGamePanel gameView) {
        this.gameView = gameView;
        this.answerView = gameView.getAnswersBtns();
    }
}
