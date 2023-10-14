package athleticli.commands.sleep;


import athleticli.commands.sleep.AddSleepCommand;
import athleticli.data.sleep.SleepList;
import athleticli.data.sleep.Sleep;
import athleticli.data.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class AddSleepCommandTest {
    @Test
    public void testExecute() {
        // Create a Data object with an empty SleepList
        Data data = new Data();
        SleepList sleepList = data.getSleeps();
        assertEquals(0, sleepList.size());

        // Create an AddSleepCommand and execute it
        AddSleepCommand command = new AddSleepCommand("2021-01-01 23:00", "2021-01-02 07:00");
        String[] result = command.execute(data);

        // Check that the output is correct
        String[] expected = {
            "Got it. I've added this sleep record:",
            "  2021-01-01 23:00 to 2021-01-02 07:00",
            "Now you have 1 sleep records in the list."
        };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }

        // Check that the SleepList now contains the new Sleep object
        assertEquals(1, sleepList.size());
        Sleep newSleep = sleepList.get(0);
        assertEquals("sleep from 2021-01-01 23:00 to 2021-01-02 07:00", newSleep.toString());
    }
}