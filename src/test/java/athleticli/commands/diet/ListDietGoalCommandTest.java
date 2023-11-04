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

class ListDietGoalCommandTest {

    private ArrayList<DietGoal> filledInputDietGoals;
    private DietGoal dietGoalFats;
    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();

        dietGoalFats = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "fats", 10000);

        filledInputDietGoals = new ArrayList<>();
        filledInputDietGoals.add(dietGoalFats);
    }

    @Test
    void execute_emptyInputList_returnNoDietGoalMessage() {
        String[] expectedString = {"There are no goals at the moment. Add a diet goal to start."};
        ListDietGoalCommand listDietGoalCommand = new ListDietGoalCommand();
        assertArrayEquals(expectedString, listDietGoalCommand.execute(data));
    }

    @Test
    void execute_filledInputList_returnDietGoalPresentMessage() {
        try {
            String[] expectedString = {"These are your goal(s):\n", "\t1. [HEALTHY]  WEEKLY "
                    + "fats intake progress: (0/10000)\n", "Now you have 1 diet goal(s)."};
            ListDietGoalCommand listDietGoalCommand = new ListDietGoalCommand();
            SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
            setDietGoalCommand.execute(data);
            assertArrayEquals(expectedString, listDietGoalCommand.execute(data));
        } catch (AthletiException e) {
            assert (false);
        }
    }
}
