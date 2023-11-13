package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityList;
import athleticli.ui.Message;

/**
 * Executes the list activity command provided by the user.
 */
public class ListActivityCommand extends Command {
    private final boolean isDetailed;

    /**
     * Constructs instance of ListActivityCommand.
     *
     * @param isDetailed Whether the list should be detailed.
     */
    public ListActivityCommand(boolean isDetailed) {
        this.isDetailed = isDetailed;
    }

    /**
     * Lists the activities in either a detailed or summary format.
     *
     * @param data      Current data containing the activity list.
     * @return          The message containing listing of activities which will be shown to the user. The format is
     *                  based on the 'isDetailed' flag.
     */
    @Override
    public String[] execute(Data data) {
        ActivityList activities = data.getActivities();
        final int size = activities.size();

        if (size == 0) {
            return new String[]{Message.MESSAGE_EMPTY_ACTIVITY_LIST};
        }

        if (isDetailed) {
            return printDetailedList(activities, size);
        } else {
            return printList(activities, size);
        }
    }

    /**
     * Prints the list of activities.
     *
     * @param activities    The current activity list.
     * @param size          The size of the activity list.
     * @return              The message containing listing of activities which will be shown to the user.
     */
    public String[] printList(ActivityList activities, int size) {
        String[] output = new String[size + 2];
        output[0] = Message.MESSAGE_ACTIVITY_LIST;

        int index = 1;
        for (Activity activity : activities) {
            output[index++] = index-1 + "." + activity.toString();
        }

        output[index] = Message.MESSAGE_ACTIVITY_LIST_END;
        return output;
    }

    /**
     * Prints the detailed list of activities.
     *
     * @param activities    The current activity list.
     * @param size          The size of the activity list.
     * @return              The message containing listing of activities which will be shown to the user.
     */
    public String[] printDetailedList(ActivityList activities, int size) {
        String[] output = new String[size + 1];
        output[0] = Message.MESSAGE_ACTIVITY_LIST;
        int index = 1;
        for (Activity activity : activities) {
            output[index++] = activity.toDetailedString();
        }
        return output;
    }
}
