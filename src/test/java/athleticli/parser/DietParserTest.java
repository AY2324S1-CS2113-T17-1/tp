package athleticli.parser;

import static athleticli.parser.DietParser.checkEmptyDietArguments;
import static athleticli.parser.DietParser.checkMissingDietArguments;
import static athleticli.parser.DietParser.parseCalories;
import static athleticli.parser.DietParser.parseCarb;
import static athleticli.parser.DietParser.parseDiet;
import static athleticli.parser.DietParser.parseDietEdit;
import static athleticli.parser.DietParser.parseDietGoalDelete;
import static athleticli.parser.DietParser.parseDietGoalSetEdit;
import static athleticli.parser.DietParser.parseDietIndex;
import static athleticli.parser.DietParser.parseFat;
import static athleticli.parser.DietParser.parseProtein;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

import athleticli.exceptions.AthletiException;

public class DietParserTest {
    //@@author  nihalzp
    @Test
    void checkMissingDietArguments_missingProtein_throwAthletiException() {
        int caloriesMarkerPos = 1;
        int proteinMarkerPos = -1;
        int carbMarkerPos = 2;
        int fatMarkerPos = 3;
        int datetimeMarkerPos = 4;
        assertThrows(AthletiException.class,
                () -> checkMissingDietArguments(caloriesMarkerPos, proteinMarkerPos, carbMarkerPos,
                        fatMarkerPos, datetimeMarkerPos));
    }

    @Test
    void checkMissingDietArguments_missingCalories_throwAthletiException() {
        int caloriesMarkerPos = -1;
        int proteinMarkerPos = 1;
        int carbMarkerPos = 2;
        int fatMarkerPos = 3;
        int datetimeMarkerPos = 4;
        assertThrows(AthletiException.class,
                () -> checkMissingDietArguments(caloriesMarkerPos, proteinMarkerPos, carbMarkerPos,
                        fatMarkerPos, datetimeMarkerPos));
    }

    @Test
    void checkMissingDietArguments_missingCarb_throwAthletiException() {
        int caloriesMarkerPos = 1;
        int proteinMarkerPos = 2;
        int carbMarkerPos = -1;
        int fatMarkerPos = 3;
        int datetimeMarkerPos = 4;
        assertThrows(AthletiException.class,
                () -> checkMissingDietArguments(caloriesMarkerPos, proteinMarkerPos, carbMarkerPos,
                        fatMarkerPos, datetimeMarkerPos));
    }

    @Test
    void checkMissingDietArguments_missingFat_throwAthletiException() {
        int caloriesMarkerPos = 1;
        int proteinMarkerPos = 2;
        int carbMarkerPos = 3;
        int fatMarkerPos = -1;
        int datetimeMarkerPos = 4;
        assertThrows(AthletiException.class,
                () -> checkMissingDietArguments(caloriesMarkerPos, proteinMarkerPos, carbMarkerPos,
                        fatMarkerPos, datetimeMarkerPos));
    }

    @Test
    void checkMissingDietArguments_missingDatetime_throwAthletiException() {
        int caloriesMarkerPos = 1;
        int proteinMarkerPos = 2;
        int carbMarkerPos = 3;
        int fatMarkerPos = 4;
        int datetimeMarkerPos = -1;
        assertThrows(AthletiException.class,
                () -> checkMissingDietArguments(caloriesMarkerPos, proteinMarkerPos, carbMarkerPos,
                        fatMarkerPos, datetimeMarkerPos));
    }

    @Test
    void checkEmptyDietArguments_emptyCalories_throwAthletiException() {
        String emptyCalories = "";
        String nonEmptyProtein = "1";
        String nonEmptyCarb = "2";
        String nonEmptyFat = "3";
        String nonEmptyDatetime = "2021-10-06 10:00";
        assertThrows(AthletiException.class,
                () -> checkEmptyDietArguments(emptyCalories, nonEmptyProtein, nonEmptyCarb, nonEmptyFat,
                        nonEmptyDatetime));
    }

    @Test
    void checkEmptyDietArguments_emptyProtein_throwAthletiException() {
        String nonEmptyCalories = "1";
        String emptyProtein = "";
        String nonEmptyCarb = "2";
        String nonEmptyFat = "3";
        String nonEmptyDatetime = "2021-10-06 10:00";
        assertThrows(AthletiException.class,
                () -> checkEmptyDietArguments(nonEmptyCalories, emptyProtein, nonEmptyCarb, nonEmptyFat,
                        nonEmptyDatetime));
    }

    @Test
    void checkEmptyDietArguments_emptyCarb_throwAthletiException() {
        String nonEmptyCalories = "1";
        String nonEmptyProtein = "2";
        String emptyCarb = "";
        String nonEmptyFat = "3";
        String nonEmptyDatetime = "2021-10-06 10:00";
        assertThrows(AthletiException.class,
                () -> checkEmptyDietArguments(nonEmptyCalories, nonEmptyProtein, emptyCarb, nonEmptyFat,
                        nonEmptyDatetime));
    }

    @Test
    void checkEmptyDietArguments_emptyFat_throwAthletiException() {
        String nonEmptyCalories = "1";
        String nonEmptyProtein = "2";
        String nonEmptyCarb = "3";
        String emptyFat = "";
        String nonEmptyDatetime = "2021-10-06 10:00";
        assertThrows(AthletiException.class,
                () -> checkEmptyDietArguments(nonEmptyCalories, nonEmptyProtein, nonEmptyCarb, emptyFat,
                        nonEmptyDatetime));
    }

    @Test
    void checkEmptyDietArguments_emptyDatetime_throwAthletiException() {
        String nonEmptyCalories = "1";
        String nonEmptyProtein = "2";
        String nonEmptyCarb = "3";
        String nonEmptyFat = "4";
        String emptyDatetime = "";
        assertThrows(AthletiException.class,
                () -> checkEmptyDietArguments(nonEmptyCalories, nonEmptyProtein, nonEmptyCarb, nonEmptyFat,
                        emptyDatetime));
    }

    @Test
    void parseCalories_validCalories_returnCalories() throws AthletiException {
        int expected = 5;
        int actual = parseCalories("5");
        assertEquals(expected, actual);
    }

    @Test
    void parseCalories_nonIntegerInput_throwAthletiException() {
        String nonIntegerInput = "nonInteger";
        assertThrows(AthletiException.class, () -> parseCalories(nonIntegerInput));
    }

    @Test
    void parseCalories_negativeIntegerInput_throwAthletiException() {
        String nonIntegerInput = "-1";
        assertThrows(AthletiException.class, () -> parseCalories(nonIntegerInput));
    }

    @Test
    void parseCalories_bigIntegerInput_throwAthletiException() {
        String bigIntegerInput1 = "1000001";
        String bigIntegerInput2 = "10000000000000000000000";
        assertThrows(AthletiException.class, () -> parseCalories(bigIntegerInput1));
        assertThrows(AthletiException.class, () -> parseCalories(bigIntegerInput2));
    }

    @Test
    void parseProtein_validProtein_returnProtein() throws AthletiException {
        int expected = 5;
        int actual = parseProtein("5");
        assertEquals(expected, actual);
    }

    @Test
    void parseProtein_nonIntegerInput_throwAthletiException() {
        String nonIntegerInput = "nonInteger";
        assertThrows(AthletiException.class, () -> parseProtein(nonIntegerInput));
    }

    @Test
    void parseProtein_negativeIntegerInput_throwAthletiException() {
        String nonIntegerInput = "-1";
        assertThrows(AthletiException.class, () -> parseProtein(nonIntegerInput));
    }

    @Test
    void parseProtein_bigIntegerInput_throwAthletiException() {
        String bigIntegerInput1 = "1000001";
        String bigIntegerInput2 = "10000000000000000000000";
        assertThrows(AthletiException.class, () -> parseProtein(bigIntegerInput1));
        assertThrows(AthletiException.class, () -> parseProtein(bigIntegerInput2));
    }

    @Test
    void parseCarb_validCarb_returnCarb() throws AthletiException {
        int expected = 5;
        int actual = parseCarb("5");
        assertEquals(expected, actual);
    }

    @Test
    void parseCarb_nonIntegerInput_throwAthletiException() {
        String nonIntegerInput = "nonInteger";
        assertThrows(AthletiException.class, () -> parseCarb(nonIntegerInput));
    }

    @Test
    void parseCarb_negativeIntegerInput_throwAthletiException() {
        String nonIntegerInput = "-1";
        assertThrows(AthletiException.class, () -> parseCarb(nonIntegerInput));
    }

    @Test
    void parseCarb_bigIntegerInput_throwAthletiException() {
        String bigIntegerInput1 = "1000001";
        String bigIntegerInput2 = "10000000000000000000000";
        assertThrows(AthletiException.class, () -> parseCarb(bigIntegerInput1));
        assertThrows(AthletiException.class, () -> parseCarb(bigIntegerInput2));
    }

    @Test
    void parseFat_validFat_returnFat() throws AthletiException {
        int expected = 5;
        int actual = parseFat("5");
        assertEquals(expected, actual);
    }

    @Test
    void parseFat_nonIntegerInput_throwAthletiException() {
        String nonIntegerInput = "nonInteger";
        assertThrows(AthletiException.class, () -> parseFat(nonIntegerInput));
    }

    @Test
    void parseFat_negativeIntegerInput_throwAthletiException() {
        String nonIntegerInput = "-1";
        assertThrows(AthletiException.class, () -> parseFat(nonIntegerInput));
    }

    @Test
    void parseFat_bigIntegerInput_throwAthletiException() {
        String bigIntegerInput1 = "1000001";
        String bigIntegerInput2 = "10000000000000000000000";
        assertThrows(AthletiException.class, () -> parseFat(bigIntegerInput1));
        assertThrows(AthletiException.class, () -> parseFat(bigIntegerInput2));
    }

    @Test
    void parseDietEdit_validInput_returnDietEdit() throws AthletiException {
        String validInput = "2 calories/1 protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        HashMap<String, String> actual = parseDietEdit(validInput);
        HashMap<String, String> expected = new HashMap<>();
        expected.put(Parameter.CALORIES_SEPARATOR, "1");
        expected.put(Parameter.PROTEIN_SEPARATOR, "2");
        expected.put(Parameter.CARB_SEPARATOR, "3");
        expected.put(Parameter.FAT_SEPARATOR, "4");
        expected.put(Parameter.DATETIME_SEPARATOR, "2023-10-06T10:00");
        assertEquals(expected, actual);
    }

    @Test
    void parseDietEdit_someMarkersPresent_returnDietEdit() throws AthletiException {
        String validInput = "2 calories/1 protein/2 carb/3";
        HashMap<String, String> actual = parseDietEdit(validInput);
        HashMap<String, String> expected = new HashMap<>();
        expected.put(Parameter.CALORIES_SEPARATOR, "1");
        expected.put(Parameter.PROTEIN_SEPARATOR, "2");
        expected.put(Parameter.CARB_SEPARATOR, "3");
        assertEquals(expected, actual);
    }

    @Test
    void parseDietEdit_zeroValidInput_throwAthletiException() {
        String invalidInput = "2 calorie/1 proteins/2 carbs/3 fats/4 datetime/2023-10-06";
        assertThrows(AthletiException.class, () -> parseDietEdit(invalidInput));
    }

    @Test
    void parseDietIndex_validIndex_returnIndex() throws AthletiException {
        int expected = 5;
        int actual = parseDietIndex("5");
        assertEquals(expected, actual);
    }

    @Test
    void parseDietIndex_nonIntegerInput_throwAthletiException() {
        String nonIntegerInput = "nonInteger";
        assertThrows(AthletiException.class, () -> parseDietIndex(nonIntegerInput));
    }

    @Test
    void parseDietIndex_nonPositiveIntegerInput_throwAthletiException() {
        String nonIntegerInput = "0";
        assertThrows(AthletiException.class, () -> parseDietIndex(nonIntegerInput));
    }

    @Test
    void parseDietIndex_bigIntegerInput_throwAthletiException() {
        String bigIntegerInput = "10000000000000000000000";
        assertThrows(AthletiException.class, () -> parseDietIndex(bigIntegerInput));
    }

    @Test
    void parseDiet_emptyInput_throwAthletiException() {
        String emptyInput = "";
        assertThrows(AthletiException.class, () -> parseDiet(emptyInput));
    }

    //@@author  yicheng-toh
    @Test
    void parseDietGoalSetEdit_noInput_throwAthletiException() {
        String oneValidOneInvalidGoalString = " ";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(oneValidOneInvalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_oneValidOneInvalidGoal_throwAthletiException() {
        String oneValidOneInvalidGoalString = "calories/60 protein/protine";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(oneValidOneInvalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_zeroTargetValue_throwAthletiException() {
        String zeroTargetValueGoalString = "calories/0";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(zeroTargetValueGoalString));
    }

    @Test
    void parseDietGoalSetEdit_oneInvalidGoal_throwAthletiException() {
        String invalidGoalString = "calories/caloreis protein/protein";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_repeatedDietGoal_throwAthletiException() {
        String invalidGoalString = "calories/1 calories/1";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_invalidNutrient_throwAthletiException() {
        String invalidGoalString = "calorie/1";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalDelete_nonIntegerInput_throwAthletiException() {
        String nonIntegerInput = "nonInteger";
        assertThrows(AthletiException.class, () -> parseDietGoalDelete(nonIntegerInput));
    }

    @Test
    void parseDietGoalDelete_nonPositiveIntegerInput_throwAthletiException() {
        String nonIntegerInput = "0";
        assertThrows(AthletiException.class, () -> parseDietGoalDelete(nonIntegerInput));
    }
}
