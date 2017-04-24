import java.util.HashMap;

/**
 * Created by Brian on 24/04/2017.
 */
public abstract class Member extends Person {
    private double height;
    private double startingWeight;
    private String chosenPackage;
    private HashMap<date, assessmentDetails> memberProgress;

    public Member (double height, double startingWeight, String chosenPackage, HashMap memberProgress)
    {
        if ((height >= 1) && (height <= 3))
        {
            this.height=height;
        }
        if ((staringWeight >= 35) && (startingWeight <= 250))
        {
            this.startingWeight=startingWeight;
        }
    }
}
