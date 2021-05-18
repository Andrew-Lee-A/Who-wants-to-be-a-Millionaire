package controllers;

import animation.GameState;
import gui_components.AnswerButtons;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import player.Player;
import question.Question;

/**
 * This class is used to represent to control when a answer is clicked within
 * the game view, this class also acts as the model... thus it is also the observer
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class AnswerController extends Observable implements ActionListener {
    private GameState gameState;
    private AnswerButtons answerView;
    private ArrayList<Question> questionModel;
    private int currentQuestion;
    
    public AnswerController(AnswerButtons answerView, GameState gameState) {
        this.gameState = gameState;
        this.answerView = answerView;
        this.questionModel = new ArrayList<>();
        currentQuestion = 0;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gameState.setLifeLineUsedThisRound(false);

        if (!(answerView.getCorrectButton() == e.getSource())) { // exit the game
            resetPanel();
            gameState.updateRecords();
            gameState.goToMainMenu();
        } else { // increment score and change the question
            answerView.setAnswers(questionModel.get(++currentQuestion).getAnswers());
            questionLabel.setText(questionModel.get(currentQuestion).getText());
            questionTimer.resetCounter();
            timerLabel.setText(questionTimer.getCounter().toString());
            timerLabel.setForeground(INTIAL_TIMER_COLOR);

            Player p = gameState.getPlayer();
            p.setHighscore(p.getCurrentHighscore() + 1);
        }
    }
}
