package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwimTest {

    private static final String CAPTION = "Afternoon Swim";
    private static final int DURATION = 35;
    private static final int DISTANCE = 1000;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 8, 29, 9, 45);
    private static final Swim.SwimmingStyle STYLE = Swim.SwimmingStyle.BUTTERFLY;
    private Swim swim;

    @BeforeEach
    public void setUp() {
        swim = new Swim(CAPTION, DURATION, DISTANCE, DATE, STYLE);
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
        String expected = "[Swim] Afternoon Swim | Distance: 1.00 km | Avg Lap Time: 105s | Time: 0h 35m | " +
                "August 29, 2023 at 9:45 AM";
        assertEquals(expected, swim.toString());
    }


}
