package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.SleepGoalList;
import athleticli.ui.Message;

import java.util.logging.Logger;

/**
 * Represents a command which lists all the sleep goals.
 */
public class ListSleepGoalCommand extends Command {
    private static final Logger logger = Logger.getLogger(ListSleepGoalCommand.class.getName());

    /**
     * Constructor for ListSleepCommand.
     */
    public ListSleepGoalCommand() {
    }

    /**
     * Lists the sleep goals.
     *
     * @param data The current data containing the sleep goal list.
     * @return The message containing listing of sleep goals which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        SleepGoalList sleepGoals = data.getSleepGoals();
        int size = sleepGoals.size();
        String[] output = new String[size + 1];
        output[0] = Message.MESSAGE_SLEEP_GOAL_LIST;
        for (int i = 0; i < sleepGoals.size(); i++) {
            output[i + 1] = (i + 1) + ". " + sleepGoals.get(i).toString(data);
        }
        return output;
    }
}
