package athleticli.commands.sleep;

import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

/**
 * Represents a command which lists all the sleep records.
 */
public class ListSleepCommand extends Command {
    private static final Logger logger = Logger.getLogger(ListSleepCommand.class.getName());

    /**
     * Lists all the sleep records in the sleep list.
     *
     * @param data The current data containing the sleep list.
     * @return The message array which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        logger.info("Executing ListSleepCommand");
        SleepList sleeps = data.getSleeps();
        final int size = sleeps.size();

        if (size == 0) {
            logger.fine("Sleep list is empty");
            return new String[] {
                Message.MESSAGE_SLEEP_LIST_EMPTY
            };
        }
        
        return printList(sleeps, size);
    }

    /**
     * Prints the list of sleep records.
     * 
     * @param sleeps The current sleep list.
     * @param size The size of the sleep list.
     * @return The message containing list of sleep records which will be shown to the user.
     */
    public String[] printList(SleepList sleeps, int size) {
        logger.fine("Printing sleep list");
        logger.info("Sleep count: " + sleeps.size());
        logger.info("Sleep list: " + sleeps.toString());

        String[] output = new String[size+1];
        output[0] = Message.MESSAGE_SLEEP_LIST;
        for (int i = 0; i < size; i++) {
            assert sleeps.get(i) != null : "Sleep record cannot be null";
            output[i+1] = (i + 1) + ". " + sleeps.get(i).toString();
        }
        return output;
    }
}
