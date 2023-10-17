package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
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
    public String[] execute(Data data) {
        SleepList sleepList = data.getSleeps();
        if (index > sleepList.size() || index < 1) {
            return new String[] {
                Message.MESSAGE_SLEEP_DELETE_INVALID_INDEX
            };
        }
        Sleep oldSleep = sleepList.get(index - 1);
        sleepList.remove(index - 1);
        
        String returnMessage = String.format(Message.MESSAGE_SLEEP_DELETE_RETURN, index, oldSleep.toString());
        return new String[] {
            returnMessage
        };

    }

}


