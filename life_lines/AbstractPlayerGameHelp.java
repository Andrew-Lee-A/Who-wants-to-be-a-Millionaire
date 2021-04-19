package life_lines;

import java.util.ArrayList;
import javax.swing.JButton;
import question.Answer;
import question.Question;

/**
 * This class is an abstract class that represents the relationship between all
 * player in game help features, i.e. phone a friend, ask the audience etc...
 *
 * @author Rhys Van Rooyen Student ID: 19049569
 */
public abstract class AbstractPlayerGameHelp {

    protected boolean isUsed;
    protected JButton button;

    public AbstractPlayerGameHelp(JButton button) {
        isUsed = false;
        this.button = button; //note the button represents the revelavant lifeline
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }

    public JButton getButton() {
        return this.button;
    }
    
    public boolean isUsed() {
        return isUsed;
    }

    /**
    * @param questionToQuery, a Question object to reduce the answers from four
    * to two
    * @return ArrayList<Answer>, represents the two possible answers from the original
    * four questions. This will be used in ask the audience and fifty fifty responses.
     */
    public ArrayList<Answer> getHelp(Question questionToQuery) {
        ArrayList<Answer> selectedAnswers = new ArrayList<>();

        boolean addedWrongAns = false;
        for (Answer ans : questionToQuery.getAnswers()) {
            if (ans.isCorrect()) {
                selectedAnswers.add(ans);
            } else if (!addedWrongAns) {
                selectedAnswers.add(ans);
                addedWrongAns = true;
            }
        }

        return selectedAnswers;
    }
}
