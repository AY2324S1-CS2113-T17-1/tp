package athleticli.data.activity;

import athleticli.data.Data;
import athleticli.data.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static athleticli.data.Goal.Timespan;
import static athleticli.data.activity.ActivityGoal.GoalType;
import static athleticli.data.activity.ActivityGoal.Sport;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityGoalTest {

    private ActivityList activityList;
    private ActivityGoal activityGoal;
    private Data data;

    private Timespan period = Timespan.WEEKLY;
    private final LocalDate date = LocalDate.now();
    private final String caption = "Sunday = Runday";
    private final int distance = 3000;

    @BeforeEach
    void setUp() {
        data = new Data();
    }

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

    @Test
    void setTargetValue() {
    }

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
