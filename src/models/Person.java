package models;

/**
 * Created by Brian on 24/04/2017.
 * @author Brian O'Sullivan
 *
 * Abstract class which forms the base for the Member and Trainer class
 */
public abstract class Person {
    private String email;
    private String name;
    private String address;
    private String gender;

    /**
     * Constructor for class Person. Creates and instance of this class.
     *
     * @param email String representing the email for this person
     * @param name String representing the name for this person
     * @param address String representing the address for this person
     * @param gender String representing the gender for this person
     */
    public Person(String email, String name, String address, String gender) {
        setEmail(email);
        setName(name);
        setAddress(address);
        setGender(gender);
    }

    /**
     *toString method, returns a human readable representation of the Person object, Is over-ridden by its subclasses
     *
     * @return String which is human readable
     */
    public String toString() {
        return "E-Mail Address: " + email
                + "\nName: " + name
                + "\nAddress: " + address
                + "\nGender: " + gender;
    }

    //Accessor Methods
    /**
     * Method which returns the email for this person
     * @return String representing this persons email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method which returns the name for this person
     * @return String representing this persons name
     */
    public String getName() {
        return name;
    }

    /**
     * Method which returns the address for this person
     * @return String representing this persons address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method which returns the gender for this person
     * @return String representing this persons gender
     */
    public String getGender() {
        return gender;
    }

    //Mutator Methods
    /**
     * Method which sets this persons email
     * @param email String representing the email for this person
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method which sets the name for this person. If the name is longer than 30 characters it will be truncated
     * and only the first 30 characters will be retained.
     * @param name String representing the name for this person
     */
    public void setName(String name) {
        if (name.length() > 30) {
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
    }

    /**
     * Method which sets the address for this person.
     * @param address String representing the address for this person
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method which sets the gender for this person. Will set the gender to "M", "F" or "Unspecified" based on user input
     * @param gender String representing the gender for this person.
     */
    public void setGender(String gender) {
        if ((gender.toUpperCase().equals("MALE")) || (gender.toUpperCase().equals("M"))) {
            this.gender = "M";
        } else if (gender.toUpperCase().equals("FEMALE") || (gender.toUpperCase().equals("F"))) {
            this.gender = "F";
        } else {
            this.gender = "Unspecified";
        }
    }
}
