package controllers;

import java.util.Scanner;
import utils.ScannerInput;

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

    private void welcomeMenu(){
        System.out.println("Welcome to your very own personal gym app!");
        System.out.println("---------------");
        System.out.println("Would you like to Login to your account or Register a new one? [L/R]");
    }

    private void runMenu(){
        welcomeMenu();
    }
}
