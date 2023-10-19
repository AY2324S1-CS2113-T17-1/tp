package athleticli.commands.sleep;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Executes the edit sleep commands provided by the user.
 */
public class EditSleepCommand extends Command {

    private final Logger LOGGER = Logger.getLogger(EditSleepCommand.class.getName());
    private int index;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for EditSleepCommand.
     * @param index Index of the sleep to be edited.
     * @param from New start time of the sleep.
     * @param to New end time of the sleep.
     */
    public EditSleepCommand(int index, LocalDateTime from, LocalDateTime to) {
        this.index = index;
        this.from = from;
        this.to = to;

        assert from != null : "Start time cannot be null";
        assert to != null : "End time cannot be null";
        assert from.isBefore(to) : "Start time must be before end time";

        LOGGER.fine("Creating EditSleepCommand with index: " + index + " from: " + from + " and to: " + to);
    }
    
    /**
     * Edits the sleep record at the specified index.
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
        Sleep newSleep = new Sleep(from, to);
        sleepList.set(accessIndex, newSleep);

        String returnMessage = String.format(Message.MESSAGE_SLEEP_EDIT_RETURN, index);
        return new String[] {
            returnMessage,
            "original: " + oldSleep.toString(),
            "to new: " + newSleep.toString(),
        };

    }

}


