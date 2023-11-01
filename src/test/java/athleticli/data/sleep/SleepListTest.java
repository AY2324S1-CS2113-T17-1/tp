package athleticli.data.sleep;

import athleticli.data.Goal.TimeSpan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepListTest {

    private SleepList sleepList;
    private Sleep sleepFirst;
    private Sleep sleepSecond;

    @BeforeEach
    public void setup() {
        sleepList = new SleepList();
        LocalDateTime dateSecond = LocalDateTime.now();
        LocalDateTime dateFirst = LocalDateTime.now().minusDays(1);
        sleepFirst = new Sleep(dateFirst, dateFirst.plusHours(8));
        sleepSecond = new Sleep(dateSecond, dateSecond.plusHours(8));
        sleepList.add(sleepFirst);
        sleepList.add(sleepSecond);
    }

    @Test
    public void testFind() {
        assertEquals(sleepList.find(LocalDate.now()).get(0), sleepSecond);
        assertEquals(sleepList.find(LocalDate.now().minusDays(1)).get(0), sleepFirst);
    }

    @Test
    public void testSort() {
        sleepList.sort();
        assertEquals(sleepList.get(0), sleepSecond);
        assertEquals(sleepList.get(1), sleepFirst);
    }

    @Test
    public void testFilterByTimespan() {
        sleepList.sort();
        ArrayList<Sleep> result = sleepList.filterByTimespan(TimeSpan.WEEKLY);
        assertEquals(result.get(0), sleepSecond);
        assertEquals(result.get(1), sleepFirst);
        result = sleepList.filterByTimespan(TimeSpan.DAILY);
        assertEquals(result.get(0), sleepSecond);
        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetTotalSleepDuration() {
        int expected = 8 * 60 * 60 * 2;
        int actual = sleepList.getTotalSleepDuration(Sleep.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }
}
