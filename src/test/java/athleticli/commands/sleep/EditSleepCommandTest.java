package athleticli.commands.sleep;


import athleticli.data.sleep.SleepList;
import athleticli.data.sleep.Sleep;
import athleticli.data.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class EditSleepCommandTest {
    @Test
    public void testExecute() {
        Data data = new Data();
        SleepList sleepList = data.getSleeps();
        sleepList.add(new Sleep("08:00", "10:00"));
        sleepList.add(new Sleep("09:00", "11:00"));
        
        // Execute command
        EditSleepCommand command = new EditSleepCommand(1, "10:00", "20:00");
        String[] output = command.execute(data);

        // Check that sleep was edited
        assertEquals(2, sleepList.size());
        assertEquals("sleep from 10:00 to 20:00", sleepList.get(0).toString());

        // Check output message
        assertEquals("Got it. I've changed this sleep record at index 1:", output[0]);
        assertEquals("original: sleep from 08:00 to 10:00", output[1]);
        assertEquals("to new: sleep from 10:00 to 20:00", output[2]);
    }
}
