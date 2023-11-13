package athleticli.commands.sleep;

import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 *  Represents a command which deletes a sleep entry.
 */
public class DeleteSleepCommand extends Command {
    private final Logger logger = Logger.getLogger(DeleteSleepCommand.class.getName());    
    private final int index;

    /**
     * Constructor for DeleteSleepCommand.
     * 
     * @param index Index of the sleep to be deleted.
     */
    public DeleteSleepCommand(int index) {
        this.index = index;
        logger.fine("Creating DeleteSleepCommand with index: " + index);
    }

    /**
     * Deletes the sleep record at the specified index.
     * 
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     * @throws AthletiException If the index is out of bounds.
     */
    public String[] execute(Data data) throws AthletiException {
        SleepList sleeps = data.getSleeps();
        try {
            final Sleep sleep = sleeps.get(index-1);
            sleeps.remove(sleep);

            logger.info("Deleting sleep: " + sleep.toString());
            logger.info("Sleep count: " + sleeps.size());
            logger.info("Sleep list: " + sleeps.toString());

            return new String[]{
                Message.MESSAGE_SLEEP_DELETED, 
                sleep.toString(),
                String.format(Message.MESSAGE_SLEEP_COUNT, sleeps.size())
            };
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(Message.ERRORMESSAGE_SLEEP_DELETE_INDEX_OOBE);
        }
    } 
}
