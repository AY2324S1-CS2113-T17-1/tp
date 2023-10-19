package athleticli.commands.sleep;

import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 *  Executes the delete sleep commands provided by the user.
 */
public class DeleteSleepCommand extends Command {

    private int index;
    private static final Logger LOGGER = Logger.getLogger(DeleteSleepCommand.class.getName());

    /**
     * Constructor for DeleteSleepCommand.
     * @param index Index of the sleep to be deleted.
     */
    public DeleteSleepCommand(int index) {
        this.index = index;
        LOGGER.fine("Creating DeleteSleepCommand with index: " + index);
    }

    /**
     * Deletes the sleep record at the specified index.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        SleepList sleepList = data.getSleeps();

        //accessIndex is the index of the sleep in the list accounting for zero-indexing
        int accessIndex = index - 1; 
        if (accessIndex < 0 || accessIndex >= sleepList.size()) {
            throw new AthletiException(Message.ERRORMESSAGE_SLEEP_EDIT_INDEX_OOBE);
        }
        assert accessIndex >= 0 : "Index cannot be less than 0";
        assert accessIndex < sleepList.size() : "Index cannot be more than size of list";

        Sleep oldSleep = sleepList.get(accessIndex);
        sleepList.remove(accessIndex);
        LOGGER.fine("Deleted sleep: " + oldSleep);
        
        String returnMessage = String.format(Message.MESSAGE_SLEEP_DELETE_RETURN, index, oldSleep.toString());
        return new String[] {
            returnMessage
        };

    }

}


