package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;

import java.time.LocalDateTime;

public class DeleteSleepCommandTest {

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
    public void testExecuteWithValidIndex() {
        DeleteSleepCommand command = new DeleteSleepCommand(1);
        String[] expected = {
            "Got it. I've deleted this sleep record at index 1: sleep record from 17-10-2023 22:00 to 18-10-2023 06:00"
        };
        assertArrayEquals(expected, command.execute(data));
    }

    @Test
    public void testExecuteWithInvalidIndex() {
        DeleteSleepCommand commandNegative = new DeleteSleepCommand(-1);
        String[] expectedNegative = { "Invalid index. Please enter a valid index." };
        assertArrayEquals(expectedNegative, commandNegative.execute(data));

        DeleteSleepCommand commandZero = new DeleteSleepCommand(0);
        String[] expectedZero = { "Invalid index. Please enter a valid index." };
        assertArrayEquals(expectedZero, commandZero.execute(data));

        DeleteSleepCommand commandBeyond = new DeleteSleepCommand(3); // Only 2 records in the list.
        String[] expectedBeyond = { "Invalid index. Please enter a valid index." };
        assertArrayEquals(expectedBeyond, commandBeyond.execute(data));
    }

    @Test
    public void testExecuteWithEmptyList() {
        data.setSleeps(new SleepList()); // Empty list
        DeleteSleepCommand command = new DeleteSleepCommand(1);
        String[] expected = { "Invalid index. Please enter a valid index." };
        assertArrayEquals(expected, command.execute(data));
    }

}
