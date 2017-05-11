package models;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Brian on 24/04/2017.
 */
public class StudentMember extends Member {
    private String studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage, String studentId, String collegeName) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
        setStudentId(studentId);
        setCollegeName(collegeName);
        chosenPackage(chosenPackage);
    }

    public void chosenPackage(String packageChoice) {
        if (getCollegeName().toUpperCase().equals("WIT")) {
            setChosenPackage("WIT");
        }
        else
            setChosenPackage("Package 3");
    }

    //Accessor Methods
    public String getStudentId() {
        return studentId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    //Mutator Methods
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String toString() {
        return super.toString() + "\nStudent ID: " + studentId
                + "\nCollege Name: " + collegeName;
    }
}
