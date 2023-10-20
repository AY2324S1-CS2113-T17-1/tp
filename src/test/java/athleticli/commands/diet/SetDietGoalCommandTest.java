package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.diet.DietGoal;
import athleticli.exceptions.AthletiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class SetDietGoalCommandTest {

    private ArrayList<DietGoal> emptyInputDietGoals;
    private ArrayList<DietGoal> filledInputDietGoals;
    private DietGoal dietGoalFats;
    private Data data;

    @BeforeEach
    void setUp() {
        emptyInputDietGoals = new ArrayList<>();
        dietGoalFats = new DietGoal("fats", 10000);
        data = new Data();
        filledInputDietGoals = new ArrayList<>();
        filledInputDietGoals.add(dietGoalFats);
    }

    @Test
    void execute_emptyInputList_expectNoError() {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(emptyInputDietGoals);
        assertDoesNotThrow(() -> setDietGoalCommand.execute(data));
    }

    @Test
    void execute_emptyInputList_expectCorrectMessage() {
        try {
            SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(emptyInputDietGoals);
            String[] expectedString = {"These are your goal(s):\n", "", "Now you have 0 diet goal(s)."};
            String[] actualString = setDietGoalCommand.execute(data);
            assertArrayEquals(expectedString, actualString);
        } catch (AthletiException e) {
            fail(e);
        }
    }

    @Test
    void execute_oneNewInputDietGoal_expectNoError() {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        assertDoesNotThrow(() -> setDietGoalCommand.execute(data));
    }

    @Test
    void execute_oneNewInputDietGoal_expectCorrectMessage() {
        try {
            SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
            String[] expectedString = {"These are your goal(s):\n", "\t1. fats intake progress: " +
                    "(0/10000)\n", "Now you have 1 diet goal(s)."};
            String[] actualString = setDietGoalCommand.execute(data);
            assertArrayEquals(expectedString, actualString);
        } catch (AthletiException e) {
            fail(e);
        }
    }

    @Test
    void execute_oneExistingInputDietGoal_expectAthletiException() {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        try {
            setDietGoalCommand.execute(data);
        } catch (AthletiException e) {
            fail(e);
        }
        assertThrows(AthletiException.class, () -> setDietGoalCommand.execute(data));
    }
}
