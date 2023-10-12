package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwimTest {

    private static final String CAPTION = "Swim Training";
    private static final int DURATION = 30;
    private static final int DISTANCE = 1000;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 8, 0);
    private static final Swim.SwimmingStyle STYLE = Swim.SwimmingStyle.BUTTERFLY;
    private Swim swim;

    @BeforeEach
    void setUp() {
        swim = new Swim(CAPTION, DURATION, DISTANCE, DATE, STYLE);
    }

    @Test
    void calculateAverageLapTime() {
        assertEquals(90, swim.calculateAverageLapTime());
    }

    @Test
    void calculateLaps() {
        assertEquals(20, swim.calculateLaps());
    }
}
