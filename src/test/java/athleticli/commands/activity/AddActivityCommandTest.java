package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddActivityCommandTest {
    private AddActivityCommand addActivityCommand;
    private Activity activity;
    private Data data;

    @BeforeEach
    void setUp() {
        activity = new Activity("test", 1, 1, null);
        addActivityCommand = new AddActivityCommand(activity);
        data = new Data();
    }

    @Test
    void execute() {
        String[] expected = {"Well done! I've added this activity:"};
        String[] actual = addActivityCommand.execute(data);
        assertEquals(expected[0], actual[0]);
    }
}
