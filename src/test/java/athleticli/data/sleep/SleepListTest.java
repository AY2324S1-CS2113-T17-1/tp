package athleticli.data.sleep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SleepListTest {

    private SleepList sleepList;
    private Sleep sleep1;
    private Sleep sleep2;

    @BeforeEach
    public void setup() {
        sleepList = new SleepList();
        sleep1 = new Sleep(LocalDateTime.of(2023, 10, 17, 22, 0), 
                          LocalDateTime.of(2023, 10, 18, 6, 0));
        sleep2 = new Sleep(LocalDateTime.of(2023, 10, 18, 22, 0), 
                          LocalDateTime.of(2023, 10, 19, 6, 0));
    }

    @Test
    public void testToStringWithEmptyList() {
        assertEquals("", sleepList.toString());
    }

    @Test
    public void testToStringWithOneSleepObject() {
        sleepList.add(sleep1);
        String expected = "1. sleep record from 17-10-2023 22:00 to 18-10-2023 06:00\n";
        assertEquals(expected, sleepList.toString());
    }

    @Test
    public void testToStringWithMultipleSleepObjects() {
        sleepList.add(sleep1);
        sleepList.add(sleep2);
        String expected = "1. sleep record from 17-10-2023 22:00 to 18-10-2023 06:00\n"
                        + "2. sleep record from 18-10-2023 22:00 to 19-10-2023 06:00\n";
        assertEquals(expected, sleepList.toString());
    }

    @Test
    public void testAddSleep() {
        sleepList.add(sleep1);
        assertEquals(1, sleepList.size());
        assertEquals(sleep1, sleepList.get(0));
    }

    @Test
    public void testRemoveSleep() {
        sleepList.add(sleep1);
        sleepList.add(sleep2);
        sleepList.remove(sleep1);
        assertEquals(1, sleepList.size());
        assertEquals(sleep2, sleepList.get(0));
    }
}
