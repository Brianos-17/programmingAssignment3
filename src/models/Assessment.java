package models;


/**
 * Created by Brian on 24/04/2017.
 * @author Brian O'Sullivan
 *
 * Assessment class holds information pertaining to members health and body measurements
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

    /**
     * Constructor for class Assessment. Creates an instance of this class.
     *
     * @param weight double representing the weight for this Assessment
     * @param chest double representing the chest measurement for this Assessment
     * @param thigh double representing the thigh measurement for this Assessment
     * @param upperArm double representing the upper arm measurement for this Assessment
     * @param waist double representing the waist measurement for this Assessment
     * @param hips double representing the hip measurement for this Assessment
     * @param comment String representing a comment made by a trainer for this Assessment
     * @param trainer Trainer representing the object from class Trainer for this Assessment
     */
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
    /**
     * Method which sets the weight for this Assessment
     * @param weight double representing the weight for this Assessment
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Method which sets the chest measurement for this Assessment
     * @param chest double representing the chest measurement for this Assessment
     */
    public void setChest(double chest) {
        this.chest = chest;
    }

    /**
     * Method which sets the thigh measurement for this Assessment
     * @param thigh double representing the thigh measurement for this Assessment
     */
    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    /**
     * Method which sets the upper arm measurement for this Assessment
     * @param upperArm double representing the upper arm measurement for this Assessment
     */
    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    /**
     * Method which sets the waist measurement for this Assessment
     * @param waist double representing the waist measurement for this Assessment
     */
    public void setWaist(double waist) {
        this.waist = waist;
    }

    /**
     * Method which sets the hip measurement for this Assessment
     * @param hips double representing the hip measurement for this Assessment
     */
    public void setHips(double hips) {
        this.hips = hips;
    }

    /**
     * Method which sets the comment for this Assessment
     * @param comment String representing the comment left by a trainer for this Assessment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Method which sets the Trainer for this Assessment
     * @param trainer Trainer responsible for this assessment for a member
     */
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    //Accessor Methods

    /**
     * Method which returns the weight for this particular assessment
     * @return double representing the weight for this assessment
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Method which returns the chest measurement for this particular assessment
     * @return double representing the chest measurement for this assessment
     */
    public double getChest() {
        return chest;
    }

    /**
     * Method which returns the thigh measurement for this particular assessment
     * @return double representing the thigh measurement for this assessment
     */
    public double getThigh() {
        return thigh;
    }

    /**
     * Method which returns the upper arm measurement for this particular assessment
     * @return double representing the upper arm measurement for this assessment
     */
    public double getUpperArm() {
        return upperArm;
    }

    /**
     * Method which returns the waist measurement for this particular assessment
     * @return double representing the waist measurement for this assessment
     */
    public double getWaist() {
        return waist;
    }

    /**
     * Method which returns the hip measurement for this particular assessment
     * @return double representing the hip measurement for this assessment
     */
    public double getHips() {
        return hips;
    }

    /**
     * Method which returns the comment left by a trainer for this particular assessment
     * @return String representing the comment entered by a trainer for this assessment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Method which returns the trainer for this particular assessment
     * @return Trainer responsible for this assessment for a member
     */
    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * Method which returns a human readable string of this instance of class Assessment
     * @return String which is human readable
     */
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


