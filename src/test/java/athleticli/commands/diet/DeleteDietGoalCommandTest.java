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

class DeleteDietGoalCommandTest {

    private Data data;
    private DietGoal dietGoalFats;
    private ArrayList<DietGoal> filledInputDietGoals;

    @BeforeEach
    void setUp() {
        data = new Data();

        dietGoalFats = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 10000);

        filledInputDietGoals = new ArrayList<>();
        filledInputDietGoals.add(dietGoalFats);
    }

    @Test
    void execute_deleteOneItemFromFilledDietGoalList_expectCorrectMessage() {
        try {
            SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
            setDietGoalCommand.execute(data);
            System.out.println(data.getDietGoals());
            DeleteDietGoalCommand deleteDietGoalCommand = new DeleteDietGoalCommand(1);
            String[] expectedString = new String[]{"The following goal has been deleted:\n", "[HEALTHY]  "
                    + "WEEKLY fats intake progress: (0/10000)\n",};
            assertArrayEquals(expectedString, deleteDietGoalCommand.execute(data));
        } catch (AthletiException e) {
            fail(e);
        }
    }

    @Test
    void execute_deleteOneItemFromEmptyDietGoalList_expectAthletiException() {
        DeleteDietGoalCommand deleteDietGoalCommand = new DeleteDietGoalCommand(100);
        assertThrows(AthletiException.class, () -> deleteDietGoalCommand.execute(data));
    }

    @Test
    void execute_integerExceedListSize_expectAthletiException() {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        DeleteDietGoalCommand deleteDietGoalCommand = new DeleteDietGoalCommand(100);
        try {
            setDietGoalCommand.execute(data);
        } catch (AthletiException e) {
            fail();
        }
        assertThrows(AthletiException.class, () -> deleteDietGoalCommand.execute(data));
    }
}
