package life_lines;

import java.util.InputMismatchException;

/**
 * This class is used to throw an error when invalid input 
 * is provided to the PlayerGameHelp classes children.
 * 
 * @author Rhys Van Rooyen Student ID: 19049569
 */
public class InvalidPlayerGameHelpCall extends InputMismatchException {

    /*
    * Emmpty constructor used when no detail text provided.
    */
    public InvalidPlayerGameHelpCall() {
    }

    /*
    * @param string, String represents what the error was thrown for.
    */
    public InvalidPlayerGameHelpCall(String string) {
        super(string);
    }
    
}
