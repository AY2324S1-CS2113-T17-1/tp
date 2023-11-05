package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwimTest {

    private static final String CAPTION = "Afternoon Swim";
    private static final LocalTime DURATION = LocalTime.of(0, 35);
    private static final int DISTANCE = 1000;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 8, 29, 9, 45);
    private static final Swim.SwimmingStyle STYLE = Swim.SwimmingStyle.BUTTERFLY;
    private Swim swim;

    @BeforeEach
    public void setUp() {
        swim = new Swim(CAPTION, DURATION, DISTANCE, DATE, STYLE);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(CAPTION, swim.getCaption());
        assertEquals(DURATION, swim.getMovingTime());
        assertEquals(DISTANCE, swim.getDistance());
        assertEquals(DATE, swim.getStartDateTime());
        assertEquals(STYLE, swim.getStyle());
    }

    @Test
    public void calculateAverageLapTime() {
        assertEquals(105, swim.calculateAverageLapTime());
    }

    @Test
    public void calculateLaps() {
        assertEquals(20, swim.calculateLaps());
    }

    @Test
    public void testToString() {
        String expected = "[Swim] Afternoon Swim | Distance: 1.00 km | Avg Lap Time: 105s | Time: 35m 0s | " +
                "August 29, 2023 at 9:45 AM";
        assertEquals(expected, swim.toString());
    }

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


}
