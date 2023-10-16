package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;

import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Executes the add sleep commands provided by the user.
 */
public class AddSleepCommand extends Command {

    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

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
        
        return new String[] {
            "Got it. I've added this sleep record:",
            "  " + from.format(FORMATTER) + " to " + to.format(FORMATTER),
            "Now you have " + sleepList.size() + " sleep records in the list."
        };
    }
}
