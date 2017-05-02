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
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    public GymApi() {
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();
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

    public Member searchMembersByEmail(String emailEntered) {
        int i = 0;
        while (i < members.size()) {
            if (members.get(i).getEmail().equals(emailEntered)) {
                return members.get(i);
            }
            i++;
        }
        return null;
    }

    public String searchMembersByName(String nameEntered) {
        if (members.size() > 0) {
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i).getName().contains(nameEntered)) {
                    return members.get(i).toString();
                } else {
                    return "There are no members in the gym matching your search. Please try again.";
                }
            }
        }
        return "There are currently no members in the gym.";
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
        String list = "";
        for (int i = 0; i <= members.size(); i++) {
            list = list + members.get(i).toString();
        }
        return list;
    }


    public String listMembersWithIdealWeight() {
        StringBuilder list = new StringBuilder();
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                if (isIdealBodyWeight(members.get(i), members.get(i).latestAssessment())) {
                    list.append(members.get(i));
                } else {
                    return "There are no members in the gym with an Ideal Body Weight.";
                }
                i++;
            }
        } else {
            return "There are currently no members in the Gym.";
        }
        return "The following members have an Ideal Body Weight: " + list.toString();
    }

    public String listMembersBySpecificBMICategory(String category) {
        StringBuilder list = new StringBuilder();
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                if (determineBMICategory(
                        calculateBMI(members.get(i), members.get(i).latestAssessment())).equals(category)) {
                    list.append(members.get(i));
                } else {
                    return "There are no members in the gym matching this category.";
                }
            }
        } else {
            return "There are currently no members in the Gym.";
        }
        return list.toString();
    }

    public String listMemberDetailsImperialAndMetric() {
        StringBuilder list = new StringBuilder();
        if (members.size() > 0) {
            int i = 0;
            while (i < members.size()) {
                list.append(members.get(i).getName()).append(": ").append(members.get(i).latestAssessment().getWeight())
                        .append(" KGs ( ").append(convertWeightKgToPounds(members.get(i).latestAssessment())).append(" lbs) ")
                        .append(members.get(i).getHeight()).append(" Metres ( ").append(convertHeightMetresToInches(members.get(i)))
                        .append(" inches).\n");
            }
        } else {
            return "There are currently no members in ths Gym.";
        }
        return list.toString();
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


