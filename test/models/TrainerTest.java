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

    @Before
    public void setUp() throws Exception{
        normalTrainer1 = new Trainer("brianos-17@hotmail.com", "Brian O'Sullivan", "11 Manor Drive, Manor Village, Tralee, Co. Kerry", "Male" ,"Cardio");
        normalTrainer2 = new Trainer("sarah-17@hotmail.com", "Sarah Jones", "5 Calder House, Brownes Road, Waterford, Co. Waterford", "F", "Strength");
    }

    @Test
    public void getSpeciality() throws Exception {
        assertThat(normalTrainer1.getSpeciality(), is("Cardio"));
        assertThat(normalTrainer2.getSpeciality(), is("Strength"));
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
    }

    @After
    public void tearDown() {

    }

}