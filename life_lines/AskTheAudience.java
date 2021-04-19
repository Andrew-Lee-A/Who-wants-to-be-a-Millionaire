package life_lines;

import java.util.Random;
import javax.swing.JButton;

/**
 *  The AskTheAudince class is used to simulate the ask
 *  the audience feature of who wants to be a millionaire.
 *  note it will always select 2 most likely answers for simplicity, 
 *  and will not use the entire audience to vote (most of the time
 *  1 or 2 people will not vote). This is fine as in reality the entire
 *  audience would be unlikely to vote.
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class AskTheAudience extends AbstractPlayerGameHelp {
    private int audienceNumber = 300;
    
    public AskTheAudience(JButton button) {
        super(button);
    }
    
    /**
     * The getAudienceResponse method takes a audience number
     * and returns an int array where the first 2 elements represent
     * 
     * NOTE not all people in the audience will vote with this method, this is
     * fine as in real life not all of the audience would vote either.
     * @return int[], an array on integers representing the votes, where the first
     * 2 elements are the most voted for
    */
    public int[] getAudienceResponse() throws IllegalArgumentException {
        if(audienceNumber < 0) {
            throw new IllegalArgumentException("Audience cannot be less than 0 i.e. negative people");
        }
        
        int[] audienceResponses = new int[4];

        Random rand = new Random();
        
        audienceNumber -= 50;
        audienceResponses[0] = rand.nextInt((audienceNumber - 80)) + 50;
        audienceNumber -= audienceResponses[0];
        audienceNumber -= 15;
        audienceResponses[1] = rand.nextInt(audienceNumber) + 15;
        return audienceResponses;
    }

}
