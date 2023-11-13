package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDateTime;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;

public class FindSleepCommandTest {
    
    private Data data;
    private Sleep sleep1;
    private Sleep sleep2;
    private Sleep sleep3;

    @BeforeEach
    public void setup() throws AthletiException {
        data = new Data();
        SleepList sleepList = new SleepList();
        sleep1 = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0), 
                          LocalDateTime.of(2023, 10, 18, 6, 0));
        sleep2 = new Sleep(LocalDateTime.of(2023, 10, 18, 22, 0), 
                          LocalDateTime.of(2023, 10, 19, 6, 0));
        sleep3 = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0), 
                          LocalDateTime.of(2023, 10, 20, 6, 0));
        sleepList.add(sleep1);
        sleepList.add(sleep2);
        sleepList.add(sleep3);
        data.setSleeps(sleepList);
    }

    @Test
    public void testExecuteWithValidInput_findOneSleep() throws AthletiException {
        FindSleepCommand findSleepCommand = new FindSleepCommand(LocalDate.of(2023, 10, 18));
        String[] expected = {
            "I've found these sleeps:",
            "[Sleep] | Date: 2023-10-18 | Start Time: October 18, 2023 at 10:00 PM " +
                "| End Time: October 19, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
        };
        String[] actual = findSleepCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExecuteWithValidInput_findTwoSleeps() throws AthletiException {
        FindSleepCommand findSleepCommand = new FindSleepCommand(LocalDate.of(2023, 10, 17));
        String[] expected = {
            "I've found these sleeps:",
            "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
            "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 20, 2023 at 6:00 AM | Sleeping Duration: 2 Days 8 Hours ",
        };
        String[] actual = findSleepCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExecuteWithValidInput_findNoSleeps() throws AthletiException {
        FindSleepCommand findSleepCommand = new FindSleepCommand(LocalDate.of(2023, 10, 19));
        String[] expected = {
            "I've found these sleeps:",
        };
        String[] actual = findSleepCommand.execute(data);
        assertArrayEquals(expected, actual);
    }
}
