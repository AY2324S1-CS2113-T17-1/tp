package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.SleepGoal;
import athleticli.data.sleep.SleepGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.util.logging.Logger;

/**
 * Represents a command which edits an activity goal.
 */
public class EditSleepGoalCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditSleepGoalCommand.class.getName());
    private final SleepGoal sleepGoal;

    /**
     * Constructor for EditActivityGoalCommand.
     * 
     * @param sleepGoal Activity goal to be edited.
     */
    public EditSleepGoalCommand(SleepGoal sleepGoal) {
        this.sleepGoal = sleepGoal;
    }

    /**
     * Updates the sleep goal list.
     *
     * @param data The current data containing the sleep goal list.
     * @return The message which will be shown to the user.
     * @throws AthletiException if no such goal exists
     */

    public String[] execute(Data data) throws athleticli.exceptions.AthletiException {
        logger.info("Editing sleep goal with goal type " + this.sleepGoal.getGoalType() + " and time span " +
                this.sleepGoal.getTimeSpan());
        
        SleepGoalList sleepGoals = data.getSleepGoals();
        for (SleepGoal goal : sleepGoals) {
            if (goal.getGoalType() == this.sleepGoal.getGoalType() &&
                    goal.getTimeSpan() == this.sleepGoal.getTimeSpan()) {
                goal.setTargetValue(this.sleepGoal.getTargetValue());
                logger.info("Sleep goal edited successfully");
                return new String[]{Message.MESSAGE_SLEEP_GOAL_EDITED, this.sleepGoal.toString(data)};
            }
        }

        logger.warning("No such goal exists");

        throw new AthletiException(Message.MESSAGE_NO_SUCH_GOAL_EXISTS);
    }
}
