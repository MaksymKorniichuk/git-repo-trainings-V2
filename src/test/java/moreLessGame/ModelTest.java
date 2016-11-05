package moreLessGame;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelTest {
    @Test
    public void checkValueTestByOutOfRangeNumber() throws Exception {
        int minLimit = 0;
        int maxLimit = 100;
        Model model = new Model(minLimit, maxLimit);

        int appendixToMaxLimit = 10;
        StatesOfNumberChecking answerForNumberOutOfRange = model.checkValue(maxLimit + appendixToMaxLimit);
        assertEquals("Test failed: bad number is inside of range.", answerForNumberOutOfRange,
                StatesOfNumberChecking.INPUT_NUMBER_IS_OUT_OF_RANGE);
    }

    @Test
    public void checkValueTestByMinLimitInputNumber() throws Exception {
        int minLimit = 0;
        int maxLimit = 100;
        Model model = new Model(minLimit, maxLimit);

        StatesOfNumberChecking answerForMinLimit = model.checkValue(minLimit);
        assertTrue("Test failed with input number as minimum limit.",
                answerForMinLimit == StatesOfNumberChecking.INPUT_NUMBER_IS_OUT_OF_RANGE);
    }

    @Test
    public void checkValueTestByMaxLimitInputNumber() throws Exception {
        int minLimit = 0;
        int maxLimit = 100;
        Model model = new Model(minLimit, maxLimit);

        StatesOfNumberChecking answerForMaxLimit = model.checkValue(maxLimit);

        assertTrue("Test failed with input number as maximum limit.",
                answerForMaxLimit == StatesOfNumberChecking.INPUT_NUMBER_IS_OUT_OF_RANGE);
    }

    @Test
    public void checkValueTestByGuessedNumber() throws Exception {
        int minLimit = 0;
        int maxLimit = 2;
        Model model = new Model(minLimit, maxLimit);

        StatesOfNumberChecking answerForGoodNumber = model.checkValue(minLimit + 1);
        assertTrue("Test failed with guessed number.",
                answerForGoodNumber == StatesOfNumberChecking.INPUT_NUMBER_IS_GUESSED_NUMBER);
    }

    @Test
    public void checkValueTestByGoodNumber() throws Exception {
        int minLimit = 0;
        int maxLimit = 100;
        Model model = new Model(minLimit, maxLimit);

        StatesOfNumberChecking answerForGoodNumber = model.checkValue(minLimit + 1);
        assertTrue("Test failed with good input number.",
                answerForGoodNumber == StatesOfNumberChecking.INPUT_NUMBER_IS_GUESSED_NUMBER
                        || answerForGoodNumber == StatesOfNumberChecking.GUESSED_NUMBER_IS_LESS
                        || answerForGoodNumber == StatesOfNumberChecking.GUESSED_NUMBER_IS_BIGGER);
    }

    @Test
    public void getNewRandomIntIncludingLimitValuesTestByCorrectRandomNumber() throws Exception{
        int minLimit = -2;
        int maxLimit = 2;
        int numberOfIterations = 1000;

        Random rand = new Random();
        int randomNumber;
        for (int i = 0; i < numberOfIterations; i++) {
            randomNumber = rand.nextInt(maxLimit - minLimit -1) + minLimit + 1 ;
            assertTrue("Test failed: random number is out of range.", randomNumber > minLimit && randomNumber < maxLimit);
        }
    }
}