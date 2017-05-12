package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Brian on 11/05/2017.
 */
public class AssessmentTest {
    private Assessment assessment1;
    private Assessment assessment2;
    private Trainer trainer1;
    private Trainer trainer2;

    @Before
    public void setUp() throws Exception {
        assessment1 = new Assessment(89.5, 40, 25, 20, 32, 38, "Very Good", trainer1);
        assessment2 = new Assessment(150, 50, 42, 33, 43, 49, "Needs Improvement", trainer2);
    }

    @Test
    public void testWeight() throws Exception {
        assertThat(assessment1.getWeight(), is(89.5));
        assessment1.setWeight(105.0);
        assertThat(assessment1.getWeight(), is(105.0));
        assertThat(assessment2.getWeight(), is(150.0));
        assessment2.setWeight(50.0);
        assertThat(assessment2.getWeight(), is(50.0));
    }

    @Test
    public void testChest() throws Exception {
        assertThat(assessment1.getChest(), is(40.0));
        assessment1.setChest(50.0);
        assertThat(assessment1.getChest(), is(50.0));
        assertThat(assessment2.getChest(), is(50.0));
        assessment2.setChest(72.0);
        assertThat(assessment2.getChest(), is(72.0));
    }

    @Test
    public void testThigh() throws Exception {
        assertThat(assessment1.getThigh(), is(25.0));
        assessment1.setThigh(30.0);
        assertThat(assessment1.getThigh(), is(30.0));
        assertThat(assessment2.getThigh(), is(42.0));
        assessment2.setThigh(50.0);
        assertThat(assessment2.getThigh(), is(50.0));
    }

    @Test
    public void testUpperArm() throws Exception {
        assertThat(assessment1.getUpperArm(), is(20.0));
        assessment1.setUpperArm(25.0);
        assertThat(assessment1.getUpperArm(), is(25.0));
        assertThat(assessment2.getUpperArm(), is(33.0));
        assessment2.setUpperArm(15.0);
        assertThat(assessment2.getUpperArm(), is(15.0));
    }

    @Test
    public void testWaist() throws Exception {
        assertThat(assessment1.getWaist(), is(32.0));
        assessment1.setWaist(48.0);
        assertThat(assessment1.getWaist(), is(48.0));
        assertThat(assessment2.getWaist(), is(43.0));
        assessment2.setWaist(10.0);
        assertThat(assessment2.getWaist(), is(10.0));
    }

    @Test
    public void testHips() throws Exception {
        assertThat(assessment1.getHips(), is(38.0));
        assessment1.setHips(24.0);
        assertThat(assessment1.getHips(), is(24.0));
        assertThat(assessment2.getHips(), is(49.0));
        assessment2.setHips(700.0);
        assertThat(assessment2.getHips(), is(700.0));
    }

    @Test
    public void testComment() throws Exception {
        assertThat(assessment1.getComment(), is("Very Good"));
        assessment1.setComment("Hello");
        assertThat(assessment1.getComment(), is("Hello"));
        assertThat(assessment2.getComment(), is("Needs Improvement"));
        assessment2.setComment("You're fine");
        assertThat(assessment2.getComment(), is("You're fine"));
    }

    @Test
    public void testTrainer() throws Exception {
        assertThat(assessment1.getTrainer(), is(trainer1));
        assessment1.setTrainer(trainer2);
        assertThat(assessment1.getTrainer(), is(trainer2));
        assertThat(assessment2.getTrainer(), is(trainer2));
        assessment2.setTrainer(trainer1);
        assertThat(assessment2.getTrainer(), is(trainer1));
    }

    @After
    public void tearDown() {

    }
}
