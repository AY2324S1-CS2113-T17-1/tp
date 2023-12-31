package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Swim class.
 */
public class SwimTest {

    private static final String CAPTION = "Afternoon Swim";
    private static final LocalTime DURATION = LocalTime.of(0, 35);
    private static final int DISTANCE = 1000;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 8, 29, 9, 45);
    private static final Swim.SwimmingStyle STYLE = Swim.SwimmingStyle.BUTTERFLY;
    private Swim swim;

    /**
     * Sets up the Swim object for testing.
     */
    @BeforeEach
    public void setUp() {
        swim = new Swim(CAPTION, DURATION, DISTANCE, DATE, STYLE);
    }

    /**
     * Tests the constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        assertEquals(CAPTION, swim.getCaption());
        assertEquals(DURATION, swim.getMovingTime());
        assertEquals(DISTANCE, swim.getDistance());
        assertEquals(DATE, swim.getStartDateTime());
        assertEquals(STYLE, swim.getStyle());
    }

    /**
     * Tests the calculation of average lap time.
     */
    @Test
    public void calculateAverageLapTime() {
        assertEquals(105, swim.calculateAverageLapTime());
    }

    /**
     * Tests the calculation of laps.
     */
    @Test
    public void calculateLaps() {
        assertEquals(20, swim.calculateLaps());
    }

    /**
     * Tests the String representation of the Swim object.
     */
    @Test
    public void testToString() {
        String expected = "[Swim] Afternoon Swim | Distance: 1.00 km | Lap Time: 105s | Time: 35m 0s | " +
                "August 29, 2023 at 9:45 AM";
        assertEquals(expected, swim.toString());
    }

    /**
     * Tests the detailed String representation of the Swim object.
     * Disabled due to gradle issues.
     */
    @Test
    @Disabled
    public void testToDetailedString() {
        String expected = "[Swim - Afternoon Swim - August 29, 2023 at 9:45 AM]\n"
                + "\tDistance: 1.00 km                      Time: 00:35:00\n"
                + "\tLaps: 20                               Style: BUTTERFLY\n"
                + "\tAvg Lap Time: 105 s                    Calories: 0 kcal";
        String actual = swim.toDetailedString();
        assertEquals(expected, actual);
    }

    /**
     * Tests the unparsing of the swim object.
     */
    @Test
    void unparse() {
        String actual = swim.unparse();
        String expected = "[Swim]: Afternoon Swim duration/00:35:00 distance/1000 datetime/2023-08-29T09:45 "
                + "style/BUTTERFLY";
        assertEquals(expected, actual);
    }
}
