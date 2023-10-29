package athleticli.data.activity;

import athleticli.data.Goal.TimeSpan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityListTest {

    private static final String CAPTION = "Sunday = Runday";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private ActivityList activityList;
    private Activity activityFirst;
    private Activity activitySecond;


    @BeforeEach
    void setUp() {
        activityList = new ActivityList();
        LocalDateTime dateSecond = LocalDateTime.now();
        LocalDateTime dateFirst = LocalDateTime.now().minusDays(1);
        activityFirst = new Activity(CAPTION, DURATION, DISTANCE, dateFirst);
        activitySecond = new Activity(CAPTION, DURATION, DISTANCE, dateSecond);
        activityList.add(activityFirst);
        activityList.add(activitySecond);
    }

    @Test
    void find() {
        assertEquals(activityList.find(LocalDate.now()).get(0), activitySecond);
        assertEquals(activityList.find(LocalDate.now().minusDays(1)).get(0), activityFirst);
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
        ArrayList<Activity> filteredList = activityList.filterByTimespan(TimeSpan.WEEKLY);
        assertEquals(filteredList.get(0), activitySecond);
        assertEquals(filteredList.get(1), activityFirst);
        filteredList = activityList.filterByTimespan(TimeSpan.DAILY);
        assertEquals(filteredList.get(0), activitySecond);
        assertEquals(filteredList.size(), 1);
    }

    @Test
    void getTotalDistance_activity_totalDistance() {
        int expected = 2 * DISTANCE;
        int actual = activityList.getTotalDistance(Activity.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    @Test
    void getTotalDistance_run_zero() {
        int expected = 0;
        int actual = activityList.getTotalDistance(Run.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    @Test
    void getTotalDuration_activity_totalTime() {
        int expected = 2 * DURATION.toSecondOfDay();
        int actual = activityList.getTotalDuration(Activity.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    @Test
    void getTotalDuration_run_zero() {
        int expected = 0;
        int actual = activityList.getTotalDuration(Run.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }
}
