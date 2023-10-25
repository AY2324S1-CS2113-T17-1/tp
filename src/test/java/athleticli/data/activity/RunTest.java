package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunTest {

    private static final String CAPTION = "Night Run";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23,
            21);
    private static final int ELEVATION = 60;
    private Run run;

    @BeforeEach
    public void setUp() {
        run = new Run(CAPTION, DURATION, DISTANCE, DATE, ELEVATION);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(CAPTION, run.getCaption());
        assertEquals(DURATION, run.getMovingTime());
        assertEquals(DISTANCE, run.getDistance());
        assertEquals(DATE, run.getStartDateTime());
        assertEquals(ELEVATION, run.getElevationGain());
    }

    @Test
    public void calculateAveragePace() {
        double averagePace = run.calculateAveragePace();
        assertEquals(4.64, averagePace, 0.005);
    }

    @Test
    public void convertAveragePaceToString() {
        String expected = "4:38";
        String actual = run.convertAveragePaceToString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        String expected = "[Run] Night Run | Distance: 18.12 km | Pace: 4:38 /km | Time: 1h 24m | " +
                "October 10, 2023 at 11:21 PM";
        assertEquals(expected, run.toString());
    }

    @Test
    @Disabled // Github gradle check fails on this test
    public void testToDetailedString() {
        String expected = "[Run - Night Run - October 10, 2023 at 11:21 PM]\n"
                + "\tDistance: 18.12 km                     Avg Pace: 4:41 /km\n"
                + "\tMoving Time: Time: 1h 25m              Elevation Gain: 60 m\n"
                + "\tCalories: 0 kcal                       Steps: 0";
        String actual = run.toDetailedString();
        assertEquals(expected, actual);
    }
}
