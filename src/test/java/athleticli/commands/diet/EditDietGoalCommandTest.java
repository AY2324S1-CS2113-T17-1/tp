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

class EditDietGoalCommandTest {

    private ArrayList<DietGoal> emptyInputDietGoals;
    private ArrayList<DietGoal> filledInputDietGoals;
    private ArrayList<DietGoal> filledChangedInputDietGoals;
    private DietGoal dietGoalCarb;
    private DietGoal dietGoalFats;
    private DietGoal newDietGoalFats;
    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();

        dietGoalCarb = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "carb", 10000);
        dietGoalFats = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 10000);
        newDietGoalFats = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 10);

        emptyInputDietGoals = new ArrayList<>();
        filledInputDietGoals = new ArrayList<>();
        filledInputDietGoals.add(dietGoalFats);
        filledInputDietGoals.add(dietGoalCarb);
        filledChangedInputDietGoals = new ArrayList<>();
        filledChangedInputDietGoals.add(newDietGoalFats);
    }

    @Test
    void execute_emptyInputList_expectCorrectMessage() {
        try {
            EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(emptyInputDietGoals);
            String[] expectedString = {"These are your goal(s):\n", "", "Now you have 0 diet goal(s)."};
            String[] actualString = editDietGoalCommand.execute(data);
            assertArrayEquals(expectedString, actualString);
        } catch (AthletiException e) {
            fail(e);
        }
    }

    @Test
    void execute_oneNewInputDietGoal_expectError() {
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledInputDietGoals);
        assertThrows(AthletiException.class, () -> editDietGoalCommand.execute(data));
    }

    @Test
    void execute_changeOneExistingInputDietGoal_expectCorrectMessage() {
        try {
            SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
            EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledChangedInputDietGoals);
            String[] expectedString = {"These are your goal(s):\n", "\t1. [HEALTHY]  "
                    + "WEEKLY fats intake progress: (0/10)\n\n" + "\t2. [HEALTHY]  "
                    + "WEEKLY carb intake progress: (0/10000)\n", "Now you have 2 diet goal(s)."};
            setDietGoalCommand.execute(data);
            assertArrayEquals(expectedString, editDietGoalCommand.execute(data));
        } catch (AthletiException e) {
            fail(e);
        }
    }
}
