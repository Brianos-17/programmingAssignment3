package utils;

import models.Member;
import models.Assessment;

/**
 * Created by Brian on 24/04/2017.
 */
public class Analytics {

    public static double calculateBMI(Member member, Assessment assessment) {
        return toTwoDecimalPlaces(assessment.getWeight() / (member.getHeight() * member.getHeight()));
    }

    public static String determineBMICategory(double bmiValue) {
        if (calculateBMI() < 15) {
            return "VERY SEVERELY UNDERWEIGHT";
        } else if ((calculateBMI() >= 15) && (calculateBMI() < 16)) {
            return "SEVERELY UNDERWEIGHT";
        } else if ((calculateBMI() >= 16) && (calculateBMI() < 18.5)) {
            return "UNDERWEIGHT";
        } else if ((calculateBMI() >= 18.5) && (calculateBMI() < 25)) {
            return "NORMAL";
        } else if ((calculateBMI() >= 25) && (calculateBMI() < 30)) {
            return "OVERWEIGHT";
        } else if ((calculateBMI() >= 30) && (calculateBMI() < 35)) {
            return "MODERATELY OBESE";
        } else if ((calculateBMI() >= 35) && (calculateBMI() < 40)) {
            return "SEVERELY OBESE";
        } else {
            return "VERY SEVERELY OBESE";
        }
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        if (member.getGender().equals("M")) {
            if (convertHeightMetresToInches(member) > 60) {
                if ((((convertHeightMetresToInches(member) - 60) * 2.3) + 50) <= ((assessment.getWeight() + 2))
                        && ((((convertHeightMetresToInches(member) - 60) * 2.3) + 50) >= ((assessment.getWeight()) - 2))))
                {
                    return true;
                }
            } else {
                if ((50) <= ((assessment.getWeight() + 2)) && ((50) >= ((assessment.getWeight() - 2)))) {
                    return true;
                }
            }
        } else {
            if (convertHeightMetresToInches(member) > 60) {
                if ((((convertHeightMetresToInches(member) - 60) * 2.3) + 45.5) <= ((assessment.getWeight() + 2))
                        && ((((convertHeightMetresToInches(member) - 60) * 2.3) + 45.5) >= ((assessment.getWeight() - 2)))) {
                    return true;
                }
            } else {
                if ((45.5) <= ((assessment.getWeight() + 2)) && ((45.5) >= ((assessment.getWeight() - 2)))) {
                    return true;
                }
            }
        }
    }

    private static double convertHeightMetresToInches(Member member) {
        return toTwoDecimalPlaces(member.getHeight() * 39.37);
    }

    private static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
    }
}
