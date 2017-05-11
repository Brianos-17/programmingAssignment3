package controllers;

import models.*;
import models.Trainer;
import models.Member;
import models.Assessment;

import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.ScannerInput.*;


/**
 * Created by Brian on 26/04/2017.
 */
public class MenuController {
    private GymApi gym;
    private Scanner input;
    private static String email; //Allows members to view their profiles without having to search their own email again
    private HashMap<String, String> chosenPackage;
    private boolean membersInGym;

    public static void main(String[] args) {
        new MenuController();
    }

    private MenuController() {
        input = new Scanner(System.in);
        gym = new GymApi();
        chosenPackage = new HashMap<>();
        chosenPackage.put("Package 1", "Allowed access anytime to gym.\nFree access to all classes.\n" +
                "\\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes." +
                "\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 3", "Allowed access to gym at off-peak times.\n€5 fee for all" +
                "classes.\nNo access to deluxe changing rooms.");
        chosenPackage.put("WIT", "Allowed access to gym during term time.\n€4 fee for all classes." +
                "\nNo access to deluxe changing rooms.");
        membersInGym();
        welcomeMenu();
    }

    private void membersInGym() {
        if (gym.members.size() > 0) {
            membersInGym = true;
        }
    }

    private void welcomeMenu() {
        try {
            gym.load();
            System.out.println("Checking for any available gym data...");
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
        System.out.println("Welcome to your very own personal gym app!");
        System.out.println("---------------");
        if (membersInGym = true) {
            String input = validNextString("Would you like to Login to your account or Register a new one? [L/R]");
            if (input.toUpperCase().equals("L")) {
                String memberOrTrainer = validNextString("Are you logging in as a Member or as a Trainer? [M/T]");
                if (memberOrTrainer.toUpperCase().equals("M")) {
                    email = validNextString("Please enter your e-mail address: ");
                    if (gym.searchMembersByEmail(email) != null) {
                        System.out.println("Welcome " + gym.searchMembersByEmail(email).getName() + "!\nYou have logged in successfully!");
                        runMemberMenu();
                    } else {
                        System.out.println("There is no Member registered to this email. Access Denied.");
                        System.exit(0);
                    }
                } else if (memberOrTrainer.toUpperCase().equals("T")) {
                    email = validNextString("Please enter your e-mail address: ");
                    if (gym.searchTrainersByEmail(email) != null) {
                        System.out.print("Welcome! You have logged in successfully!\n");
                        runTrainerMenu();
                    } else {
                        System.out.println("There is no Trainer registered to this email. Access Denied.");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Invalid option entered. Please try again: ");
                }
            } else if (input.toUpperCase().equals("R")) {
                register();
            }
        } else {
            System.out.println("There are currently no members in this gym. Please Register a new account.");
            register();
        }
    }

    private void runMemberMenu() {
        int option = memberMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.println("Here is your current profile: ");
                    System.out.println(gym.searchMembersByEmail(email).toString());
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    progressReport(email);
                    break;
                default:
                    System.out.println("Invalid option entered. Please try again.");
                    break;
            }
            System.out.println("\nPress any key to continue: ");
            input.nextLine();
            input.nextLine();
            option = memberMenu();
        }
        System.out.println("Exiting the program. Goodbye...");
        try {
            gym.store();
            System.out.println("Saving gym details...");
        } catch (Exception e) {
            System.err.println("Error writing to fle: " + e);
        }
        System.exit(0);
    }


    private void runTrainerMenu() {
        int option = trainerMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    gym.listMembers();
                    break;
                case 3:
                    String emailSearch = validNextString("Please enter the email you wish to search by: ");
                    System.out.println(gym.searchMembersByEmail(emailSearch));
                    break;
                case 4:
                    String nameSearch = validNextString("Please enter the name you wish to search by: ");
                    System.out.println(gym.searchMembersByName(nameSearch));
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
                                    Trainer trainer = gym.searchTrainersByEmail(email);
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
                                    System.out.println("Current comment: " + commentMember.latestAssessment().getComment());
                                    String newComment = validNextString("Please enter your updated comment: ");
                                    commentMember.latestAssessment().setComment(newComment);
                                    System.out.println("Trainers comment has been updated.");
                                }
                                break;
                            default:
                                System.out.println("Invalid option entered. Please try again.");
                                break;
                        }
                        System.out.println("\nPress any key to continue: ");
                        input.nextLine();
                        input.nextLine();
                        assessmentOption = assessmentSubMenu();
                    }
                    runTrainerMenu();
                    break;
                case 8://Report Sub menu
                    int reportOption = reportSubMenu();
                    while (reportOption != 0) {
                        switch (reportOption) {
                            case 1: //Specific member progress (via email search)
                                String reportEmail = validNextString("Please enter the email of the of the member you are looking for: ");
                                gym.searchMembersByEmail(reportEmail).getMemberProgress();
                                String input = validNextString("You have selected " + gym.searchMembersByEmail(reportEmail).getName() + "\nIs this the correct member? [Y/N]");
                                if (input.toUpperCase().equals("Y")) {
                                    progressReport(reportEmail);
                                }
                                break;
                            case 2: //Specific member progress (via name search)
                                String reportName = validNextString("Please enter the name of the member you are looking for: ");
                                String search = gym.searchMembersByName(reportName);
                                System.out.println(search);
                                String check = validNextString("Is this the correct Member? [Y/N]");
                                if (check.toUpperCase().equals("Y")) {
                                    Pattern p = Pattern.compile("[A-Z0-9.%+-]+@[A-Z0-9.-]+\\.[A-Z0-9-.]{2,4}", Pattern.CASE_INSENSITIVE);
                                    Matcher emailMatch = p.matcher(search);
                                    while (emailMatch.find()) {
                                        System.out.println(emailMatch.group());
                                        progressReport(emailMatch.group());
                                    }
                                }
                                break;
                            case 3:
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
                    runTrainerMenu();
                default:
                    System.out.println("Invalid option entered. Please try again.");
                    break;
            }
            System.out.println("\nPress any key to continue: ");
            input.nextLine();
            input.nextLine();
            option = trainerMenu();
        }
        System.out.println("Exiting the program. Goodbye...");
        try {
            gym.store();
            System.out.println("Saving gym details...");
        } catch (Exception e) {
            System.err.println("Error writing to fle: " + e);
        }
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
        System.out.println("Welcome to your progress menu!");
        System.out.println("---------------");
        System.out.println("   -- 1) View progress by weight");
        System.out.println("   -- 2) View progress by chest measurement");
        System.out.println("   -- 3) View progress by thigh measurement");
        System.out.println("   -- 4) View progress by upper arm measurement");
        System.out.println("   -- 5) View progress by waist measurement");
        System.out.println("   -- 6) View progress by hips measurement");
        System.out.println("   -----------------)");
        System.out.println("   -- 0) Exit to main menu");
        System.out.println("   -----------------)");
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
        String memberOrTrainer = validNextString("Would you like to register as a Member or as a Trainer? [M/T]");

        if (memberOrTrainer.toUpperCase().equals("M")) {
            System.out.println("You have chosen to register as a Member.");
            registerMember();
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

    private void registerMember() {
        String input = validNextString("Would you like to register as a Premium Member or a Student Member? [P/S]");
        if ((input.toUpperCase().equals("P")) || (input.toUpperCase().equals("S"))) {
            System.out.println("Please enter your details: ");
            email = validNextString("Please enter your email: ");
            while (gym.searchMembersByEmail(email) != null) {
                System.out.print("Sorry but that email is currently registered to another user.\nPlease try again.");
                email = validNextString("Please enter your email: ");
            }
            String name = validNextString("Please enter your name: ");
            String address = validNextString("Please enter your address: ");
            String gender = validNextString("Please enter your gender [M/F]: ");
            double height = validNextDouble("Please enter your height(in Metres): ");
            double startingWeight = validNextDouble("Please enter your current weight(in KGs): ");
            String chosenPackage = validNextString("Please enter your chosen gym package: ");
            if (input.toUpperCase().equals("S")) {
                String studentId = validNextString("Please enter your Student ID: ");
                String collegeName = validNextString("Please enter your College's Name: ");
                gym.addMember(new StudentMember(email, name, address, gender, height, startingWeight, chosenPackage, studentId, collegeName));
            } else if (input.toUpperCase().equals("P")) {
                gym.addMember(new PremiumMember(email, name, address, gender, height, startingWeight, chosenPackage));
            }
        } else {
            System.out.println("Invalid option. Please try again: ");
        }
    }

    private void update() {
        Member currentMember = gym.searchMembersByEmail(email);
        String a = validNextString("Would you like to update your email address? [Y/N]");
        if (a.toUpperCase().equals("Y")) {
            String updateEmail = validNextString("Please enter your new email: ");
            currentMember.setEmail(updateEmail);
        }
        String b = validNextString("Would you like to update your name? [Y/N]");
        if (b.toUpperCase().equals("Y")) {
            String updateName = validNextString("Please enter your new name: ");
            currentMember.setName(updateName);
        }
        String c = validNextString("Would you like to update your address? [Y/N]");
        if (c.toUpperCase().equals("Y")) {
            String updateAddress = validNextString("Please enter your new address: ");
            currentMember.setAddress(updateAddress);
        }
        String d = validNextString("Would you like to update your gender? [Y/N]");
        if (d.toUpperCase().equals("Y")) {
            String updateGender = validNextString("Please enter your gender: [M/F]");
            if (updateGender.toUpperCase().equals("M")) {
                updateGender = "M";
            } else {
                updateGender = "F";
            }
            currentMember.setGender(updateGender);
        }
        String e = validNextString("Would you like to update your height? [Y/N]");
        if (e.toUpperCase().equals("Y")) {
            double updateHeight = validNextDouble("Please enter your new height(in metres): ");
            currentMember.setHeight(updateHeight);
        }
        String f = validNextString("Would you like to update your starting weight? [Y/N]");
        if (f.toUpperCase().equals("Y")) {
            double updateStartingWeight = validNextDouble("Please enter your new starting weight(in Kgs): ");
            currentMember.setStartingWeight(updateStartingWeight);
        }
        String g = validNextString("Would you like to update your chosen package? [Y/N]");
        if (g.toUpperCase().equals("Y")) {
            String updateChosenPackage = validNextString("Please enter your updated package: ");
            currentMember.setChosenPackage(updateChosenPackage);
        }
    }

    private void progressReport(String email) {
        int memberSubMenu = memberSubMenu();
        while (memberSubMenu != 0) {
            Map memberProgress = gym.searchMembersByEmail(email).getMemberProgress();
            Map<Date, Assessment> newMap = new TreeMap<>(memberProgress);
            Iterator<Map.Entry<Date, Assessment>> entries = newMap.entrySet().iterator();
            switch (memberSubMenu) {
                case 1:
                    System.out.println("Here is a view of your progress by weight: ");
                    while (entries.hasNext()) {
                        Map.Entry<Date, Assessment> entry = entries.next();
                        System.out.println("Date: " + entry.getKey() + "\tWeight: " + entry.getValue().getWeight() + "Kg's");
                    }
                    break;

                case 2:
                    System.out.println("Here is a view of your progress by chest measurement: ");
                    while (entries.hasNext()) {
                        Map.Entry<Date, Assessment> entry = entries.next();
                        System.out.println("Date: " + entry.getKey() + "\tChest: " + entry.getValue().getChest() + "cm's");
                    }
                    break;

                case 3:
                    System.out.println("Here is a view of your progress by thigh measurement: ");
                    while (entries.hasNext()) {
                        Map.Entry<Date, Assessment> entry = entries.next();
                        System.out.println("Date: " + entry.getKey() + "\tThigh: " + entry.getValue().getThigh() + "cm's");
                    }
                    break;

                case 4:
                    System.out.println("Here is a view of your progress by upper arm measurement: ");
                    while (entries.hasNext()) {
                        Map.Entry<Date, Assessment> entry = entries.next();
                        System.out.println("Date: " + entry.getKey() + "\tUpper Arm: " + entry.getValue().getUpperArm() + "cm's");
                    }
                    break;

                case 5:
                    System.out.println("Here is a view of your progress by waist measurement: ");
                    while (entries.hasNext()) {
                        Map.Entry<Date, Assessment> entry = entries.next();
                        System.out.println("Date: " + entry.getKey() + "\tUpper Arm: " + entry.getValue().getWaist() + "cm's");
                    }
                    break;

                case 6:
                    System.out.println("Here is a view of your progress by hips measurement: ");
                    while (entries.hasNext()) {
                        Map.Entry<Date, Assessment> entry = entries.next();
                        System.out.println("Date: " + entry.getKey() + "\tUpper Arm: " + entry.getValue().getHips() + "cm's");
                    }
                    break;

                default:
                    System.out.println("Invalid option entered. Please try again.");
                    break;
            }
            System.out.println("\nPress any key to continue: ");
            input.nextLine();
            input.nextLine();
            memberSubMenu = memberSubMenu();
        }
    }

}
