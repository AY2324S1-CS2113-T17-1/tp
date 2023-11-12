package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.data.Data;
import athleticli.data.sleep.SleepGoal;
import athleticli.exceptions.AthletiException;


public class EditSleepGoalCommandTest {

    private Data data;
    private SleepGoal sleepGoal;
    private EditSleepGoalCommand editSleepGoalCommand;

    @BeforeEach
    public void setUp() {
        data = new Data();
        SleepGoal.GoalType goalType = SleepGoal.GoalType.DURATION;
        SleepGoal.TimeSpan timeSpan = SleepGoal.TimeSpan.WEEKLY;
        int initialGoalValue = 7; // Original goal value
        int newGoalValue = 8; // New goal value for editing

        SleepGoal initialSleepGoal = new SleepGoal(goalType, timeSpan, initialGoalValue);
        data.getSleepGoals().add(initialSleepGoal);

        sleepGoal = new SleepGoal(goalType, timeSpan, newGoalValue);
        editSleepGoalCommand = new EditSleepGoalCommand(sleepGoal);
    }

    @Test
    public void execute_goalExists_edited() throws AthletiException {
        String[] actual = editSleepGoalCommand.execute(data);
        String[] expected = {"Alright, I've edited this sleep goal:", sleepGoal.toString(data)};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void execute_goalDoesNotExist_exceptionThrown() {
        SleepGoal nonExistingSleepGoal = new SleepGoal(SleepGoal.GoalType.DURATION, SleepGoal.TimeSpan.MONTHLY, 9);
        EditSleepGoalCommand commandWithNonExistingGoal = new EditSleepGoalCommand(nonExistingSleepGoal);

        AthletiException thrown = assertThrows(AthletiException.class, () -> {
            commandWithNonExistingGoal.execute(data);
        });

        String expectedMessage = "No such goal exists."; 
        assertEquals(expectedMessage, thrown.getMessage());
    }
}
