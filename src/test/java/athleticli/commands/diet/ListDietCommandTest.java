package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.diet.Diet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Tests the list diet commands provided by the user.
 */
public class ListDietCommandTest {
    private static final int CALORIES = 100;
    private static final int PROTEIN = 20;
    private static final int CARB = 30;
    private static final int FAT = 40;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 10, 10, 10, 10);
    private Diet diet;
    private ListDietCommand listDietCommand;
    private Data data;

    @BeforeEach
    void setUp() {
        diet = new Diet(CALORIES, PROTEIN, CARB, FAT, DATE_TIME);
        listDietCommand = new ListDietCommand();
        data = new Data();
        data.getDiets().add(diet);
    }

    @Test
    void execute() {
        String[] expected = {"Here are the diets in your list:", "\t1. " + diet.toString(),
                             "Now you have tracked a total of 1 diets. Keep grinding!"};
        String[] actual = listDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }
}
