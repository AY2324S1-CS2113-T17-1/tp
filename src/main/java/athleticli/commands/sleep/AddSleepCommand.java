package athleticli.commands.sleep;

import java.util.logging.Logger;
import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

/**
 * Represents a command which adds a sleep entry.
 */
public class AddSleepCommand extends Command {
    private final Logger logger = Logger.getLogger(AddSleepCommand.class.getName());
    private final Sleep sleep;

    /**
     * Constructor for AddSleepCommand.
     * 
     * @param sleep Sleep to be added.
     */
    public AddSleepCommand(Sleep sleep) {
        this.sleep = sleep;
        logger.fine("Creating AddSleepCommand with sleep: " + sleep.toString());
        assert sleep.getStartDateTime() != null : "Start time cannot be null";
        assert sleep.getEndDateTime() != null : "End time cannot be null";
        assert sleep.getStartDateTime().isBefore(sleep.getEndDateTime()) : "Start time must be before end time";
    }

    /**
     * Adds the sleep record to the sleep list. Sorts the sleep list after adding.
     * 
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        SleepList sleeps = data.getSleeps();
        sleeps.add(this.sleep);
        sleeps.sort();
        int size = sleeps.size();

        logger.info("Added sleep: " + this.sleep.toString());
        logger.info("Sleep count: " + sleeps.size());
        logger.info("Sleep list: " + sleeps.toString());

        String countMessage;
        if (size > 1) {
            countMessage = String.format(Message.MESSAGE_SLEEP_COUNT, size);
        } else {
            countMessage = String.format(Message.MESSAGE_SLEEP_FIRST, size);
        }
        return new String[] {Message.MESSAGE_SLEEP_ADDED, this.sleep.toString(), countMessage};
    }
}
