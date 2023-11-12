package athleticli.data.diet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DietTest {
    private static final int CALORIES = 10000;
    private static final int PROTEIN = 20000;
    private static final int CARB = 30000;
    private static final int FAT = 40000;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 10, 10, 10, 10);

    private Diet diet;

    @BeforeEach
    void setUp() {
        diet = new Diet(CALORIES, PROTEIN, CARB, FAT, DATE_TIME);
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
    void getDateTime_initializeCommonArgs_expectArgs() {
        assertEquals(DATE_TIME, diet.getDateTime());
    }

    @Test
    void setDateTime_setCommonArgs_expectArgs() {
        LocalDateTime newDateTime = LocalDateTime.of(2020, 10, 10, 10, 11);
        diet.setDateTime(newDateTime);
        assertEquals(newDateTime, diet.getDateTime());
    }

    @Test
    void toString_initializeCommonArgs_expectArgs() {
        String expected =
                "Calories: 10000 cal | Protein: 20000 mg | Carb: 30000 mg | Fat: 40000 mg | October " +
                        "10, 2020 at 10:10 AM";
        assertEquals(expected, diet.toString());
    }
}
