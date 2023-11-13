package athleticli.data.diet;

import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DietListTest {
    private static final int CALORIES = 10000;
    private static final int PROTEIN = 20000;
    private static final int CARB = 30000;
    private static final int FAT = 40000;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 10, 10, 10, 10);

    private Diet diet;
    private DietList dietList;

    @BeforeEach
    void setUp() {
        dietList = new DietList();
        diet = new Diet(CALORIES, PROTEIN, CARB, FAT, DATE_TIME);
    }

    @Test
    void add_addOneDiet_expectSizeOne() {
        dietList.add(diet);
        assertEquals(1, dietList.size());
    }

    @Test
    void remove_removeExistingDiet_expectSizeOne() {
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
        dietList.add(diet);
        assertEquals(diet, dietList.get(0));
    }

    @Test
    void size_initializeArgs_expectZero() {
        assertEquals(0, dietList.size());
    }

    @Test
    void size_addTenDiets_expectTen() {
        for (int i = 0; i < 10; i++) {
            dietList.add(diet);
        }
        assertEquals(10, dietList.size());
    }

    @Test
    void testToString_oneExistingDiet_expectCorrectFormat() {
        dietList.add(diet);
        assertEquals("\t1. " + diet, dietList.toString());
    }

    @Test
    void testToString_twoExistingDiets_expectCorrectFormat() {
        dietList.add(diet);
        dietList.add(diet);
        assertEquals("\t1. " + diet.toString() + "\n\t2. " + diet.toString(), dietList.toString());
    }

    @Test
    void testToString_zeroExistingDiets_expectCorrectFormat() {
        assertEquals("", dietList.toString());
    }

    @Test
    void testToString_threeExistingDiets_expectCorrectFormat() {
        dietList.add(diet);
        dietList.add(diet);
        dietList.add(diet);
        assertEquals("\t1. " + diet.toString() + "\n\t2. " + diet.toString() + "\n\t3. " + diet.toString(),
                dietList.toString());
    }

    @Test
    void unparse_oneExistingDiet_expectCorrectFormat() {
        String commandArgs = "calories/10000 protein/20000 carb/30000 fat/40000 datetime/2020-10-10T10:10";
        assertEquals(commandArgs, dietList.unparse(diet));
    }

    @Test
    void parse_oneExistingDiet_expectCorrectFormat() throws AthletiException {
        String commandArgs = "calories/10000 protein/20000 carb/30000 fat/40000 datetime/2020-10-10T10:10";
        assertEquals(diet.toString(), dietList.parse(commandArgs).toString());
    }

    @Test
    void parse_oneExistingDietWithExtraSpaces_expectCorrectFormat() throws AthletiException {
        String commandArgs = "calories/10000 protein/20000 carb/30000 fat/40000 datetime/2020-10-10T10:10";
        assertEquals(diet.toString(), dietList.parse(commandArgs).toString());
    }

    @Test
    void parse_dietConstructorWithDietListParse_expectSameDiet() throws AthletiException {
        String commandArgs = dietList.unparse(diet);
        assertEquals(diet.toString(), dietList.parse(commandArgs).toString());
    }
}
