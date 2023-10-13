package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.Run;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddActivityCommandTest {

    private static final String CAPTION = "Night Run";
    private static final int DURATION = 85;
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private static final int ELEVATION = 60;
    private Run run;
    private AddActivityCommand addActivityCommand;
    private Data data;



    @BeforeEach
    void setUp() {
        run = new Run(CAPTION, DURATION, DISTANCE, DATE, ELEVATION);
        addActivityCommand = new AddActivityCommand(run);
        data = new Data();
    }

    @Test
    void execute() {
        String[] expected = {"Well done! I've added this activity:", run.toString(), "Now you have tracked your " +
                "first activity. This is just the beginning!"};
        String[] actual = addActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
