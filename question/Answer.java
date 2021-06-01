package question;

import java.util.InputMismatchException;

/**
 * The Answer class is used to display a potential answer within the game i.e.
 * What is 1 + 2 a possible Answer object could store the string 7 with the
 * isCorrect member being set to false.
 *
 * @author Rhys Van Rooyen Student ID: 19049569
 */
public class Answer {

    private String text;
    private boolean isCorrect;

    /**
     * A String representing the text of the answer, and a boolean representing
     * if that answer is correct
     *
     * @param text
     * @param isCorrect
     */
    public Answer(String text, boolean isCorrect) throws InputMismatchException, NullPointerException {
        if (text.trim().length() == 0) {
            throw new InputMismatchException("An answer cannot have an empty string");
        }

        this.text = text;
        this.isCorrect = isCorrect;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) throws InputMismatchException, NullPointerException {
        if (text.trim().length() == 0) {
            throw new InputMismatchException("An answer cannot have an empty string");
        }
        this.text = text;
    }

    /**
     * @return the isCorrect
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

}
