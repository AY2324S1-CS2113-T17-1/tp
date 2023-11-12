package athleticli.data.sleep;

import athleticli.data.Goal.TimeSpan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepGoalListTest {
    
    private SleepGoalList sleepGoalList;

    @BeforeEach
    void setup() {
        sleepGoalList = new SleepGoalList();
    }
    
    @Test
    void unparse_sleepDurationGoalDaily_unparsed() {
        String expected = "type/DURATION period/DAILY target/50000";
        SleepGoal goal = new SleepGoal(SleepGoal.GoalType.DURATION, TimeSpan.DAILY, 50000);
        String actual = sleepGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    @Test
    void unparse_sleepDurationGoalWeekly_unparsed() {
        String expected = "type/DURATION period/WEEKLY target/10000";
        SleepGoal goal = new SleepGoal(SleepGoal.GoalType.DURATION, TimeSpan.WEEKLY, 10000);
        String actual = sleepGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    @Test
    void unparse_sleepDurationGoalMonthly_unparsed() {
        String expected = "type/DURATION period/MONTHLY target/0";
        SleepGoal goal = new SleepGoal(SleepGoal.GoalType.DURATION, TimeSpan.MONTHLY, 0);
        String actual = sleepGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    @Test
    void unparse_sleepDurationGoalYearly_unparsed() {
        String expected = "type/DURATION period/YEARLY target/-1";
        SleepGoal goal = new SleepGoal(SleepGoal.GoalType.DURATION, TimeSpan.YEARLY, -1);
        String actual = sleepGoalList.unparse(goal);
        assertEquals(expected, actual);
    }

    @Test
    void findDuplicate_noDuplicate_false() {
        SleepGoal goal = new SleepGoal(SleepGoal.GoalType.DURATION, TimeSpan.DAILY, 50000);
        assertFalse(sleepGoalList.isDuplicate(goal.getGoalType(), goal.getTimeSpan()));
    }

    @Test
    void findDuplicate_duplicate_true() {
        SleepGoal goal = new SleepGoal(SleepGoal.GoalType.DURATION, TimeSpan.DAILY, 50000);
        sleepGoalList.add(goal);
        assertTrue(sleepGoalList.isDuplicate(goal.getGoalType(), goal.getTimeSpan()));
    }
}
