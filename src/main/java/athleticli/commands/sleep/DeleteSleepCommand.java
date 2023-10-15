package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;

import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;

public class DeleteSleepCommand extends Command {

    private int index;

    public DeleteSleepCommand(int index) {
        this.index = index;
    }
    
    public String[] execute(Data data) {
        SleepList sleepList = data.getSleeps();
        if (index > sleepList.size() || index < 1) {
            return new String[] {
                "Invalid index. Please enter a valid index."
            };
        }
        Sleep oldSleep = sleepList.get(index - 1);
        sleepList.remove(index - 1);
        
        return new String[] {
            "Got it. I've deleted this sleep record at index " + index + ": " +  oldSleep.toString()
        };

    }

   
}


