package utils;

import java.util.Scanner;

/**
 * Created by Brian on 24/04/2017.
 * @author Brian O'Sullivan
 *
 * ScannerInput class holds static methods used to read users inputs
 *
 */
public class ScannerInput {

    /**
     * Method which reads the users input as a String from the MenuController
     * @param prompt The message which prompts the users input
     * @return The users input, when valid
     */
    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * Method which reads the users input as a double from the MenuController
     * @param prompt The message which prompts the users input
     * @return The users input, when valid
     */
    public static double validNextDouble(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Double.parseDouble(input.next());
            }
            catch (Exception e){
                input.nextLine();
                System.err.println("Invalid option. Please enter a valid number: ");
            }
        }
        while (true);
    }

    /**
     * Method which reads the users input as an int from the MenuController
     * @param prompt The message which prompts the users input
     * @return The users input, when valid
     */
    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt(input.next());
            } catch (Exception e) {
                input.nextLine();
                System.err.println("Invalid option. Please enter a valid number: ");
            }
        }
        while (true);
    }
}