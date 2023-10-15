package athleticli.data.diet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DietGoalTest {

    @Test
    void getNutrients_initializeCommonArgs_expectArgs() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        assertEquals("protein", proteinGoal.getNutrients());
    }

    @Test
    void setNutrients_setCommonArgs_expectArgs() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        proteinGoal.setNutrients("Advanced Protein");
        assertEquals("Advanced Protein", proteinGoal.getNutrients());
    }

    @Test
    void getTargetValue_initializeCommonArgs_expectArgs() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        assertEquals(10000, proteinGoal.getTargetValue());
    }

    @Test
    void setTargetValue_initializeCommonArgs_expectArgs() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        proteinGoal.setTargetValue(10);
        assertEquals(10, proteinGoal.getTargetValue());
    }

    @Test
    void getCurrentValue_initializeCommonArgs_expectZero() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        assertEquals(0, proteinGoal.getCurrentValue());
    }

    @Test
    void setCurrentValue() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        proteinGoal.setCurrentValue(20);
        assertEquals(20, proteinGoal.getCurrentValue());
    }

    @Test
    void getIsGoalAchieved_currentValueGreaterThanTargetValue_expectTrue() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        proteinGoal.setCurrentValue(20000);
        assertTrue(proteinGoal.getIsGoalAchieved());
    }

    @Test
    void getIsGoalAchieved_currentValueEqualToTargetValue_expectTrue() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        proteinGoal.setCurrentValue(10000);
        assertTrue(proteinGoal.getIsGoalAchieved());
    }

    @Test
    void getIsGoalAchieved_currentValueLesserThanTargetValue_expectFalse() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        proteinGoal.setCurrentValue(100);
        assertFalse(proteinGoal.getIsGoalAchieved());
    }

    @Test
    void testToString_initializeCommonArgs_expectCorrectFormat() {
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        assertEquals("protein intake progress: (0/10000)\n", proteinGoal.toString());
    }
}
