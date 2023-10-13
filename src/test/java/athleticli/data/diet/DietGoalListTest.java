package athleticli.data.diet;

import athleticli.data.diet.DietGoal;
import athleticli.data.diet.DietGoalList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DietGoalListTest {

    @Test
    void add_addOneGoal_expectSizeOne() {
        DietGoalList dietGoals = new DietGoalList();
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        dietGoals.add(proteinGoal);
        assertEquals(1, dietGoals.size());
    }

    @Test
    void remove_removeExistingGoal_expectSizeOne() {
        DietGoalList dietGoals = new DietGoalList();
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        dietGoals.add(proteinGoal);
        dietGoals.remove(0);
        assertEquals(0, dietGoals.size());
    }

    @Test
    void remove_removeFromZeroGoals_expectIndexOutOfRangeError() {
        DietGoalList dietGoals = new DietGoalList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dietGoals.remove(0);
        });
    }

    @Test
    void get_addOneGoal_expectGetSameGoal() {
        DietGoalList dietGoals = new DietGoalList();
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        dietGoals.add(proteinGoal);
        assertEquals(proteinGoal, dietGoals.get(0));
    }

    @Test
    void size_initializeArgs_expectZero() {
        DietGoalList dietGoals = new DietGoalList();
        assertEquals(0, dietGoals.size());
    }

    @Test
    void size_addTenGoals_expectTen() {
        DietGoalList dietGoals = new DietGoalList();
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        for (int i = 0; i < 10; i++) {
            dietGoals.add(proteinGoal);
        }
        assertEquals(10, dietGoals.size());
    }

    @Test
    void testToString_oneExistingGoal_expectCorrectFormat() {
        DietGoalList dietGoals = new DietGoalList();
        DietGoal proteinGoal = new DietGoal("protein", 10000);
        dietGoals.add(proteinGoal);
        assertEquals("1. protein intake progress: (0/10000)\n", dietGoals.toString());
    }
}
