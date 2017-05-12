package models;


/**
 * Created by Brian on 24/04/2017.
 */
public class Assessment {
    private double weight;
    private double chest;
    private double thigh;
    private double upperArm;
    private double waist;
    private double hips;
    private String comment;
    private Trainer trainer;

    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips,
                      String comment, Trainer trainer) {
        setWeight(weight);
        setChest(chest);
        setThigh(thigh);
        setUpperArm(upperArm);
        setWaist(waist);
        setHips(hips);
        setComment(comment);
        setTrainer(trainer);
    }

    //Mutator Methods
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    //Accessor Methods
    public double getWeight() {
        return weight;
    }

    public double getChest() {
        return chest;
    }

    public double getThigh() {
        return thigh;
    }

    public double getUpperArm() {
        return upperArm;
    }

    public double getWaist() {
        return waist;
    }

    public double getHips() {
        return hips;
    }

    public String getComment() {
        return comment;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String toString(){
        return "Weight: " + getWeight()
                + "\nChest: " + getChest()
                + "\nThigh: " + getThigh()
                + "\nUpper Arm: " + getUpperArm()
                + "\nWaist: " + getWaist()
                + "\nHip: " + getHips()
                + "\nTrainer's Comment: " + getComment()
                + "\nTrainer: " + getTrainer();
    }
}


