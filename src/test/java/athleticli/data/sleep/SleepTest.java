package athleticli.data.sleep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SleepTest {

    private LocalDateTime from;
    private LocalDateTime to;

    @BeforeEach
    public void setup() {
        from = LocalDateTime.of(2023, 10, 17, 22, 0); // 17-10-2023 22:00
        to = LocalDateTime.of(2023, 10, 18, 6, 0);   // 18-10-2023 06:00
    }

    @Test
    public void testToString() {
        Sleep sleep = new Sleep(from, to);
        String expected = "sleep record from 2023-10-17 22:10 to 2023-10-18 06:10";
        assertEquals(expected, sleep.toString());
    }

}
