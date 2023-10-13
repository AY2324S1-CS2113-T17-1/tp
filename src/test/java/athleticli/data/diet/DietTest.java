package athleticli.data.diet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DietTest {
    private static final int CALORIES = 10000;
    private static final int PROTEIN = 20000;
    private static final int CARB = 30000;
    private static final int FAT = 40000;
    private Diet diet;

    @BeforeEach
    void setUp() {
        diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
    }

    @Test
    void getCalories_initializeCommonArgs_expectArgs() {
        assertEquals(CALORIES, diet.getCalories());
    }

    @Test
    void setCalories_setCommonArgs_expectArgs() {
        diet.setCalories(10);
        assertEquals(10, diet.getCalories());
    }

    @Test
    void getProtein_initializeCommonArgs_expectArgs() {
        assertEquals(PROTEIN, diet.getProtein());
    }

    @Test
    void setProtein_setCommonArgs_expectArgs() {
        diet.setProtein(20);
        assertEquals(20, diet.getProtein());
    }

    @Test
    void getCarb_initializeCommonArgs_expectArgs() {
        assertEquals(CARB, diet.getCarb());
    }

    @Test
    void setCarb_setCommonArgs_expectArgs() {
        diet.setCarb(30);
        assertEquals(30, diet.getCarb());
    }

    @Test
    void getFat_initializeCommonArgs_expectArgs() {
        assertEquals(FAT, diet.getFat());
    }

    @Test
    void setFat_setCommonArgs_expectArgs() {
        diet.setFat(40);
        assertEquals(40, diet.getFat());
    }

    @Test
    void toString_initializeCommonArgs_expectArgs() {
        String expected = "Calories: 10000 Protein: 20000 Carb: 30000 Fat: 40000";
        assertEquals(expected, diet.toString());
    }
}
