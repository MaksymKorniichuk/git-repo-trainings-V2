package moreLessGame;

import java.util.List;

/**
 * The class contains a texts constants and methods to communicate with user.
 * @author Maksym
 */
public class View {
    // Text's constants
    final String GREETING = "Welcome to our game \"More-Less\". You can exit program any moment entered \"exit\". Have fun :)\n";
    final String GETTING_MIN_LIMIT = "Please, enter minimum limit of range (integer number):";
    final String BAD_MIN_LIMIT = "Sorry, this value do NOT fit to be a minimum limit of range. Please, try again:";
    final String GETTING_MAX_LIMIT = "Please, enter maximum limit of range (integer number):";
    final String BAD_MAX_LIMIT = "Sorry, this value do not fit to be a maximum limit of range. Please, try again:";
    final String START_GAME = "\nWe choose some number in your range. Now, try to guess it. Good luck.";
    final String INPUT_INT_DATA_IN_RANGE = "Please, input integer number in range";
    final String INPUT_NUMBER_IS_NOT_GUESSED_NUMBER = "Sorry, but we guessed another number.";
    final String GUESSED_NUMBER_IS_BIGGER = "Our number is bigger.";
    final String GUESSED_NUMBER_IS_LESS = "Our number is less.";
    final String INPUT_number_IS_OUT_OF_RANGE = "Sorry, but your number is out of range";
    final String WRONG_INPUT_DATA = "You entered not an integer number.";
    final String CONGRATULATIONS = "\nCongratulations !!! You guessed the number: ";
    final String NUMBER_OF_ENTERED_NUMBERS = "\nThe number of numbers you entered: ";
    final String ENTERED_NUMBERS = "You entered next number(s):";
    final String EXIT_BY_USER = "\nYou closed the game. Good luck :)";

    /**
     * Printing a message in console.
     * @param message message, that printings.
     */
    void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Printing a message and value in console.
     * @param message message that printings.
     * @param value integer number that printings.
     */
    void printMessageAndInt(String message, int value) {
        System.out.println(message + value);
    }

    /**
     * Printing of message and integer`s range.
     * @param message message that printings.
     * @param minLimit minimum limit of printings range.
     * @param maxLimit maximum limit of printings range.
     */
    void printMessageAndRange(String message, int minLimit, int maxLimit) {
        System.out.println(message + " (" + minLimit + ", " + maxLimit + ")");
    }

    /**
     * Printing of message and list of integers.
     * @param message message that printings.
     * @param intsList list of integers that printings.
     */
    void printMessageAndIntList(String message, List<Integer> intsList){
        System.out.print(message + " ");
        for (Integer number: intsList) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
