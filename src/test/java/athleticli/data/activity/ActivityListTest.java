package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityListTest {

    private static final String CAPTION = "Sunday = Runday";
    private static final int DURATION = 84;
    private static final int DISTANCE = 18120;
    private ActivityList activityList;
    private LocalDateTime dateFirst;
    private LocalDateTime dateSecond;
    private Activity activityFirst;
    private Activity activitySecond;


    @BeforeEach
    void setUp() {
        activityList = new ActivityList();
        dateSecond = LocalDateTime.of(2023, 10, 10, 23, 21);
        dateFirst = LocalDateTime.of(2023, 10, 9, 23, 21);
        activityFirst = new Activity(CAPTION, DURATION, DISTANCE, dateFirst);
        activitySecond = new Activity(CAPTION, DURATION, DISTANCE, dateSecond);
        activityList.add(activityFirst);
        activityList.add(activitySecond);
    }

    @Test
    void find() {
        assertEquals(activityList.find(LocalDate.of(2023, 10, 10)).get(0), activitySecond);
        assertEquals(activityList.find(LocalDate.of(2023, 10, 9)).get(0), activityFirst);
    }

    @Test
    void sort() {
        activityList.sort();
        assertEquals(activityList.get(0), activitySecond);
        assertEquals(activityList.get(1), activityFirst);
    }

    @Test
    void filterByTimespan() {
        activityList.sort();
        ArrayList<Object> filteredList = activityList.filterByTimespan(LocalDate.of(2023, 10, 9),
                LocalDate.of(2023, 10, 9));
        assertEquals(filteredList.get(0), activityFirst);
        filteredList = activityList.filterByTimespan(LocalDate.of(2023, 10, 9), LocalDate.of(2023, 10, 10));
        assertEquals(filteredList.get(0), activitySecond);
    }
}
