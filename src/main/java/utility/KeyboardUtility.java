package utility;

import exception.IllegalDateException;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Scanner;

/**
 * Created by Moon on 23/04/2021
 */
public abstract class KeyboardUtility {
    private static final String INVALID_MSG = "Invalid input! Please try again...";
    public static final Scanner KEYBOARD = new Scanner(System.in);
    public static int askForChoice(String message, String[] options) {
        System.out.println(message);
        return askForChoice(options);
    }
    public static int askForChoice(String[] options) {
        while (true) {
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d. %s%n", i + 1, options[i]);
            }
            int chosenIdx = askForInt(String.format("Enter your choice (1-%d):", options.length)) - 1;
            if (chosenIdx < 0 || chosenIdx >= options.length) {
                System.out.println(INVALID_MSG);
                System.out.println("Please enter a choice in the valid range");
            } else {
                return chosenIdx;
            }
        }
    }
    public static int askForInt(String message) {
        while (true) {
            String input = ask(message);
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println(INVALID_MSG);
            }
        }
    }
    public static String ask(String message) {
        System.out.println(message);
        return KEYBOARD.nextLine();
    }
    public static LocalDate askForFullDate(String datum){
        while (true) {
            System.out.println(datum);
            int inputYear = askForInt("Year:");
            try {
                if (inputYear < 1900 || inputYear > LocalDate.now().getYear()) {
                    throw new IllegalDateException("Year must be >1900 and before this year");
                }
                int inputMonth = askForInt("Month (1-12):");
                if (inputMonth > 12 || inputMonth < 1) {
                    throw new IllegalDateException("Month must be between 1 and 12");
                }
                int daysInMonth = Month.of(inputMonth).length(Year.isLeap(inputYear));
                int inputDay = askForInt("Day (1-" + daysInMonth + "):");
                if (inputDay > daysInMonth || inputDay < 1) {
                    throw new IllegalDateException("Day must be between 1 and " + daysInMonth + " for " + Month.of(inputMonth));
                }
                return LocalDate.of(inputYear, inputMonth, inputDay);
            } catch (IllegalDateException ide) {
                System.out.println(INVALID_MSG);
                System.out.println(ide.getMessage());
            }
        }
    }
    public static boolean askYorN(String message){
        while (true) {
            String input = ask(message + "(y/n)");
            char firstLetter = '0';  // yes no y n other YES NO Y ohyes
            try {
                firstLetter = input.toLowerCase().charAt(0);
            } catch (Exception e) {
                System.out.println(INVALID_MSG);
                continue;
            }
            switch (firstLetter) {
                case 'y':
                    return true;
                case 'n':
                    return false;
                default:
                    break;
            }
        }
    }
}
