package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;

public class EditSleepCommandTest {

    private Data data;
    private Sleep sleep1;
    private Sleep sleep2;

    @BeforeEach
    public void setup() {
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
        EditSleepCommand command = new EditSleepCommand(1, LocalDateTime.of(2023, 10, 17, 23, 0), 
                                                        LocalDateTime.of(2023, 10, 18, 7, 0));
        String[] expected = {
            "Got it. I've changed this sleep record at index 1:",
            "original: [Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
                "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ",
            "to new: [Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 11:00 PM " +
                "| End Time: October 18, 2023 at 7:00 AM | Sleeping Duration: 8 Hours ",
        };
        
        assertArrayEquals(expected, command.execute(data));
    }

    @Test
    public void testExecuteWithInvalidIndex() {
        EditSleepCommand commandNegative = new EditSleepCommand(-1, LocalDateTime.of(2023, 10, 17, 23, 0), 
                                                                LocalDateTime.of(2023, 10, 18, 7, 0));
        assertThrows(AthletiException.class, () -> commandNegative.execute(data));

        EditSleepCommand commandZero = new EditSleepCommand(0, LocalDateTime.of(2023, 10, 17, 23, 0), 
                                                           LocalDateTime.of(2023, 10, 18, 7, 0));
        assertThrows(AthletiException.class, () -> commandZero.execute(data));

        EditSleepCommand commandBeyond = new EditSleepCommand(3, LocalDateTime.of(2023, 10, 17, 23, 0), 
                                                              LocalDateTime.of(2023, 10, 18, 7, 0));
        assertThrows(AthletiException.class, () -> commandBeyond.execute(data));
    }

}

