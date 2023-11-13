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
    public void setup() throws AthletiException {
        data = new Data();
        SleepList sleepList = new SleepList();
        sleep1 = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0), 
                          LocalDateTime.of(2023, 10, 18, 6, 0));
        sleep2 = new Sleep(LocalDateTime.of(2023, 10, 18, 22, 0), 
                          LocalDateTime.of(2023, 10, 19, 6, 0));
        sleepList.add(sleep1);
        data.setSleeps(sleepList);
    }

    @Test
    public void testExecuteWithValidIndex() throws AthletiException {
        EditSleepCommand command = new EditSleepCommand(1, sleep2);
        String[] expected = {
            "Alright, I've changed this sleep record:",
            "original: " + sleep1.toString(),
            "new: " + sleep2.toString(),
        };
        assertArrayEquals(expected, command.execute(data));
    }

    @Test
    public void testExecuteWithInvalidIndex() {
        EditSleepCommand commandNegative = new EditSleepCommand(-1, sleep1);
        assertThrows(AthletiException.class, () -> commandNegative.execute(data));

        EditSleepCommand commandZero = new EditSleepCommand(0, sleep1);
        assertThrows(AthletiException.class, () -> commandZero.execute(data));

        EditSleepCommand commandBeyond = new EditSleepCommand(3, sleep1); // Only 2 records in the list.
        assertThrows(AthletiException.class, () -> commandBeyond.execute(data));
    }
}

