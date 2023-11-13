package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.HealthyDietGoal;
import athleticli.data.diet.UnhealthyDietGoal;
import athleticli.exceptions.AthletiException;
import athleticli.parser.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EditDietGoalCommandTest {

    private ArrayList<DietGoal> emptyInputDietGoals;
    private ArrayList<DietGoal> filledInputDietGoals;
    private ArrayList<DietGoal> filledValidUpdatedDietGoals;
    private ArrayList<DietGoal> filledInvalidGoalTypeDietGoals;
    private ArrayList<DietGoal> filledInconsistentTargetValueWithTimeSpanDietGoals;
    private DietGoal dietGoalCarbWeekly;
    private DietGoal dietGoalFatWeekly;
    private DietGoal newDietGoalFatWeekly;
    private DietGoal dietGoalFatDaily;
    private DietGoal unhealthyDietGoalFatDaily;
    private DietGoal newDietGoalFatWeeklySmall;
    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();

        dietGoalCarbWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "carb", 10000);
        dietGoalFatWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fat", 10000);
        dietGoalFatDaily = new HealthyDietGoal(Goal.TimeSpan.DAILY, "fat", 100);
        newDietGoalFatWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fat", 100);
        newDietGoalFatWeeklySmall = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fat", 1);
        unhealthyDietGoalFatDaily = new UnhealthyDietGoal(Goal.TimeSpan.WEEKLY,
                Parameter.NUTRIENTS_FAT, 10000);

        emptyInputDietGoals = new ArrayList<>();
        filledInputDietGoals = new ArrayList<>();
        filledValidUpdatedDietGoals = new ArrayList<>();
        filledInvalidGoalTypeDietGoals = new ArrayList<>();

        filledInputDietGoals.add(dietGoalFatWeekly);
        filledInputDietGoals.add(dietGoalCarbWeekly);

        filledValidUpdatedDietGoals.add(newDietGoalFatWeekly);
        filledInvalidGoalTypeDietGoals.add(unhealthyDietGoalFatDaily);

        filledInconsistentTargetValueWithTimeSpanDietGoals = new ArrayList<>();
        filledInconsistentTargetValueWithTimeSpanDietGoals.add(newDietGoalFatWeeklySmall);

    }

    @Test
    void execute_emptyInputList_expectCorrectMessage() throws AthletiException {
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(emptyInputDietGoals);
        String[] expectedString = {"These are your goal(s):\n", "", "Now you have 0 diet goal(s)."};
        String[] actualString = editDietGoalCommand.execute(data);
        assertArrayEquals(expectedString, actualString);

    }

    @Test
    void execute_oneNotExistedDietGoal_expectError() {
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledInputDietGoals);
        assertThrows(AthletiException.class, () -> editDietGoalCommand.execute(data));
    }

    @Test
    void execute_invalidDietGoalType_expectError() throws AthletiException {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledInvalidGoalTypeDietGoals);
        setDietGoalCommand.execute(data);
        assertThrows(AthletiException.class, () -> editDietGoalCommand.execute(data));
    }
    @Test
    void execute_inconsistentDietGoal_expectError() throws AthletiException {
        filledInputDietGoals.add(dietGoalFatDaily);
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        EditDietGoalCommand editDietGoalCommand =
                new EditDietGoalCommand(filledInconsistentTargetValueWithTimeSpanDietGoals);
        setDietGoalCommand.execute(data);
        assertThrows(AthletiException.class, () -> editDietGoalCommand.execute(data));
    }

    @Test
    void execute_changeOneExistingInputDietGoal_expectCorrectMessage() throws AthletiException {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledValidUpdatedDietGoals);
        String[] expectedString = {"These are your goal(s):\n", "\t1. [HEALTHY]  "
                + "WEEKLY fat intake progress: (0/100)\n\n" + "\t2. [HEALTHY]  "
                + "WEEKLY carb intake progress: (0/10000)\n", "Now you have 2 diet goal(s)."};
        setDietGoalCommand.execute(data);
        assertArrayEquals(expectedString, editDietGoalCommand.execute(data));

    }
}
