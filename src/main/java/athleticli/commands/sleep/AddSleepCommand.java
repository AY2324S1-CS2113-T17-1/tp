package athleticli.commands.sleep;

import java.time.LocalDateTime;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

/**
 * Executes the add sleep commands provided by the user.
 */
public class AddSleepCommand extends Command {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for AddSleepCommand.
     * @param from Start time of the sleep.
     * @param to End time of the sleep.
     */
    public AddSleepCommand(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Adds the sleep record to the sleep list.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) {
        SleepList sleepList = data.getSleeps();
        Sleep newSleep = new Sleep(from, to);
        sleepList.add(newSleep);
        String returnMessage2 = String.format(Message.MESSAGE_SLEEP_ADD_RETURN_2, sleepList.size());
        return new String[] {
            Message.MESSAGE_SLEEP_ADD_RETURN_1,
            newSleep.toString(),
            returnMessage2
        };
    }
}
