package controllers;


import models.*;
import models.Trainer;

import java.util.Scanner;

import static utils.ScannerInput.*;


/**
 * Created by Brian on 26/04/2017.
 */
public class MenuController {
    private GymApi gym;
    private Scanner input;

    public static void main(String[] args) {
        new MenuController();
    }

    private MenuController() {
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
            } else if (memberOrTrainer.toLowerCase().equals("t")) {
                String validEmail = validNextString("Please enter your e-mail address: ");
                if (gym.searchTrainersByEmail(validEmail) != null) {
                    System.out.print("Welcome! You have logged in successfully!");
                    //Move on to next menu
                } else {
                    System.out.println("There is no Trainer registered to this email. Access Denied.");
                    //Include code to exit system
                }
            } else {
                System.out.println("Invalid option entered. Please try again: ");
            }
        } else if (input.toLowerCase().equals("r")) {
            String memberOrTrainer = validNextString("Would you like to register as a Member or as a Trainer? [M/T]");
            if (memberOrTrainer.toLowerCase().equals("m")) {
                String premiumOrStudent = validNextString("You have chosen to register as a Member. Would you like to register as a Premium Member or a Student Member? [P/S] ");
                if (premiumOrStudent.toLowerCase().equals("p")) {
                    System.out.println("Please enter your details: ");
                    String email = validNextString("Please enter your email: ");
                    String name = validNextString("Please enter your name: ");
                    String address = validNextString("Please enter your address: ");
                    String gender = validNextString("Please enter your gender [M/F]: ");
                    double height = validNextDouble("Please enter your height: ");
                    double startingWeight = validNextDouble("Please enter your current weight: ");
                    String chosenPackage = "Premium";
                    gym.addMember(new PremiumMember(email, name, address, gender, height, startingWeight, chosenPackage));
                }
                else if(premiumOrStudent.toLowerCase().equals("s")){
                    System.out.println("Please enter your details: ");
                    String email = validNextString("Please enter your email: ");
                    String name = validNextString("Please enter your name: ");
                    String address = validNextString("Please enter your address: ");
                    String gender = validNextString("Please enter your gender [M/F]: ");
                    double height = validNextDouble("Please enter your height: ");
                    double startingWeight = validNextDouble("Please enter your current weight: ");
                    String studentId = validNextString("Please enter your Student ID: ");
                    String collegeName = validNextString("Please enter your College's Name: ");
                    String chosenPackage = "Student";
                    gym.addMember(new StudentMember(email, name, address, gender, height, startingWeight, chosenPackage, studentId, collegeName));
                }
                else{
                    System.out.println("Invalid option entered. Please try again: ");
                }

            } else if (memberOrTrainer.toLowerCase().equals("t")) {
                System.out.println("Please enter your details: ");
                String email = validNextString("Please enter your email: ");
                String name = validNextString("Please enter your name: ");
                String address = validNextString("Please enter your address: ");
                String gender = validNextString("Please enter your gender [M/F]: ");
                String speciality = validNextString("Please enter your speciality: ");
                gym.addTrainer(new Trainer(email, name, address, gender, speciality));
            } else {
                System.out.println("Invalid option entered. Please try again: ");
            }

        }
    }
}

    private void runMenu() {
        welcomeMenu();
    }
}
