package controllers;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Brian on 11/05/2017.
 */
public class GymApiTest {
    private GymApi gym;
    private StudentMember normalStudentMember1;
    private StudentMember normalStudentMember2;
    private PremiumMember normalPremiumMember1;
    private Trainer normalTrainer1;
    private Trainer normalTrainer2;
    private Assessment assessment1;
    private Assessment assessment2;
    private Assessment assessment3;
    private Assessment assessment4;
    private Assessment assessment5;
    private Assessment assessment6;

    @Before
    public void setUp() throws Exception {
        normalStudentMember1 = new StudentMember("student1@email.com", "Genji", "54 Fake Street, Waterford, Co.Waterford", "M", 2.1, 84.6, "WIT", "1234567", "WIT");
        normalStudentMember2 = new StudentMember("fake@email.com", "Genji 2", "54 Fake Street, Waterford, Co.Waterford", "M", 2.1, 84.6, "WIT", "1234567", "WIT");
        normalPremiumMember1 = new PremiumMember("email1@email.com", "Tony Stark", "Avengers Tower, New York, America", "Male", 1.8, 76, "Package 1" );
        normalTrainer1 = new Trainer("brianos-17@hotmail.com", "Brian O'Sullivan", "11 Manor Drive, Manor Village, Tralee, Co. Kerry", "Male" ,"Cardio");
        normalTrainer2 = new Trainer("sarah-17@hotmail.com", "Sarah Jones", "5 Calder House, Brownes Road, Waterford, Co. Waterford", "F", "Strength");
        assessment1 = new Assessment(101, 40, 25, 20, 32, 38, "Very Good", normalTrainer1);
        assessment2 = new Assessment(74, 50, 42, 33, 43, 49, "Needs Improvement", normalTrainer2);
        assessment3 = new Assessment(51, 50, 42, 33, 43, 49, "Needs Improvement", normalTrainer2);
        assessment4 = new Assessment(87, 50, 42, 33, 43, 49, "Needs Improvement", normalTrainer2);
        assessment5 = new Assessment(120, 50, 42, 33, 43, 49, "Needs Improvement", normalTrainer2);
        assessment6 = new Assessment(200, 50, 42, 33, 43, 49, "Needs Improvement", normalTrainer2);
        gym = new GymApi();
    }

    @Test
    public void testEmptyArrayLists() throws Exception {
        assertThat(gym.members.size(), is(0));
        assertThat(gym.trainers.size(), is(0));
    }

    @Test
    public void testAddingAndCountingMember() throws Exception {
        gym.addMember(normalStudentMember1);
        assertThat(gym.numberOfMembers(), is(1));
        gym.addMember(normalPremiumMember1);
        assertThat(gym.numberOfMembers(), is(2));
    }

    @Test
    public void testAddingAndCountingTrainer() throws Exception {
        gym.addTrainer(normalTrainer1);
        assertThat(gym.numberOfTrainers(), is(1));
        gym.addTrainer(normalTrainer2);
        assertThat(gym.numberOfTrainers(), is(2));
    }

    @Test
    public void testGetMembers() throws Exception {
        assertTrue(gym.getMembers().isEmpty());
        gym.addMember(normalStudentMember1);
        gym.addMember(normalPremiumMember1);
        assertNotNull(gym.getMembers());
    }

    @Test
    public void testGetTrainers() throws Exception {
        assertTrue(gym.getTrainers().isEmpty());
        gym.addTrainer(normalTrainer1);
        gym.addTrainer(normalTrainer2);
        assertNotNull(gym.getTrainers());
    }

    @Test
    public void testIsValidMemberIndex() throws Exception {
        gym.addMember(normalStudentMember1);
        gym.addMember(normalPremiumMember1);
        assertThat(gym.isValidMemberIndex(-1), is(false));
        assertThat(gym.isValidMemberIndex(0), is(true));
        assertThat(gym.isValidMemberIndex(1), is(true));
        assertThat(gym.isValidMemberIndex(2), is(false));
    }

    @Test
    public void testIsValidTrainerIndex() throws Exception {
        gym.addTrainer(normalTrainer1);
        gym.addTrainer(normalTrainer2);
        assertThat(gym.isValidTrainerIndex(-1), is(false));
        assertThat(gym.isValidTrainerIndex(0), is(true));
        assertThat(gym.isValidTrainerIndex(1), is(true));
        assertThat(gym.isValidTrainerIndex(2), is(false));
    }

    @Test
    public void testSearchMembersByName() throws Exception {
        assertThat(gym.searchMembersByName("Anything"), is("There are currently no members in the gym."));
        gym.addMember(normalStudentMember1);
        gym.addMember(normalStudentMember2);
        gym.addMember(normalPremiumMember1);
        assertThat(gym.searchMembersByName("Invalid Name"), is("There are no members in the gym matching your description."));
        assertThat(gym.searchMembersByName("Genji"), is(normalStudentMember1.toString() + "\n" + normalStudentMember2.toString() + "\n"));
        assertThat(gym.searchMembersByName("Gen"), is(normalStudentMember1.toString() + "\n" + normalStudentMember2.toString() + "\n"));
        assertThat(gym.searchMembersByName("Tony Stark"), is(normalPremiumMember1.toString() + "\n"));
    }

    @Test
    public void testSearchMembersByEmail() throws Exception {
        assertNull(gym.searchMembersByEmail("Not a real email"));
        gym.addMember(normalStudentMember1);
        gym.addMember(normalStudentMember2);
        gym.addMember(normalPremiumMember1);
        assertThat(gym.searchMembersByEmail("student1@email.com"), is(normalStudentMember1));
        assertThat(gym.searchMembersByEmail("fake@email.com"), is(normalStudentMember2));
        assertThat(gym.searchMembersByEmail("email1@email.com"), is(normalPremiumMember1));
    }

    @Test
    public void testSearchTrainersByEmail() throws Exception {
        assertNull(gym.searchTrainersByEmail("brianos-17@hotmail.com"));
        assertNull(gym.searchTrainersByEmail("sarah-17@hotmail.com"));
        gym.addTrainer(normalTrainer1);
        gym.addTrainer(normalTrainer2);
        assertThat(gym.searchTrainersByEmail("brianos-17@hotmail.com"), is(normalTrainer1));
        assertThat(gym.searchTrainersByEmail("sarah-17@hotmail.com"), is(normalTrainer2));
        assertNull(gym.searchTrainersByEmail("not a real email"));
    }

    @Test
    public void testListMembers() throws Exception {
        assertThat(gym.listMembers(), is("There are currently no members in this gym."));
        gym.addMember(normalStudentMember1);
        assertThat(gym.listMembers(), is(normalStudentMember1.toString() + "\n"));
        gym.addMember(normalStudentMember2);
        assertThat(gym.listMembers(), is(normalStudentMember1.toString() + "\n" + normalStudentMember2.toString() + "\n"));
        gym.addMember(normalPremiumMember1);
        assertThat(gym.listMembers(), is(normalStudentMember1.toString() + "\n" + normalStudentMember2.toString() + "\n" + normalPremiumMember1.toString() + "\n"));
    }

    @Test
    public void testListMembersWithIdealWeight() throws Exception {
        assertThat(gym.listMembersWithIdealWeight(), is("There are currently no members in the Gym."));
        Date date = new Date();
        normalStudentMember2.setMemberProgress(date, assessment2);
        gym.addMember(normalStudentMember2);
        assertThat(gym.listMembersWithIdealWeight(), is("There are no members in the gym with an Ideal Body Weight."));
        normalStudentMember1.setMemberProgress(date, assessment1);
        gym.addMember(normalStudentMember1);
        assertThat(gym.listMembersWithIdealWeight(), is("The following members have an Ideal Body Weight: \n" + normalStudentMember1 + "\n"));
        normalPremiumMember1.setMemberProgress(date, assessment2);
        gym.addMember(normalPremiumMember1);
        assertThat(gym.listMembersWithIdealWeight(), is("The following members have an Ideal Body Weight: \n" + normalStudentMember1 + "\n" + normalPremiumMember1 + "\n"));
    }

    @Test
    public void testListMembersBySpecificBMICategory() throws Exception {
        assertThat(gym.listMembersBySpecificBMICategory("NORMAL"), is("There are currently no members in the Gym."));
        Date date = new Date();
        gym.addMember(normalStudentMember1);
        gym.addMember(normalStudentMember2);
        gym.addMember(normalPremiumMember1);
        normalStudentMember1.setMemberProgress(date, assessment1);
        normalStudentMember2.setMemberProgress(date, assessment2);
        normalPremiumMember1.setMemberProgress(date, assessment3);
        assertThat(gym.listMembersBySpecificBMICategory("VERY SEVERELY UNDERWEIGHT"), is("There are no members in the gym matching this category."));
        assertThat(gym.listMembersBySpecificBMICategory("SEVERELY UNDERWEIGHT"), is(normalPremiumMember1 + "\n"));
        assertThat(gym.listMembersBySpecificBMICategory("UNDERWEIGHT"), is(normalStudentMember2 + "\n"));
        assertThat(gym.listMembersBySpecificBMICategory("NORMAL"), is(normalStudentMember1 + "\n"));
        normalPremiumMember1.setMemberProgress(date, assessment4);
        assertThat(gym.listMembersBySpecificBMICategory("OVERWEIGHT"), is(normalPremiumMember1 + "\n"));
        normalPremiumMember1.setMemberProgress(date, assessment1);
        assertThat(gym.listMembersBySpecificBMICategory("MODERATELY OBESE"), is(normalPremiumMember1 + "\n"));
        normalPremiumMember1.setMemberProgress(date, assessment5);
        assertThat(gym.listMembersBySpecificBMICategory("SEVERELY OBESE"), is(normalPremiumMember1 + "\n"));
        normalPremiumMember1.setMemberProgress(date, assessment6);
        assertThat(gym.listMembersBySpecificBMICategory("VERY SEVERELY OBESE"), is(normalPremiumMember1 + "\n"));
    }

    @Test
    public void testListMemberDetailsImperialAndMetric() throws Exception {
        assertThat(gym.listMemberDetailsImperialAndMetric(), is("There are currently no members in ths Gym."));
        Date date = new Date();
        gym.addMember(normalStudentMember1);
        normalStudentMember1.setMemberProgress(date, assessment1);
        assertThat(gym.listMemberDetailsImperialAndMetric(), is("Genji: 101.0KGs (222.2lbs) 2.1 Metres (82.67 inches).\n"));
        gym.addMember(normalStudentMember2);
        normalStudentMember2.setMemberProgress(date, assessment2);
        assertThat(gym.listMemberDetailsImperialAndMetric(), is("Genji: 101.0KGs (222.2lbs) 2.1 Metres (82.67 inches).\nGenji 2: 74.0KGs (162.8lbs) 2.1 Metres (82.67 inches).\n"));
    }

    @After
    public void tearDown() {

    }
}
