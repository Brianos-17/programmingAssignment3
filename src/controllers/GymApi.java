package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import java.util.ArrayList;

/**
 * Created by Brian on 25/04/2017.
 */
public class GymApi {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    public GymApi(){
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void addTrainer(Trainer trainer){
        trainers.add(trainer);
    }

    public int numberOfMembers(){
        return members.size();
    }

    public int numberOfTrainers(){
        return trainers.size();
    }

    public ArrayList<Member> getMembers(){
        return members;
    }

    public ArrayList<Trainer> getTrainers(){
        return trainers;
    }

    public boolean isValidMemberIndex(int index){
        if(index <= (members.size()-1) && (index > -1)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isValidTrainerIndex(int index){
        if(index <= (trainers.size() -1) && (index > -1)){
            return true;
        }
        else{
            return false;
        }
    }

    public Member searchMembersByEmail(String emailEntered){
        int i = 0;
        while(i <= members.size()){
            if(members.get(i).getEmail().equals(emailEntered)){
              return members.get(i);
              break;
            }
            else{
               return
            }
            i ++;
        }
    }

    public String searchMembersByName(String nameEntered){

    }

    public Person searchTrainersByEmail(String emailEntered){

    }

    public String listMembers(){
        String list = "";
        for(int i = 0; i <= members.size(); i ++){
            list = "Member Details; "
                    + "\nMember Name: " + members.get(i).getName()
                    + "\nMember E-Mail: " + members.get(i).getEmail()
                    + "\nMember Address: " + members.get(i).getAddress()
                    + "\nMember Gender: " + members.get(i).getGender()
                    + "\nMember Height: " + members.get(i).getHeight()
                    + "\nMember Starting Weight: " + members.get(i).getStartingWeight()
                    + "\nMember Package: " + members.get(i).getChosenPackage();
          if(members.get(i).getChosenPackage().equals("student")){
            list = list + "\nStudent ID: " + members.get(i).getStudentId()
                    + "\nCollege Name: " + members.get(i).getCollegeName()
            }
        }
    }
}
