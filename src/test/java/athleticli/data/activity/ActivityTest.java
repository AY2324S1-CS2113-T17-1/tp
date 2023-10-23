package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityTest {

    private static final String CAPTION = "Sunday = Runday";
    private static final int DURATION = 84;
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private Activity activity;

    @BeforeEach
    public void setUp() {
        activity = new Activity(CAPTION, DURATION, DISTANCE, DATE);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(CAPTION, activity.getCaption());
        assertEquals(DURATION, activity.getMovingTime());
        assertEquals(DISTANCE, activity.getDistance());
        assertEquals(DATE, activity.getStartDateTime());
    }

    @Test
    public void testToString() {
        String expected = "[Activity] Sunday = Runday | Distance: 18.12 km | Time: 1h 24m | " +
                "October 10, 2023 at 11:21 PM";
        assertEquals(expected, activity.toString());
    }

    @Test
    public void testToDetailedString() {
        String expected = "[Activity - Sunday = Runday - October 10, 2023 at 11:21 PM]\n" +
                "\tDistance: 18.12 km                     Time: 1h 24m\n" +
                "\tCalories: 0 kcal                       ...";
        String actual = activity.toDetailedString();
        assertEquals(expected, actual);
    }

    @Test
    public void generateDistanceStringOutput() {
        String actual = activity.generateDistanceStringOutput();
        String expected = "Distance: 18.12 km";
        assertEquals(expected, actual);
    }

    @Test
    public void generateMovingTimeStringOutput() {
        String actual = activity.generateMovingTimeStringOutput();
        String expected = "Time: 1h 24m";
        assertEquals(expected, actual);
    }

    @Test
    public void generateStartDateTimeStringOutput() {
        String actual = activity.generateStartDateTimeStringOutput();
        String expected = "October 10, 2023 at 11:21 PM";
        assertEquals(expected, actual);
    }

    @Test
    public void formatTwoColumns() {
        String actual = activity.formatTwoColumns("Distance: 18.12 km", "Time: 1h 24m", 30);
        String expected = "Distance: 18.12 km            Time: 1h 24m";
        assertEquals(expected, actual);
    }
}
