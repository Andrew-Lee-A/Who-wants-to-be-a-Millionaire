/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.Timer;
import question.Question;
import question.QuestionList;

/**
 * This class is used to control how a game will
 * be played within the Who Wants To Be A Millionaire
 * game.
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class PlayGame {
    
    /**
     * 
     * @param timer, The current timer timing each question duration
     * @param counter
     * @param timerLabel
     * @return 
     */
    private static ArrayList<Question> resetGame(Timer timer, Integer counter, JLabel timerLabel) {
        // reset timer
        counter = 60;
        timer.stop();
        timerLabel.setForeground(new Color(63, 255, 202));
        timerLabel.setText(counter.toString());
        
        // reset questions
        return getNewQuestions();
    }
    
    /**
     * This method returns a new list of questions from the question pool
     * @return ArrayList<Question>, a list of questions form the question pool
     */
    public static ArrayList<Question> getNewQuestions() {
        return QuestionList.selectQuestions();
    }
}
