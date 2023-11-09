package athleticli.commands.sleep;

import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 *  Executes the delete sleep command provided by the user.
 */
public class DeleteSleepCommand extends Command {
    private final int index;
    private final Logger logger = Logger.getLogger(DeleteSleepCommand.class.getName());

    /**
     * Constructor for DeleteSleepCommand.
     * @param index Index of the sleep to be deleted.
     */
    public DeleteSleepCommand(int index) {
        this.index = index;
        logger.fine("Creating DeleteSleepCommand with index: " + index);
    }

    /**
     * Deletes the sleep record at the specified index.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        SleepList sleeps = data.getSleeps();
        try {
            final Sleep sleep = sleeps.get(index-1);
            logger.info("Deleting sleep: " + sleep.toString());
            logger.info("Sleep count: " + sleeps.size());
            logger.info("Sleep list: " + sleeps.toString());
            sleeps.remove(sleep);
            assert index >= 0 : "Access index cannot be less than 0";
            assert index < sleeps.size() : "Index cannot be more than size of sleep list";
            return new String[]{Message.MESSAGE_SLEEP_DELETED, sleep.toString(),
                    String.format(Message.MESSAGE_SLEEP_COUNT, sleeps.size())};
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(Message.ERRORMESSAGE_SLEEP_DELETE_INDEX_OOBE);
        }
    }
}