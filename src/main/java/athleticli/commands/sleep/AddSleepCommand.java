package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;

import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;

public class AddSleepCommand extends Command {

    private String from;
    private String to;

    public AddSleepCommand(String from, String to) {
        this.from = from;
        this.to = to;
    }
    
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


