package org.patika.utils;

import java.util.Scanner;

public class MenuInputUtils {
    public static void pressToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    //Warn the users about invalid inputs.
    public static void warnClientAboutInputValues() {
        System.out.println("Please enter valid values.");
    }

    //Get an integer input.
    public static int getIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        try {
            number = scanner.nextInt();
        } catch (Exception e) {
            warnClientAboutInputValues();
        }
        return number;
    }

    public static float getFloatInput() {
        Scanner scanner = new Scanner(System.in);
        float number = -1;
        try {
            number = scanner.nextFloat();
        } catch (Exception e) {
            warnClientAboutInputValues();
        }
        return number;
    }

    public static String getStringInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input;
    }
}
