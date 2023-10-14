package athleticli.data.sleep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SleepTest {
    @Test
    public void testSleepToString() {
        Sleep sleep = new Sleep("10:00 PM", "6:00 AM");
        assertEquals("sleep from 10:00 PM to 6:00 AM", sleep.toString());
    }

    

}
