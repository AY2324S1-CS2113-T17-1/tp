package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

public class ListSleepCommand extends Command {

    /**
     * Lists all the sleep records in the sleep list.
     *
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute (Data data) {
        SleepList sleeps = data.getSleeps();
        final int size = sleeps.size();
        if (size == 0) {
            return new String[] {
                Message.MESSAGE_SLEEP_LIST_EMPTY
            };
        }

        return printList(sleeps, size);
    }

    public String[] printList(SleepList sleeps, int size) {
        String[] returnString = new String[size+1];
        returnString[0] = Message.MESSAGE_SLEEP_LIST;
        for (int i = 0; i < size; i++) {
            returnString[i+1] = (i + 1) + ". " + sleeps.get(i).toString();
        }
        return returnString;
    }
}
