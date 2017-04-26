package controllers;

import java.util.Scanner;
import static utils.ScannerInput.*;



/**
 * Created by Brian on 26/04/2017.
 */
public class MenuController {
    private GymApi gym;
    private Scanner input;

    public static void main (String[] args){
        new MenuController();
    }

    private MenuController(){
        input = new Scanner(System.in);
        gym = new GymApi();
        runMenu();
    }

    private void welcomeMenu() {
        System.out.println("Welcome to your very own personal gym app!");
        System.out.println("---------------");
        String input = validNextString("Would you like to Login to your account or Register a new one? [L/R]");
        if (input.toLowerCase().equals("l")) {
            String memberOrTrainer = validNextString("Are you logging in as a Member or as a Trainer? [M/T]");
            if (memberOrTrainer.toLowerCase().equals("m")) {
                String validEmail = validNextString("Please enter your e-mail address: ");
                if (gym.searchMembersByEmail(validEmail) != null) {
                    System.out.println("Welcome! You have logged in successfully!");
                    //Move on to next menu
                } else {
                    System.out.println("There is no Member registered to this email. Access Denied.");
                    //Include code to exit system
                }
            }
            else if(memberOrTrainer.toLowerCase().equals("t")){
                String validEmail = validNextString("Please enter your e-mail address: ");
                if(gym.searchTrainersByEmail(validEmail) != null){
                    System.out.print("Welcome! You have logged in sucsessfully!");
                    //Move on to next menu
                }  else {
                    System.out.println("There is no Trainer registered to this email. Access Denied.");
                    //Include code to exit system
                }
            }
            else {
                System.out.println("Invalid option entered. Please try again: ");
            }

            }
            else if (input.toLowerCase().equals("r")) {
            String memberOrTrainer = validNextString("Would you like to register as a Member or as a Trainer? [M/T]");
            if(memberOrTrainer.toLowerCase().equals("m")){
                System.out.println("Please enter your details: ");
                gym.addMember();
            }
            else if(memberOrTrainer.toLowerCase().equals("t")){
                System.out.println("Please enter your details: ");
                gym.addTrainer();
            }
            else{
                System.out.println("Invalid option entered. Please try again: ");
            }

            }
        }
    }

    private void runMenu(){
        welcomeMenu();
    }
}
