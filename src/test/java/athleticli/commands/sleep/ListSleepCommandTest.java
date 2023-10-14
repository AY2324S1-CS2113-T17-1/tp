package athleticli.commands.sleep;

import athleticli.data.sleep.SleepList;
import athleticli.data.sleep.Sleep;
import athleticli.data.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class ListSleepCommandTest {
    @Test
    public void testExecute() {
        // Create a Data object with an empty SleepList
        Data data = new Data();
        SleepList sleepList = data.getSleeps();
        assertEquals(0, sleepList.size());

        sleepList.add(new Sleep("10:00 PM", "6:00 AM"));
        sleepList.add(new Sleep("11:00 PM", "7:00 AM"));
        sleepList.add(new Sleep("12:00 PM", "8:00 AM"));

        // Create an ListSleepCommand and execute it
        ListSleepCommand command = new ListSleepCommand();
        String[] result = command.execute(data);
        

        assertEquals("Here are the sleep records in your list:" + "\n", result[0]);
        assertEquals(sleepList.toString(), result[1]);
    }
}