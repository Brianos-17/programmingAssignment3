package models;

import models.Member;

/**
 * Created by Brian on 24/04/2017.
 */
public class PremiumMember extends Member {


    public PremiumMember(double height, double startingWeight, String chosenPackage, Hashmap memberProgress)
    {
        super(height, startingWeight, chosenPackage, memberProgress);
    }
}
