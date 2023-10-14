package athleticli.data.sleep;

import athleticli.data.sleep.SleepList;
import athleticli.data.sleep.Sleep;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class SleepListTest {
    @Test
    public void testToString() {
        SleepList sleepList = new SleepList();
        sleepList.add(new Sleep("10:00 PM", "6:00 AM"));
        sleepList.add(new Sleep("11:00 PM", "7:00 AM"));
        assertEquals("1. sleep from 10:00 PM to 6:00 AM\n2. sleep from 11:00 PM to 7:00 AM\n", sleepList.toString());
    }

    @Test
    public void testAddAndGet() {
        SleepList sleepList = new SleepList();
        Sleep sleep1 = new Sleep("10:00 PM", "6:00 AM");
        Sleep sleep2 = new Sleep("11:00 PM", "7:00 AM");
        sleepList.add(sleep1);
        sleepList.add(sleep2);
        assertEquals(sleep1, sleepList.get(0));
        assertEquals(sleep2, sleepList.get(1));
    }
}