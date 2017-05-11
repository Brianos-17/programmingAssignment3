package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Brian on 11/05/2017.
 */
public class PremiumMemberTest {
    private PremiumMember normalPremiumMember1;
    private PremiumMember normalPremiumMember2;

    @Before
    public void set() throws Exception {
        normalPremiumMember1 = new PremiumMember("email1@email.com", "Tony Stark", "Avengers Tower, New York, America", "Male", 1.8, 76, "Package 1" );
        normalPremiumMember2 = new PremiumMember("email2@email.com", "Black Widow", "52 Boradway, New York, America", "Female", 1.5, 67, "Package 2");
    }

    @Test
    public void testEmail() throws Exception {
        assertThat(normalPremiumMember1.getEmail(), is("email1@email.com"));
        assertThat(normalPremiumMember2.getEmail(), is("email2@email.com"));
        normalPremiumMember1.setEmail("TestEmail@hotmail.com");
        assertThat(normalPremiumMember1.getEmail().equals("email1@email.com"), is(false));
        assertThat(normalPremiumMember1.getEmail(), is("TestEmail@hotmail.com"));
        normalPremiumMember2.setEmail("ForExample@hotmail.com");
        assertThat(normalPremiumMember2.getEmail().equals("email2@email.com"), is(false));
        assertThat(normalPremiumMember2.getEmail(), is("ForExample@hotmail.com"));
    }

    @Test
    public void testName() throws Exception {
        assertThat(normalPremiumMember1.getName(), is("Tony Stark"));
        assertThat(normalPremiumMember2.getName(), is("Black Widow"));
        normalPremiumMember1.setName("Steve Rogers");
        assertThat(normalPremiumMember1.getName().equals("Tony Stark"), is(false));
        assertThat(normalPremiumMember1.getName(), is("Steve Rogers"));
        normalPremiumMember2.setName("Maria Hill");
        assertThat(normalPremiumMember2.getName().equals("Black Widow"), is(false));
        assertThat(normalPremiumMember2.getName(), is("Maria Hill"));
        normalPremiumMember1.setName("This name is longer than 30 characters");
        assertThat(normalPremiumMember1.getName(), is("This name is longer than 30 ch"));
    }

    @Test
    public void testAddress() throws Exception {
        assertThat(normalPremiumMember1.getAddress(), is("Avengers Tower, New York, America"));
        assertThat(normalPremiumMember2.getAddress(), is("52 Boradway, New York, America"));
        normalPremiumMember1.setAddress("Anything");
        assertThat(normalPremiumMember1.getAddress().equals("Avengers Tower, New York, America"), is(false));
        assertThat(normalPremiumMember1.getAddress(), is("Anything"));
        normalPremiumMember2.setAddress("Example Address");
        assertThat(normalPremiumMember2.getAddress().equals("52 Boradway, New York, America"), is(false));
        assertThat(normalPremiumMember2.getAddress(), is("Example Address"));
    }

    @Test
    public void testGender() throws Exception {
        assertThat(normalPremiumMember1.getGender(), is("M"));
        normalPremiumMember1.setGender("Female");
        assertThat(normalPremiumMember1.getGender(), is("F"));
        normalPremiumMember1.setGender("Male");
        assertThat(normalPremiumMember1.getGender(), is("M"));
        assertThat(normalPremiumMember2.getGender(), is("F"));
        normalPremiumMember2.setGender("Incorrect");
        assertThat(normalPremiumMember2.getGender(), is("Unspecified"));
        normalPremiumMember2.setGender("m");
        assertThat(normalPremiumMember2.getGender(), is("M"));
        normalPremiumMember2.setGender("f");
        assertThat(normalPremiumMember2.getGender(), is("F"));
    }

    @Test
    public void testHeight() throws Exception {
        assertThat(normalPremiumMember1.getHeight(), is(1.8));
        assertThat(normalPremiumMember2.getHeight(), is(1.5));
        normalPremiumMember1.setHeight(2.5);
        assertThat(normalPremiumMember1.getHeight(), is(2.5));
        normalPremiumMember2.setHeight(3.1);
        assertThat(normalPremiumMember2.getHeight(), is(1.5));
        normalPremiumMember2.setHeight(3.0);
        assertThat(normalPremiumMember2.getHeight(), is(3.0));
        normalPremiumMember1.setHeight(0.9);
        assertThat(normalPremiumMember1.getHeight(), is(2.5));
        normalPremiumMember1.setHeight(1.0);
        assertThat(normalPremiumMember1.getHeight(), is(1.0));
    }

    @Test
    public void testStartingWeight() throws Exception {
        assertThat(normalPremiumMember1.getStartingWeight(), is(76.0));
        assertThat(normalPremiumMember2.getStartingWeight(), is(67.0));
        normalPremiumMember1.setStartingWeight(34.0);
        assertThat(normalPremiumMember1.getStartingWeight(), is(76.0));
        normalPremiumMember1.setStartingWeight(35.0);
        assertThat(normalPremiumMember1.getStartingWeight(), is(35.0));
        normalPremiumMember2.setStartingWeight(251.0);
        assertThat(normalPremiumMember2.getStartingWeight(), is(67.0));
        normalPremiumMember2.setStartingWeight(250.0);
        assertThat(normalPremiumMember2.getStartingWeight(), is(250.0));
    }

    @Test
    public void testChosenPackage() throws Exception {
        normalPremiumMember1.chosenPackage("Package 1");
        assertThat(normalPremiumMember1.getChosenPackage(), is("Package 1"));
        assertThat(normalPremiumMember2.getChosenPackage(), is("Package 2"));
        normalPremiumMember2.chosenPackage("Package 3");
        assertThat(normalPremiumMember2.getChosenPackage(), is("Package 3"));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(normalPremiumMember1.toString().contains("Tony Stark"), is(true));
        assertThat(normalPremiumMember1.toString().contains("email1@email.com"), is(true));
        assertThat(normalPremiumMember1.toString().contains("WIT"), is(false));
        assertThat(normalPremiumMember2.toString().contains("Black Widow"), is(true));
        assertThat(normalPremiumMember2.toString().contains("67"), is(true));
        assertThat(normalPremiumMember2.toString().contains("1.5"), is(true));
    }

    @After
    public void tearDown() {

    }
}
