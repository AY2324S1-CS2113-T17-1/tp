package athleticli.data.diet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DietGoalListTest {
    private static final int PROTEIN = 10000;
    private DietGoal proteinGoal;
    private DietGoalList dietGoals;

    @BeforeEach
    void setUp() {
        dietGoals = new DietGoalList();
        proteinGoal = new DietGoal("protein", PROTEIN);
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
    void testToString_oneExistingGoal_expectCorrectFormat() {
        dietGoals.add(proteinGoal);

        assertEquals("1. protein intake progress: (0/10000)\n", dietGoals.toString());
    }
}
