package models;

import models.Member;

/**
 * Created by Brian on 24/04/2017.
 */
public class StudentMember extends Member {
    private String studentId;
    private String collegeName;

    public StudentMember (String studentId, String collegeName)
    {
        super();
        this.studentId = studentId;
        this.collegeName = collegeName;
    }
}
