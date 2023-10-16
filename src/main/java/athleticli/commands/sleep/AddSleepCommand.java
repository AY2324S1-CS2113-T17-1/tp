package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;

import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;

/**
 * Executes the add sleep commands provided by the user.
 */
public class AddSleepCommand extends Command {

    private String from;
    private String to;

    /**
     * Constructor for AddSleepCommand.
     * @param from Start time of the sleep.
     * @param to End time of the sleep.
     */
    public AddSleepCommand(String from, String to) {
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
            "  " + from + " to " + to,
            "Now you have " + sleepList.size() + " sleep records in the list."
        };

    }

   
}


