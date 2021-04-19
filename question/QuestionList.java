package question;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * The QuestionList class is used to initialize a list of 15 questions for the
 * game by selecting from a pool of random questions
 *
 * @author Rhys Van Rooyen Student ID: 19049569
 */
public class QuestionList {

    public QuestionList() {
    }

    /**
     *
     * @return ArrayList<Question>, representing the selected questions from the
     * pool of questions, note that 15 questions are selected at random from the
     * pool of questions provided in the method allAvailableQuestions.
     */
    public static ArrayList<Question> selectQuestions() {
        ArrayList<Question> allQuestions = allAvailableQuestions();
        ArrayList<Question> selectedQuestions = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            int indexSelected = rand.nextInt(allQuestions.size());
            selectedQuestions.add(allQuestions.get(indexSelected));
            allQuestions.remove(indexSelected);
        }

        return selectedQuestions;
    }

    /*
    *   @return ArrayList<Question>, representing all the questions that can
    *   be selected from
     */
    private static ArrayList<Question> allAvailableQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        Answer[] answersToAdd = new Answer[4];

        //1. What was known as a falchion?
        questions.add(new Question("What kind of weapon is a falchion?",
                createAnswer(new Answer("sword", true), new Answer("Dagger", false), new Answer("Spear", false), new Answer("Donkey", false))));

        //2. What is the approximate age of the Sun?
        answersToAdd[0] = new Answer("3.6 billion years", false);
        answersToAdd[1] = new Answer("4.6 billion years", true);
        answersToAdd[2] = new Answer("4.0 billion years", false);
        answersToAdd[3] = new Answer("1 million years", false);
        questions.add(new Question("What is the approximate age of the Sun?",
                createAnswer(new Answer("3.6 billion years", false), new Answer("4.6 billion years", true), new Answer("4.0 billion years", false), new Answer("1 million years", false))));

        //3. When was the film Shrek 2 released?
        questions.add(new Question("When was the film Shrek 2 released?", createAnswer(
                new Answer("1999", false),
                new Answer("2001", false),
                new Answer("2004", true),
                new Answer("2015", false))));

        //4. How old was Michael Jordan when he retired?
        questions.add(new Question("How old was Michael Jordan when he retired?", createAnswer(new Answer("32", true),
                new Answer("38", false),
                new Answer("40", false),
                new Answer("42", false)
        )));

        //5. In what year was the Mandalorian released?
        questions.add(new Question("In what year was the Mandalorian released", createAnswer(new Answer("2002", false),
                new Answer("2018", false),
                new Answer("2019", true),
                new Answer("2021", false))));

        //6. In what year did Barack Obama become president?
        questions.add(new Question("In what year did Barack Obama become president", createAnswer(new Answer("2009", true),
                new Answer("2014", false),
                new Answer("2017", false),
                new Answer("2021", false))));

        //7. Which element is represented by Au on the periodic table?
        questions.add(new Question("Which element is represented by Au on the periodic table?", createAnswer(new Answer("Silver", false),
                new Answer("Bronze", false),
                new Answer("Oxygen", false),
                new Answer("Gold", true))));

        //8. How many levels are in The Simpsons: Hit & Run ?
        questions.add(new Question("How many levels are in \"The Simpsons: Hit & Run\"?", createAnswer(         new Answer("7", true),
         new Answer("10", false),
         new Answer("12", false),
         new Answer("21", false))));

        //9. What is the average length of an Emu ?
        questions.add(new Question("What is the average length of an Emu?", createAnswer(        //9. What is the average length of an Emu ?
         new Answer("0.6 meters", false),
         new Answer("0.8 meters", false),
         new Answer("1.6 meters", false),
         new Answer("1.8 meters", true))));

        //10. Which company created Java?
        questions.add(new Question("Which company created Java?", createAnswer(         new Answer("Sun Microsystems, Inc.", true),
         new Answer("Microsoft", false),
         new Answer("Google", false),
         new Answer("Apple", false))));

        //11. In what year was Facebook created?
        questions.add(new Question("In what year was Facebook created?", createAnswer(         new Answer("1998", false),
         new Answer("2002", false),
         new Answer("2004", true),
         new Answer("2006", false))));

        //12. What is main character in the Halo game series referred to as?
        questions.add(new Question("What is main character in the Halo game series referred to as?", createAnswer(         new Answer("Master Chief", true),
         new Answer("Spyro", false),
         new Answer("Mschief Master", false),
         new Answer("Steve", false))));

        //13. Which country has the largest surface area?
        questions.add(new Question("Which country has the largest surface area?", createAnswer(         new Answer("China", false),
         new Answer("France", false),
         new Answer("Russia", true),
         new Answer("South Korea", false))));

        //14. How many languages are there in the world?
        questions.add(new Question("How many languages are there in the world?", createAnswer(         new Answer("5897", false),
         new Answer("4201", false),
         new Answer("7000", true),
         new Answer("7201", false))));

        //15. What is the capital of New Zealand?
        questions.add(new Question("What is the capital of New Zealand?", createAnswer(         new Answer("Auckland", false),
         new Answer("Wellington", true),
         new Answer("Queenstown", false),
         new Answer("Canberra", false))));

        //16. What year was the Nintendo Switch released?
        questions.add(new Question("What year was the Nintendo Switch released?", createAnswer(         new Answer("2016", false),
         new Answer("2017", true),
         new Answer("2018", false),
         new Answer("2019", false))));

        //17. What was the tallest dinosaur?
        questions.add(new Question("What was the tallest dinosaur?", createAnswer(        new Answer("Tyrannosaurus Rex", false),
        new Answer("Megalosaurus", false),
        new Answer("Sauroposeidon", true),
        new Answer("Spinosaurus", false))));

        //18. How much is a mcdonalds cheeseburger combo in New Zealand?
        questions.add(new Question("How much is a mcdonalds cheeseburger combo in New Zealand?", createAnswer(        new Answer("$7.26", false),
        new Answer("$7.84", false),
        new Answer("$8.64", true),
        new Answer("$9.12", false))));

        //19. Who painted the mona lisa?
        questions.add(new Question("Who painted the mona lisa?", createAnswer(        new Answer("Leonardo da Vinci", true),
        new Answer("Bob Ross", false),
        new Answer("John Key", false),
        new Answer("Will Smith", false))));

        //20. How many pandas are currently in the wild?
        questions.add(new Question("How many pandas are currently in the wild?", createAnswer(        new Answer("1500", false),
        new Answer("1725", false),
        new Answer("1864", true),
        new Answer("2069", false))));

        return questions;
    }

    /**
     * Helper class to make a group of answers for a question, all parameters
     * are Answer objects with one Answer object being the correct answer.
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @return Answer[], an array of correct answers
     * @throws InputMismatchException this is thrown if no correct answer
     * provided
     */
    private static Answer[] createAnswer(Answer a, Answer b, Answer c, Answer d) throws InputMismatchException {
        if (!(a.isCorrect() || b.isCorrect() || c.isCorrect() || d.isCorrect())) {
            throw new InputMismatchException("Cannot have no correct answer.");
        }

        return new Answer[]{a, b, c, d};
    }
}
