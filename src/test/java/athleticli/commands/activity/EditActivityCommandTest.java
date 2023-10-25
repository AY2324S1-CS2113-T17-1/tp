package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.Run;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EditActivityCommandTest {
    private static final String CAPTION = "Night Run";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private AddActivityCommand addActivityCommand;
    private Data data;
    private Run run;

    @BeforeEach
    void setUp() {
        Activity activity = new Activity(CAPTION, DURATION, DISTANCE, DATE);
        addActivityCommand = new AddActivityCommand(activity);
        data = new Data();
        addActivityCommand.execute(data);
        run = new Run(CAPTION, DURATION, DISTANCE, DATE, 60);
    }

    @Test
    void execute_validIndex_activityEdited() throws AthletiException {
        EditActivityCommand editActivityCommand = new EditActivityCommand(run, 1);
        editActivityCommand.execute(data);
        String[] expected = {"Ok, I've updated this activity:", run.toString(), "You have tracked a total of 1 " +
                "activities. Keep pushing!"};
        String[] actual = editActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        assertEquals(run, data.getActivities().get(0));
    }

    @Test
    void execute_invalidIndex_exceptionThrown() {
        EditActivityCommand editActivityCommand = new EditActivityCommand(run, 2);
        assertThrows(AthletiException.class, () -> editActivityCommand.execute(data));
    }
}
