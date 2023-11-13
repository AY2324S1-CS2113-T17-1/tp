package athleticli.data.activity;

import athleticli.data.Goal.TimeSpan;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the ActivityList class.
 */
class ActivityListTest {

    private static final String CAPTION = "Sunday = Runday";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private ActivityList activityList;
    private Activity activityFirst;
    private Activity activitySecond;

    /**
     * Sets up the ActivityList instance and adds two activities to it before each test.
     */
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

    /**
     * Tests the find method. The find method should return a list of activities that occurred on the given date.
     */
    @Test
    void find() {
        assertEquals(activityList.find(LocalDate.now()).get(0), activitySecond);
        assertEquals(activityList.find(LocalDate.now().minusDays(1)).get(0), activityFirst);
    }

    /**
     * Tests the sort method. It should sort the activities in the list by date.
     */
    @Test
    void sort() {
        activityList.sort();
        assertEquals(activityList.get(0), activitySecond);
        assertEquals(activityList.get(1), activityFirst);
    }

    /**
     * Tests the filtering of activities by timespan. The filterByTimespan method should return a list of activities
     * that occurred within the given timespan.
     */
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

    /**
     * Tests the total distance calculation for activities within a week.
     */
    @Test
    void getTotalDistance_activity_totalDistance() {
        int expected = 2 * DISTANCE;
        int actual = activityList.getTotalDistance(Activity.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    /**
     * Tests the total distance calculation for runs within a week. Since the activities in the list are not runs, it
     * should return 0.
     */
    @Test
    void getTotalDistance_run_zero() {
        int expected = 0;
        int actual = activityList.getTotalDistance(Run.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    /**
     * Tests the total duration calculation for activities within a week.
     */
    @Test
    void getTotalDuration_activity_totalTime() {
        int expected = 2 * DURATION.toSecondOfDay();
        int actual = activityList.getTotalDuration(Activity.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    /**
     * Tests the total duration calculation for runs within a week. Since the activities in the list are not runs, it
     * should return 0.
     */
    @Test
    void getTotalDuration_run_zero() {
        int expected = 0;
        int actual = activityList.getTotalDuration(Run.class, TimeSpan.WEEKLY);
        assertEquals(expected, actual);
    }

    /**
     * Tests the unparsing of an activity.
     */
    @Test
    void unparse_activity_unparsed() {
        String expected = "[Activity]: Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01T06:00";
        Activity activity = new Activity("Morning Run", LocalTime.of(1, 0), 10000,
                LocalDateTime.of(2021, 9, 1, 6, 0));
        String actual = activityList.unparse(activity);
        assertEquals(expected, actual);
    }

    /**
     * Tests the unparsing of a run.
     */
    @Test
    void unparse_run_unparsed() {
        String expected = "[Run]: Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01T06:00 elevation/60";
        Run run = new Run("Morning Run", LocalTime.of(1, 0), 10000,
                LocalDateTime.of(2021, 9, 1, 6, 0), 60);
        String actual = activityList.unparse(run);
        assertEquals(expected, actual);
    }

    /**
     * Tests the parsing of an activity. The parsed activity should be the same as the original activity.
     *
     * @throws AthletiException If the activity cannot be parsed
     */
    @Test
    void parse_activity_parsed() throws AthletiException {
        Activity expected = new Activity("Morning Run", LocalTime.of(1, 0), 10000,
                LocalDateTime.of(2021, 9, 1, 6, 0));
        ActivityList activities = new ActivityList();
        String unparsedActivity = activities.unparse(expected);
        Activity actual = activities.parse(unparsedActivity);

        assertEquals(expected.getCaption(), actual.getCaption());
        assertEquals(expected.getMovingTime(), actual.getMovingTime());
        assertEquals(expected.getDistance(), actual.getDistance());
        assertEquals(expected.getStartDateTime(), actual.getStartDateTime());
    }

    /**
     * Tests the parsing of a run. The parsed run should be the same as the original run.
     *
     * @throws AthletiException If the run cannot be parsed.
     */
    @Test
    void parse_run_parsed() throws AthletiException {
        Run expected = new Run("Morning Run", LocalTime.of(1, 0), 10000,
                LocalDateTime.of(2021, 9, 1, 6, 0), 60);
        ActivityList activities = new ActivityList();
        String unparsedActivity = activities.unparse(expected);
        Run actual = (Run) activities.parse(unparsedActivity);

        assertEquals(expected.getCaption(), actual.getCaption());
        assertEquals(expected.getMovingTime(), actual.getMovingTime());
        assertEquals(expected.getDistance(), actual.getDistance());
        assertEquals(expected.getStartDateTime(), actual.getStartDateTime());
    }
}
