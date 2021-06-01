package question;

import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Timer;

/**
 * This class is used to model a timer for a particular question
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class QuestionTimer extends Observable {

    private Integer counter;
    private final Timer timer;

    public QuestionTimer(ActionListener al) {
        counter = 60;
        timer = new Timer(1000, al);
    }

    // constructor used for test cases
    public QuestionTimer() {
        counter = 60;
        timer = null;
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
        if (counter - 1 >= 0) {
            --counter;
        }
        this.setChanged();
        this.notifyObservers(this);
    }

    public Integer getCounter() {
        return counter;
    }
}
