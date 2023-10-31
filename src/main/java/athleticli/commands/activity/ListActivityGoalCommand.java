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
        ActivityGoalList activities = data.getActivityGoals();
        return new String[]{Message.MESSAGE_ACTIVITY_GOAL_LIST, activities.toString(data)};
    }
}
