package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityList;
import athleticli.ui.Message;

/**
 * Executes the add activity commands provided by the user.
 */
public class AddActivityCommand extends Command {
    private final Activity activity;

    /**
     * Constructor for AddActivityCommand.
     * @param activity Activity to be added.
     */
    public AddActivityCommand(Activity activity){
        this.activity = activity;
    }

    /**
     * Updates the activity list.
     * @param data        The current data containing the activity list.
     * @return            The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        ActivityList activities = data.getActivities();
        activities.add(this.activity);
        activities.sort();
        int size = activities.size();
        String countMessage;
        if (size > 1) {
            countMessage = String.format(Message.MESSAGE_ACTIVITY_COUNT, size);
        } else {
            countMessage = Message.MESSAGE_ACTIVITY_FIRST;
        }
        return new String[]{Message.MESSAGE_ACTIVITY_ADDED, this.activity.toString(), countMessage};
    }

}
