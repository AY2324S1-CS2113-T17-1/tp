package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.Goal.TimeSpan;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.ActivityGoal.GoalType;
import athleticli.data.activity.ActivityGoal.Sport;
import athleticli.data.activity.ActivityGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Represents a command which deletes an activity goal.
 */
public class DeleteActivityGoalCommand extends Command {
    GoalType goalType;
    Sport sport;
    TimeSpan timeSpan;


    /**
     * Constructor for DeleteActivityGoalCommand.
     *
     * @param activityGoal Activity goal to be deleted.
     */
    public DeleteActivityGoalCommand(ActivityGoal activityGoal) {
        this.goalType = activityGoal.getGoalType();
        this.sport = activityGoal.getSport();
        this.timeSpan = activityGoal.getTimeSpan();
    }


    /**
     * Deletes the activity goal from the activity goal list.
     *
     * @param data The current data containing the activity goal list.
     * @return The message which will be shown to the user.
     * @throws AthletiException if no such goal exists
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        ActivityGoalList activityGoals = data.getActivityGoals();
        String activityGoalString = "";
        if (!activityGoals.isDuplicate(this.goalType, this.sport, this.timeSpan)) {
            throw new AthletiException(Message.MESSAGE_NO_SUCH_GOAL_EXISTS);
        }
        for (int i = 0; i < activityGoals.size(); i++) {
            if (activityGoals.get(i).getGoalType() == this.goalType &&
                        activityGoals.get(i).getSport() == this.sport &&
                        activityGoals.get(i).getTimeSpan() == this.timeSpan) {
                activityGoalString = activityGoals.get(i).toString(data);
                activityGoals.remove(i);
                break;
            }
        }
        return new String[]{Message.MESSAGE_ACTIVITY_GOAL_DELETED, activityGoalString};
    }
}
