package athleticli.data.sleep;

import athleticli.data.Goal.TimeSpan;
import athleticli.exceptions.AthletiException;

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
    private Sleep sleepParse;

    @BeforeEach
    public void setup() throws AthletiException {
        sleepList = new SleepList();
        LocalDateTime dateSecond = LocalDateTime.now();
        LocalDateTime dateFirst = LocalDateTime.now().minusDays(1);
        
        sleepFirst = new Sleep(dateFirst, dateFirst.plusHours(8));
        sleepSecond = new Sleep(dateSecond, dateSecond.plusHours(8));
        sleepParse = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0),
                LocalDateTime.of(2023, 10, 18, 6, 0));
       
        sleepList.add(sleepFirst);
        sleepList.add(sleepSecond);
        sleepList.add(sleepParse);
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
        int actual = sleepList.getTotalSleepDuration(TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    @Test
    public void parse_sleep_parsed() throws AthletiException {
        String arguments = "start/2023-10-17 22:00 end/2023-10-18 06:00";
        Sleep expected = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0),
                LocalDateTime.of(2023, 10, 18, 6, 0));
        Sleep actual = sleepList.parse(arguments);
        assertEquals(expected.getStartDateTime(), actual.getStartDateTime());
        assertEquals(expected.getEndDateTime(), actual.getEndDateTime());
    }

    @Test
    public void unparse_sleep_unparsed() throws AthletiException {
        String expected = "start/2023-10-17T22:00 end/2023-10-18T06:00";
        Sleep actual = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0),
                LocalDateTime.of(2023, 10, 18, 6, 0));
        String actualString = sleepList.unparse(actual);
        assertEquals(expected, actualString);
    }
}
