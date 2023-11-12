package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.ActivityGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Represents a command which adds an activity goal to the activity goal list.
 */
public class SetActivityGoalCommand extends Command {
    private final ActivityGoal activityGoal;

    /**
     * Constructs instance of SetActivityGoalCommand.
     *
     * @param activityGoal Activity goal to be added.
     */
    public SetActivityGoalCommand(ActivityGoal activityGoal){
        this.activityGoal = activityGoal;
    }

    /**
     * Adds the activity goal to the activity goal list.
     *
     * @param data              The current data containing the activity goal list.
     * @return                  The message which will be shown to the user.
     * @throws AthletiException If a duplicate activity goal is detected.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        ActivityGoalList activityGoals = data.getActivityGoals();

        if (activityGoals.isDuplicate(activityGoal.getGoalType(), activityGoal.getSport(),
                activityGoal.getTimeSpan())) {
            throw new AthletiException(Message.MESSAGE_DUPLICATE_ACTIVITY_GOAL);
        }

        activityGoals.add(this.activityGoal);
        return new String[]{Message.MESSAGE_ACTIVITY_GOAL_ADDED, this.activityGoal.toString(data)};
    }
}
