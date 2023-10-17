package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Executes the edit activity command provided by the user.
 */
public class EditActivityCommand extends Command {
    private final int index;
    private final Activity activity;

    /**
     * Constructor for EditActivityCommand.
     * @param index Index of the activity to be edited.
     * @param activity Updated Activity.
     */
    public EditActivityCommand(Activity activity, int index) {
        this.index = index;
        this.activity = activity;
    }

    /**
     * Executes the edit activity command.
     * @param data Data object containing the current list of activities.
     * @return String array containing the messages to be printed to the user.
     * @throws AthletiException If the index provided is out of bounds.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        ActivityList activities = data.getActivities();
        try {
            activities.set(index-1, activity);
            return new String[]{Message.MESSAGE_ACTIVITY_UPDATED, activity.toString(),
                    String.format(Message.MESSAGE_ACTIVITY_COUNT, activities.size())};
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_INDEX_OUT_OF_BOUNCE);
        }
    }
}
