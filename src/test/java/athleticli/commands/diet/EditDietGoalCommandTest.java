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
    private DietGoal dietGoalFatsWeekly;
    private DietGoal newDietGoalFatsWeekly;
    private DietGoal dietGoalFatsDaily;
    private DietGoal unhealthyDietGoalFatsDaily;
    private DietGoal newDietGoalFatsWeeklySmall;
    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();

        dietGoalCarbWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "carb", 10000);
        dietGoalFatsWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 10000);
        dietGoalFatsDaily = new HealthyDietGoal(Goal.TimeSpan.DAILY, "fats", 100);
        newDietGoalFatsWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 100);
        newDietGoalFatsWeeklySmall = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 1);
        unhealthyDietGoalFatsDaily = new UnhealthyDietGoal(Goal.TimeSpan.WEEKLY,
                Parameter.NUTRIENTS_FATS, 10000);

        emptyInputDietGoals = new ArrayList<>();
        filledInputDietGoals = new ArrayList<>();
        filledValidUpdatedDietGoals = new ArrayList<>();
        filledInvalidGoalTypeDietGoals = new ArrayList<>();

        filledInputDietGoals.add(dietGoalFatsWeekly);
        filledInputDietGoals.add(dietGoalCarbWeekly);

        filledValidUpdatedDietGoals.add(newDietGoalFatsWeekly);
        filledInvalidGoalTypeDietGoals.add(unhealthyDietGoalFatsDaily);

        filledInconsistentTargetValueWithTimeSpanDietGoals = new ArrayList<>();
        filledInconsistentTargetValueWithTimeSpanDietGoals.add(newDietGoalFatsWeeklySmall);

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
        filledInputDietGoals.add(dietGoalFatsDaily);
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
                + "WEEKLY fats intake progress: (0/100)\n\n" + "\t2. [HEALTHY]  "
                + "WEEKLY carb intake progress: (0/10000)\n", "Now you have 2 diet goal(s)."};
        setDietGoalCommand.execute(data);
        assertArrayEquals(expectedString, editDietGoalCommand.execute(data));

    }
}
