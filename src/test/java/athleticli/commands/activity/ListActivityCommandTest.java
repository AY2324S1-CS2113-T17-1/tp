package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityList;
import athleticli.ui.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListActivityCommandTest {
    private static final String CAPTION = "Night Run";
    private static final LocalTime DURATION = LocalTime.of(1, 24);
    private static final int DISTANCE = 18120;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 10, 10, 23, 21);
    private Data data;

    @BeforeEach
    void setUp() {
        Activity activity = new Activity(CAPTION, DURATION, DISTANCE, DATE);
        AddActivityCommand addActivityCommand = new AddActivityCommand(activity);
        data = new Data();
        // execute twice for 2 activities
        addActivityCommand.execute(data);
        addActivityCommand.execute(data);
    }

    @Test
    void execute_detailedFalse_printsShortList() {
        ListActivityCommand listActivityCommand = new ListActivityCommand(false);
        String[] expected = {"These are the activities you have tracked so far:", "1." + new Activity(CAPTION, DURATION,
            DISTANCE, DATE), "2." + new Activity(CAPTION, DURATION, DISTANCE, DATE),
            Message.MESSAGE_ACTIVITY_LIST_END};
        String[] actual = listActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void execute_detailedTrue_printsDetailedList() {
        ListActivityCommand listActivityCommand = new ListActivityCommand(true);
        ActivityList activities = data.getActivities();
        String[] expected = listActivityCommand.printDetailedList(activities, activities.size());
        String[] actual = listActivityCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void printList_validInput() {
        ActivityList activities = data.getActivities();
        ListActivityCommand listActivityCommand = new ListActivityCommand(false);
        String[] actual = listActivityCommand.printList(activities, activities.size());
        String[] expected = {"These are the activities you have tracked so far:", "1." + new Activity(CAPTION, DURATION,
            DISTANCE, DATE), "2." + new Activity(CAPTION, DURATION, DISTANCE, DATE),
            Message.MESSAGE_ACTIVITY_LIST_END};
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void printDetailedList() {
        ActivityList activities = data.getActivities();
        ListActivityCommand listActivityCommand = new ListActivityCommand(true);
        String[] actual = listActivityCommand.printDetailedList(activities, activities.size());
        String[] expected = {"These are the activities you have tracked so far:", new Activity(CAPTION, DURATION,
                DISTANCE, DATE).toDetailedString(),
                new Activity(CAPTION, DURATION, DISTANCE, DATE).toDetailedString()};
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
