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
    private Activity activity;

    /**
     * Constructor for AddActivityCommand.
     * @param activity Activity to be added.
     */
    public AddActivityCommand(Activity activity){
        this.activity = activity;
    }

    /**
     * Updates the activity list.
     * @param activities  The activity list to be updated.
     * @return            The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        ActivityList activities = data.getActivities();
        activities.add(this.activity);
        return new String[]{Message.MESSAGE_ACTIVITY_ADDED};
    }

}
