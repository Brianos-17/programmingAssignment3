package utils;

import models.Member;
import models.Assessment;

/**
 *Created by Brian on 24/04/2017.
 *@author Brian O'Sullivan
 *
 * Analytics class holds multiple methods which perform various calculations for the gym members based on their assessment details
 */
public class Analytics {

    /**
     * Method which calculates a members BMI based on the devine method. Truncates the resulting double to 2 decimal places
     * @param member StudentMember or Premium for who the calculation is being performed
     * @param assessment Assessment details from the members latest assessment
     * @return double representing the members BMI value
     */
    public static double calculateBMI(Member member, Assessment assessment) {
        return toTwoDecimalPlaces(assessment.getWeight() / (member.getHeight() * member.getHeight()));
    }

    /**
     * Method which determines which BMI category a member falls into based of of their BMI value calculated previously
     * @param bmiValue double representing the members BMI value
     * @return String determining which category the member falls into
     */
    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < 15) {
            return "VERY SEVERELY UNDERWEIGHT";
        } else if ((bmiValue >= 15) && (bmiValue < 16)) {
            return "SEVERELY UNDERWEIGHT";
        } else if ((bmiValue >= 16) && (bmiValue < 18.5)) {
            return "UNDERWEIGHT";
        } else if ((bmiValue >= 18.5) && (bmiValue < 25)) {
            return "NORMAL";
        } else if ((bmiValue >= 25) && (bmiValue < 30)) {
            return "OVERWEIGHT";
        } else if ((bmiValue >= 30) && (bmiValue < 35)) {
            return "MODERATELY OBESE";
        } else if ((bmiValue >= 35) && (bmiValue < 40)) {
            return "SEVERELY OBESE";
        } else {
            return "VERY SEVERELY OBESE";
        }
    }

    /**
     * Method which determines whether or not a member is of an ideal body weight based upon their gender, height, and current weight
     * @param member Member for who the calculation is being performed
     * @param assessment Assessment containing the members latest details
     * @return boolean true or false
     */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        if (member.getGender().equals("M")) {
            if (convertHeightMetresToInches(member) > 60) {
                if ((((convertHeightMetresToInches(member) - 60) * 2.3) + 50) <= ((assessment.getWeight() + 2))
                        && ((((convertHeightMetresToInches(member) - 60) * 2.3) + 50) >= ((assessment.getWeight()) - 2))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if ((50) <= ((assessment.getWeight() + 2)) && ((50) >= ((assessment.getWeight() - 2)))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (convertHeightMetresToInches(member) > 60) {
                if ((((convertHeightMetresToInches(member) - 60) * 2.3) + 45.5) <= ((assessment.getWeight() + 2))
                        && ((((convertHeightMetresToInches(member) - 60) * 2.3) + 45.5) >= ((assessment.getWeight() - 2)))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if ((45.5) <= ((assessment.getWeight() + 2)) && ((45.5) >= ((assessment.getWeight() - 2)))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * Method which converts a members height from Meteres into Inches
     * @param member Member for who the calculation is being performed
     * @return double truncated to 2 decimal places representing the members height in inches
     */
    public static double convertHeightMetresToInches(Member member) {
        return toTwoDecimalPlaces(member.getHeight() * 39.37);
    }

    /**
     * Method which converts a members weight from KGs to pounds
     * @param assessment Assessment which contains the latest details for a member
     * @return double truncated to 2 decimal places representing the members weight in pounds
     */
    public static double convertWeightKgToPounds(Assessment assessment){
        return toTwoDecimalPlaces((assessment.getWeight()) *2.2 );
    }

    /**
     * Method which truncates doubles to 2 decimal places. Is used by many other methods in the Analytics class
     * @param num doubles passed to it from other methods
     * @return double truncated to 2 decimal places
     */
    private static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
    }
}
