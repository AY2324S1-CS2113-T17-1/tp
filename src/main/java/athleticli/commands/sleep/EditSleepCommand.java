package athleticli.commands.sleep;

import java.time.LocalDateTime;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

/**
 * Executes the edit sleep commands provided by the user.
 */
public class EditSleepCommand extends Command {

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
    }
    
    /**
     * Edits the sleep record at the specified index.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) {
        SleepList sleepList = data.getSleeps();
        Sleep oldSleep = sleepList.get(index - 1);
        Sleep newSleep = new Sleep(from, to);
        sleepList.set(index - 1, newSleep);

        String returnMessage = String.format(Message.MESSAGE_SLEEP_EDIT_RETURN, index);
        
        return new String[] {
            returnMessage,
            "original: " + oldSleep.toString(),
            "to new: " + newSleep.toString(),
        };

    }

   
}


