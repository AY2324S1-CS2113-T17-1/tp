package athleticli.commands.sleep;

import athleticli.data.Data;
import athleticli.data.sleep.SleepGoal;
import athleticli.data.sleep.SleepGoalList;
import athleticli.ui.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListSleepGoalCommandTest {

    private ListSleepGoalCommand command;
    private Data data;

    @BeforeEach
    void setup() {
        command = new ListSleepGoalCommand();
        data = new Data();
    }

    @Test
    void execute_noSleepGoal_returnsNoSleepGoalMessage() {
        String[] result = command.execute(data);
        assertEquals(Message.MESSAGE_SLEEP_GOAL_LIST, result[0]);
    }

    @Test
    void execute_existingSleepGoal_returnsSleepGoalList() {
        SleepGoalList sleepGoals = data.getSleepGoals();
        SleepGoal goal1 = new SleepGoal(SleepGoal.GoalType.DURATION, SleepGoal.TimeSpan.WEEKLY, 10);
        SleepGoal goal2 = new SleepGoal(SleepGoal.GoalType.DURATION, SleepGoal.TimeSpan.MONTHLY, 20);
        SleepGoal goal3 = new SleepGoal(SleepGoal.GoalType.DURATION, SleepGoal.TimeSpan.YEARLY, 30);
        SleepGoal goal4 = new SleepGoal(SleepGoal.GoalType.DURATION, SleepGoal.TimeSpan.DAILY, 40);
        sleepGoals.add(goal1);
        sleepGoals.add(goal2);
        sleepGoals.add(goal3);
        sleepGoals.add(goal4);
        String[] actual = command.execute(data);
        String[] expected = {
            Message.MESSAGE_SLEEP_GOAL_LIST,
            "1. " + goal1.toString(data),
            "2. " + goal2.toString(data),
            "3. " + goal3.toString(data),
            "4. " + goal4.toString(data)
        };
        assertArrayEquals(expected, actual);
    }
}
