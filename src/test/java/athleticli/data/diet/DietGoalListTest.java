package athleticli.data.diet;

import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DietGoalListTest {
    private static final int PROTEIN = 10000;
    private HealthyDietGoal proteinGoal;
    private DietGoalList dietGoals;
    private Data data;

    @BeforeEach
    void setUp() {
        dietGoals = new DietGoalList();
        proteinGoal = new HealthyDietGoal(Goal.TimeSpan.WEEKLY, "protein", PROTEIN);
        data = new Data();
    }

    @Test
    void add_addOneGoal_expectSizeOne() {
        dietGoals.add(proteinGoal);
        assertEquals(1, dietGoals.size());
    }

    @Test
    void remove_removeExistingGoal_expectSizeOne() {
        dietGoals.add(proteinGoal);
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
        dietGoals.add(proteinGoal);
        assertEquals(proteinGoal, dietGoals.get(0));
    }

    @Test
    void size_initializeArgs_expectZero() {
        assertEquals(0, dietGoals.size());
    }

    @Test
    void size_addTenGoals_expectTen() {
        for (int i = 0; i < 10; i++) {
            dietGoals.add(proteinGoal);
        }
        assertEquals(10, dietGoals.size());
    }

    @Test
    void toString_oneExistingGoal_expectCorrectFormat() {
        dietGoals.add(proteinGoal);
        assertEquals("\t1. [HEALTHY]  WEEKLY protein intake progress: (0/10000)\n", dietGoals.toString(data));
    }

    @Test
    void unparse_oneDietGoal_expectCorrectFormat() {
        String actualOutput = dietGoals.unparse(proteinGoal);
        assertEquals("dietGoal WEEKLY protein 10000 healthy\n", actualOutput);
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
}
