package controllers;

import models.Member;
import models.Trainer;

import static utils.Analytics.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;


/**
 * Created by Brian on 25/04/2017.
 */
public class GymApi {
    public ArrayList<Member> members;
    public ArrayList<Trainer> trainers;

    //Constructor for GymApi Class
    public GymApi() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public int numberOfMembers() {
        return members.size();
    }

    public int numberOfTrainers() {
        return trainers.size();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public boolean isValidMemberIndex(int index) {
        if (index <= (members.size() - 1) && (index > -1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidTrainerIndex(int index) {
        if (index <= (trainers.size() - 1) && (index > -1)) {
            return true;
        } else {
            return false;
        }
    }


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

    public String listMembers() {
        if (members.size() > 0) {
            String list = "";
            for (int i = 0; i < members.size(); i++) {
                list = list + members.get(i).toString() + "\n";
            }
            return list;
        } else
            return "There are currently no members in this gym.";
    }


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

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("gym.xml"));
        members = (ArrayList<Member>) is.readObject();
        trainers = (ArrayList<Trainer>) is.readObject();
        is.close();
    }

    public void store() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("gym.xml"));
        out.writeObject(members);
        out.writeObject(trainers);
        out.close();
    }
}


