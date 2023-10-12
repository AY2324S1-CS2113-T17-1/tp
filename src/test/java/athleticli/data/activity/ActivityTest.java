package athleticli.data.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityTest {

    private static final String CAPTION = "Sunday = Runday";
    private static final int DURATION = 60;
    private static final int DISTANCE = 18000;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 8, 0);
    private Activity activity;

    @BeforeEach
    void setUp() {
        activity = new Activity(CAPTION, DURATION, DISTANCE, DATE);
    }

    @Test
    public void testConstructor() {
        assertEquals(CAPTION, activity.getCaption());
        assertEquals(DURATION, activity.getMovingTime());
        assertEquals(DISTANCE, activity.getDistance());
        assertEquals(DATE, activity.getStartDateTime());
    }
}
