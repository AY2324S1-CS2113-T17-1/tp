package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.ActivityGoalList;
import athleticli.ui.Message;

import java.util.logging.Logger;

/**
 * Lists the activity goals.
 */
public class ListActivityGoalCommand extends Command {
    private static final Logger logger = Logger.getLogger(ListActivityGoalCommand.class.getName());
    /**
     * Constructor for ListActivityCommand.
     */
    public ListActivityGoalCommand() {
    }

    /**
     * Lists the activities.
     *
     * @param data The current data containing the activity list.
     * @return The message containing listing of activities which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        logger.info("Listing activity goals");
        ActivityGoalList activityGoals = data.getActivityGoals();
        int size = activityGoals.size();
        String[] output = new String[size + 1];
        output[0] = Message.MESSAGE_ACTIVITY_GOAL_LIST;
        for (int i = 0; i < activityGoals.size(); i++) {
            output[i + 1] = (i + 1) + ". " + activityGoals.get(i).toString(data);
        }
        logger.info("Found " + size + " activity goals");
        return output;
    }
}
