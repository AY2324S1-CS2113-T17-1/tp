package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.diet.Diet;
import athleticli.exceptions.AthletiException;
import athleticli.parser.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * Contains the tests for EditDietCommand.
 */
public class EditDietCommandTest {
    private static final int CALORIES = 100;
    private static final int PROTEIN = 10;
    private static final int CARB = 20;
    private static final int FAT = 30;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2023, 10, 10, 23, 21);
    private static final int INDEX = 1;

    private Data data;

    @BeforeEach
    void setUp() {
        Diet diet = new Diet(CALORIES, PROTEIN, CARB, FAT, DATE_TIME);
        data = new Data();
        data.getDiets().add(diet);
    }

    @Test
    void execute_validIndex_dietEdited() throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.CALORIES_SEPARATOR, "200");
        dietMap.put(Parameter.PROTEIN_SEPARATOR, "20");
        dietMap.put(Parameter.CARB_SEPARATOR, "30");
        dietMap.put(Parameter.FAT_SEPARATOR, "40");
        dietMap.put(Parameter.DATETIME_SEPARATOR, "2021-10-10 23:21");
        Diet newDiet = new Diet(200, 20, 30, 40, LocalDateTime.of(2021, 10, 10, 23, 21));
        EditDietCommand editDietCommand = new EditDietCommand(INDEX, dietMap);
        editDietCommand.execute(data);
        String[] expected = {"Ok, I've updated this diet:", newDiet.toString()};
        String[] actual = editDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_validIndex_dietEditedNoCaloriesGiven() throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.PROTEIN_SEPARATOR, "20");
        dietMap.put(Parameter.CARB_SEPARATOR, "30");
        dietMap.put(Parameter.FAT_SEPARATOR, "40");
        dietMap.put(Parameter.DATETIME_SEPARATOR, "2021-10-10 23:21");
        Diet newDiet = new Diet(CALORIES, 20, 30, 40, LocalDateTime.of(2021, 10, 10, 23, 21));
        EditDietCommand editDietCommand = new EditDietCommand(INDEX, dietMap);
        editDietCommand.execute(data);
        String[] expected = {"Ok, I've updated this diet:", newDiet.toString()};
        String[] actual = editDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_validIndex_dietEditedNoProteinGiven() throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.CALORIES_SEPARATOR, "200");
        dietMap.put(Parameter.CARB_SEPARATOR, "30");
        dietMap.put(Parameter.FAT_SEPARATOR, "40");
        dietMap.put(Parameter.DATETIME_SEPARATOR, "2021-10-10 23:21");
        Diet newDiet = new Diet(200, PROTEIN, 30, 40, LocalDateTime.of(2021, 10, 10, 23, 21));
        EditDietCommand editDietCommand = new EditDietCommand(INDEX, dietMap);
        editDietCommand.execute(data);
        String[] expected = {"Ok, I've updated this diet:", newDiet.toString()};
        String[] actual = editDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_validIndex_dietEditedNoCarbGiven() throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.CALORIES_SEPARATOR, "200");
        dietMap.put(Parameter.PROTEIN_SEPARATOR, "20");
        dietMap.put(Parameter.FAT_SEPARATOR, "40");
        dietMap.put(Parameter.DATETIME_SEPARATOR, "2021-10-10 23:21");
        Diet newDiet = new Diet(200, 20, CARB, 40, LocalDateTime.of(2021, 10, 10, 23, 21));
        EditDietCommand editDietCommand = new EditDietCommand(INDEX, dietMap);
        editDietCommand.execute(data);
        String[] expected = {"Ok, I've updated this diet:", newDiet.toString()};
        String[] actual = editDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_validIndex_dietEditedNoFatGiven() throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.CALORIES_SEPARATOR, "200");
        dietMap.put(Parameter.PROTEIN_SEPARATOR, "20");
        dietMap.put(Parameter.CARB_SEPARATOR, "30");
        dietMap.put(Parameter.DATETIME_SEPARATOR, "2021-10-10 23:21");
        Diet newDiet = new Diet(200, 20, 30, FAT, LocalDateTime.of(2021, 10, 10, 23, 21));
        EditDietCommand editDietCommand = new EditDietCommand(INDEX, dietMap);
        editDietCommand.execute(data);
        String[] expected = {"Ok, I've updated this diet:", newDiet.toString()};
        String[] actual = editDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_validIndex_dietEditedNoCaloriesProteinCarbFatGiven() throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.DATETIME_SEPARATOR, "2021-10-10 23:21");
        Diet newDiet = new Diet(CALORIES, PROTEIN, CARB, FAT, LocalDateTime.of(2021, 10, 10, 23, 21));
        EditDietCommand editDietCommand = new EditDietCommand(INDEX, dietMap);
        editDietCommand.execute(data);
        String[] expected = {"Ok, I've updated this diet:", newDiet.toString()};
        String[] actual = editDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_validIndex_dietEditedNoDateTimeGiven() throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.CALORIES_SEPARATOR, "200");
        dietMap.put(Parameter.PROTEIN_SEPARATOR, "20");
        dietMap.put(Parameter.CARB_SEPARATOR, "30");
        dietMap.put(Parameter.FAT_SEPARATOR, "40");
        Diet newDiet = new Diet(200, 20, 30, 40, DATE_TIME);
        EditDietCommand editDietCommand = new EditDietCommand(INDEX, dietMap);
        editDietCommand.execute(data);
        String[] expected = {"Ok, I've updated this diet:", newDiet.toString()};
        String[] actual = editDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_invalidIndex_exceptionThrown() {
        HashMap<String, String> dietMap = new HashMap<>();
        dietMap.put(Parameter.CALORIES_SEPARATOR, "200");
        dietMap.put(Parameter.PROTEIN_SEPARATOR, "20");
        dietMap.put(Parameter.CARB_SEPARATOR, "30");
        dietMap.put(Parameter.FAT_SEPARATOR, "40");
        dietMap.put(Parameter.DATETIME_SEPARATOR, "2021-10-10 23:21");
        EditDietCommand editDietCommand = new EditDietCommand(2, dietMap);
        assertThrows(AthletiException.class, () -> editDietCommand.execute(data));
    }
}
