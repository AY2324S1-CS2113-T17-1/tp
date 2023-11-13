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
    private Sleep sleep2;
    private AddSleepCommand addSleepCommand;
    private AddSleepCommand addSleepCommand2;
   
    @BeforeEach
    public void setup() throws AthletiException {
        data = new Data();
        sleep = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0), 
                          LocalDateTime.of(2023, 10, 18, 6, 0));
        sleep2 = new Sleep(LocalDateTime.of(2023, 10, 18, 22, 0),
                          LocalDateTime.of(2023, 10, 19, 6, 0));
        addSleepCommand = new AddSleepCommand(sleep);
        addSleepCommand2 = new AddSleepCommand(sleep2);
        data.setSleeps(new SleepList());
    }

    @Test
    public void testExecuteWithValidInput() throws AthletiException {
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
    public void testExecuteCountingSleepRecords() throws AthletiException {
        String[] expected = {
            "Well done! I've added this sleep record:",
            "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
            "You have tracked a total of 2 sleep records. Keep it up!"
        };
        addSleepCommand2.execute(data);
        String[] actual2 = addSleepCommand.execute(data);
        assertArrayEquals(expected, actual2);
    }
}
