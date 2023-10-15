package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.diet.Diet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests the add diet commands provided by the user.
 */
public class AddDietCommandTest {
    private static final int CALORIES = 100;
    private static final int PROTEIN = 20;
    private static final int CARB = 30;
    private static final int FAT = 40;
    private Diet diet;
    private AddDietCommand addDietCommand;
    private Data data;

    @BeforeEach
    void setUp() {
        diet = new Diet(CALORIES, PROTEIN, CARB, FAT);
        addDietCommand = new AddDietCommand(diet);
        data = new Data();
    }

    @Test
    void execute() {
        String[] expected = {"Well done! I've added this diet:", diet.toString(),
                "Now you have tracked your " + "first diet. This is just the beginning!"};
        String[] actual = addDietCommand.execute(data);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
