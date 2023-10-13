package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunTest {

    private static final String CAPTION = "Night Run";
    private static final int DURATION = 85;
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private static final int ELEVATION = 60;
    private Run run;

    @BeforeEach
    public void setUp() {
        run = new Run(CAPTION, DURATION, DISTANCE, DATE, ELEVATION);
    }

    @Test
    public void calculateAveragePace() {
        double averagePace = run.calculateAveragePace();
        assertEquals(4.690949227373068, averagePace);
    }

    @Test
    public void convertAveragePaceToString() {
        String expected = "4:41";
        String actual = run.convertAveragePaceToString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        String expected = "[Run] Night Run | Distance: 18.12 km | Pace: 4:41 /km | Time: 1h 25m | " +
                "\"October 10, 2023 at 11:21 PM\"";
        assertEquals(expected, run.toString());
    }
}
