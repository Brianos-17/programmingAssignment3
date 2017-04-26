package models;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Brian on 24/04/2017.
 */
public class PremiumMember extends Member {


    public PremiumMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
    }

    public void chosenPackage(String packageChoice) {
        getChosenPackage();
    }

    public String toString(){
        return super.toString();
    }
}
