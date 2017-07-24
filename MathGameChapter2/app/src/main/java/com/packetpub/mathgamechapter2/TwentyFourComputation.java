package com.packetpub.mathgamechapter2;

import java.util.Arrays;

/**
 * Created by Sean on 7/21/2017.
 */

public class TwentyFourComputation {
    double x = 0;
    double y = 0;
    double z = 0;
    double[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    String[] operations = {"+", "-", "*", "/"};
    int counter = 0;
    double[][] successfulNumbers = new double[3185][4];


    private static void printArray(double[][] anArray) {
        System.out.println(Arrays.deepToString(anArray));
    }
    public double[][] generateNumbers() {
        for (double a : numbers) {
            for (double b : numbers) {
                for (String i : operations) {
                    if (i == "+") {
                        x = a + b;
                    } else if (i == "-") {
                        x = a - b;
                    } else if (i == "*") {
                        x = a * b;
                    } else {
                        x = a / b;
                    }
                    for (double c : numbers) {
                        for (String i2 : operations) {
                            if (i2 == "+") {
                                y = x + c;
                            } else if (i2 == "-") {
                                y = x - c;
                            } else if (i2 == "*") {
                                y = x * c;
                            } else {
                                y = x / c;
                            }
                            for (double d : numbers) {
                                for (String i3 : operations) {
                                    if (i3 == "+") {
                                        z = y + d;
                                    } else if (i3 == "-") {
                                        z = y - d;
                                    } else if (i3 == "*") {
                                        z = y * d;
                                    } else {
                                        z = y / d;
                                    }
                                    if (z==24){
                                        successfulNumbers[counter][0] = a;
                                        successfulNumbers[counter][1] = b;
                                        successfulNumbers[counter][2] = c;
                                        successfulNumbers[counter][3] = d;
                                        counter++;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        return successfulNumbers;
    }
}
