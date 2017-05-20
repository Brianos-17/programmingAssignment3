package models;

import java.util.*;
import java.util.HashMap;

/**
 * Created by Brian on 24/04/2017.
 * @author Brian O'Sullivan
 *
 * Member class, is an extension of the Person class, but is also abstract itself and will have its own concrete implementations
 * This class adds its own instance fields height, startingWeight, chosenPackage and a Hashmap to store Date and Assessment classes
 */
public abstract class Member extends Person {
    private double height;
    private double startingWeight;
    private String chosenPackage;
    private HashMap<Date, Assessment> memberProgress;

    /**
     * Constructor for class Member. Takes in parameters from its super class and adds its own.
     *
     * @param email String representing this members email
     * @param name String representing this members name
     * @param address String representing this members address
     * @param gender String representing this members gender
     * @param height double representing this members height in Meters
     * @param startingWeight double representing this members starting weight in KGs
     * @param chosenPackage String representing this members chosen gym package
     */
    public Member(String email, String name, String address, String gender,
                  double height, double startingWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartingWeight(startingWeight);
        setChosenPackage(chosenPackage);
        memberProgress = new HashMap<>();
    }

    //Accessor Methods
    /**
     * Method which returns this members height
     * @return double representing this members height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Method which returns this members starting weight
     * @return double representing this members starting weight in KGs
     */
    public double getStartingWeight() {
        return startingWeight;
    }

    /**
     * Method which returns this members chosen package
     * @return String representing this members chosen gym package
     */
    public String getChosenPackage() {
        return chosenPackage;
    }

    /**
     * Method which returns the HashMap containing this members Assessments with the dates on which they were added
     * @return HashMap containing member assessments
     */
    public HashMap getMemberProgress() {
        return memberProgress;
    }

    //Mutator Methods

    /**
     * Method which sets the height in meters for this member. Will only accept a double between 1 and 3
     * @param height double representing the height for this member
     */
    public void setHeight(double height) {
        if ((height >= 1) && (height <= 3)) {
            this.height = height;
        }
    }

    /**
     * Method which sets the starting weight in KGs for this member. Will only accept a double between 35 and 250
     * @param startingWeight double representing this members starting weight
     */
    public void setStartingWeight(double startingWeight) {
        if ((startingWeight >= 35) && (startingWeight <= 250)) {
            this.startingWeight = startingWeight;
        }
    }

    /**
     * Method which sets the chosen package for this member
     * @param chosenPackage String representing the chosen gym package for this member
     */
    public void setChosenPackage(String chosenPackage) {
            this.chosenPackage = chosenPackage;
    }

    /**
     * Method which adds a new assessment of class Assessment to this members HashMap along with the Date it was conducted as its key
     * @param date Date of which the assessment was entered into the HashMap, acts as the key
     * @param assessment Assessment containing members details, acts as the value
     */
    public void setMemberProgress(Date date, Assessment assessment) {
        memberProgress.put(date, assessment);
    }

    /**
     * Method which returns a human readable string of the member object. Over-rides the toString in its super class PErson
     * Is Over-ridden by its own subclasses
     * @return  String which is human readable
     */
    public String toString() {
        return super.toString() + "\nHeight(in metres): " + height
                + "\nStarting Weight(in kgs): " + startingWeight
                + "\nChosen Package: " + chosenPackage;
    }

    /**
     * Method which returns the latest assessment based on the last entry in the memberProgress HashMap (by calender date)
     * @return Assessment which is the most recently added by alender date
     */
    public Assessment latestAssessment() {
        return memberProgress.get(sortedAssessmentDates().last());
    }

    /**
     * Method which sorts the entire memberProgress HashMap in order of their dates
     * @return the assessments dates sorted in date order
     */
    public SortedSet<Date> sortedAssessmentDates() {
         return new TreeSet<>(memberProgress.keySet());
    }

    /**
     * The concrete implementation of this method will be completed in Member subclasses
     * @param chosenPackage String representing the chosen gym package for this member
     */
    public abstract void chosenPackage(String chosenPackage);
}
