package athleticli.commands.sleep;

import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

public class ListSleepCommand extends Command {

    private static final Logger logger = Logger.getLogger(ListSleepCommand.class.getName());

    /**
     * Lists all the sleep records in the sleep list.
     *
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) {
        logger.info("Executing ListSleepCommand");
        SleepList sleeps = data.getSleeps();
        final int size = sleeps.size();
        if (size == 0) {
            logger.warning("Sleep list is empty");
            return new String[] {
                Message.MESSAGE_SLEEP_LIST_EMPTY
            };
        }

        return printList(sleeps, size);
    }

    public String[] printList(SleepList sleeps, int size) {
        logger.fine("Printing sleep list");
        String[] returnString = new String[size+1];
        returnString[0] = Message.MESSAGE_SLEEP_LIST;
        for (int i = 0; i < size; i++) {
            assert sleeps.get(i) != null : "Sleep record cannot be null";
            returnString[i+1] = (i + 1) + ". " + sleeps.get(i).toString();
        }
        
        return returnString;
    }
    
}
