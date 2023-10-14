package athleticli.commands.sleep;

import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteSleepCommandTest {

    @Test
    public void execute_validIndex_success() {
        Data data = new Data();
        SleepList sleepList = data.getSleeps();
        sleepList.add(new Sleep("08:00", "10:00"));
        sleepList.add(new Sleep("09:00", "11:00"));
        
        // Execute command
        DeleteSleepCommand command = new DeleteSleepCommand(1);
        String[] output = command.execute(data);

        // Check that sleep was deleted
        assertEquals(1, sleepList.size());
        assertEquals("sleep from 09:00 to 11:00", sleepList.get(0).toString());

        // Check output message
        assertEquals("Got it. I've deleted this sleep record at index 1: sleep from 08:00 to 10:00", output[0]);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Data data = new Data();
        SleepList sleepList = data.getSleeps();
        sleepList.add(new Sleep("08:00", "10:00"));

        // Execute command
        DeleteSleepCommand command = new DeleteSleepCommand(2);
        String[] output = command.execute(data);

        // Check that sleep was not deleted
        assertEquals(1, sleepList.size());

        // Check output message
        assertEquals("Invalid index. Please enter a valid index.", output[0]);
    }
}
