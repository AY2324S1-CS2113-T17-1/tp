package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;

import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;

public class EditSleepCommand extends Command {

    private int index;
    private String from;
    private String to;

    public EditSleepCommand(int index, String from, String to) {
        this.index = index;
        this.from = from;
        this.to = to;
    }
    
    public String[] execute(Data data) {
        SleepList sleepList = data.getSleeps();
        Sleep oldSleep = sleepList.get(index-1);
        Sleep newSleep = new Sleep(from, to);
        sleepList.set(index-1, newSleep);
        
        return new String[] {
            "Got it. I've changed this sleep record at index " + index + ":",
            "original: " + oldSleep.toString(),
            "to new: " + newSleep.toString(),
        };

    }

   
}


