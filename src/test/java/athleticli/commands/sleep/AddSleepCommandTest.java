package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import athleticli.data.Data;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.data.sleep.Sleep;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddSleepCommandTest {
    
    private Data data;
    private Sleep sleep;
    private AddSleepCommand addSleepCommand;
   
    @BeforeEach
    public void setup() throws AthletiException {
        data = new Data();
        sleep = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0), 
                          LocalDateTime.of(2023, 10, 18, 6, 0));
        addSleepCommand = new AddSleepCommand(sleep);
        data.setSleeps(new SleepList());
    }

    @Test
    public void testExecuteWithValidInput() {
        String[] expected = {
            "Well done! I've added this sleep record:",
            "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
            "You have tracked your first sleep record. This is just the beginning!"
        };
        String[] actual = addSleepCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExecuteCountingSleepRecords() {
        String[] expected = {
            "Well done! I've added this sleep record:",
            "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
            "You have tracked a total of 2 sleep records. Keep it up!"
        };
        addSleepCommand.execute(data);
        String[] actual2 = addSleepCommand.execute(data);
        assertArrayEquals(expected, actual2);
    }
}
