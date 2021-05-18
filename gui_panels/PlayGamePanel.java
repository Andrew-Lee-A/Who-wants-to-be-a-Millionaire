package gui_panels;

import gui_components.AnswerButtons;
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
import animation.GameState;
import controllers.AnswerController;
import gui_components.WalkAwayButton;
import java.util.Observable;
import java.util.Observer;
import life_lines.FiftyFifty;
import question.QuestionTimer;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class PlayGamePanel extends JPanel implements Observer {

    private WalkAwayButton walkAwayButton;
    private LifeLinePanel lifeLinesPanel;
    private final AnswerButtons answersPanel;
    private final JLabel questionLabel;
    private final JLabel timerLabel;
    private final int INSIDE_PADDING = 20;
    private int panelWidth = 1280;
    private int panelHeight = 720;
    private final Color BACKGROUND_COLOR = new Color(0, 0, 0);
    private final GameState gameState;
    private Question currentQuestionAsked;
    private final Color INTIAL_TIMER_COLOR = new Color(63, 255, 202);
    private final QuestionTimer questionTimer;

    public PlayGamePanel(int panelWidth, int panelHeight, GameState gameState, QuestionTimer questionTimer, Question initialQuestion) {
        this.gameState = gameState;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.questionTimer = questionTimer;
        currentQuestionAsked = initialQuestion;

        // Initialise attributes for panels
        walkAwayButton = new WalkAwayButton("Walk Away", new Dimension(100, 60), gameState);
        lifeLinesPanel = new LifeLinePanel(new Dimension(380, 60), gameState, new Color(64, 64, 206), new Color(64, 206, 135), BACKGROUND_COLOR, BACKGROUND_COLOR);
        answersPanel = new AnswerButtons(new Dimension((this.panelWidth - (2 * INSIDE_PADDING)), 320),
                BACKGROUND_COLOR, new Color(64, 206, 135), new Color(255, 255, 255), INSIDE_PADDING, initialQuestion.getAnswers());

        // Creating the question label
        questionLabel = new JLabel(initialQuestion.getText());
        questionLabel.setSize(new Dimension(1000, 70));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("", Font.BOLD, 26));

        // Creating the timer label
        timerLabel = new JLabel("60");
        timerLabel.setForeground(INTIAL_TIMER_COLOR);
        timerLabel.setSize(100, 60);
        timerLabel.setFont(new Font("", Font.BOLD, 60));

        // Adding walk away features
        walkAwayButton.getWalkAwayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPanel();
                questionTimer.resetCounter();
                questionTimer.stopTimer();
                gameState.updateRecords();
                gameState.setLifeLineUsedThisRound(false);
                gameState.goToMainMenu();
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

    /**
     * this helper method allows the game to be put back into its initial state
     * with new questions supplied
     */
    private void resetPanel() {

        //Reset timer styling
        timerLabel.setForeground(INTIAL_TIMER_COLOR);
        timerLabel.setText("60");
        
        //Reset question styling
        questionLabel.setText(currentQuestionAsked.getText());
        answersPanel.setAnswers(currentQuestionAsked.getAnswers());

        // Reset life lines styling
        lifeLinesPanel.resetLifeLineStyling();
        lifeLinesPanel.resetLifeLinesUsed();
        this.repaint();
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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof QuestionTimer) { // the timer label is being changed
            QuestionTimer qt = (QuestionTimer) o;

            switch (qt.getCounter()) {
                case 40:
                    timerLabel.setForeground(new Color(220, 255, 0));
                    break;
                case 20:
                    timerLabel.setForeground(new Color(255, 169, 0));
                    break;
                case 10:
                    timerLabel.setForeground(new Color(221, 41, 34));
                    break;
                case 0:
                    resetPanel();
                    break;
            }

            if (qt.getCounter() > 0) { // only redraw the counter if set
                timerLabel.setText(qt.getCounter().toString());
            }

        } else if (o instanceof AnswerController) { // if an answer has been clicked adjust styling

            AnswerController ansModel = (AnswerController) o; // note the controller is also the model in this case
            if (ansModel.isCorrectChoice()) {
                answersPanel.setAnswers(currentQuestionAsked.getAnswers());
                questionLabel.setText(currentQuestionAsked.getText());
                questionTimer.resetCounter();
                timerLabel.setText(questionTimer.getCounter().toString());
                timerLabel.setForeground(INTIAL_TIMER_COLOR);
            } else {
                resetPanel();
            }
        } else if (o instanceof AbstractPlayerGameHelp) { // if a life line has been clicked adjust styling

            if (o instanceof FiftyFifty && !gameState.isLifeLineUsedThisRound()) { // fifty fifty
                AbstractPlayerGameHelp lifeLine = lifeLinesPanel.getFiftyFiftyHelper();
                lifeLinesPanel.buttonClicked(lifeLine);
                setButtonTextBlank(lifeLine.getHelp(currentQuestionAsked));

            } else if (o instanceof AskTheAudience && !gameState.isLifeLineUsedThisRound()) { // ask the audience
                AskTheAudience lifeLine = lifeLinesPanel.getAskTheAudienceHelper();
                lifeLinesPanel.buttonClicked(lifeLine);
                questionLabel.setText("<html>" + questionLabel.getText() + "<br>" + "the two most voted audience options are..." + "</html>");
                setButtonTextBlank(lifeLine.getHelp(currentQuestionAsked));

            } else if (o instanceof PhoneAFriend && !gameState.isLifeLineUsedThisRound()) { // phone a friend
                PhoneAFriend lifeLine = lifeLinesPanel.getPhoneAFriendHelper();
                lifeLinesPanel.buttonClicked(lifeLine);
                ArrayList<Answer> ans = lifeLine.getHelp(currentQuestionAsked);
                questionLabel.setText("<html>" + questionLabel.getText() + "<br>" + lifeLinesPanel.getPhoneAFriendHelper().friendsResponse(ans) + "</html>");
                setButtonTextBlank(ans);
            }
        }
    }

    /**
     * Used to get the question timer object
     *
     * @return QuestionTimer, returns the question timer
     */
    public QuestionTimer getCounterTimer() {
        return questionTimer;
    }

    public AnswerButtons getAnswersBtns() {
        return this.answersPanel;
    }

    /**
     * returns the lifelines in the game
     *
     * @return index 0 represents the fifty fifty, 1 the ask the audience and 2
     * the phone a friend....
     */
    public AbstractPlayerGameHelp[] getLifeLines() {
        return (new AbstractPlayerGameHelp[]{lifeLinesPanel.getFiftyFiftyHelper(), lifeLinesPanel.getAskTheAudienceHelper(), lifeLinesPanel.getPhoneAFriendHelper()});
    }

    public JButton getFiftyFiftyButton() {
        return lifeLinesPanel.getFiftyFiftyHelper().getButton();
    }

    public JButton getAskTheAudienceButton() {
        return lifeLinesPanel.getAskTheAudienceHelper().getButton();
    }

    public JButton getPhoneAFriendButton() {
        return lifeLinesPanel.getPhoneAFriendHelper().getButton();
    }

    public void setAnswersController(ActionListener controller) {
        JButton[] questionButtons = answersPanel.getButtons();
        for (int i = 0; i < questionButtons.length; i++) {
            questionButtons[i].addActionListener(controller);
        }
    }

    public void setLifeLinesController(ActionListener controller) {
        lifeLinesPanel.getFiftyFiftyHelper().getButton().addActionListener(controller);
        lifeLinesPanel.getAskTheAudienceHelper().getButton().addActionListener(controller);
        lifeLinesPanel.getPhoneAFriendHelper().getButton().addActionListener(controller);
    }

    public void setQuestionAsked(Question q) {
        this.currentQuestionAsked = q;
    }
}
