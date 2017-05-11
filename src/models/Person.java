package models;

/**
 * Created by Brian on 24/04/2017.
 */
public abstract class Person {
    private String email;
    private String name;
    private String address;
    private String gender;

    //Constructor for class models.Person
    public Person(String email, String name, String address, String gender) {
        setEmail(email);
        setName(name);
        setAddress(address);
        setGender(gender);
    }

    public String toString() {
        return "E-Mail Address: " + email
                + "\nName: " + name
                + "\nAddress: " + address
                + "\nGender: " + gender;
    }

    //Accessor Methods
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    //Mutator Methods


    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        if (name.length() > 30) {
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
