package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.ActivityGoalList;
import athleticli.ui.Message;

/**
 * Lists the activity goals.
 */
public class ListActivityGoalCommand extends Command {
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
        ActivityGoalList activityGoals = data.getActivityGoals();
        int size = activityGoals.size();
        String[] output = new String[size + 1];
        output[0] = Message.MESSAGE_ACTIVITY_GOAL_LIST;
        for (int i = 0; i < activityGoals.size(); i++) {
            output[i + 1] = (i + 1) + ". " + activityGoals.get(i).toString(data);
        }
        return output;
    }
}
