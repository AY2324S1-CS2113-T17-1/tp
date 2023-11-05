package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.ActivityList;
import athleticli.ui.Message;

/**
 * Executes the list activity command provided by the user.
 */
public class ListActivityCommand extends Command {
    private final boolean isDetailed;

    /**
     * Constructor for ListActivityCommand.
     * @param isDetailed Whether the list should be detailed.
     */
    public ListActivityCommand(boolean isDetailed) {
        this.isDetailed = isDetailed;
    }

    /**
     * Lists the activities.
     * @param data      The current data containing the activity list.
     * @return          The message containing listing of activities which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        ActivityList activities = data.getActivities();
        final int size = activities.size();
        if (isDetailed) {
            return printDetailedList(activities, size);
        } else {
            return printList(activities, size);
        }
    }

    /**
     * Prints the list of activities.
     * @param activities    The current activity list.
     * @param size          The size of the activity list.
     * @return              The message containing listing of activities which will be shown to the user.
     */
    public String[] printList(ActivityList activities, int size) {
        String[] output = new String[size + 1];
        output[0] = Message.MESSAGE_ACTIVITY_LIST;
        for (int i = 0; i < size; i++) {
            output[i + 1] = (i + 1) + "." + activities.get(i).toString();
        }
        return output;
    }

    /**
     * Prints the detailed list of activities.
     * @param activities    The current activity list.
     * @param size          The size of the activity list.
     * @return              The message containing listing of activities which will be shown to the user.
     */
    public String[] printDetailedList(ActivityList activities, int size) {
        String[] output = new String[size + 1];
        output[0] = Message.MESSAGE_ACTIVITY_LIST;
        for (int i = 0; i < size; i++) {
            output[i+1] = activities.get(i).toDetailedString();
        }
        return output;
    }
}
