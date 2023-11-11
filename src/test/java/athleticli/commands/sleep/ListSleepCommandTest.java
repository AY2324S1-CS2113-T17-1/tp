package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;

public class ListSleepCommandTest {
    private Data data;
    private Sleep sleep1;
    private Sleep sleep2;

    @BeforeEach
    public void setup() throws AthletiException {
        data = new Data();
        SleepList sleepList = new SleepList();
        sleep1 = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0), 
                          LocalDateTime.of(2023, 10, 18, 6, 0));
        sleep2 = new Sleep(LocalDateTime.of(2023, 10, 18, 22, 0), 
                          LocalDateTime.of(2023, 10, 19, 6, 0));
        sleepList.add(sleep1);
        sleepList.add(sleep2);
        data.setSleeps(sleepList);
    }

    @Test
    public void testExecuteWithRecords() {
        ListSleepCommand command = new ListSleepCommand();
        String[] expected = {
            "Here are the sleep records in your list:\n",
            "1. [Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
            "2. [Sleep] | Date: 2023-10-18 | Start Time: October 18, 2023 at 10:00 PM " +
                "| End Time: October 19, 2023 at 6:00 AM | Sleeping Duration: 8 Hours "
        };    
        String[] actual = command.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExecuteWithEmptyList() {
        data.setSleeps(new SleepList()); 
        ListSleepCommand command = new ListSleepCommand();
        String[] expected = {
            "You have no sleep records in your list."
        };
        String[] actual = command.execute(data);
        assertArrayEquals(expected, actual);
    }
}
