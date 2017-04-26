package utils;

import java.util.Scanner;


/**
 * Created by Brian on 24/04/2017.
 */
public class ScannerInput {

    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

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

