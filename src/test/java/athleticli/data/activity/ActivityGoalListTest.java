package athleticli.data.activity;

import athleticli.data.Goal.TimeSpan;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the ActivityGoalList class.
 */
class ActivityGoalListTest {
    private ActivityGoalList activityGoalList;

    /**
     * Creates a new ActivityGoalList before each test.
     */
    @BeforeEach
    void setUp() {
        activityGoalList = new ActivityGoalList();
    }

    /**
     * Tests the unparsing of an running distance goal.
     */
    @Test
    void unparse_runningDistanceGoal_unparsed() {
        String expected = "sport/RUNNING type/DISTANCE period/WEEKLY target/10000";
        ActivityGoal goal = new ActivityGoal(TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 10000);
        String actual = activityGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    /**
     * Tests the unparsing of a swimming duration goal.
     */
    @Test
    void unparse_swimmingDurationGoal_unparsed() {
        String expected = "sport/SWIMMING type/DURATION period/MONTHLY target/120";
        ActivityGoal goal = new ActivityGoal(TimeSpan.MONTHLY, ActivityGoal.GoalType.DURATION,
                ActivityGoal.Sport.SWIMMING, 120);
        String actual = activityGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    /**
     * Tests the unparsing of a cycling distance goal.
     */
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

    /**
     * Tests the parsing of a swimming duration goal.
     *
     * @throws AthletiException If the goal is not parsed correctly.
     */
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

    /**
     * Tests the find duplicate method when there is no duplicate. It should return false.
     */
    @Test
    void findDuplicate_noDuplicate_false() {
        ActivityGoal goal = new ActivityGoal(TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 10000);
        activityGoalList.add(goal);
        boolean actual = activityGoalList.isDuplicate(ActivityGoal.GoalType.DISTANCE, ActivityGoal.Sport.RUNNING,
                TimeSpan.MONTHLY);
        assertFalse(actual);
    }

    /**
     * Tests the find duplicate method when there is a duplicate. It should return true.
     */
    @Test
    void findDuplicate_duplicate_true() {
        ActivityGoal goal = new ActivityGoal(TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 10000);
        activityGoalList.add(goal);
        boolean actual = activityGoalList.isDuplicate(ActivityGoal.GoalType.DISTANCE, ActivityGoal.Sport.RUNNING,
                TimeSpan.WEEKLY);
        assertTrue(actual);
    }

}
