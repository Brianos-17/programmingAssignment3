package utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;
import static utils.ScannerInput.*;

/**
 * Created by Brian on 20/05/2017.
 * @author Brian O'Sullivan
 *
 * ScannerInput Test class. Performs tests on the methods in the ScannerInput class.
 * Class informed by: http://stackoverflow.com/questions/36374600/test-functions-with-junit-where-functions-get-user-input
 */
public class ScannerInputTest {

    /**
     * Method which simulates user input and tests to see if input is a string
     * @throws Exception if test fails
     */
    @Test
    public void testValidNextString() throws Exception {
        String inputData = "Test for string input";
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        String input = validNextString("Please enter a string:");
        assertEquals(inputData, input);
        assertNotEquals(5, input);
        String newInputData = "New Test";
        System.setIn(new ByteArrayInputStream(newInputData.getBytes()));
        String newInput = validNextString("Please enter a new String:");
        assertEquals("New Test", newInput);
        assertNotEquals("Test for a string input", newInput);
    }

    /**
     * Method which simulates user input and tests to see if input is an int
     * @throws Exception if test fails
     */
    @Test
    public void testValidNextInt() throws Exception {
        String inputData = "123";
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        int input = validNextInt("Please enter an int:");
        assertEquals(123, input);
        assertNotEquals("Hello", input);
        assertNotEquals("123", input);
        String newInputData = "321";
        System.setIn(new ByteArrayInputStream(newInputData.getBytes()));
        int newInput = validNextInt("Please enter a new int:");
        assertEquals(321, newInput);
        assertNotEquals(123, newInput);
    }

    /**
     * Method which simulates user input and tests to see if input is a double
     * @throws Exception if test fails
     */
    @Test
    public void testValidNextDouble() throws Exception {
        String inputData = "5.5";
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        double input = validNextDouble("Please enter a double:");
        assertEquals(5.5, input, 0);
        assertNotEquals(5.0, input, 0);
        assertNotEquals(5.0, input, 0.1);
        String newInputData = "7.28";
        System.setIn(new ByteArrayInputStream(newInputData.getBytes()));
        double newInput = validNextDouble("Please enter a new double:");
        assertEquals(7.28, newInput, 0);
        assertNotEquals(5.5, newInput, 0);
    }
}
