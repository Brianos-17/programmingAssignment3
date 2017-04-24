package models;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Brian on 24/04/2017.
 */
public abstract class Member extends Person {
    private double height;
    private double startingWeight;
    private String chosenPackage;
    private HashMap<Date, Assessment> memberProgress;

    public Member (String email, String name, String address, String gender,
                   double height, double startingWeight, String chosenPackage, HashMap memberProgress)
    {
        super(email, name, address, gender);
        if ((height >= 1) && (height <= 3))
        {
            this.height=height;
        }
        if ((startingWeight >= 35) && (startingWeight <= 250))
        {
            this.startingWeight=startingWeight;
        }
    }
}
