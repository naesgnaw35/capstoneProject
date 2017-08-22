package com.packetpub.mathgamechapter2;

import java.util.Arrays;

/**
 * Created by Sean on 7/21/2017.
 */

public class TwentyFourComputation {
    static double x = 0;
    static double y = 0;
    static double z = 0;
    static double[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    static String[] stringNumbers =  {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    static String[] operations = {"+", "-", "*", "/"};
    static int counter = 0;
    static int[][] successfulNumbers = new int[11419][4];
    static String[][] successfulNumbersOperations = new String[11419][7];


    private static void printArray(double[][] anArray) {
        System.out.println(Arrays.deepToString(anArray));
    }
    public static void generateNumbers() {
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
                                        successfulNumbers[counter][0] = (int)a;
                                        successfulNumbers[counter][1] = (int)b;
                                        successfulNumbers[counter][2] = (int)c;
                                        successfulNumbers[counter][3] = (int)d;
                                        //store the operations first in the return array
                                        successfulNumbersOperations[counter][1] = i;
                                        successfulNumbersOperations[counter][3] = i2;
                                        successfulNumbersOperations[counter][5] = i3;
                                        counter++;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public String[][] returnNumbersOperations(){
        generateNumbers();
        //Take int numbers from successfulNumbers and add it in String form to the return array
        for (int i = 0; i<11419; i++){
            for (int i2 = 0; i2 < 4; i2++){
                successfulNumbersOperations[i][i2*2] = String.valueOf(successfulNumbers[i][i2]);
            }
        }
        return successfulNumbersOperations;
    }
}
