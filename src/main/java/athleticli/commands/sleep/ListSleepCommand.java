package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

public class ListSleepCommand extends Command {

    /**
     * Lists all the sleep records in the sleep list.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute (Data data) {
        SleepList sleepList = data.getSleeps();
        if (sleepList.size() == 0) {
            return new String[] {
                Message.MESSAGE_SLEEP_LIST_EMPTY
            };
        }
        return new String[] {
            Message.MESSAGE_SLEEP_LIST,
            sleepList.toString()
        };
    }
}
