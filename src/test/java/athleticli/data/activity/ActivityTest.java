package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Activity class.
 */
public class ActivityTest {

    private static final String CAPTION = "Sunday = Runday";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private Activity activity;

    /**
     * Sets up the Activity object for testing.
     */
    @BeforeEach
    public void setUp() {
        activity = new Activity(CAPTION, DURATION, DISTANCE, DATE);
    }

    /**
     * Tests the constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        assertEquals(CAPTION, activity.getCaption());
        assertEquals(DURATION, activity.getMovingTime());
        assertEquals(DISTANCE, activity.getDistance());
        assertEquals(DATE, activity.getStartDateTime());
    }

    /**
     * Tests the String representation of the Activity object.
     */
    @Test
    public void testToString() {
        String expected = "[Activity] Sunday = Runday | Distance: 18.12 km | Time: 1h 24m | " +
                "October 10, 2023 at 11:21 PM";
        assertEquals(expected, activity.toString());
    }

    /**
     * Tests the detailed String representation of the Activity object.
     * Disabled due to gradle issues.
     */
    @Test
    @Disabled
    public void toDetailedString() {
        String expected = "[Activity - Sunday = Runday - October 10, 2023 at 11:21 PM]\n" +
                "\tDistance: 18.12 km                     Time: 01:24:00\n" +
                "\tCalories: 0 kcal                       ...";
        String actual = activity.toDetailedString();
        assertEquals(expected, actual);
    }

    /**
     * Tests the generation of the distance String output.
     */
    @Test
    public void generateDistanceStringOutput() {
        String actual = activity.generateDistanceStringOutput();
        String expected = "Distance: 18.12 km";
        assertEquals(expected, actual);
    }

    /**
     * Tests the generation of the moving time String output.
     */
    @Test
    public void generateMovingTimeStringOutput() {
        String actual = activity.generateMovingTimeStringOutput();
        String expected = "Time: 01:24:00";
        assertEquals(expected, actual);
    }

    /**
     * Tests the generation of the start date and time String output.
     */
    @Test
    public void generateStartDateTimeStringOutput() {
        String actual = activity.generateStartDateTimeStringOutput();
        String expected = "October 10, 2023 at 11:21 PM";
        assertEquals(expected, actual);
    }

    /**
     * Tests the formatting of two columns.
     */
    @Test
    public void formatTwoColumns() {
        String actual = activity.formatTwoColumns("Distance: 18.12 km", "Time: 1h 24m", 30);
        String expected = "Distance: 18.12 km            Time: 1h 24m";
        assertEquals(expected, actual);
    }

    /**
     * Tests the generation of the short representation of the moving time String output when hours are not zero.
     * If hours are not zero, the hours should be included in the output and the seconds should be omitted.
     */
    @Test
    void generateShortMovingTimeStringOutput_hoursNotZero() {
        String expected = "Time: 1h 24m";
        String actual = activity.generateShortMovingTimeStringOutput();
        assertEquals(expected, actual);
    }

    /**
     * Tests the generation of the short representation of the moving time String output when hours are zero.
     * If hours are zero, the hours should be omitted from the output and the seconds should be included.
     */
    @Test
    void generateShortMovingTimeStringOutput_hoursZero() {
        activity = new Activity(CAPTION, LocalTime.of(0, 24, 20), DISTANCE, DATE);
        String expected = "Time: 24m 20s";
        String actual = activity.generateShortMovingTimeStringOutput();
        assertEquals(expected, actual);
    }
}
