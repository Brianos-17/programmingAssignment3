package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Brian on 02/05/2017.
 */
public class TrainerTest {
    private Trainer normalTrainer1;
    private Trainer normalTrainer2;
    private Trainer invalidTrainer1;
    private Trainer invalidTrainer2;

    @Before
    public void setUp() throws Exception{
        normalTrainer1 = new Trainer("brianos-17@hotmail.com", "Brian O'Sullivan", "11 Manor Drive, Manor Village, Tralee, Co. Kerry", "Male" ,"Cardio");
        normalTrainer2 = new Trainer("sarah-17@hotmail.com", "Sarah Jones", "5 Calder House, Brownes Road, Waterford, Co. Waterford", "F", "Strength");
        invalidTrainer1 = new Trainer("FakeEmail@fake.com", "Dillan Johnson", "123 Fake Street", "Not a gender", "Not a speciality");
        invalidTrainer2 = new Trainer("email", "Mary O'Shea", "24 New Drive, Waterford, Co. Waterford", "Fmail", "Weights");
    }

    @Test
    public void getSpeciality() throws Exception {
        assertThat(normalTrainer1.getSpeciality(), is("Cardio"));
        assertThat(normalTrainer2.getSpeciality(), is("Strength"));
        assertThat(invalidTrainer1.getSpeciality().equals("Legs"), is(false));
        assertThat(invalidTrainer2.getSpeciality().equals("Test"), is(false));
    }

    @Test
    public void setSpeciality() throws Exception {
        normalTrainer1.setSpeciality("Weights");
        assertThat(normalTrainer1.getSpeciality(), is("Weights"));
        normalTrainer2.setSpeciality("Cardio");
        assertThat(normalTrainer2.getSpeciality(), is ("Cardio"));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(normalTrainer1.toString().contains("Brian O'Sullivan"), is(true));
        assertThat(normalTrainer1.toString().contains("brianos-17@hotmail.com"), is(true));
        assertThat(normalTrainer2.toString().contains("5 Calder House"), is(true));
        assertThat(normalTrainer2.toString().contains("f"), is(true));
        assertThat(invalidTrainer1.toString().contains("Male"), is(false));
        assertThat(invalidTrainer1.toString().contains("Unspecified"), is(true));
        assertThat(invalidTrainer2.toString().contains("F"), is(false));
        assertThat(invalidTrainer2.toString().contains("email"), is(true));
    }

    //Tests both getter and setter for email
    @Test
    public void testEmail() throws Exception{
        assertThat(normalTrainer1.getEmail(), is("brianos-17@hotmail.com"));
        normalTrainer1.setEmail("brianos-27@hotmail.com");
        assertThat(normalTrainer1.getEmail(), is("brianos-27@hotmail.com"));
        assertThat(normalTrainer2.getEmail(), is("sarah-17@hotmail.com"));
        normalTrainer2.setEmail("thisIsATest@test.com");
        assertThat(normalTrainer2.getEmail().equals("sarah-17@hotmail.com"), is(false));
    }

    //Tests both getter and setter for name
    @Test
    public void testName()  throws Exception {
        assertThat(normalTrainer1.getName().equals("Brian O'Sullivan"), is(true));
        normalTrainer1.setName("Test Name");
        assertThat(normalTrainer1.getName().equals("Test Name"), is(true));
        assertThat(normalTrainer2.getName().equals("Not her name"), is(false));
        normalTrainer2.setName("Jessica Jones");
        assertThat(normalTrainer2.getName().equals("Sarah Jones"), is(false));
        assertThat(normalTrainer2.getName().equals("Jessica Jones"), is(true));
    }

    //Tests both getter and setter for address
    @Test
    public void testAddress() throws Exception {
        assertThat(normalTrainer1.getAddress(), is("11 Manor Drive, Manor Village, Tralee, Co. Kerry"));
        normalTrainer1.setAddress("Test Address");
        assertThat(normalTrainer1.getAddress(), is("Test Address"));
        assertThat(normalTrainer2.getAddress().equals("Something Wrong"), is(false));
        normalTrainer2.setAddress("Test Address 2");
        assertThat(normalTrainer2.getAddress(), is("Test Address 2"));
        assertThat(normalTrainer2.getAddress().equals("5 Calder House, Brownes Road, Waterford, Co. Waterford"), is(false));
    }

    //Tests both getter and setter for gender
    @Test
    public void testGender() throws Exception {
        assertThat(normalTrainer1.getGender(), is("M"));
        normalTrainer1.setGender("Female");
        assertThat(normalTrainer1.getGender(), is("F"));
        normalTrainer1.setGender("Male");
        assertThat(normalTrainer1.getGender(), is("M"));
        assertThat(normalTrainer2.getGender(), is("F"));
        normalTrainer2.setGender("Incorrect");
        assertThat(normalTrainer2.getGender(), is("Unspecified"));
        normalTrainer2.setGender("m");
        assertThat(normalTrainer2.getGender(), is("M"));
        normalTrainer2.setGender("f");
        assertThat(normalTrainer2.getGender(), is("F"));
    }

    @After
    public void tearDown() {

    }

}