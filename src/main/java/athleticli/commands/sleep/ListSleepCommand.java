package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;

import athleticli.data.sleep.SleepList;

public class ListSleepCommand extends Command {

    public String[] execute (Data data) {
        SleepList sleepList = data.getSleeps();
        return new String[] {
            "Here are the sleep records in your list:" + "\n",
            sleepList.toString()
        };
    }
}
