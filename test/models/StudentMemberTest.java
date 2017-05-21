package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Brian on 11/05/2017.
 * @author Brian O'Sullivan
 *
 * StudentMember Test class. Performs junit testing on methods in the StudentMember class
 */
public class StudentMemberTest {
    private StudentMember normalStudentMember1;
    private StudentMember normalStudentMember2;
    private StudentMember normalStudentMember3;

    /**
     * Creates new instances of model classes in order to perform junit tests of StudentMember class methods
     * @throws Exception if test fails
     */
    @Before
    public void setUp() throws Exception {
        normalStudentMember1 = new StudentMember("student1@email.com", "Genji", "54 Fake Street, Waterford, Co.Waterford", "M", 2.1, 84.6, "WIT", "1234567", "WIT");
        normalStudentMember2 = new StudentMember("student2@email.com", "Pharah", "Dublin Ireland", "F", 1.9, 74.1, "Package 1", "987654321", "WIT");
        normalStudentMember3 = new StudentMember("student3@email.com", "Symmetra", "321 Main Street, waterford", "Female", 2.0, 82.1, "Package 2", "147852369", "UCC");
    }

    /**
     * Tests getEmail and setEmail methods for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testEmail() throws Exception {
        assertThat(normalStudentMember1.getEmail(), is("student1@email.com"));
        assertThat(normalStudentMember2.getEmail(), is("student2@email.com"));
        assertThat(normalStudentMember3.getEmail(), is("student3@email.com"));
        normalStudentMember1.setEmail("TestEmail@hotmail.com");
        assertThat(normalStudentMember1.getEmail().equals("student1@email.com"), is(false));
        assertThat(normalStudentMember1.getEmail(), is("TestEmail@hotmail.com"));
        normalStudentMember2.setEmail("123");
        assertThat(normalStudentMember2.getEmail().equals("student2@email.com"), is(false));
        assertThat(normalStudentMember2.getEmail(), is("123"));
    }

    /**
     * Tests getName and setName methods for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testName() throws Exception {
        assertThat(normalStudentMember1.getName(), is("Genji"));
        assertThat(normalStudentMember2.getName(), is("Pharah"));
        normalStudentMember1.setName("Steve Rogers");
        assertThat(normalStudentMember1.getName().equals("Genji"), is(false));
        assertThat(normalStudentMember1.getName(), is("Steve Rogers"));
        normalStudentMember2.setName("Maria Hill");
        assertThat(normalStudentMember2.getName().equals("Pharah"), is(false));
        assertThat(normalStudentMember2.getName(), is("Maria Hill"));
        assertThat(normalStudentMember3.getName(), is("Symmetra"));
        normalStudentMember3.setName("This name is longer than 30 characters");
        assertThat(normalStudentMember3.getName(), is("This name is longer than 30 ch"));
    }

    /**
     * Tests getAddress and setAddress for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testAddress() throws Exception {
        assertThat(normalStudentMember1.getAddress(), is("54 Fake Street, Waterford, Co.Waterford"));
        assertThat(normalStudentMember2.getAddress(), is("Dublin Ireland"));
        normalStudentMember1.setAddress("Anything");
        assertThat(normalStudentMember1.getAddress().equals("54 Fake Street, Waterford, Co.Waterford"), is(false));
        assertThat(normalStudentMember1.getAddress(), is("Anything"));
        normalStudentMember2.setAddress("Example Address");
        assertThat(normalStudentMember2.getAddress().equals("Dublin Ireland"), is(false));
        assertThat(normalStudentMember2.getAddress(), is("Example Address"));
    }

    /**
     * Tests getGender and setGender for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testGender() throws Exception {
        assertThat(normalStudentMember1.getGender(), is("M"));
        normalStudentMember1.setGender("Female");
        assertThat(normalStudentMember1.getGender(), is("F"));
        normalStudentMember1.setGender("Male");
        assertThat(normalStudentMember1.getGender(), is("M"));
        assertThat(normalStudentMember2.getGender(), is("F"));
        normalStudentMember2.setGender("Incorrect");
        assertThat(normalStudentMember2.getGender(), is("Unspecified"));
        normalStudentMember2.setGender("m");
        assertThat(normalStudentMember2.getGender(), is("M"));
        normalStudentMember2.setGender("f");
        assertThat(normalStudentMember2.getGender(), is("F"));
    }

    /**
     * Tests getHeight and setHeight for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testHeight() throws Exception {
        assertThat(normalStudentMember1.getHeight(), is(2.1));
        assertThat(normalStudentMember2.getHeight(), is(1.9));
        assertThat(normalStudentMember3.getHeight(), is(2.0));
        normalStudentMember1.setHeight(2.5);
        assertThat(normalStudentMember1.getHeight(), is(2.5));
        normalStudentMember2.setHeight(3.1);
        assertThat(normalStudentMember2.getHeight(), is(1.9));
        normalStudentMember2.setHeight(3.0);
        assertThat(normalStudentMember2.getHeight(), is(3.0));
        normalStudentMember3.setHeight(0.9);
        assertThat(normalStudentMember3.getHeight(), is(2.0));
        normalStudentMember3.setHeight(1.0);
        assertThat(normalStudentMember3.getHeight(), is(1.0));
    }

    /**
     * Tests getStratingWeight and setStartingWeight for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testStartingWeight() throws Exception {
        assertThat(normalStudentMember1.getStartingWeight(), is(84.6));
        assertThat(normalStudentMember2.getStartingWeight(), is(74.1));
        normalStudentMember1.setStartingWeight(34.0);
        assertThat(normalStudentMember1.getStartingWeight(), is(84.6));
        normalStudentMember1.setStartingWeight(35.0);
        assertThat(normalStudentMember1.getStartingWeight(), is(35.0));
        normalStudentMember2.setStartingWeight(251.0);
        assertThat(normalStudentMember2.getStartingWeight(), is(74.1));
        normalStudentMember2.setStartingWeight(250.0);
        assertThat(normalStudentMember2.getStartingWeight(), is(250.0));
    }

    /**
     * Tests getStudentId and setStudentId for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testStudentID() throws Exception {
        assertThat(normalStudentMember1.getStudentId(), is("1234567"));
        assertThat(normalStudentMember2.getStudentId(), is("987654321"));
        assertThat(normalStudentMember3.getStudentId(), is("147852369"));
        normalStudentMember1.setStudentId("Student ID");
        assertThat(normalStudentMember1.getStudentId().equals("1234567"), is(false));
        assertThat(normalStudentMember1.getStudentId(), is("Student ID"));
        normalStudentMember2.setStudentId("Valid Student ID");
        assertThat(normalStudentMember2.getStudentId(), is("Valid Student ID"));
    }

    /**
     * Tests getCollegeName and setCollege for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testCollegeName() throws Exception {
        assertThat(normalStudentMember1.getCollegeName(), is("WIT"));
        assertThat(normalStudentMember2.getCollegeName(), is("WIT"));
        assertThat(normalStudentMember3.getCollegeName(), is("UCC"));
        normalStudentMember1.setCollegeName("UCD");
        assertThat(normalStudentMember1.getCollegeName(), is("UCD"));
        normalStudentMember2.setCollegeName("ITT");
        assertThat(normalStudentMember2.getCollegeName(), is("ITT"));
        normalStudentMember3.setCollegeName("NUIG");
        assertThat(normalStudentMember3.getCollegeName(), is("NUIG"));
    }

    /**
     * Tests getChosenPackage and chosenPackage for StudentMember class.
     * @throws Exception if test fails
     */
    @Test
    public void testChosenPackage() throws Exception {
        assertThat(normalStudentMember1.getChosenPackage(), is("WIT"));
        assertThat(normalStudentMember2.getChosenPackage(), is("WIT"));
        assertThat(normalStudentMember3.getChosenPackage(), is("Package 3"));
        normalStudentMember1.chosenPackage("Package 1");
        assertThat(normalStudentMember1.getChosenPackage(), is("WIT"));
        normalStudentMember2.chosenPackage("UCD");
        assertThat(normalStudentMember2.getChosenPackage(), is("WIT"));
        normalStudentMember3.chosenPackage("WIT");
        assertThat(normalStudentMember3.getChosenPackage(), is("Package 3"));
        normalStudentMember1.setCollegeName("UCD");
        normalStudentMember1.chosenPackage("Package 1");
        assertThat(normalStudentMember1.getChosenPackage(), is("Package 3"));
    }

    /**
     * Tests toString method for StudentMember class. Ensures the resulting sting contains the correct elements.
     * @throws Exception if test fails
     */
    @Test
    public void testToString() throws Exception {
        assertThat(normalStudentMember1.toString().contains("Genji"), is(true));
        assertThat(normalStudentMember1.toString().contains("WIT"), is(true));
        assertThat(normalStudentMember2.toString().contains("1.9"), is(true));
        assertThat(normalStudentMember2.toString().contains("Dublin Ireland"), is(true));
        assertThat(normalStudentMember3.toString().contains("student3@email.com"), is(true));
        assertThat(normalStudentMember3.toString().contains("Pharah"), is(false));
    }

    /**
     * Tears down test objects once the junit tests have been run
     */
    @After
    public void tearDown() {

    }
}
