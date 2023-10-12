package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunTest {

    private static final String CAPTION = "Morning Run";
    private static final int DURATION = 80;
    private static final int DISTANCE = 18000;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 8, 0);
    private static final int ELEVATION = 60;
    private Run run;

    @BeforeEach
    void setUp() {
        run = new Run(CAPTION, DURATION, DISTANCE, DATE, ELEVATION);
    }

    @Test
    void calculateAveragePace() {
        double averagePace = run.calculateAveragePace();
        assertEquals(4.444444444444445, averagePace);
    }

    @Test
    void convertAveragePaceToString() {
    }
}
