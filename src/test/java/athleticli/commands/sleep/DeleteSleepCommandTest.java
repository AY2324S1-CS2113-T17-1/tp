package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;

import java.time.LocalDateTime;

public class DeleteSleepCommandTest {

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
    public void testExecuteWithValidIndex() throws AthletiException {
        DeleteSleepCommand command = new DeleteSleepCommand(1);
        String[] expected = {
            "Gotcha, I've deleted this sleep record:",
            "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
            "You have tracked a total of 1 sleep records. Keep it up!"
        };
        String[] actual = command.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExecuteWithInvalidIndex() throws AthletiException {
        DeleteSleepCommand commandNegative = new DeleteSleepCommand(-1);
        assertThrows(AthletiException.class, () -> commandNegative.execute(data));

        DeleteSleepCommand commandZero = new DeleteSleepCommand(0);
        assertThrows(AthletiException.class, () -> commandZero.execute(data));

        DeleteSleepCommand commandBeyond = new DeleteSleepCommand(3); // Only 2 records in the list.
        assertThrows(AthletiException.class, () -> commandBeyond.execute(data));
    }

    @Test
    public void testExecuteWithEmptyList() throws AthletiException {
        data.setSleeps(new SleepList()); // Empty list
        DeleteSleepCommand command = new DeleteSleepCommand(1);
        assertThrows(AthletiException.class, () -> command.execute(data));
    }

}
