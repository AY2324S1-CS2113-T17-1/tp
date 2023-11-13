package athleticli.data.sleep;

import athleticli.data.Data;
import athleticli.exceptions.AthletiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static athleticli.data.Goal.TimeSpan;
import static athleticli.data.sleep.SleepGoal.GoalType;
import static athleticli.data.sleep.SleepGoal.TimeSpan;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepGoalTest {
    
    private SleepGoal sleepGoal;
    private Data data;
    private GoalType goalType = GoalType.DURATION;
    private TimeSpan period = TimeSpan.WEEKLY;

    @BeforeEach
    void setUp() {
        data = new Data();
    }

    @Test
    void isAchieved_sleepDurationGoal_true() throws AthletiException {
        int targetValue = 8000;

        sleepGoal = new SleepGoal(goalType, period, targetValue);

        LocalDateTime date = LocalDateTime.now();
        Sleep sleep = new Sleep(date, date.plusHours(8));
        SleepList sleepList = data.getSleeps();
        sleepList.add(sleep);

        boolean expected = true;
        boolean actual = sleepGoal.isAchieved(data);
        assertEquals(expected, actual);
    }

    @Test
    void isAchieved_sleepDurationGoal_false() throws AthletiException {
        int targetValue = 8000;
    
        sleepGoal = new SleepGoal(goalType, period, targetValue);

        LocalDateTime date = LocalDateTime.now();
        Sleep sleep = new Sleep(date, date.plusHours(1));
        SleepList sleepList = data.getSleeps();
        sleepList.add(sleep);

        boolean expected = false;
        boolean actual = sleepGoal.isAchieved(data);
        assertEquals(expected, actual);
    }
    
}
