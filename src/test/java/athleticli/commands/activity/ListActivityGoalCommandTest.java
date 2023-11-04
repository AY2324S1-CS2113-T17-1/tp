package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.ActivityGoalList;
import athleticli.ui.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListActivityGoalCommandTest {

    private ListActivityGoalCommand command;
    private Data data;

    @BeforeEach
    void setUp() {
        command = new ListActivityGoalCommand();
        data = new Data();
    }

    @Test
    void execute_noActivityGoal_returnsNoActivityGoalMessage() {
        String[] result = command.execute(data);
        assertEquals(Message.MESSAGE_ACTIVITY_GOAL_LIST, result[0]);
    }

    @Test
    void execute_existingActivityGoal_returnsActivityGoalList() {
        ActivityGoalList activityGoals = data.getActivityGoals();
        ActivityGoal goal1 = new ActivityGoal(ActivityGoal.TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 10);
        ActivityGoal goal2 = new ActivityGoal(ActivityGoal.TimeSpan.MONTHLY, ActivityGoal.GoalType.DURATION,
                ActivityGoal.Sport.CYCLING, 20);
        ActivityGoal goal3 = new ActivityGoal(ActivityGoal.TimeSpan.YEARLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.SWIMMING, 30);
        ActivityGoal goal4 = new ActivityGoal(ActivityGoal.TimeSpan.DAILY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.GENERAL, 40);
        activityGoals.add(goal1);
        activityGoals.add(goal2);
        activityGoals.add(goal3);
        activityGoals.add(goal4);
        String[] actual = command.execute(data);
        String[] expected = {
            Message.MESSAGE_ACTIVITY_GOAL_LIST,
            "1. " + goal1.toString(data),
            "2. " + goal2.toString(data),
            "3. " + goal3.toString(data),
            "4. " + goal4.toString(data)
        };
        assertArrayEquals(expected, actual);
    }
}
