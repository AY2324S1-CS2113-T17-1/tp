package athleticli.data.diet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DietListTest {
    private static final int CALORIES = 10000;
    private static final int PROTEIN = 20000;
    private static final int CARB = 30000;
    private static final int FAT = 40000;
    private DietList dietList;

    @BeforeEach
    void setUp() {
        dietList = new DietList();
    }

    @Test
    void add_addOneDiet_expectSizeOne() {
        Diet diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
        dietList.add(diet);
        assertEquals(1, dietList.size());
    }

    @Test
    void remove_removeExistingDiet_expectSizeOne() {
        Diet diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
        dietList.add(diet);
        dietList.remove(0);
        assertEquals(0, dietList.size());
    }

    @Test
    void remove_removeFromZeroDiets_expectIndexOutOfRangeError() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dietList.remove(0);
        });
    }

    @Test
    void get_addOneDiet_expectGetSameDiet() {
        Diet diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
        dietList.add(diet);
        assertEquals(diet, dietList.get(0));
    }

    @Test
    void size_initializeArgs_expectZero() {
        assertEquals(0, dietList.size());
    }

    @Test
    void size_addTenDiets_expectTen() {
        Diet diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
        for (int i = 0; i < 10; i++) {
            dietList.add(diet);
        }
        assertEquals(10, dietList.size());
    }

    @Test
    void testToString_oneExistingDiet_expectCorrectFormat() {
        Diet diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
        dietList.add(diet);
        assertEquals("1. " + diet.toString(), dietList.toString());
    }

    @Test
    void testToString_twoExistingDiets_expectCorrectFormat() {
        Diet diet1 = new Diet(CALORIES, PROTEIN, CARB, FAT);
        Diet diet2 = new Diet(CALORIES, PROTEIN, CARB, FAT);
        dietList.add(diet1);
        dietList.add(diet2);
        assertEquals("1. " + diet1.toString() + "\n2. " + diet2.toString(), dietList.toString());
    }

    @Test
    void testToString_zeroExistingDiets_expectCorrectFormat() {
        assertEquals("", dietList.toString());
    }

    @Test
    void testToString_threeExistingDiets_expectCorrectFormat() {
        Diet diet1 = new Diet(CALORIES, PROTEIN, CARB, FAT);
        Diet diet2 = new Diet(CALORIES, PROTEIN, CARB, FAT);
        Diet diet3 = new Diet(CALORIES, PROTEIN, CARB, FAT);
        dietList.add(diet1);
        dietList.add(diet2);
        dietList.add(diet3);
        assertEquals("1. " + diet1.toString() + "\n2. " + diet2.toString() + "\n3. " + diet3.toString(),
                dietList.toString());
    }
}
