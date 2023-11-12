package athleticli.data.diet;

import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DietGoalListTest {
    private static final int PROTEIN = 10000;
    private HealthyDietGoal weeklyProteinGoal;
    private HealthyDietGoal dailyProteinGoal;
    private HealthyDietGoal dailyProteinGoalSmall;
    private DietGoalList dietGoals;
    private Data data;

    @BeforeEach
    void setUp() {
        dietGoals = new DietGoalList();
        weeklyProteinGoal = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "protein", PROTEIN);
        dailyProteinGoal = new HealthyDietGoal(Goal.TimeSpan.DAILY, "protein", PROTEIN);
        dailyProteinGoalSmall = new HealthyDietGoal(Goal.TimeSpan.DAILY, "protein", 1);
        data = new Data();
    }

    @Test
    void add_addOneGoal_expectSizeOne() {
        dietGoals.add(weeklyProteinGoal);
        assertEquals(1, dietGoals.size());
    }

    @Test
    void remove_removeExistingGoal_expectSizeOne() {
        dietGoals.add(weeklyProteinGoal);
        dietGoals.remove(0);
        assertEquals(0, dietGoals.size());
    }

    @Test
    void remove_removeFromZeroGoals_expectIndexOutOfRangeError() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dietGoals.remove(0);
        });
    }

    @Test
    void get_addOneGoal_expectGetSameGoal() {
        dietGoals.add(weeklyProteinGoal);
        assertEquals(weeklyProteinGoal, dietGoals.get(0));
    }

    @Test
    void size_initializeArgs_expectZero() {
        assertEquals(0, dietGoals.size());
    }

    @Test
    void size_addTenGoals_expectTen() {
        for (int i = 0; i < 10; i++) {
            dietGoals.add(weeklyProteinGoal);
        }
        assertEquals(10, dietGoals.size());
    }

    @Test
    void toString_oneExistingGoal_expectCorrectFormat() {
        dietGoals.add(weeklyProteinGoal);
        assertEquals("\t1. [HEALTHY]  WEEKLY protein intake progress: (0/10000)\n", dietGoals.toString(data));
    }

    @Test
    void unparse_oneDietGoal_expectCorrectFormat() {
        String actualOutput = dietGoals.unparse(weeklyProteinGoal);
        assertEquals("dietGoal WEEKLY protein 10000 healthy", actualOutput);
    }

    @Test
    void parse_validInput_expectDietGoal() throws AthletiException {
        String validInput = "dietGoal WEEKLY protein 10000 healthy";
        DietGoal newProteinGoal = dietGoals.parse(validInput);
        assert newProteinGoal instanceof HealthyDietGoal;
    }

    @Test
    void parse_invalidInput_expectDietGoal() throws AthletiException {
        String validInput = "dietGoal WEEKLYprotein10000";
        assertThrows(AthletiException.class, () -> {
            dietGoals.parse(validInput);
        });
    }

    @Test
    void isTargetValueConsistentWithTimeSpan_dailyTargetValueEqualToWeeklyTargetValue_returnFalse() {
        dietGoals.add(weeklyProteinGoal);
        assertFalse(dietGoals.isTargetValueConsistentWithTimeSpan(dailyProteinGoal));

    }
    @Test
    void isTargetValueConsistentWithTimeSpan_sameTimeSpan_returnTrue() {
        dietGoals.add(weeklyProteinGoal);
        assertTrue(dietGoals.isTargetValueConsistentWithTimeSpan(weeklyProteinGoal));
    }

    @Test
    void isTargetValueConsistentWithTimeSpan_weeklyTargetValueGreaterThanDailyTargetValue_returnTrue() {
        dietGoals.add(weeklyProteinGoal);
        assertTrue(dietGoals.isTargetValueConsistentWithTimeSpan(dailyProteinGoalSmall));
    }

    @Test
    void isTargetValueConsistentWithTimeSpan_dailyTargetValueLessThanWeeklyTargetValue_returnTrue() {
        dietGoals.add(dailyProteinGoalSmall);
        assertTrue(dietGoals.isTargetValueConsistentWithTimeSpan(weeklyProteinGoal));
    }
}
