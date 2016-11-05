package moreLessGame;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class provides check of input value, transferring it to model
 * and reception a result from model.
 * @author Maksym
 */
public class Controller {
    private Model model;
    private View view;

    /**
     * Constructor of the class. Initialize model and view.
     * @param model an object of class Model.
     * @param view  an object of class View.
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Method gets input number, transfer it to model, get a result from model
     * and continue, until the hidden value will be found.
     */
    void mainProcess() {
        InputStream source = System.in;
        Scanner scanner = new Scanner(source);

        view.printMessage(view.GREETING);

        // inputting minimum limit of range
        Integer usersMinLimitOfRange = inputMinLimitOfRange(scanner);
        if(usersMinLimitOfRange == null){
            view.printMessage(view.EXIT_BY_USER);
            return;
        }

        // inputting maximum limit of range
        Integer usersMaxLimitOfRange = inputMaxLimitOfRange(scanner, usersMinLimitOfRange);
        if(usersMaxLimitOfRange == null){
            view.printMessage(view.EXIT_BY_USER);
            return;
        }

        // generating of secret number in model
        model.regenerateSecretNumber(usersMinLimitOfRange, usersMaxLimitOfRange);

        view.printMessage(view.START_GAME);

        // getting first value from user
        Integer inputValue = inputValueUsingScanner(scanner);
        if(inputValue == null){
            view.printMessage(view.EXIT_BY_USER);
            return;
        }
        // checking of first value from user
        StatesOfNumberChecking resultOfCheckingFromModel = model.checkValue(inputValue);

        // getting and checking data from user, until user enter the guessed value
        while (resultOfCheckingFromModel != StatesOfNumberChecking.INPUT_NUMBER_IS_GUESSED_NUMBER) {
            // if input number is out of range: writing a message and getting next value from user
            if (resultOfCheckingFromModel == StatesOfNumberChecking.INPUT_NUMBER_IS_OUT_OF_RANGE) {
                view.printMessageAndRange(view.INPUT_number_IS_OUT_OF_RANGE, model.getCurrentMinLimitOfRange(),
                        model.getCurrentMaxLimitOfRange());
            } else { // if input number is in correct range, but is not a guessed number
                view.printMessage(view.INPUT_NUMBER_IS_NOT_GUESSED_NUMBER);
                // if guessed number is bigger, that user input
                if (resultOfCheckingFromModel == StatesOfNumberChecking.GUESSED_NUMBER_IS_BIGGER) {
                    view.printMessage(view.GUESSED_NUMBER_IS_BIGGER);
                    // if guessed number is less, that user input
                } else if (resultOfCheckingFromModel == StatesOfNumberChecking.GUESSED_NUMBER_IS_LESS) {
                    view.printMessage(view.GUESSED_NUMBER_IS_LESS);
                }
            }

            // getting next value from user
            inputValue = inputValueUsingScanner(scanner);
            if(inputValue == null){
                view.printMessage(view.EXIT_BY_USER);
                return;
            }
            // checking value inputted from user
            resultOfCheckingFromModel = model.checkValue(inputValue);
        }

        view.printMessageAndInt(view.CONGRATULATIONS, inputValue);
        ArrayList<Integer> allInputNumbers = model.getAllInputNumbers();
        view.printMessageAndInt(view.NUMBER_OF_ENTERED_NUMBERS, allInputNumbers.size());
        view.printMessageAndIntList(view.ENTERED_NUMBERS, allInputNumbers);
    }

    /**
     * Checking input data from input stream and getting an integer value.
     * @param scanner input stream.
     * @return an integer value.
     */
    private Integer inputValueUsingScanner(Scanner scanner) {
        view.printMessageAndRange(view.INPUT_INT_DATA_IN_RANGE, model.getCurrentMinLimitOfRange(),
                model.getCurrentMaxLimitOfRange());

        String strInput;
        // cycle for getting integer number from user
        while (!scanner.hasNextInt()) {  // wait for entering data and if it is NOT integer:
            strInput = scanner.next(); // get this value as string
            if(GlobalConstants.EXIT_LABEL.equals(strInput)){ // and compare it with exit label
                return null; // exit from method, because inputted value is exit label
            }
            view.printMessage(view.WRONG_INPUT_DATA);
            view.printMessageAndRange(view.INPUT_INT_DATA_IN_RANGE, model.getCurrentMinLimitOfRange(),
                    model.getCurrentMaxLimitOfRange());
        }

        // if we are here, input value is an integer
        return scanner.nextInt();
    }

    /**
     * Checking input value from input stream and getting an integer
     * for minimum limit of range.
     * @param scanner input stream.
     * @return an integer value for minimum limit of range.
     */
    private Integer inputMinLimitOfRange(Scanner scanner){
        view.printMessage(view.GETTING_MIN_LIMIT);

        String strInput;
        // cycle for getting minimum range from user
        while (!scanner.hasNextInt()){ // wait for entering data and if it is NOT integer:
            strInput = scanner.next(); // get this value as string
            if(GlobalConstants.EXIT_LABEL.equals(strInput)){ // and compare it with exit label
                return null; // exit method, because inputed value is exit label
            }
            view.printMessage(view.BAD_MIN_LIMIT);
        }

        // if we are here, input value is an integer
        return scanner.nextInt();
    }

    /**
     * Checking input value from input stream and getting an integer
     * for maximum limit of range.
     * @param scanner input stream.
     * @param minLimitOfRange minimum limit of range.
     * @return an integer value for maximum limit of range.
     */
    private Integer inputMaxLimitOfRange(Scanner scanner, int minLimitOfRange){
        view.printMessage(view.GETTING_MAX_LIMIT);

        int intInput;
        String strInput;
        // cycle for getting maximum range from user
        while (true){
            if(!scanner.hasNextInt()){ // wait for entering data and if it is NOT integer:
                strInput = scanner.next(); // get this value as string
                if(GlobalConstants.EXIT_LABEL.equals(strInput)){ // and compare it with exit label
                    return null; // exit method, because inputted value is exit label
                }

                // if we are here, inputted value is NOT an exit label and is NOT an integer,
                // so we printing message and begin from start
                view.printMessage(view.BAD_MAX_LIMIT);
                continue;
            }

            // if we are here, input value is an integer
            intInput = scanner.nextInt(); // getting input number
            if(intInput <= minLimitOfRange+1){ // if inputted number can NOT be a maximum limit of range
                view.printMessage(view.BAD_MAX_LIMIT); // printing message and begin from start
            } else { // if inputted integer is good
                break; // breaking our cycle
            }
        }

        // if we are here, input value can be a maximum limit of range
        return intInput;
    }
}
