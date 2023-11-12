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
import static org.junit.jupiter.api.Assertions.fail;

class SetDietGoalCommandTest {

    private ArrayList<DietGoal> emptyInputDietGoals;
    private ArrayList<DietGoal> filledInputDietGoals;
    private ArrayList<DietGoal> filledUnhealthyInputDietGoals;
    private ArrayList<DietGoal> filledNewHealthyInputDietGoals;
    private DietGoal dietGoalFatsWeekly;
    private DietGoal dietGoalFatsDaily;
    private DietGoal dietGoalCarbWeekly;
    private DietGoal unhealthyDietGoalFatsDaily;
    private Data data;

    @BeforeEach
    void setUp() {
        emptyInputDietGoals = new ArrayList<>();
        filledInputDietGoals = new ArrayList<>();
        filledUnhealthyInputDietGoals = new ArrayList<>();
        filledNewHealthyInputDietGoals = new ArrayList<>();

        dietGoalFatsWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, Parameter.NUTRIENTS_FATS, 10000);
        dietGoalFatsDaily = new HealthyDietGoal(Goal.TimeSpan.DAILY, Parameter.NUTRIENTS_FATS, 1000000);
        dietGoalCarbWeekly = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, Parameter.NUTRIENTS_CARB, 10000);
        unhealthyDietGoalFatsDaily = new UnhealthyDietGoal(Goal.TimeSpan.DAILY,
                Parameter.NUTRIENTS_FATS, 10000);
        data = new Data();

        filledInputDietGoals.add(dietGoalFatsWeekly);
        filledInputDietGoals.add(dietGoalCarbWeekly);
        filledUnhealthyInputDietGoals.add(unhealthyDietGoalFatsDaily);
        filledNewHealthyInputDietGoals.add(dietGoalFatsDaily);
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
    void execute_dailyTargetValueGreaterThanOrEqualToWeekly_expectAthletiException() throws AthletiException {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        SetDietGoalCommand setDailyDietGoalCommand = new SetDietGoalCommand(filledNewHealthyInputDietGoals);
        setDietGoalCommand.execute(data);

        assertThrows(AthletiException.class, () -> setDailyDietGoalCommand.execute(data));
    }

    @Test
    void execute_conflictingDietGoalTypes_expectAthletiException() throws AthletiException {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        SetDietGoalCommand setDietGoalCommandUnhealthyGoals = new SetDietGoalCommand(filledUnhealthyInputDietGoals);
        setDietGoalCommandUnhealthyGoals.execute(data);
        assertThrows(AthletiException.class, () -> setDietGoalCommand.execute(data));
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
