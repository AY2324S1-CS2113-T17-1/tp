package athleticli.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import athleticli.data.Goal;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepGoal;
import athleticli.exceptions.AthletiException;



public class SleepParserTest {

    @Test 
    void parseSleep_validInput_success() throws AthletiException {
        String input = "start/2020-10-10 10:00 end/2020-10-10 11:00";
        Sleep sleep = SleepParser.parseSleep(input);
        LocalDateTime start = LocalDateTime.parse("2020-10-10 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime end = LocalDateTime.parse("2020-10-10 11:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(start, sleep.getStartDateTime());
        assertEquals(end, sleep.getEndDateTime());
    }

    @Test
    void parseSleep_invalidInput_throwsException() {
        String input = "start/2020-10-10 10:00 end/2020-10-10 09:00";
        assertThrows(AthletiException.class, () -> SleepParser.parseSleep(input));
    }

    @Test
    void parseSleepIndex_validInput_success() throws AthletiException {
        String input = "1 start/2020-10-10 10:00 end/2020-10-10 11:00";
        int index = SleepParser.parseSleepIndex(input);
        assertEquals(1, index);
    }

    @Test
    void parseSleepIndex_invalidInput_throwsException() {
        String input = "-1000 start/2020-10-10 10:00 end/2020-10-10 11:00";
        assertThrows(AthletiException.class, () -> SleepParser.parseSleepIndex(input));
    }

    @Test
    void parseSleepGoal_validInput_success() throws AthletiException {
        String input = "type/duration period/daily target/10000";
        SleepGoal goal = SleepParser.parseSleepGoal(input);
        assertEquals(SleepGoal.GoalType.DURATION, goal.getGoalType());
        assertEquals(Goal.TimeSpan.DAILY, goal.getTimeSpan());
        assertEquals(10000, goal.getTargetValue());
    }

    @Test
    void parseSleepGoal_invalidInput_throwsException() {
        String input = "type/duration period/daily target/-1000";
        assertThrows(AthletiException.class, () -> SleepParser.parseSleepGoal(input));
    }
    
}
