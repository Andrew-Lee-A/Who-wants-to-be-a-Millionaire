/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

/**
 *
 * @author GORILLARIG
 */
public class QuestionInvalidAnswers extends IllegalArgumentException {

    /*
    * No argument constructor used when no description provided.
     */
    public QuestionInvalidAnswers() {
        super();
    }

    /*
    * Single argument constructor
    * @param string, String representing the exception thrown.
     */
    public QuestionInvalidAnswers(String string) {
        super(string);
    }
}
