package athleticli.commands.activity;


import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.ActivityGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.util.logging.Logger;

/**
 * Represents a command which edits an activity goal.
 */
public class EditActivityGoalCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditActivityGoalCommand.class.getName());
    private final ActivityGoal activityGoal;

    /**
     * Constructor for EditActivityGoalCommand.
     *
     * @param activityGoal Activity goal to be edited.
     */
    public EditActivityGoalCommand(ActivityGoal activityGoal) {
        this.activityGoal = activityGoal;
    }

    /**
     * Updates the activity goal list.
     *
     * @param data The current data containing the activity goal list.
     * @return The message which will be shown to the user.
     * @throws AthletiException if no such goal exists
     */
    @Override
    public String[] execute(Data data) throws athleticli.exceptions.AthletiException {
        logger.info("Editing activity goal with goal type " + this.activityGoal.getGoalType() + " and sport " +
                            this.activityGoal.getSport() + " and time span " + this.activityGoal.getTimeSpan());
        ActivityGoalList activityGoals = data.getActivityGoals();
        for (ActivityGoal goal : activityGoals) {
            if (goal.getSport() == this.activityGoal.getSport() &&
                        goal.getGoalType() == this.activityGoal.getGoalType() &&
                        goal.getTimeSpan() == this.activityGoal.getTimeSpan()) {
                goal.setTargetValue(this.activityGoal.getTargetValue());
                logger.info("Activity goal edited successfully");
                return new String[]{Message.MESSAGE_ACTIVITY_GOAL_EDITED, this.activityGoal.toString(data)};
            }
        }
        logger.warning("No such goal exists");
        throw new AthletiException(Message.MESSAGE_NO_SUCH_GOAL_EXISTS);
    }
}
