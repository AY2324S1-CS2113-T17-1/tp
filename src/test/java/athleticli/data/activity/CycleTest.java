package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CycleTest {

    private Cycle cycle;
    private static final String CAPTION = "Cycling to work";
    private static final int DURATION = 60;
    private static final int DISTANCE = 30500;
    private static final int ELEVATION = 100;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 8, 0);

    @BeforeEach
    void setUp() {
        cycle = new Cycle(CAPTION, DURATION, DISTANCE, DATE, ELEVATION);
    }

    @Test
    void calculateAverageSpeed() {
        assertEquals(30.5, cycle.calculateAverageSpeed());
    }
}