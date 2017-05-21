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
 *
 * @author Brian O'Sullivan
 *         <p>
 *         MenuController Class. Allows users to sign up, log in and interact with the gym app
 */
public class MenuController {
    private GymApi gym;
    private Scanner input;
    private static String email; //Allows members to view their profiles without having to search their own email again
    private HashMap<String, String> chosenPackage;
    private boolean membersInGym;

    /**
     * Default main method which kicks of when the program is launched. Runs the MenuController constructor
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        new MenuController();
    }

    /**
     * Constructor for class MenuController. Creates an instance of this class.
     * Creates new Scanner, GymApi, and pre-loads the chosenPackage HashMap.
     * Checks if there are currently any members already to prevent login option if there are no accounts
     * Runs the welcome menu
     */
    private MenuController() {
        input = new Scanner(System.in);
        gym = new GymApi();
        chosenPackage = new HashMap<>();
        chosenPackage.put("Package 1", "Allowed access anytime to gym.\nFree access to all classes.\n" +
                "\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes." +
                "\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 3", "Allowed access to gym at off-peak times.\n€5 fee for all" +
                "classes.\nNo access to deluxe changing rooms.");
        chosenPackage.put("WIT", "Allowed access to gym during term time.\n€4 fee for all classes." +
                "\nNo access to deluxe changing rooms.");
        membersInGym();
        welcomeMenu();
    }

    /**
     * Method to check whether there are already members in the gym.
     * If so the membersInGym boolean is set to true and the welcome menu will give both the options login and register
     * Otherwise only the register option is given
     */
    private void membersInGym() {
        try {
            gym.load();
            System.out.println("Checking for any available gym data...");
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
        if (gym.members.size() > 0) {
            membersInGym = true;
        }
    }

    /**
     * The welcomeMenu method loads any existing data and then asks the user to either log in or register a new account.
     * If a users chooses to register they are taken to a different method.
     * There are seperate log in paths for members and trainers leading to different sub menus.
     * If a user fails their log in authentication the system will automatically exit
     */
    private void welcomeMenu() {
        System.out.println("Welcome to your very own personal gym app!");
        System.out.println("---------------");
        if (membersInGym) {
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

    /**
     * Method which prints out the options available to the user while running the member menu
     *
     * @return int representing the option the user wishes to use in the runMemberMenu method
     */
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

    /**
     * Method which prints out the options available to the user while running the sub section of the member menu
     *
     * @return int representing the option the user wishes to carry out
     */
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

    /**
     * Method which prints out the options available to the user while running the runTrainerMenu method
     *
     * @return int representing the option the users wishes to carry out
     */
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

    /**
     * Method which prints out the options available to the user in the assessment sub menu of the trainer menu
     *
     * @return int representing the option the user wishes to carry out
     */
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

    /**
     * Method which prints out the options available in the report sub menu of the trainer menu
     *
     * @return int representing the option the user wishes to carry out
     */
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

    /**
     * Method which runs the member menu. Switch statements allow users to cycle through all options choosing which ones
     * they want to carry out.
     * Leads to a series of other methods depending on the option chosen by the user
     * 0 will exit the system and save the data, or bring the user out of sub menus
     */
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
            gym.JSONstore();
            System.out.println("Saving gym details...");
        } catch (Exception e) {
            System.err.println("Error writing to fle: " + e);
        }
        System.exit(0);
    }

    /**
     * Method which runs the trainer menu and its associated sub menus: assessment and report
     * Switch statement allows the users to cycle through and select whatever option they wish. This method leads to a series
     * of other methods in the the MenuController and other classes
     * 0 will exit the system and save the data, or bring users out of the sub menu they are currently in
     */
    private void runTrainerMenu() {
        int option = trainerMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    System.out.println(gym.listMembers());
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
                    System.out.println(gym.listMembersWithIdealWeight());
                    break;
                case 6: //List members with a specific BMI category
                    System.out.println("Please select the category of BMI you wish to search by: "
                            + "\n 1) VERY SEVERELY UNDERWEIGHT"
                            + "\n 2) SEVERELY UNDERWEIGHT"
                            + "\n 3) UNDERWEIGHT"
                            + "\n 4) NORMAL"
                            + "\n 5) OVERWEIGHT"
                            + "\n 6) MODERATELY OBESE"
                            + "\n 7) SEVERELY OBESE"
                            + "\n 8) VERY SEVERELY OBESE");
                    int category = input.nextInt();
                    if (category == 1) {
                        System.out.println(gym.listMembersBySpecificBMICategory("VERY SEVERELY UNDERWEIGHT"));
                    } else if (category == 2) {
                        System.out.println(gym.listMembersBySpecificBMICategory("SEVERELY UNDERWEIGHT"));
                    } else if (category == 3) {
                        System.out.println(gym.listMembersBySpecificBMICategory("UNDERWEIGHT"));
                    } else if (category == 4) {
                        System.out.println(gym.listMembersBySpecificBMICategory("NORMAL"));
                    } else if (category == 5) {
                        System.out.println(gym.listMembersBySpecificBMICategory("OVERWEIGHT"));
                    } else if (category == 6) {
                        System.out.println(gym.listMembersBySpecificBMICategory("MODERATELY OBESE"));
                    } else if (category == 7) {
                        System.out.println(gym.listMembersBySpecificBMICategory("SEVERELY OBESE"));
                    } else if (category == 8) {
                        System.out.println(gym.listMembersBySpecificBMICategory("VERY SEVERELY OBESE"));
                    } else System.out.println("Invalid option. Please try again");
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
                                    System.out.println("New assessment has been added.\n");
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
            gym.JSONstore();
            System.out.println("Saving gym details...");
        } catch (Exception e) {
            System.err.println("Error writing to fle: " + e);
        }
        System.exit(0);
    }

    /**
     * Method to allow new users to register as either a member or a trainer.
     * Users choosing to register as a member are re-directed to the registerMember method. Trainers are registered and add to the
     * trainers arraylist.
     * After registration users are brought to the appropriate menu
     */
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

    /**
     * Method to allow users to register as a member. Different options are given to register as either one of the two Member subclasses.
     * Once valid data has been entered the members are added to the members ArrayList and brought to the members menu
     */
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
            while ((height < 1) || (height > 3)) {
                System.err.println("Invalid option entered. Please choose a height between 1 and 3 meters.");
                height = validNextDouble("Please enter your height(in Metres): ");
            }
            double startingWeight = validNextDouble("Please enter your current weight(in KGs): ");
            while ((startingWeight < 35) || (startingWeight > 250)) {
                System.err.println("Invalid option entered. Please enter a starting weight between 35 and 250 KGs.");
                startingWeight = validNextDouble("Please enter your current weight(in KGs): ");
            }
            System.out.println("Gym Packages: ");
            for(Map.Entry<String, String> chosenPackage: chosenPackage.entrySet()) {
                String key = chosenPackage.getKey();
                String value = chosenPackage.getValue();
                System.out.println("\n" + key + "\t" + value);
            }
            String chosenPackage = validNextString("Please enter your chosen gym package from the above: ");
            while(!(chosenPackage.equals("Package 1")) || (chosenPackage.equals("Package 2")) || (chosenPackage.equals("Package 3"))
                    || (chosenPackage.equals("WIT"))) {
                System.err.println("Invalid option entered. Please choose one of the above packages");
                chosenPackage = validNextString("Please enter your chosen gym package from the above: ");
            }
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

    /**
     * Method to allow members update their profile.
     * They are asked if they want to change each field, and validation checks are preformed to ensure correct data is entered
     */
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
            while ((updateHeight < 1) || (updateHeight > 3)) {
                System.err.println("Invalid option entered. Please choose a height between 1 and 3 meters.");
                updateHeight = validNextDouble("Please enter your height(in Metres): ");
            }
            currentMember.setHeight(updateHeight);
        }
        String f = validNextString("Would you like to update your starting weight? [Y/N]");
        if (f.toUpperCase().equals("Y")) {
            double updateStartingWeight = validNextDouble("Please enter your new starting weight(in Kgs): ");
            while ((updateStartingWeight < 35) || (updateStartingWeight > 250)) {
                System.err.println("Invalid option entered. Please enter a starting weight between 35 and 250 KGs.");
                updateStartingWeight = validNextDouble("Please enter your current weight(in KGs): ");
            }
            currentMember.setStartingWeight(updateStartingWeight);
        }
        String g = validNextString("Would you like to update your chosen package? [Y/N]");
        if (g.toUpperCase().equals("Y")) {
            String updateChosenPackage = validNextString("Please enter your updated package: ");
            while(!(updateChosenPackage.equals("Package 1")) || (updateChosenPackage.equals("Package 2")) || (updateChosenPackage.equals("Package 3"))
                    || (updateChosenPackage.equals("WIT"))) {
                System.err.println("Invalid option entered. Please enter a valid package.");
                updateChosenPackage = validNextString("Please enter your updated package: ");
            }
            currentMember.setChosenPackage(updateChosenPackage);
        }
    }

    /**
     * Method which allows a user to look up the progress of a specific member based on their email address.
     * Returns a list in reverse chronological order of the dates and the specific measurement the user has requested
     *
     * @param email String representing the email for a specific member
     */
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