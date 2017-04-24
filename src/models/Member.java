package models;

import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;

/**
 * Created by Brian on 24/04/2017.
 */
public abstract class Member extends Person {
    private double height;
    private double startingWeight;
    private String chosenPackage;
    private HashMap<Date, Assessment> memberProgress;

    public Member(String email, String name, String address, String gender,
                  double height, double startingWeight, String chosenPackage, HashMap memberProgress) {
        super(email, name, address, gender);
        if ((height >= 1) && (height <= 3)) {
            this.height = height;
        }
        if ((startingWeight >= 35) && (startingWeight <= 250)) {
            this.startingWeight = startingWeight;
        }
    }

    //Accessor Methods
    public double getHeight() {
        return height;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public HashMap<Date, Assessment> getMemberProgress() {
        return memberProgress;
    }

    //Mutator Methods
    public void setHeight(double height) {
        this.height = height;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    public void setMemberProgress(HashMap<Date, Assessment> memberProgress) {
        this.memberProgress = memberProgress;
    }

    //Returns the latest assessment based on last entry (by calendar date)
    public Assessment latestAssessment() {
        
    }

    //Returns the assessments dates sorted in date order
    public SortedSet<Date> sortedAssessmentDates() {

    }

    //The concrete implementation of this method will be completed in member subclasses
    public abstract void chosenPackage(String chosenPackage);

}
