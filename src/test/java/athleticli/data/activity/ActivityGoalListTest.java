package athleticli.data.activity;

import athleticli.data.Goal.TimeSpan;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityGoalListTest {
    private ActivityGoalList activityGoalList;

    @BeforeEach
    void setUp() {
        activityGoalList = new ActivityGoalList();
    }

    @Test
    void parse() {

    }

    @Test
    void unparse_runningDistanceGoal_unparsed() {
        String expected = "sport/RUNNING type/DISTANCE period/WEEKLY target/10000";
        ActivityGoal goal = new ActivityGoal(TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 10000);
        String actual = activityGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    @Test
    void unparse_swimmingDurationGoal_unparsed() {
        String expected = "sport/SWIMMING type/DURATION period/MONTHLY target/120";
        ActivityGoal goal = new ActivityGoal(TimeSpan.MONTHLY, ActivityGoal.GoalType.DURATION,
                ActivityGoal.Sport.SWIMMING, 120);
        String actual = activityGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    @Test
    void parse_runningDistanceGoal_parsed() throws AthletiException {
        ActivityGoal expected = new ActivityGoal(TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 10000);
        String unparsedActivity = activityGoalList.unparse(expected);
        ActivityGoal actual = activityGoalList.parse(unparsedActivity);
        assertEquals(expected.getGoalType(), actual.getGoalType());
        assertEquals(expected.getSport(), actual.getSport());
        assertEquals(expected.getTargetValue(), actual.getTargetValue());
        assertEquals(expected.getTimeSpan(), actual.getTimeSpan());
    }

    @Test
    void parse_swimmingDurationGoal_parsed() throws AthletiException {
        ActivityGoal expected = new ActivityGoal(TimeSpan.MONTHLY, ActivityGoal.GoalType.DURATION,
                ActivityGoal.Sport.SWIMMING, 120);
        String unparsedActivity = activityGoalList.unparse(expected);
        ActivityGoal actual = activityGoalList.parse(unparsedActivity);
        assertEquals(expected.getGoalType(), actual.getGoalType());
        assertEquals(expected.getSport(), actual.getSport());
        assertEquals(expected.getTargetValue(), actual.getTargetValue());
        assertEquals(expected.getTimeSpan(), actual.getTimeSpan());
    }

}
