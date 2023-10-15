package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.diet.Diet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the list diet commands provided by the user.
 */
public class ListDietCommandTest {
    private static final int CALORIES = 100;
    private static final int PROTEIN = 20;
    private static final int CARB = 30;
    private static final int FAT = 40;
    private Diet diet;
    private ListDietCommand listDietCommand;
    private Data data;

    @BeforeEach
    void setUp() {
        diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
        listDietCommand = new ListDietCommand();
        data = new Data();
        data.getDiets().add(diet);
    }

    @Test
    void execute() {
        String[] expected = {"Here are the diets in your list:", "1. " + diet.toString(),
                "Now you have tracked a total of 1 diets. Keep grinding!"};
        String[] actual = listDietCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
