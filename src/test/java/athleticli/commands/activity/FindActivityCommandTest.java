package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityList;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the FindActivityCommand class.
 */
class FindActivityCommandTest {
    private static final String CAPTION = "Night Run";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private Data data;
    private Activity activity;

    /**
     * Sets up the activity and data to be used in the tests.
     */
    @BeforeEach
    void setUp() {
        activity = new Activity(CAPTION, DURATION, DISTANCE, DATE);
        data = new Data();
        ActivityList activities = data.getActivities();
        activities.add(activity);
    }

    /**
     * Tests that the correct message is returned when there are matching activities.
     *
     * @throws AthletiException If there is an error in the execution of the command.
     */
    @Test
    void execute_matchingDate_returnsMatchingActivity() throws AthletiException {
        String[] expected = {"I've found these activities:", activity.toString()};
        FindActivityCommand findActivityCommand = new FindActivityCommand(DATE.toLocalDate());
        String[] actual = findActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    /**
     * Tests that the correct message is returned when there are no matching activities.
     *
     * @throws AthletiException If there is an error in the execution of the command.
     */
    @Test
    void execute_noMatchingDate_returnsNoMatchingActivityMessage() throws AthletiException {
        String[] expected = {"I've found these activities:"};
        FindActivityCommand findActivityCommand = new FindActivityCommand(DATE.toLocalDate().plusDays(1));
        String[] actual = findActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
