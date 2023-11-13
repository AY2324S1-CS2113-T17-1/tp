package athleticli.data.activity;

import athleticli.data.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static athleticli.data.Goal.TimeSpan;
import static athleticli.data.activity.ActivityGoal.GoalType;
import static athleticli.data.activity.ActivityGoal.Sport;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the ActivityGoal class.
 */
class ActivityGoalTest {

    private ActivityList activityList;
    private ActivityGoal activityGoal;
    private Data data;

    private TimeSpan period = TimeSpan.WEEKLY;
    private final LocalDate date = LocalDate.now();
    private final String caption = "Sunday = Runday";
    private final int distance = 3000;

    /**
     * Initializes the data instance before each test.
     */
    @BeforeEach
    void setUp() {
        data = new Data();
    }

    /**
     * Tests whether a fully achieved goal is recognized as achieved.
     */
    @Test
    void isAchieved_activityDistanceGoal_true() {
        int targetValue = 8000;
        GoalType goalType = GoalType.DISTANCE;
        Sport sport = Sport.GENERAL;
        activityGoal = new ActivityGoal(period, goalType, sport, targetValue);

        LocalTime duration = LocalTime.of(1, 24);
        LocalDateTime date = LocalDateTime.now();
        Activity activity = new Activity(caption, duration, distance, date);
        ActivityList activityList = data.getActivities();
        activityList.add(activity);
        activityList.add(activity);
        activityList.add(activity);

        boolean expected = true;
        boolean actual = activityGoal.isAchieved(data);
        assertEquals(expected, actual);
    }

    /**
     * Tests whether a goal is recognized as not achieved when the target value is not reached.
     */
    @Test
    void isAchieved_runGoalWithNoTrackedRun_false() {
        int targetValue = 8000;
        GoalType goalType = GoalType.DISTANCE;
        Sport sport = Sport.RUNNING;
        activityGoal = new ActivityGoal(period, goalType, sport, targetValue);

        LocalTime duration = LocalTime.of(1, 24);
        LocalDateTime date = LocalDateTime.now();
        Activity activity = new Activity(caption, duration, distance, date);
        ActivityList activityList = data.getActivities();
        activityList.add(activity);
        activityList.add(activity);
        activityList.add(activity);

        boolean expected = false;
        boolean actual = activityGoal.isAchieved(data);
        assertEquals(expected, actual);
    }

    /**
     * Tests whether a goal is detected as not achieved when the target value is reached outside the specified period.
     */
    @Test
    void isAchieved_goalAchievedOutsidePeriod_false() {
        int targetValue = 120;
        GoalType goalType = GoalType.DURATION;
        Sport sport = Sport.GENERAL;
        activityGoal = new ActivityGoal(period, goalType, sport, targetValue);

        LocalTime duration = LocalTime.of(1, 24);
        LocalDateTime dateWithinPeriod = LocalDateTime.now();
        LocalDateTime dateOutsidePeriod = LocalDateTime.now().minusDays(15);
        Activity activityWithinPeriod = new Activity(caption, duration, distance, dateWithinPeriod);
        Activity activityOutsidePeriod = new Activity(caption, duration, distance, dateOutsidePeriod);

        ActivityList activityList = data.getActivities();
        activityList.add(activityWithinPeriod);
        activityList.add(activityOutsidePeriod);

        boolean expected = false;
        boolean actual = activityGoal.isAchieved(data);
        assertEquals(expected, actual);
    }

    /**
     * Tests the getActivityClass method.
     */
    @Test
    void getActivityClass() {
        GoalType goalType = GoalType.DURATION;
        Sport sport = Sport.RUNNING;
        activityGoal = new ActivityGoal(period, goalType, sport, 0);
        Class<?> expected = Run.class;
        Class<?> actual = activityGoal.getActivityClass();
        assertEquals(expected, actual);
    }
}
