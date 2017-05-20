package models;

/**
 * Created by Brian on 24/04/2017.
 * @author Brian O'Sullivan
 *
 * PremiumMember is a subclass of class Member.
 */
public class PremiumMember extends Member {

    /**
     * Constructor for class PremiumMember. Creates an instance of PremiumMember.
     * Inherits all its instance fields from its superclass
     *
     * @param email String representing the email for this PremiumMember
     * @param name String representing the name for this PremiumMember
     * @param address String representing the address for this PremiumMember
     * @param gender String representing the gender for this PremiumMember
     * @param height double representing the height for this PremiumMember
     * @param startingWeight double representing the starting weight for this PremiumMember
     * @param chosenPackage String representing the chosen gym package for this PremiumMember
     */
    public PremiumMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
        chosenPackage(chosenPackage);
    }

    /**
     * Method which sets the chosen package for this premium member.
     * Concrete implementation of the abstract method in the member class
     * @param packageChoice String representing the chosen gym package for this premium member
     */
    public void chosenPackage(String packageChoice) {
        setChosenPackage(packageChoice);
    }

    /**
     * Method which returns a human readable string of this premium member object.
     * Over-rides the toString method in the Member class
     * @return String which is human readable
     */
    public String toString(){
        return super.toString();
    }
}