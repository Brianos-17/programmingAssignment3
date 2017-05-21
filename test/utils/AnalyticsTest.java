package utils;

import models.Assessment;
import models.PremiumMember;
import models.StudentMember;
import models.Trainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static utils.Analytics.*;

/**
 * Created by Brian on 20/05/2017.
 * @author Brian O'Sullivan
 *
 * Analytics Test class. Performs junit testing on methods in the Analytics class
 */
public class AnalyticsTest {
    private PremiumMember normalPremiumMember1;
    private PremiumMember normalPremiumMember2;
    private StudentMember normalStudentMember1;
    private StudentMember normalStudentMember2;
    private Assessment assessment1;
    private Assessment assessment2;
    private Assessment assessment3;
    private Assessment assessment4;
    private Assessment assessment5;
    private Trainer normalTrainer1;

    /**
     * Creates new instances of model classes in order to perform junit tests of Analytics class methods
     * @throws Exception if test fails
     */
    @Before
    public void setUp() throws Exception {
        normalPremiumMember1 = new PremiumMember("email1@email.com", "Tony Stark", "Avengers Tower, New York, America", "Male", 1.8, 76, "Package 1");
        normalPremiumMember2 = new PremiumMember("member@member.com", "Black Widow", "123 Street", "Female", 2.0, 80, "Package 2");
        normalStudentMember1 = new StudentMember("student1@email.com", "Genji", "54 Fake Street, Waterford, Co.Waterford", "M", 2.1, 84.6, "WIT", "1234567", "WIT");
        normalStudentMember2 = new StudentMember("example@example.com", "Jessica Jones", "321 Street", "F", 1.4, 48, "Package 3", "123456789A", "WIT");
        assessment1 = new Assessment(89.5, 40, 25, 20, 32, 38, "Very Good", normalTrainer1);
        assessment2 = new Assessment(150, 50, 42, 33, 43, 49, "Needs Improvement", normalTrainer1);
        assessment3 =  new Assessment(70, 50, 42, 33, 43, 49, "Doing good", normalTrainer1);
        assessment4 = new Assessment(43, 50, 42, 30, 40, 35, "Improvement", normalTrainer1);
        assessment5 = new Assessment(165, 50, 42, 30, 40, 35, "Very Bad", normalTrainer1);
        normalTrainer1 = new Trainer("brianos-17@hotmail.com", "Brian O'Sullivan", "11 Manor Drive, Manor Village, Tralee, Co. Kerry", "Male" ,"Cardio");
    }

    /**
     * Junit test to test calculateBMI method. Ensures the double returned is truncated to 2 decimal places
     * @throws Exception if test fails
     */
    @Test
    public void testCalculateBMI() throws Exception {
        assertThat(calculateBMI(normalPremiumMember1, assessment1), is(not(equalTo(27.6))));
        assertThat(calculateBMI(normalPremiumMember1, assessment1), is(27.62));
        assertThat(calculateBMI(normalPremiumMember1, assessment1), is(not(equalTo(46.2))));
        assertThat(calculateBMI(normalPremiumMember1, assessment2), is(46.29));
    }

    /**
     * Junit test to test determineBMICategory method. Returns String of members BMI category.
     * @throws Exception if test fails
     */
    @Test
    public void testDetermineBMICategory() throws Exception {
        assertThat(determineBMICategory(calculateBMI(normalStudentMember1, assessment4)), is("VERY SEVERELY UNDERWEIGHT"));
        assertThat(determineBMICategory(calculateBMI(normalStudentMember1, assessment3)), is("SEVERELY UNDERWEIGHT"));
        assertThat(determineBMICategory(calculateBMI(normalPremiumMember1, assessment4)), is("UNDERWEIGHT"));
        assertThat(determineBMICategory(calculateBMI(normalPremiumMember1, assessment3)), is("NORMAL"));
        assertThat(determineBMICategory(calculateBMI(normalPremiumMember1, assessment1)), is("OVERWEIGHT"));
        assertThat(determineBMICategory(calculateBMI(normalStudentMember1, assessment2)), is("MODERATELY OBESE"));
        assertThat(determineBMICategory(calculateBMI(normalStudentMember1, assessment5)), is("SEVERELY OBESE"));
        assertThat(determineBMICategory(calculateBMI(normalPremiumMember1, assessment2)), is("VERY SEVERELY OBESE"));
    }

    /**
     * Junit test to test isIdealBodyWeight method. Boolean testing.
     * @throws Exception if test fails
     */
    @Test
    public void testIsIdealBodyWeight() throws Exception {
        assertFalse(isIdealBodyWeight(normalPremiumMember1, assessment3));
        assertFalse(isIdealBodyWeight(normalStudentMember1, assessment1));
        assertTrue(isIdealBodyWeight(normalPremiumMember2, assessment1));
        assertTrue(isIdealBodyWeight(normalStudentMember2, assessment4));
    }

    /**
     * Junit test to test convertHeightMetersToInches method. Ensures the returned double is truncated to 2 decimal places
     * @throws Exception if test fails
     */
    @Test
    public void testConvertHeightMetresToInches() throws Exception {
        assertThat(convertHeightMetresToInches(normalPremiumMember1), is(not(equalTo(70.8))));
        assertThat(convertHeightMetresToInches(normalPremiumMember1), is(70.86));
        assertThat(convertHeightMetresToInches(normalPremiumMember2), is(78.73));
        assertThat(convertHeightMetresToInches(normalStudentMember1), is(82.67));
        assertThat(convertHeightMetresToInches(normalStudentMember2), is(55.11));
        assertThat(convertHeightMetresToInches(normalStudentMember2), is(not(equalTo(55.1))));
    }

    /**
     * Junit test to test convertWeightKgToPound method.
     * @throws Exception if test fails
     */
    @Test
    public void testConvertWeightKgToPounds() throws Exception {
        assertThat(convertWeightKgToPounds(assessment1), is(not(equalTo(197))));
        assertThat(convertWeightKgToPounds(assessment1), is(196.9));
        assertThat(convertWeightKgToPounds(assessment1), is(not(equalTo(196))));
        assertThat(convertWeightKgToPounds(assessment2), is(not(equalTo(329.9))));
        assertThat(convertWeightKgToPounds(assessment2), is(330.0));
        assertThat(convertWeightKgToPounds(assessment2), is(not(equalTo(330.1))));
        assertThat(convertWeightKgToPounds(assessment3), is(not(equalTo(153.9))));
        assertThat(convertWeightKgToPounds(assessment3), is(154.0));
        assertThat(convertWeightKgToPounds(assessment3), is(not(equalTo(154.1))));
        assertThat(convertWeightKgToPounds(assessment4), is(not(equalTo(94.5))));
        assertThat(convertWeightKgToPounds(assessment4), is(94.6));
        assertThat(convertWeightKgToPounds(assessment4), is(not(equalTo(95))));
        assertThat(convertWeightKgToPounds(assessment5), is(not(equalTo(362.9))));
        assertThat(convertWeightKgToPounds(assessment5), is(363.0));
        assertThat(convertWeightKgToPounds(assessment5), is(not(equalTo(364))));
    }

    /**
     * Tears down test objects once the junit tests have been run
     */
    @After
    public void tearDown() {

    }

}
