package moreLessGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class calculate a random value in inputted range, get another value,
 * check its and return the result of its checking.
 * @author Maksym
 */
public class Model {
    /**
     * Current minimum limit of range.
     */
    private int currentMinLimitOfRange;

    /**
     * Current maximum limit of range.
     */
    private int currentMaxLimitOfRange;

    /**
     * All input numbers.
     */
    private ArrayList<Integer> allInputNumbers;

    /**
     * Hidden number, calculating as random numberin limits of range.
     */
    private Integer hiddenIntNumber;

    /**
     * Constructor of the class. Initialize limits of range and generate hidden number.
     * @param basedMinLimitOfRange an integer representation of minimum limit of range
     *                             for hidden number.
     * @param basedMaxLimitOfRange an integer representation of maximum limit of range
     *                             for hidden number.
     */
    public Model(int basedMinLimitOfRange, int basedMaxLimitOfRange) {
        currentMaxLimitOfRange = basedMaxLimitOfRange;
        currentMinLimitOfRange = basedMinLimitOfRange;
        hiddenIntNumber = getNewRandomIntIncludingLimitValues(basedMinLimitOfRange, basedMaxLimitOfRange);
        allInputNumbers = new ArrayList<>();
    }

    /**
     * Comparison of input value and hidden number.
     * @param inputValue an input integer value for checking.
     * @return an StatesOfNumberChecking object, that represent a state of current comparison.
     */
    public StatesOfNumberChecking checkValue(int inputValue) {
        allInputNumbers.add(inputValue);
        boolean inputValueIsInCurrentRange = inputValue > currentMinLimitOfRange
                && inputValue < currentMaxLimitOfRange;

        if (!inputValueIsInCurrentRange) {
            return StatesOfNumberChecking.INPUT_NUMBER_IS_OUT_OF_RANGE;
        }

        if (hiddenIntNumber > inputValue) {
            currentMinLimitOfRange = inputValue;
            return StatesOfNumberChecking.GUESSED_NUMBER_IS_BIGGER;
        }

        if (hiddenIntNumber < inputValue) {
            currentMaxLimitOfRange = inputValue;
            return StatesOfNumberChecking.GUESSED_NUMBER_IS_LESS;
        }

        // last variant: hiddenIntNumber.equals(inputValue)
        return StatesOfNumberChecking.INPUT_NUMBER_IS_GUESSED_NUMBER;
    }

    /**
     * Calculation of a random integer number in inputted range NOT inclusively.
     * @param minLimitOfRange an integer minimum limit of range.
     * @param maxLimitOfRange an integer maximum limit of range.
     * @return an integer number in current range.
     */
    private int getNewRandomIntIncludingLimitValues(int minLimitOfRange, int maxLimitOfRange) {
        Random rand = new Random();
        return rand.nextInt(maxLimitOfRange - minLimitOfRange -1) + minLimitOfRange + 1 ;
    }

    /**
     * Get new limits of range, generate new random number in inputted
     * range NOT inclusively and deleting all input numbers.
     * @param newMinLimit an integer minimum limit of range.
     * @param newMaxLimit an integer maximum limit of range.
     */
    public void regenerateSecretNumber(int newMinLimit, int newMaxLimit){
        currentMinLimitOfRange = newMinLimit;
        currentMaxLimitOfRange = newMaxLimit;
        hiddenIntNumber = getNewRandomIntIncludingLimitValues(newMinLimit, newMaxLimit);
        allInputNumbers = new ArrayList<>();
    }

    /**
     * Return the current minimum limit of range.
     * @return current minimum limit of range.
     */
    public int getCurrentMinLimitOfRange() {
        return currentMinLimitOfRange;
    }

    /**
     * Return the current maximum limit of range.
     * @return current maximum limit of range.
     */
    public int getCurrentMaxLimitOfRange() {
        return currentMaxLimitOfRange;
    }

    /**
     * Return list of all input numbers.
     * @return list of all input numbers.
     */
    public ArrayList<Integer> getAllInputNumbers() {
        return allInputNumbers;
    }
}
