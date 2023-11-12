package athleticli.data;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class GoalTest {

    @Test
    void checkDate_startDate_returnTrue() {
        Goal.TimeSpan timeSpan = Goal.TimeSpan.WEEKLY;
        assertTrue(Goal.checkDate(LocalDate.now().minusDays(timeSpan.getDays() - 1), timeSpan));
    }

    @Test
    void checkDate_beforeStartDate_returnFalse() {
        Goal.TimeSpan timeSpan = Goal.TimeSpan.MONTHLY;
        assertFalse(Goal.checkDate(LocalDate.now().minusDays(timeSpan.getDays()), timeSpan));
    }

    @Test
    void checkDate_endDate_returnTrue() {
        Goal.TimeSpan timeSpan = Goal.TimeSpan.YEARLY;
        assertTrue(Goal.checkDate(LocalDate.now(), timeSpan));
    }

    @Test
    void checkDate_afterEndDate_returnFalse() {
        Goal.TimeSpan timeSpan = Goal.TimeSpan.MONTHLY;
        assertFalse(Goal.checkDate(LocalDate.now().plusDays(1), timeSpan));
    }
}
