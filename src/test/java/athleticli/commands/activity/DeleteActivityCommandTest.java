package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.Run;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteActivityCommandTest {

    private static final String CAPTION = "Night Run";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private static final int ELEVATION = 60;
    private Run run;
    private DeleteActivityCommand deleteActivityCommand;
    private Data data;

    @BeforeEach
    void setUp() {
        run = new Run(CAPTION, DURATION, DISTANCE, DATE, ELEVATION);
        AddActivityCommand addActivityCommand = new AddActivityCommand(run);
        data = new Data();
        addActivityCommand.execute(data);
    }

    @Test
    void execute_validIndex_activityDeleted() throws AthletiException {
        String[] expected = {"Gotcha, I've deleted this activity:", run.toString(), "You have tracked a total of 0 " +
                "activities. Keep pushing!"};
        deleteActivityCommand = new DeleteActivityCommand(1);
        String[] actual = deleteActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void execute_invalidIndex_exceptionThrown() {
        deleteActivityCommand = new DeleteActivityCommand(0);
        assertThrows(AthletiException.class, () -> deleteActivityCommand.execute(data));
    }
}
