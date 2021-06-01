package question;

/**
 *
 * The Question class is used to store a question, a question object will also
 * contain four answer objects where one answer object has the isCorrect member
 * set to true. Furthermore, a text member is included that represents the
 * question.
 *
 * @author Rhys Van Rooyen Student ID: 19049569
 */
public class Question {

    private String text;
    private Answer[] answers;

    /*
    * @param text, string representing the questions text
    * @param answers, an array of 4 Answer objects
     */
    public Question(String text, Answer[] answers) throws IllegalArgumentException, NullPointerException, QuestionInvalidAnswers {
        if (text.trim().length() == 0) {
            throw new IllegalArgumentException("The question cannot be an empty string");
        }

        this.text = text;

        if (answers.length != 4) {
            throw new QuestionInvalidAnswers("Invalid amount of answers, 4 answers must be present!");
        }

        this.answers = answers;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the answers
     */
    public Answer[] getAnswers() {
        return answers;
    }
    
    @Override
    public String toString() {
        String s = "";
        
        s += text + '\n';
        
        int i = 1;
        for(Answer ans : answers) {
            s += i++ + ") " + ans.getText() + '\n';
        }
        
        return s;
    }

}
