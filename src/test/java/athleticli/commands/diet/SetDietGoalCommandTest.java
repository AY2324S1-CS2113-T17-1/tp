package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.HealthyDietGoal;
import athleticli.exceptions.AthletiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class SetDietGoalCommandTest {

    private ArrayList<DietGoal> emptyInputDietGoals;
    private ArrayList<DietGoal> filledInputDietGoals;
    private DietGoal dietGoalFats;
    private DietGoal dietGoalCarb;
    private Data data;

    @BeforeEach
    void setUp() {
        emptyInputDietGoals = new ArrayList<>();
        dietGoalFats = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 10000);
        dietGoalCarb = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "carb", 10000);
        data = new Data();
        filledInputDietGoals = new ArrayList<>();
        filledInputDietGoals.add(dietGoalFats);
        filledInputDietGoals.add(dietGoalCarb);
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
    void execute_oneNewInputDietGoal_expectCorrectMessage() {
        try {
            SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
            String[] expectedString = {"These are your goal(s):\n", "\t1. [HEALTHY]  "
                    + "WEEKLY fats intake progress: (0/10000)\n\n" + "\t2. [HEALTHY]  "
                    + "WEEKLY carb intake progress: (0/10000)\n", "Now you have 2 diet goal(s)."};
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
