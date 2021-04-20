/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import gui_panels.LifeLinePanel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.Timer;
import question.Question;
import question.QuestionList;
import question.QuestionTimer;

/**
 * This class is used to control how a game will
 * be played within the Who Wants To Be A Millionaire
 * game.
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class PlayGame {
    
    /**
     * 
     * @param questionTimer
     * @return 
     */
    public static ArrayList<Question> resetGame(QuestionTimer questionTimer, LifeLinePanel lifeLines) {
        // reset timer
        questionTimer.resetCounter();
        questionTimer.startTimer();
        
        // reset life lines to not used
        lifeLines.getFiftyFiftyHelper().setIsUsed(false);
        lifeLines.getAskTheAudienceHelper().setIsUsed(false);
        lifeLines.getPhoneAFriendHelper().setIsUsed(false);
        
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
