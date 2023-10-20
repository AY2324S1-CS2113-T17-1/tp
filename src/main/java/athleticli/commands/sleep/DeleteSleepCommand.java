package athleticli.commands.sleep;

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

    /**
     * Constructor for DeleteSleepCommand.
     * @param index Index of the sleep to be deleted.
     */
    public DeleteSleepCommand(int index) {
        this.index = index;
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
        Sleep oldSleep = sleepList.get(accessIndex);
        sleepList.remove(accessIndex);
        
        String returnMessage = String.format(Message.MESSAGE_SLEEP_DELETE_RETURN, index, oldSleep.toString());
        return new String[] {
            returnMessage
        };

    }

}


