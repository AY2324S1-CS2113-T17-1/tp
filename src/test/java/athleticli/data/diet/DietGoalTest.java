package athleticli.data.diet;

import athleticli.commands.diet.AddDietCommand;
import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.parser.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DietGoalTest {

    private DietGoalStub proteinGoal;
    private DietGoalStub fatsGoal;
    private DietGoalStub carbGoal;
    private DietGoalStub caloriesGoal;
    private Data data;
    private Diet diet;
    private final int calories = 10000;
    private final int protein = 20000;
    private final int carb = 30000;
    private final int fats = 40000;
    private final LocalDateTime dateTime = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        proteinGoal = new DietGoalStub(Goal.TimeSpan.WEEKLY, Parameter.NUTRIENTS_PROTEIN, 10000);
        fatsGoal = new DietGoalStub(Goal.TimeSpan.WEEKLY, Parameter.NUTRIENTS_FATS, 10000);
        carbGoal = new DietGoalStub(Goal.TimeSpan.WEEKLY, Parameter.NUTRIENTS_CARB, 10000);
        caloriesGoal = new DietGoalStub(Goal.TimeSpan.WEEKLY, Parameter.NUTRIENTS_CALORIES, 10000);
        data = new Data();
        diet = new Diet(calories, protein, carb, fats, dateTime);
    }

    @Test
    void getNutrients_initializeCommonArgs_expectArgs() {
        assertEquals("protein", proteinGoal.getNutrient());
    }

    @Test
    void setNutrients_setCommonArgs_expectArgs() {
        proteinGoal.setNutrient("Advanced Protein");
        assertEquals("Advanced Protein", proteinGoal.getNutrient());
    }

    @Test
    void getTargetValue_initializeCommonArgs_expectArgs() {
        assertEquals(10000, proteinGoal.getTargetValue());
    }

    @Test
    void setTargetValue_initializeCommonArgs_expectArgs() {
        proteinGoal.setTargetValue(10);
        assertEquals(10, proteinGoal.getTargetValue());
    }

    @Test
    void getCurrentValue_newProteinGoal_expectZero() {
        assertEquals(0, proteinGoal.getCurrentValue(data));
    }



    @Test
    void isAchieved_currentValueGreaterThanAndEqualToTargetValue_expectTrue() {
        AddDietCommand addDietCommand = new AddDietCommand(diet);
        addDietCommand.execute(data);
        boolean allGoalsAchieved = fatsGoal.isAchieved(data) && caloriesGoal.isAchieved(data)
                && carbGoal.isAchieved(data) && proteinGoal.isAchieved(data);
        assertTrue(allGoalsAchieved);
    }

    @Test
    void isAchieved_currentValueLesserThanTargetValue_expectFalse() {
        assertFalse(caloriesGoal.isAchieved(data));
    }

    @Test
    void toString_initializeCommonArgs_expectCorrectFormat() {
        String expectedString = " WEEKLY protein intake progress: (0/10000)\n";
        assertEquals(expectedString, proteinGoal.toString(data));
    }
}
