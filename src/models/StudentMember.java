package models;

/**
 * Created by Brian on 24/04/2017.
 * @author Brian O'Sullivan
 *
 * Student Member in a concrete subclass of the abstract class Memeber
 */
public class StudentMember extends Member {
    private String studentId;
    private String collegeName;

    /**
     * Constructor for class StudentMember. Creates an instance of this class.
     *
     * @param email String representing the email for this student member
     * @param name String representing the name for this student member
     * @param address String representing the address for this student member
     * @param gender String representing the gender for this student member
     * @param height double representing the height for this student member
     * @param startingWeight double representing the starting weight for this student member
     * @param chosenPackage String representing the chosen gym package for this student member
     * @param studentId String representing the student id number for this student member
     * @param collegeName String representing the college name that this student member attends
     */
    public StudentMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage, String studentId, String collegeName) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
        setStudentId(studentId);
        setCollegeName(collegeName);
        chosenPackage(chosenPackage);
    }

    /**
     * Method which sets the chosen gym package for this student member. Concrete implementation of the abstract method in Member class.
     * If the students college name is WIT it will change their chosen package to "WIT" also. Otherwise all chosen packages will be set to "Package 3"
     * @param packageChoice String representing the chosen gym package for this student member
     */
    public void chosenPackage(String packageChoice) {
        if (getCollegeName().toUpperCase().equals("WIT")) {
            setChosenPackage("WIT");
        }
        else
            setChosenPackage("Package 3");
    }

    //Accessor Methods
    /**
     * Method which returns the student id number for this student member
     * @return String representing the student id for this student member
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Method which returns the college name for this student member
     * @return String representing the college name this student member attends
     */
    public String getCollegeName() {
        return collegeName;
    }

    //Mutator Methods

    /**
     * Method which sets the student id for this student member
     * @param studentId String representing the student id for this student member
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Method which sets the college name for this student member
     * @param collegeName String representing the college name this student member attends
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * Method which returns a human readable string of this student member object
     * Over-rides the toString method in its superclass Member
     * @return Sting which is human readable
     */
    public String toString() {
        return super.toString() + "\nStudent ID: " + studentId
                + "\nCollege Name: " + collegeName;
    }
}
