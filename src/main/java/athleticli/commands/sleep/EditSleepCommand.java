package athleticli.commands.sleep;

import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Executes the edit sleep command provided by the user.
 */
public class EditSleepCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditSleepCommand.class.getName());
    private final int index;
    private final Sleep newSleep;

    /**
     * Constructor for EditSleepCommand.
     * @param index Index of the sleep to be edited.
     * @param from New start time of the sleep.
     * @param to New end time of the sleep.
     */
    public EditSleepCommand(int index, Sleep newSleep) {
        this.index = index;
        this.newSleep = newSleep;
        logger.fine("Creating EditSleepCommand with index: " + index);
    }
    
    /**
     * Edits the sleep record at the specified index.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        SleepList sleeps = data.getSleeps();
        try {
            final Sleep oldSleep = sleeps.get(index-1);
            sleeps.set(index-1, newSleep);
            logger.info("Activity at index " + index + " successfully edited");
            return new String[]{Message.MESSAGE_SLEEP_EDITED, 
                "original: " + oldSleep,
                "new: " + newSleep};
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(Message.ERRORMESSAGE_SLEEP_EDIT_INDEX_OOBE);
        }
    }
}
