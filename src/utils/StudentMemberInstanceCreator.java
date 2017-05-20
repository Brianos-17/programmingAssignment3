package utils;

import com.google.gson.InstanceCreator;
import models.StudentMember;

import java.lang.reflect.Type;

/**
 * Created by Brian on 19/05/2017.
 */
public class StudentMemberInstanceCreator implements InstanceCreator<StudentMember> {
    private String email;
    private String name;
    private String address;
    private String gender;
    private double height;
    private double startingWeight;
    private String chosenPackage;
    private String studentId;
    private String collegeName;

    public StudentMemberInstanceCreator(String email, String name, String address, String gender,
                                        double height, double startingWeight, String chosenPackage, String studentId, String collegeName) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startingWeight = startingWeight;
        this.chosenPackage = chosenPackage;
        this.studentId = studentId;
        this.collegeName = collegeName;
    }

    @Override
    public StudentMember createInstance(Type type) {
        return new StudentMember(email, name, address, gender, height, startingWeight, chosenPackage, studentId, collegeName);
    }
}
