package controllers;


import models.*;
import models.Trainer;
import models.Member;
import models.Assessment;

import java.util.Date;
import java.util.Scanner;

import static utils.ScannerInput.*;


/**
 * Created by Brian on 26/04/2017.
 */
public class MenuController {
    private GymApi gym;
    private Scanner input;
    private static String validEmail;

    public static void main(String[] args) {
        new MenuController();
    }

    private MenuController() {
        input = new Scanner(System.in);
        gym = new GymApi();
        welcomeMenu();
    }

    private void welcomeMenu() {
        System.out.println("Welcome to your very own personal gym app!");
        System.out.println("---------------");
        String input = validNextString("Would you like to Login to your account or Register a new one? [L/R]");
        if (input.toUpperCase().equals("L")) {
            String memberOrTrainer = validNextString("Are you logging in as a Member or as a Trainer? [M/T]");
            if (memberOrTrainer.toUpperCase().equals("M")) {
                validEmail = validNextString("Please enter your e-mail address: ");
                if (gym.searchMembersByEmail(validEmail) != null) {
                    System.out.println("Welcome! You have logged in successfully!");
                    runMemberMenu();
                } else {
                    System.out.println("There is no Member registered to this email. Access Denied.");
                    //Include code to exit system
                }
            } else if (memberOrTrainer.toUpperCase().equals("T")) {
                validEmail = validNextString("Please enter your e-mail address: ");
                if (gym.searchTrainersByEmail(validEmail) != null) {
                    System.out.print("Welcome! You have logged in successfully!");
                    runTrainerMenu();
                } else {
                    System.out.println("There is no Trainer registered to this email. Access Denied.");
                    //Include code to exit system
                }
            } else {
                System.out.println("Invalid option entered. Please try again: ");
            }
        } else if (input.toUpperCase().equals("R")) {
            String memberOrTrainer = validNextString("Would you like to register as a Member or as a Trainer? [M/T]");
            if (memberOrTrainer.toUpperCase().equals("M")) {
                System.out.println("You have chosen to register as a Member.");
                register();
                runMemberMenu();
            } else if (memberOrTrainer.toUpperCase().equals("T")) {
                System.out.println("Please enter your details: ");
                String email = validNextString("Please enter your email: ");
                String name = validNextString("Please enter your name: ");
                String address = validNextString("Please enter your address: ");
                String gender = validNextString("Please enter your gender [M/F]: ");
                String speciality = validNextString("Please enter your speciality: ");
                gym.addTrainer(new Trainer(email, name, address, gender, speciality));
                System.out.println("Congratulations you have successfully registered!");
                runTrainerMenu();
            } else {
                System.out.println("Invalid option entered. Please try again: ");
            }

        }
    }

    private void runMemberMenu() {
        int option = memberMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.println("Here is your current profile: ");
                    System.out.println(gym.searchMembersByEmail(validEmail).toString());
                    break;
                case 2:
                    register();//Cant update email. Will have to look at this.
                    break;
                case 3:
                    int memberSubMenu = memberSubMenu();
                    while (memberSubMenu != 0) {
                        switch (memberSubMenu) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Invalid option entered. Please try again.");
                                break;
                        }
                        System.out.println("\nPress and key to continue: ");
                        input.nextLine();
                        input.nextLine();
                        memberSubMenu = memberSubMenu();
                    }
                    System.out.println("Exiting back to Member menu...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option entered. Please try again.");
                    break;
            }
            System.out.println("\nPress and key to continue: ");
            input.nextLine();
            input.nextLine();
            option = memberMenu();
        }
        System.out.println("Exiting the program. Goodbye...");
        System.exit(0);
    }


    private void runTrainerMenu() {
        int option = trainerMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    register();
                    break;
                case 2:
                    gym.listMembers();
                    break;
                case 3:
                    String emailSearch = validNextString("Please enter the email you wish to search by: ");
                    gym.searchMembersByEmail(emailSearch);
                    break;
                case 4:
                    String nameSearch = validNextString("Please enter the name you wish to search by: ");
                    gym.searchMembersByName(nameSearch);
                    break;
                case 5: //List members with an ideal body weight
                    gym.listMembersWithIdealWeight();
                    break;
                case 6: //List members with a specific BMI category
                    System.out.println("Please select the category of BMI you wish to search by: "
                            + "\n VERY SEVERELY UNDERWEIGHT"
                            + "\n SEVERELY UNDERWEIGHT"
                            + "\n UNDERWEIGHT"
                            + "\n NORMAL"
                            + "\n OVERWEIGHT"
                            + "\n MODERATELY OBESE"
                            + "\n SEVERELY OBESE"
                            + "\n VERY SEVERELY OBESE");
                    input.nextLine();
                    String category = input.nextLine();
                    gym.listMembersBySpecificBMICategory(category);
                    break;
                case 7: //Assessment Sub menu
                    int assessmentOption = assessmentSubMenu();
                    while (assessmentOption != 0) {
                        switch (assessmentOption) {
                            case 1: //Adds a new assessment for a specified Member and adds it to their Hash-map: memberProgress
                                String assessedEmail = validNextString("Please enter the email of the Member you are adding an assessment for: ");
                                Member assessedMember = gym.searchMembersByEmail(assessedEmail);
                                System.out.println(assessedMember.getName());
                                String input = validNextString("Is this the correct member? [Y/N]");
                                if (input.toUpperCase().equals("Y")) {
                                    System.out.println("Please enter this members assessment details: ");
                                    double weight = validNextDouble("Weight: ");
                                    double chest = validNextDouble("Chest: ");
                                    double thigh = validNextDouble("Thigh: ");
                                    double upperArm = validNextDouble("Upper Arm: ");
                                    double waist = validNextDouble("Waist: ");
                                    double hips = validNextDouble("Hips: ");
                                    String comment = validNextString("Trainer's Comment: ");
                                    String name = validNextString("Trainer Name: ");
                                    Trainer trainer = gym.searchTrainersByEmail(name);
                                    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment, trainer);
                                    Date date = new Date();
                                    assessedMember.setMemberProgress(date, assessment);
                                    System.out.println("New assessment has been added.");
                                }
                                break;
                            case 2:
                                String commentEmail = validNextString("Please enter the email of the Member who's comment you would like to update: ");
                                Member commentMember = gym.searchMembersByEmail(commentEmail);
                                System.out.println(commentMember.getName());
                                String check = validNextString("Is this the correct Member? [Y/N]");
                                if (check.toUpperCase().equals("Y")) {
                                    commentMember.getMemberProgress();
                                }
                                break;
                            default:
                                System.out.println("Invalid option entered. Please try again.");
                                break;
                        }
                        System.out.println("\nPress and key to continue: ");
                        input.nextLine();
                        input.nextLine();
                        assessmentOption = assessmentSubMenu();
                    }
                    System.out.println("Exiting back to Trainer menu...");
                    System.exit(0);
                    break;
                case 8://Report Sub menu
                    int reportOption = reportSubMenu();
                    while (reportOption != 0) {
                        switch (reportOption) {
                            case 1: //Specific member progress (via email search)
                                String reportEmail = validNextString("Please enter the email of the of the member you are looking for: ");
                                gym.searchMembersByEmail(reportEmail).getMemberProgress();
                                break;
                            case 2: //Specific member progress (via name search)
                                String reportName = validNextString("Please enter the name of the member you are looking for: ");
                                gym.searchMembersByName(reportName);
                                //Need to finish this method!!!
                                break;
                            case 3:// Need more clarification here!!!!
                                break;
                            default:
                                System.out.println("Invalid option entered. Please try again.");
                                break;
                        }
                        System.out.println("\nPress any key to continue: ");
                        input.nextLine();
                        input.nextLine();
                        reportOption = reportSubMenu();
                    }
                    System.out.println("Exiting back to Trainer menu...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option entered. Please try again.");
                    break;
            }
            System.out.println("\nPress and key to continue: ");
            input.nextLine();
            input.nextLine();
            option = trainerMenu();
        }
        System.out.println("Exiting the program. Goodbye...");
        System.exit(0);
    }


    private int memberMenu() {
        System.out.println("Welcome to your Member menu!");
        System.out.println("---------------");
        System.out.println("1) View your profile");
        System.out.println("2) Update your profile");
        System.out.println("3) View your progress");
        System.out.println("---------------");
        System.out.println("0) Exit");
        int option = validNextInt("==>");
        return option;
    }

    private int memberSubMenu() {
        System.out.println("Welcome to your Member menu!");
        System.out.println("---------------");
        System.out.println("x) View your profile");
        System.out.println("x) Update your profile");
        System.out.println("x) View your progress");
        System.out.println("   -- 1) View progress by weight");
        System.out.println("   -- 2) View progress by chest measurement");
        System.out.println("   -- 3) View progress by thigh measurement");
        System.out.println("   -- 4) View progress by upper arm measurement");
        System.out.println("   -- 5) View progress by waist measurement");
        System.out.println("   -- 6) View progress by hips measurement");
        System.out.println("   -----------------)");
        System.out.println("   -- 0) Exit to main menu");
        System.out.println("   -----------------)");
        System.out.println("---------------");
        System.out.println("x) Exit");
        int option = validNextInt("==>");
        return option;
    }

    private int trainerMenu() {
        System.out.println("Welcome to your Trainer menu!");
        System.out.println("---------------");
        System.out.println("1) Add a new member");
        System.out.println("2) List all members");
        System.out.println("3) Search for member by email");
        System.out.println("4) Search for member by name");
        System.out.println("5) List members with an ideal body weight");
        System.out.println("6) List members by a specific BMI category");
        System.out.println("---------------");
        System.out.println("7) Assessments");
        System.out.println("8) Reports");
        System.out.println("---------------");
        System.out.println("0) Exit");
        int option = validNextInt("==>");
        return option;
    }

    private int assessmentSubMenu() {
        System.out.println("Welcome to the Assessments menu!");
        System.out.println("");
        System.out.println("   -- 1) Add a new assessment for a member");
        System.out.println("   -- 2) Update a comment on a members assessment");
        System.out.println("   -----------------)");
        System.out.println("   -- 0) Exit to main menu");
        System.out.println("   -----------------)");
        int assessmentOption = validNextInt("==>");
        return assessmentOption;
    }

    private int reportSubMenu() {
        System.out.println("Welcome to the Reports menu!");
        System.out.println("   -- 1) Search for member progress via email");
        System.out.println("   -- 2) Search for member progress via name");
        System.out.println("   -- 3) Show overall member progress");
        System.out.println("   -----------------)");
        System.out.println("   -- 0) Exit to main menu");
        int reportOption = validNextInt("==>");
        return reportOption;
    }

    private void register() {
        String input = validNextString("Would you like to register as a Premium Member or a Student Member? [P/S]");
        if ((input.toUpperCase().equals("P")) || (input.toUpperCase().equals("S"))) {
            System.out.println("Please enter your details: ");
            validEmail = validNextString("Please enter your email: ");
            while (gym.searchMembersByEmail(validEmail) != null) {
                System.out.print("Sorry but that email is currently registered to another user.\nPlease try again.");
                validEmail = validNextString("Please enter your email: ");
            }
            String name = validNextString("Please enter your name: ");
            String address = validNextString("Please enter your address: ");
            String gender = validNextString("Please enter your gender [M/F]: ");
            double height = validNextDouble("Please enter your height: ");
            double startingWeight = validNextDouble("Please enter your current weight: ");
            String chosenPackage = validNextString("Please enter your chosen gym package: ");
            if (input.toUpperCase().equals("S")) {
                String studentId = validNextString("Please enter your Student ID: ");
                String collegeName = validNextString("Please enter your College's Name: ");
                gym.addMember(new StudentMember(validEmail, name, address, gender, height, startingWeight, chosenPackage, studentId, collegeName));
            } else if (input.toUpperCase().equals("P")) {
                gym.addMember(new PremiumMember(validEmail, name, address, gender, height, startingWeight, chosenPackage));
            }
        } else{
            System.out.println("Invalid option. Please try again: ");
        }
    }
}
