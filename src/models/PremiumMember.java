package models;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Brian on 24/04/2017.
 */
public class PremiumMember extends Member {


    public PremiumMember(String email, String name, String address, String gender, String studentId, String collegeName,
                         double height, double startingWeight, String chosenPackage, HashMap<Date, Assessment> memberProgress) {
        super(email, name, address, gender, height, startingWeight, chosenPackage, memberProgress);
    }

    public void chosenPackage(String packageChoice) {
        getChosenPackage();
    }
}
