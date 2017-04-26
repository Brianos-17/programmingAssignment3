package utils;

import java.util.Scanner;

/**
 * Created by Brian on 24/04/2017.
 */
public class ScannerInput {

    public static String validNextString(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }
}
