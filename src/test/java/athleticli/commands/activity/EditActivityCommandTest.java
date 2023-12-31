package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityChanges;
import athleticli.data.activity.Run;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the EditActivityCommand class.
 */
class EditActivityCommandTest {
    private static final String CAPTION = "Night Run";
    private static final String UPDATED_CAPTION = "Morning Run";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final LocalTime UPDATED_DURATION = LocalTime.of(1, 30);
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private static final LocalDateTime UPDATED_DATE = LocalDateTime.of(2023, 10, 11, 23, 21);
    private AddActivityCommand addActivityCommand;
    private Data data;
    private Run run;
    private Run updatedRun;
    private ActivityChanges activityChanges;

    /**
     * Sets up the required scenario for each test.
     */
    @BeforeEach
    void setUp() {
        Activity activity = new Activity(CAPTION, DURATION, DISTANCE, DATE);
        addActivityCommand = new AddActivityCommand(activity);
        data = new Data();
        addActivityCommand.execute(data);
        run = new Run(CAPTION, DURATION, DISTANCE, DATE, 60);
        addActivityCommand = new AddActivityCommand(run);
        addActivityCommand.execute(data);
        activityChanges = new ActivityChanges();
        activityChanges.setCaption(CAPTION);
        activityChanges.setDuration(DURATION);
        activityChanges.setStartDateTime(DATE);
        updatedRun = new Run(CAPTION, DURATION, DISTANCE, DATE, 60);
    }

    /**
     * Test the edit command for a valid index and checks if the activity is edited successfully.
     * @throws AthletiException If the index is invalid.
     */
    @Test
    void execute_validIndex_activityEdited() throws AthletiException {
        EditActivityCommand editActivityCommand = new EditActivityCommand(2, activityChanges, Run.class);
        editActivityCommand.execute(data);
        String[] expected = {"Ok, I've updated this activity:", updatedRun.toString(), "You have tracked a total of 2" +
                " " +
                "activities. Keep pushing!"};
        String[] actual = editActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        assertEquals(updatedRun.getCaption(), data.getActivities().get(1).getCaption());
        assertEquals(updatedRun.getMovingTime(), data.getActivities().get(1).getMovingTime());
        assertEquals(updatedRun.getDistance(), data.getActivities().get(1).getDistance());
        assertEquals(updatedRun.getStartDateTime(), data.getActivities().get(1).getStartDateTime());
    }

    /**
     * Test the edit command for an invalid index. An exception should be thrown.
     */
    @Test
    void execute_invalidIndex_exceptionThrown() {
        EditActivityCommand editActivityCommand = new EditActivityCommand(3, activityChanges, Run.class);
        assertThrows(AthletiException.class, () -> editActivityCommand.execute(data));
    }
}
