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
    private static final int LARGE_PROTEIN_TARGET_VALUE = 10000;
    private static final int SMALL_PROTEIN_TARGET_VALUE = 1;
    private HealthyDietGoal weeklyProteinGoal;
    private HealthyDietGoal dailyProteinGoal;
    private HealthyDietGoal dailyProteinGoalSmall;
    private UnhealthyDietGoal unhealthyDailyDietGoalSmall;
    private DietGoalList dietGoals;
    private Data data;

    @BeforeEach
    void setUp() {
        dietGoals = new DietGoalList();
        weeklyProteinGoal = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "protein", LARGE_PROTEIN_TARGET_VALUE);
        dailyProteinGoal = new HealthyDietGoal(Goal.TimeSpan.DAILY, "protein", LARGE_PROTEIN_TARGET_VALUE);
        dailyProteinGoalSmall = new HealthyDietGoal(Goal.TimeSpan.DAILY, "protein", SMALL_PROTEIN_TARGET_VALUE);
        unhealthyDailyDietGoalSmall = new UnhealthyDietGoal(Goal.TimeSpan.DAILY,
                "protein", SMALL_PROTEIN_TARGET_VALUE);
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
    void parse_validHealthyDietGoal_expectHealthyDietGoal() throws AthletiException {
        String validInput = "dietGoal WEEKLY protein 10000 healthy";
        DietGoal newProteinGoal = dietGoals.parse(validInput);
        assert newProteinGoal instanceof HealthyDietGoal;
    }

    @Test
    void parse_validUnhealthyDietGoal_expectUnhealthyDietGoal() throws AthletiException {
        String validInput = "dietGoal WEEKLY protein 10000 unhealthy";
        DietGoal newProteinGoal = dietGoals.parse(validInput);
        assert newProteinGoal instanceof UnhealthyDietGoal;
    }

    @Test
    void parse_invalidDietGoal_expectError() throws AthletiException {
        String invalidInput = "dietGoal WEEKLY protein 10000 invalid";
        assertThrows(AthletiException.class, () -> {
            dietGoals.parse(invalidInput);
        });
    }

    @Test
    void parse_repeatedDietGoal_expectError() throws AthletiException {
        String validHealthyInput = "dietGoal WEEKLY protein 10000 healthy";
        DietGoal newDietGoal = dietGoals.parse(validHealthyInput);
        dietGoals.add(newDietGoal);
        assertThrows(AthletiException.class, () -> {
            dietGoals.parse(validHealthyInput);
        });
    }
    @Test
    void parse_invalidDietGoalType_expectError() throws AthletiException {
        String validHealthyInput = "dietGoal WEEKLY protein 10000 healthy";
        String validUnhealthyInput = "dietGoal DAILY protein 10000 unhealthy";
        DietGoal newDietGoal = dietGoals.parse(validHealthyInput);
        dietGoals.add(newDietGoal);
        assertThrows(AthletiException.class, () -> {
            dietGoals.parse(validUnhealthyInput);
        });
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

    @Test
    void isDietGoalTypeValid_sameGoalSameType_returnTrue() {
        dietGoals.add(weeklyProteinGoal);
        assertTrue(dietGoals.isDietGoalTypeValid(dailyProteinGoalSmall));
    }

    @Test
    void isDietGoalTypeValid_sameGoalDifferentType_returnFalse() {
        dietGoals.add(weeklyProteinGoal);
        assertFalse(dietGoals.isDietGoalTypeValid(unhealthyDailyDietGoalSmall));
    }
}
