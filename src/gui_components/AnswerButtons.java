/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_components;

import question.Answer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import gui_styling.OnHoverButton;

/**
 * This class is used to represent the answer buttons when a 
 * game is being played, initializing the required buttons
 * and setting up styling features
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class AnswerButtons extends JPanel {

    private JButton[] answerButtons;
    private Color defaultBackgroundColor;
    private Color defaultButtonColor;
    private Color onHoverColor;
    private static JButton correctButton;
    private Answer[] answers;
    

    public AnswerButtons(Dimension panelSize, Color defaultBackgroundColor, Color defaultButtonColor, Color onHoverColor, int spacing, Answer[] answers) {
        this.answers = answers;
        
        // Set color info
        this.defaultBackgroundColor = defaultBackgroundColor;
        this.defaultButtonColor = defaultButtonColor;
        this.onHoverColor = onHoverColor;

        int size = 4;
        answerButtons = new JButton[size];
        for(int i = 0; i < answerButtons.length; i++) {
           answerButtons[i] =  setButton(answerButtons[i]);
        }
        
        setAnswerButtonText();
        
        super.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 100));
        super.setSize(panelSize);
        super.setBackground(defaultBackgroundColor);
        for (int i = 0; i < size; i++) {
            super.add(answerButtons[i]);
            if (i % 2 == 0) {
                super.add(Box.createRigidArea(new Dimension(panelSize.width - ((2 * answerButtons[i].getPreferredSize().width) + (2 * spacing)), 0)));
            }
        }
    }

    /**
     * setButton takes a button, and sets the values for it to fit into the 4 answers
     * layout
     * @param button, JButton the button to set 
     */
    private JButton setButton(JButton button) {
        button = new JButton();
        button.setPreferredSize(new Dimension(500, 50));
        button.setFont(new Font("", Font.BOLD, 12));
        button.setBackground(defaultButtonColor);
        button.setForeground(defaultBackgroundColor);
        button.setFocusable(false);
        button.addMouseListener(new OnHoverButton(button, defaultButtonColor, onHoverColor));
        return button;
    }
    
    /**
     * Sets the buttons with the corresponding answers
     */
    private void setAnswerButtonText() {
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(answers[i].getText());
            
            if(answers[i].isCorrect()) {
               correctButton = answerButtons[i];
            }
        }
    }
    
    /**
     * Sets the answers stored to the input answers
     * @param answers, an array of 4 answers
     */
    public void setAnswers(Answer[] answers) throws InputMismatchException {
        if(answers.length != 4) {
            throw new InputMismatchException("Error answers provided not equal to 4");
        }
        
        this.answers = answers;
        setAnswerButtonText();
    }
    
    public JButton[] getButtons() {
        return answerButtons;
    }
    
    public JButton getButton(int index) {
        return answerButtons[index];
    }
    
    public Answer getAnswer(int index) {
        return answers[index];
    }
    
    public JButton getCorrectButton() {
        return correctButton;
    }
}
