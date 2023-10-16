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

class EditDietGoalCommandTest {

    private ArrayList<DietGoal> emptyInputDietGoals;
    private ArrayList<DietGoal> filledInputDietGoals;
    private ArrayList<DietGoal> filledChangedInputDietGoals;
    private DietGoal dietGoalFats;
    private DietGoal newDietGoalFats;
    private Data data;

    @BeforeEach
    void setUp() {
        emptyInputDietGoals = new ArrayList<>();
        dietGoalFats = new DietGoal("fats", 10000);
        newDietGoalFats = new DietGoal("fats", 10);
        data = new Data();
        filledInputDietGoals = new ArrayList<>();
        filledInputDietGoals.add(dietGoalFats);
        filledChangedInputDietGoals = new ArrayList<>();
        filledChangedInputDietGoals.add(newDietGoalFats);
    }

    @Test
    void execute_emptyInputList_expectNoError() {
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(emptyInputDietGoals);
        assertDoesNotThrow(() -> editDietGoalCommand.execute(data));
    }

    @Test
    void execute_emptyInputList_expectCorrectMessage() {
        try {
            EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(emptyInputDietGoals);
            String[] expectedString = {"These are your goals:\n", ""};
            String[] actualString = editDietGoalCommand.execute(data);
            assertArrayEquals(expectedString, actualString);
        } catch (AthletiException e) {
            assert (false);
        }
    }

    @Test
    void execute_oneNewInputDietGoal_expectError() {
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledInputDietGoals);
        assertThrows(AthletiException.class, () -> editDietGoalCommand.execute(data));
    }

    @Test
    void execute_oneExistingInputDietGoal_expectNoError() {
        SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
        EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledInputDietGoals);

        try {
            setDietGoalCommand.execute(data);
        } catch (AthletiException e) {
            assert (false);
        }
        assertDoesNotThrow(() -> editDietGoalCommand.execute(data));
    }

    @Test
    void execute_changeOneExistingInputDietGoal_expectCorrectMessage() {
        try {
            SetDietGoalCommand setDietGoalCommand = new SetDietGoalCommand(filledInputDietGoals);
            EditDietGoalCommand editDietGoalCommand = new EditDietGoalCommand(filledChangedInputDietGoals);
            String[] expectedString = {"These are your goals:\n", "1. fats intake progress: (0/10)\n"};

            setDietGoalCommand.execute(data);
            assertArrayEquals(expectedString, editDietGoalCommand.execute(data));
        } catch (AthletiException e) {
            assert (false);
        }
    }
}
