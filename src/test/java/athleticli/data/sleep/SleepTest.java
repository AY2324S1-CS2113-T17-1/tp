package athleticli.data.sleep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SleepTest {

    private LocalDateTime from;
    private LocalDateTime to;
    private Sleep sleep;

    @BeforeEach
    public void setup() {
        from = LocalDateTime.of(2023, 10, 17, 22, 0);
        to = LocalDateTime.of(2023, 10, 18, 6, 0);
        sleep = new Sleep(from, to);
    }

    @Test
    public void testToString() {
        Sleep sleep = new Sleep(from, to);
        String expected = "[Sleep] | Date: 2023-10-17 | Start Time: October 17, 2023 at 10:00 PM | End Time: October 18, 2023 at 6:00 AM | Sleeping Duration: 8 Hours ";
        assertEquals(expected, sleep.toString());
    }

    @Test
    public void testCalculateSleepingDuration() {
        assertEquals(8, sleep.getSleepingTime().getHour());
        assertEquals(0, sleep.getSleepingTime().getMinute());
    }

    @Test
    public void testCalculateSleepDate() {
        LocalDateTime sleepBefore6AM = LocalDateTime.of(2023, 10, 18, 5, 0);
        Sleep sleepEarly = new Sleep(sleepBefore6AM, to);
        assertEquals(sleepBefore6AM.toLocalDate().minusDays(1), sleepEarly.getSleepDate());

        LocalDateTime sleepAfter6AM = LocalDateTime.of(2023, 10, 17, 7, 0);
        Sleep sleepLate = new Sleep(sleepAfter6AM, to);
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
        assertEquals("End Time: October 18, 2023 at 6:00 AM", sleep.generateToDateTimeStringOutput());
    }

    @Test
    public void testGenerateSleepDateStringOutput() {
        assertEquals("Date: 2023-10-17", sleep.generateSleepDateStringOutput());
    }

    @Test
    public void testToDetailedString() {
        String expectedDetail = "| ---------- | ------------------------------ |\n" + 
                "| Date       | 2023-10-17                     |\n" + 
                "| Duration   | Sleeping Duration: 8 Hours     |\n" + 
                "| From       | October 17, 2023 at 10:00 PM   |\n" + 
                "| To         | October 18, 2023 at 6:00 AM    |\n";
        assertEquals(expectedDetail, sleep.toDetailedString());
    }

}
