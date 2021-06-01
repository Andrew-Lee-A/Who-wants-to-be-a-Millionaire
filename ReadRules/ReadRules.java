/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadRules;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class ReadRules {

    private final static String FILE_NAME = "./src/ReadRules/rules.txt";

    /**
     * This method reads a txt file to get the rules from.
     *
     * @return String, representing a formatted string of the rules for the
     * game.
     */
    public static String getRules() {
        String rules = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            
            String inputLine;
            while((inputLine = br.readLine()) != null) {
                rules += inputLine;
                rules += '\n';
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Rules file was not found");
        } catch (IOException e) {
            System.err.println("File was not closed correctly");
        }

        return rules;
    }
}
