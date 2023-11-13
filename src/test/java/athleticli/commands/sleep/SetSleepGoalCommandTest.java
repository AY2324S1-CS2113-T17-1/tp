package athleticli.commands.sleep;

import athleticli.data.Data;
import athleticli.data.sleep.SleepGoal;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetSleepGoalCommandTest {
    
    private SetSleepGoalCommand setSleepGoalCommand;
    private Data data;
    private SleepGoal sleepGoal;

    @BeforeEach
    void setup() {
        data = new Data();

        SleepGoal.GoalType goalType = SleepGoal.GoalType.DURATION;
        SleepGoal.TimeSpan timeSpan = SleepGoal.TimeSpan.WEEKLY;
        int goalValue = 8; // Representing 8 minutes

        sleepGoal = new SleepGoal(goalType, timeSpan, goalValue);
        setSleepGoalCommand = new SetSleepGoalCommand(sleepGoal);
    }

    @Test
    void execute_noDuplicate_executed() throws AthletiException {
        String[] actual = setSleepGoalCommand.execute(data);
        String[] expected = {"Alright, I've added this sleep goal:", sleepGoal.toString(data)};
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_duplicate_exceptionThrown() throws AthletiException {
        // First execution to add the goal
        setSleepGoalCommand.execute(data);

        // The second execution should throw an exception
        AthletiException thrown = assertThrows(AthletiException.class, () -> {
            setSleepGoalCommand.execute(data);
        });

        String expectedMessage = "You already have a goal for this type and period! " +
                                "Please edit the existing goal instead."; 
        String actualMessage = thrown.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
