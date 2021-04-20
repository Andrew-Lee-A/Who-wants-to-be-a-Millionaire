package question;

import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This class is used to model a timer for a particular
 * question
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class QuestionTimer {
    private Integer counter;
    private final Timer timer;
    
    public QuestionTimer(ActionListener al) {
        counter = 60;
        timer = new Timer(1000, al);
    }
    
    public void resetCounter() {
        counter = 60;
    }
    
    public void startTimer() {
        timer.start();
    }
    
    public void stopTimer() {
        timer.stop();
    }
    
    public void decrementCounter() {
        --counter;
    }
    
    public Integer getCounter() {
        return counter;
    }
}
