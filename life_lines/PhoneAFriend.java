package life_lines;

import java.util.ArrayList;
import javax.swing.JButton;
import question.Answer;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class PhoneAFriend extends AbstractPlayerGameHelp {
    
    public PhoneAFriend(JButton button) {
        super(button);
    }
    
    /*
    * @param friendsAns, an ArrayList<Answers> of 2 possible answers that
    * The friend could pick
    * @return String, representing the friends guess at what the answer could be
     */
    public String friendsResponse(ArrayList<Answer> friendsAns) throws NullPointerException {
        return "I think its either " + friendsAns.get(0).getText() + " or " + friendsAns.get(1).getText() + ".";
    }
}
