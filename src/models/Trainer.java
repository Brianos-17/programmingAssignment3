package models;

/**
 * Created by Brian on 24/04/2017.
 */
public class Trainer extends Person {
    private String speciality;

    public Trainer (String email, String name, String address, String gender, String speciality)
    {
        super(email, name, address, gender);
        this.speciality = speciality;
    }
}