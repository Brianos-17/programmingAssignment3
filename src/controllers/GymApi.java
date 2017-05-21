package controllers;

import com.google.gson.*;
import com.google.gson.GsonBuilder;
import models.Member;
import models.Trainer;

import static utils.Analytics.*;

import java.io.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;


/**
 *Created by Brian on 25/04/2017.
 *@author Brian O'Sullivan
 *
 *GymApi class is utlisied by the MenuController to create and impliment the objects needed to run the gym.
 * Also used to run some basic searches for the MenuController based off of given parameters, and to save and load gym data.
 */
public class GymApi {
    public ArrayList<Member> members;
    public ArrayList<Trainer> trainers;

    /**
     * Constructor for class GymApi. Creates an instance of this class.
     * Creates new ArrayLists containing all the members and trainers in the gym
     */
    public GymApi() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    /**
     * Method which adds a new Member to the gym
     * @param member Member details for the new member being added
     */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Method which adds a new trainer to the the gym
     * @param trainer Trainer details for the new trainer being added
     */
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    /**
     * Method which calculates how many members are currently stored in the gym
     * @return int representing the number of members
     */
    public int numberOfMembers() {
        return members.size();
    }

    /**
     * Method which calculates how many trainers are currently stored in the gym
     * @return int representing the number of trainers
     */
    public int numberOfTrainers() {
        return trainers.size();
    }

    /**
     * Method which returns all members in the gym
     * @return ArrayList of type Member
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * Method which returns all trainers in the gym
     * @return ArrayList of type Trainer
     */
    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    /**
     * Method which determines whether an index number is valid for the members ArrayList
     * @param index int representing the index to be checked
     * @return boolean true or false
     */
    public boolean isValidMemberIndex(int index) {
        if (index <= (members.size() - 1) && (index > -1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method which determines whether an index number is valid for the trainers ArrayList
     * @param index int representing the index to be checked
     * @return boolean true or false
     */
    public boolean isValidTrainerIndex(int index) {
        if (index <= (trainers.size() - 1) && (index > -1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method which takes a String entered and returns a list of members whos name matches or contains the entered parameter
     * @param nameEntered String representing the search being carried out
     * @return String representing the list of members who match the search, or a message indicating otherwise
     */
    public String searchMembersByName(String nameEntered) {
        String list = "";
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                if (members.get(i).getName().contains(nameEntered)) {
                    list += members.get(i).toString() + "\n";
                }
                i++;
            }
            if (list.equals("")) {
                return "There are no members in the gym matching your description.";
            } else
                return list;
        }
        return "There are currently no members in the gym.";
    }

    /**
     * Method which takes a String representing a members email and returns the members whos email matches this earch
     * @param emailEntered String representing the search being carried out
     * @return Members whos email matches the entered search
     */
    public Member searchMembersByEmail(String emailEntered) {
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                if (members.get(i).getEmail().equals(emailEntered)) {
                    return members.get(i);
                }
                i++;
            }
            return null;
        }
        return null;
    }

    /**
     * Method which takes a string representing a trainers email and returns the trainer whos email matches this search
     * @param emailEntered String representing the search being carried out
     * @return Trainer whos email matches the entered search
     */
    public Trainer searchTrainersByEmail(String emailEntered) {
        int i = 0;
        while (i < trainers.size()) {
            if (trainers.get(i).getEmail().equals(emailEntered)) {
                return trainers.get(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Method which returns a string list all members in the gym
     * @return String list of all members in the gym
     */
    public String listMembers() {
        if (members.size() > 0) {
            String list = "\n";
            for (int i = 0; i < members.size(); i++) {
                list = list + members.get(i).toString() + "\n";
            }
            return list;
        } else
            return "There are currently no members in this gym.";
    }

    /**
     * Method which returns a list off all members in the gym who have an ideal body weight
     * @return List of all members in thr gym with an ideal body weight
     */
    public String listMembersWithIdealWeight() {
        String list = "";
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                if (isIdealBodyWeight(members.get(i), members.get(i).latestAssessment())) {
                    list += members.get(i) + "\n";
                }
                i++;
            }
            if (list.equals("")) {
                return "There are no members in the gym with an Ideal Body Weight.";
            } else
                return "The following members have an Ideal Body Weight: \n" + list;
        } else
            return "There are currently no members in the Gym.";

    }

    /**
     * Method which allows a trainer to search for members in the BMI category of their choosing
     * @param category String representing the category of BMI to be searched
     * @return String list of all memmbers who match this category or a message indicating otherwise
     */
    public String listMembersBySpecificBMICategory(String category) {
        String list = "";
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                if (determineBMICategory(
                        calculateBMI(members.get(i), members.get(i).latestAssessment())).equals(category)) {
                    list += members.get(i) + "\n";
                }
                i++;
            }
            if (list.equals("")) {
                return "There are no members in the gym matching this category.";
            } else
                return list;
        }
        return "There are currently no members in the Gym.";
    }

    /**
     * Method which returns a string list of all members with their weight and height listed both imperially and metrically
     * @return String list
     */
    public String listMemberDetailsImperialAndMetric() {
        String list = "";
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                list += members.get(i).getName() + ": " + members.get(i).latestAssessment().getWeight() + "KGs (" + convertWeightKgToPounds(members.get(i).latestAssessment()) + "lbs) "
                        + members.get(i).getHeight() + " Metres (" + convertHeightMetresToInches(members.get(i)) + " inches).\n";

                i++;
            }
            return list;
        } else
            return "There are currently no members in ths Gym.";
    }

    /**
     * Method which loads the contents of the members and trainers arraylist upon entering the system from an XML file
     * @throws Exception e
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("gym.xml"));
        members = (ArrayList<Member>) is.readObject();
        trainers = (ArrayList<Trainer>) is.readObject();
        is.close();
    }

    /**
     * Method which saves the contents of the members and trainers arraylist upon exiting the system to an XML file
     * @throws Exception e
     */
    public void store() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("gym.xml"));
        out.writeObject(members);
        out.writeObject(trainers);
        out.close();
    }

    /**
     * Method which saves the
     * @throws Exception
     */
    public void JSONstore() throws Exception {
        try (Writer writer = new FileWriter("gym.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(members, writer);
            gson.toJson(trainers, writer);
        }
    }
}


