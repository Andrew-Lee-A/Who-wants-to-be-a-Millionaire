/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import gui_components.AnswerButtons;
import question.QuestionList;
import question.Answer;
import question.Question;
import life_lines.PhoneAFriend;
import life_lines.AskTheAudience;
import life_lines.AbstractPlayerGameHelp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import animation.CurrentPanel;
import gui_components.WalkAwayButton;
import question.QuestionTimer;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class PlayGamePanel extends JPanel implements ActionListener {

    private WalkAwayButton walkAwayButton;
    private LifeLinePanel lifeLinesPanel;
    private AnswerButtons answersPanel;
    private JLabel questionLabel;
    private JLabel timerLabel;
    private final QuestionTimer questionTimer;
    private final int INSIDE_PADDING = 20;
    private int panelWidth = 1280;
    private int panelHeight = 720;
    private final Color BACKGROUND_COLOR = new Color(12, 15, 18);
    private final CurrentPanel currentPanel;
    private ArrayList<Question> questions;
    private int currentQuestion;

    public PlayGamePanel(int panelWidth, int panelHeight, CurrentPanel currentPanel) {
        this.currentPanel = currentPanel;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        setQuestions();
        currentQuestion = 0;

        // Initialise attributes for panels
        walkAwayButton = new WalkAwayButton("Walk Away", new Dimension(100, 60), currentPanel);
        lifeLinesPanel = new LifeLinePanel(new Dimension(380, 60), new Color(64, 64, 206), new Color(64, 206, 135), BACKGROUND_COLOR, new Color(206, 64, 64));
        answersPanel = new AnswerButtons(new Dimension((this.panelWidth - (2 * INSIDE_PADDING)), 320),
                BACKGROUND_COLOR, new Color(64, 206, 135), new Color(255, 255, 255), INSIDE_PADDING, questions.get(currentQuestion).getAnswers());

        // Setting the anonymous method for the answer questionButtons to update
        JButton[] questionButtons = answersPanel.getButtons();
        for (int i = 0; i < questionButtons.length; i++) {
            questionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!(answersPanel.getCorrectButton() == e.getSource())) {
                        resetGame();
                        currentPanel.goToMainMenu();
                    } else {
                        answersPanel.setAnswers(questions.get(++currentQuestion).getAnswers());
                        questionLabel.setText(questions.get(currentQuestion).getText());
                        questionTimer.resetCounter();
                        timerLabel.setText(questionTimer.getCounter().toString());
                    }
                }
            });
        }

        // bind action listeners to lifelines
        setLifeLineListeners();

        // Creating the question label
        questionLabel = new JLabel(questions.get(0).getText());
        questionLabel.setSize(new Dimension(1000, 70));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("", Font.BOLD, 26));

        // Creating the timer label
        questionTimer = new QuestionTimer(this);
        timerLabel = new JLabel(questionTimer.getCounter().toString());
        timerLabel.setForeground(new Color(63, 255, 202));
        timerLabel.setSize(100, 60);
        timerLabel.setFont(new Font("", Font.BOLD, 60));

        // Adding walk away features
        walkAwayButton.getWalkAwayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
                currentPanel.goToMainMenu();
            }
        });

        // Set the requirements for the frame
        super.setSize(new Dimension(this.panelWidth, this.panelHeight));
        super.setLayout(null);

        // Set locations
        walkAwayButton.setLocation(new Point(INSIDE_PADDING, INSIDE_PADDING));
        lifeLinesPanel.setLocation(new Point(this.panelWidth - (380 + INSIDE_PADDING), INSIDE_PADDING));
        answersPanel.setLocation(new Point(INSIDE_PADDING, 320));
        questionLabel.setLocation(new Point(200, 200));
        timerLabel.setLocation(new Point((2 * INSIDE_PADDING) + 100, INSIDE_PADDING));

        // Add relevant J components
        super.setBackground(BACKGROUND_COLOR);
        super.add(walkAwayButton);
        super.add(lifeLinesPanel);
        super.add(answersPanel);
        super.add(questionLabel);
        super.add(timerLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (questionTimer.getCounter() > 0) {
            questionTimer.decrementCounter();
            switch (questionTimer.getCounter()) {
                case 40:
                    timerLabel.setForeground(new Color(220, 255, 0));
                    break;
                case 20:
                    timerLabel.setForeground(new Color(255, 169, 0));
                    break;
                case 10:
                    timerLabel.setForeground(new Color(221, 41, 34));
                    break;
            }

            timerLabel.setText(questionTimer.getCounter().toString());
        }

        if (questionTimer.getCounter() <= 0) {
            questionTimer.stopTimer();
            questionTimer.resetCounter();
            timerLabel.setForeground(new Color(63, 255, 202));
            timerLabel.setText(questionTimer.getCounter().toString());
            currentPanel.goToMainMenu();
        }
    }

    /**
     * this helper method allows the game to be put back into its initial state
     * with new questions supplied
     */
    private void resetGame() {
        // reset timer
        questionTimer.resetCounter();
        questionTimer.stopTimer();
        timerLabel.setForeground(new Color(63, 255, 202));
        timerLabel.setText(questionTimer.getCounter().toString());
        
        // reset questions
        setQuestions();
        currentQuestion = 0;
        questionLabel.setText(questions.get(currentQuestion).getText());
        answersPanel.setAnswers(questions.get(currentQuestion).getAnswers());
        
        // Reset life lines
        lifeLinesPanel.getFiftyFiftyHelper().setIsUsed(false);
        lifeLinesPanel.getAskTheAudienceHelper().setIsUsed(false);
        lifeLinesPanel.getPhoneAFriendHelper().setIsUsed(false);
        lifeLinesPanel.resetLifeLineStyling(); 
    }

    private void setLifeLineListeners() {
        lifeLinesPanel.getFiftyFiftyHelper().getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                AbstractPlayerGameHelp lifeLine = lifeLinesPanel.getFiftyFiftyHelper();

                if (!lifeLine.isUsed()) {
                    setButtonTextBlank(lifeLine.getHelp(questions.get(currentQuestion)));
                    lifeLine.setIsUsed(true);
                }
            }
        });

        lifeLinesPanel.getAskTheAudienceHelper().getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                AskTheAudience lifeLine = lifeLinesPanel.getAskTheAudienceHelper();

                if (!lifeLine.isUsed()) {
                    questionLabel.setText("<html>" + questionLabel.getText() + "<br>" + "the two most voted audience options are..." + "</html>");
                    setButtonTextBlank(lifeLine.getHelp(questions.get(currentQuestion)));
                    lifeLine.setIsUsed(true);
                }
            }
        });

        lifeLinesPanel.getPhoneAFriendHelper().getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                PhoneAFriend lifeLine = lifeLinesPanel.getPhoneAFriendHelper();

                if (!lifeLine.isUsed()) {
                    ArrayList<Answer> ans = lifeLine.getHelp(questions.get(currentQuestion));
                    questionLabel.setText("<html>" + questionLabel.getText() + "<br>" + lifeLinesPanel.getPhoneAFriendHelper().friendsResponse(ans) + "</html>");
                    setButtonTextBlank(ans);
                    lifeLine.setIsUsed(true);
                }
            }
        });
    }

    /**
     * This method is used to set the button text of incorrect answers to blank.
     *
     * @param answers, ArrayList of 2 answers
     */
    private void setButtonTextBlank(ArrayList<Answer> answers) {

        // Loop 4 times as only 4 possible answers
        // note that the answers array list only has two possible answers
        for (int i = 0; i < 4; i++) {
            JButton currentAnswer = answersPanel.getButton(i);
            if (!(currentAnswer.getText().equals(answers.get(0).getText())
                    || currentAnswer.getText().equals(answers.get(1).getText()))) {
                currentAnswer.setText("");
            }
        }
    }

    /**
     * Used to set new questions
     */
    public void setQuestions() {
        questions = QuestionList.selectQuestions();
    }

    /**
     * Used to get the question timer object
     *
     * @return QuestionTimer, returns the question timer
     */
    public QuestionTimer getCounterTImer() {
        return questionTimer;
    }

}
