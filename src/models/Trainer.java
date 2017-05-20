package models;

/**
 * Created by Brian on 24/04/2017.
 * @author Brian O'Sullivan
 *
 * Trainer Class responsible for updating assessments for members and adding comments
 */
public class Trainer extends Person {
    private String speciality;

    /**
     * Constructor for class Trainer. Creates an instance of this class.
     * Inherits most of its instance fields from its super class Person
     *
     * @param email String representing the email for this trainer
     * @param name String representing the name for this trainer
     * @param address String representing the address for this trainer
     * @param gender String representing the gender for this trainer
     * @param speciality String representing the speciality for this trainer
     */
    public Trainer (String email, String name, String address, String gender, String speciality) {
        super(email, name, address, gender);
        setSpeciality(speciality);
    }

    //Mutator Method
    /**
     * Method which sets the speciality for this trainer. No validation is preformed on this method
     * @return String representing the speciality for this trainer
     */
    public String getSpeciality() {
        return speciality;
    }

    //Accessor Method
    /**
     * Method which sets the speciality for this trainer.
     * @param speciality String representing the speciality for this member
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * Method which returns a human readable string of the trainer object. Over-rides the toString method in its super class Person
     * @return String which is human readable
     */
    public String toString(){
        return super.toString() + "Speciality: " + speciality;
    }
}
