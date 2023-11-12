package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.diet.Diet;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the delete diet commands provided by the user.
 */
public class DeleteDietCommandTest {
    private static final int CALORIES = 100;
    private static final int PROTEIN = 20;
    private static final int CARB = 30;
    private static final int FAT = 40;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 10, 10, 10, 10);
    private Diet diet;
    private DeleteDietCommand deleteDietCommand;
    private Data data;

    @BeforeEach
    void setUp() {
        diet = new Diet(CALORIES, PROTEIN, CARB, FAT, DATE_TIME);
        deleteDietCommand = new DeleteDietCommand(1);
        data = new Data();
        data.getDiets().add(diet);
    }

    @Test
    void execute() throws AthletiException {
        String[] expected = {"Noted. I've removed this diet:", diet.toString(),
                             "Now you have tracked a total of 0 diets. Keep grinding!"};
        String[] actual = deleteDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_invalidIndex_expectException() {
        deleteDietCommand = new DeleteDietCommand(2);
        assertThrows(AthletiException.class, () -> deleteDietCommand.execute(data));
    }
}
