package athleticli.commands.sleep;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.data.Data;
import athleticli.data.sleep.SleepList;

public class AddSleepCommandTest {

    private Data data;

    @BeforeEach
    public void setup() {
        data = new Data();
        data.setSleeps(new SleepList());
    }

    @Test
    public void testExecuteWithValidInput() {
        LocalDateTime from = LocalDateTime.of(2023, 10, 17, 22, 0);
        LocalDateTime to = LocalDateTime.of(2023, 10, 18, 6, 0);
        AddSleepCommand command = new AddSleepCommand(from, to);

        String[] expected = {
            "Got it. I've added this sleep record:",
            "sleep record from 2023-10-17 22:10 to 2023-10-18 06:10",
            "Now you have 1 sleep records in the list."
        };
        
        assertArrayEquals(expected, command.execute(data));
    }

    @Test
    public void testExecuteCountingSleepRecords() {
        LocalDateTime from1 = LocalDateTime.of(2023, 10, 17, 22, 0);
        LocalDateTime to1 = LocalDateTime.of(2023, 10, 18, 6, 0);
        AddSleepCommand command1 = new AddSleepCommand(from1, to1);
        command1.execute(data); // Add first sleep record

        LocalDateTime from2 = LocalDateTime.of(2023, 10, 18, 22, 0);
        LocalDateTime to2 = LocalDateTime.of(2023, 10, 19, 6, 0);
        AddSleepCommand command2 = new AddSleepCommand(from2, to2);

        String[] expected = {
            "Got it. I've added this sleep record:",
            "sleep record from 2023-10-18 22:10 to 2023-10-19 06:10",
            "Now you have 2 sleep records in the list."
        };
        
        assertArrayEquals(expected, command2.execute(data));
    }
}
