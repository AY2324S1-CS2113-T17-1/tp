package athleticli.data.sleep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import athleticli.exceptions.AthletiException;

public class SleepTest {

    private static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2023, 10, 17, 22, 0);
    private static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2023, 10, 18, 6, 0);
    private Sleep sleep;

    @BeforeEach
    public void setup() throws AthletiException {
        sleep = new Sleep(START_DATE_TIME, END_DATE_TIME);
    }

    @Test
    public void testToString() {
        String expected = "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM " +
            "| End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ";
        assertEquals(expected, sleep.toString());
    }

    @Test
    public void testCalculateSleepingDuration() {
        assertEquals(8, sleep.getSleepingDuration().toHours());
    }

    @Test
    public void testCalculateSleepDate() throws AthletiException {
        LocalDateTime sleepBefore6AM = LocalDateTime.of(2023, 10, 18, 5, 0);
        Sleep sleepEarly = new Sleep(sleepBefore6AM, END_DATE_TIME);
        assertEquals(sleepBefore6AM.toLocalDate().minusDays(1), sleepEarly.getSleepDate());

        LocalDateTime sleepAfter6AM = LocalDateTime.of(2023, 10, 17, 7, 0);
        Sleep sleepLate = new Sleep(sleepAfter6AM, END_DATE_TIME);
        assertEquals(sleepAfter6AM.toLocalDate(), sleepLate.getSleepDate());
    }

    @Test
    public void testGenerateSleepingDurationStringOutput() {
        assertEquals("Sleeping Duration: 8 Hours ", sleep.generateSleepingDurationStringOutput());
    }

    @Test
    public void testGenerateStartDateTimeStringOutput() {
        assertEquals("Start Time: October 17, 2023 at 10:00 PM", sleep.generateStartDateTimeStringOutput());
    }

    @Test
    public void testGenerateToDateTimeStringOutput() {
        assertEquals("End Time: October 18, 2023 at 6:00 AM", sleep.generateEndDateTimeStringOutput());
    }

    @Test
    public void testGenerateSleepDateStringOutput() {
        assertEquals("Date: 2023-10-17", sleep.generateSleepDateStringOutput());
    }
}
