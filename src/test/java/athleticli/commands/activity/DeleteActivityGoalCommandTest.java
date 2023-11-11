package athleticli.commands.activity;


import athleticli.data.Data;
import athleticli.data.Goal.TimeSpan;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.ActivityGoal.GoalType;
import athleticli.data.activity.ActivityGoal.Sport;
import athleticli.data.activity.ActivityGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DeleteActivityGoalCommandTest {

    ActivityGoalList activityGoals;
    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();
        activityGoals = data.getActivityGoals();
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
    }

    @Test
    void execute_existingActivityGoal_editsActivityGoal() throws athleticli.exceptions.AthletiException {
        ActivityGoal goal = new ActivityGoal(TimeSpan.WEEKLY, GoalType.DISTANCE, Sport.RUNNING, 0);
        DeleteActivityGoalCommand command = new DeleteActivityGoalCommand(goal);
        String[] expected =
                new String[]{Message.MESSAGE_ACTIVITY_GOAL_DELETED, activityGoals.get(0).toString(data)};
        String[] actual = command.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_nonExistingActivityGoal_throwsAthletiException() {
        ActivityGoal goal = new ActivityGoal(TimeSpan.YEARLY, GoalType.DISTANCE, Sport.RUNNING, 0);
        DeleteActivityGoalCommand command = new DeleteActivityGoalCommand(goal);
        assertThrows(AthletiException.class, () -> command.execute(data));
    }
}
