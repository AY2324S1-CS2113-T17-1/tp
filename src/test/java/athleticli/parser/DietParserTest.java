package athleticli.parser;

import athleticli.data.diet.DietGoal;
import athleticli.data.diet.UnhealthyDietGoal;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static athleticli.parser.DietParser.checkDuplicateDietArguments;
import static athleticli.parser.DietParser.checkEmptyDietArguments;
import static athleticli.parser.DietParser.checkMissingDietArguments;
import static athleticli.parser.DietParser.isArgumentDuplicate;
import static athleticli.parser.DietParser.isArgumentMissing;
import static athleticli.parser.DietParser.parseDiet;
import static athleticli.parser.DietParser.parseDietEdit;
import static athleticli.parser.DietParser.parseDietGoalDelete;
import static athleticli.parser.DietParser.parseDietGoalSetAndEdit;
import static athleticli.parser.DietParser.parseDietIndex;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DietParserTest {
    //@@author  nihalzp
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

    @Test
    void isArgumentMissing_markerPresent_returnFalse() {
        String commandArgs = "protein/1 carb/2 fat/3 datetime/2021-10-06 10:00";
        String marker = "carb/";
        assert (!isArgumentMissing(commandArgs, marker));
    }

    @Test
    void isArgumentMissing_markerNotPresent_returnTrue() {
        String commandArgs = "protein/1 carb/2 fat/3 datetime/2021-10-06 10:00";
        String marker = "calories/";
        assert (isArgumentMissing(commandArgs, marker));
    }

    @Test
    void isArgumentDuplicate_markerDuplicated_returnTrue() {
        String commandArgs = "protein/1 carb/2 protein/5 fat/3 datetime/2021-10-06 10:00";
        String marker = "protein/";
        assert (isArgumentDuplicate(commandArgs, marker));
    }

    @Test
    void isArgumentDuplicate_markerNotDuplicated_returnFalse() {
        String commandArgs = "protein/1 carb/2 calories/5 fat/3 datetime/2021-10-06 10:00";
        String marker = "calories/";
        assert (!isArgumentDuplicate(commandArgs, marker));
    }

    @Test
    void isArgumentDuplicate_markerNotPresent_returnFalse() {
        String commandArgs = "protein/1 carb/2 fat/3 datetime/2021-10-06 10:00";
        String marker = "calories/";
        assert (!isArgumentDuplicate(commandArgs, marker));
    }

    @Test
    void checkMissingDietArguments_noMissingArguments_noExceptionThrown() throws AthletiException {
        String noMissingArguments = "calories/1 protein/2 carb/3 fat/4 datetime/2021-10-06 10:00";
        checkMissingDietArguments(noMissingArguments);
    }

    @Test
    void checkMissingDietArguments_missingCalories_throwAthletiException() {
        String missingCalories = "protein/1 carb/2 fat/3 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkMissingDietArguments(missingCalories));
    }

    @Test
    void checkMissingDietArguments_missingProtein_throwAthletiException() {
        String missingProtein = "calories/1 carb/2 fat/3 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkMissingDietArguments(missingProtein));
    }

    @Test
    void checkMissingDietArguments_missingCarb_throwAthletiException() {
        String missingCarb = "calories/1 protein/2 fat/3 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkMissingDietArguments(missingCarb));
    }

    @Test
    void checkMissingDietArguments_missingFat_throwAthletiException() {
        String missingFat = "calories/1 protein/2 carb/3 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkMissingDietArguments(missingFat));
    }

    @Test
    void checkMissingDietArguments_missingDatetime_throwAthletiException() {
        String missingDatetime = "calories/1 protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> checkMissingDietArguments(missingDatetime));
    }


    @Test
    void checkDuplicateDietArguments_noDuplicateArguments_noExceptionThrown() throws AthletiException {
        String noDuplicateArguments = "calories/1 protein/2 carb/3 fat/4 datetime/2021-10-06 10:00";
        checkMissingDietArguments(noDuplicateArguments);
    }

    @Test
    void checkDuplicateDietArguments_duplicateCalories_throwAthletiException() {
        String duplicateCalories = "calories/1 calories/2 protein/2 carb/3 fat/4 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkDuplicateDietArguments(duplicateCalories));
    }

    @Test
    void checkDuplicateDietArguments_duplicateProtein_throwAthletiException() {
        String duplicateProtein = "calories/1 protein/2 protein/2 carb/3 fat/4 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkDuplicateDietArguments(duplicateProtein));
    }

    @Test
    void checkDuplicateDietArguments_duplicateCarb_throwAthletiException() {
        String duplicateCarb = "calories/1 protein/2 carb/3 carb/3 fat/4 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkDuplicateDietArguments(duplicateCarb));
    }

    @Test
    void checkDuplicateDietArguments_duplicateFat_throwAthletiException() {
        String duplicateFat = "calories/1 protein/2 carb/3 fat/4 fat/4 datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkDuplicateDietArguments(duplicateFat));
    }

    @Test
    void checkDuplicateDietArguments_duplicateDatetime_throwAthletiException() {
        String duplicateDatetime =
                "calories/1 protein/2 carb/3 fat/4 datetime/2021-10-06 10:00 " + "datetime/2021-10-06 10:00";
        assertThrows(AthletiException.class, () -> checkDuplicateDietArguments(duplicateDatetime));
    }

    //@@author  yicheng-toh
    @Test
    void parseDietGoalSetEdit_unhealthyDietGoal_expectUnhealthyDietGoal() throws AthletiException {
        String oneValidOneInvalidGoalString = "WEEKLY unhealthy fats/20";
        ArrayList<DietGoal> dietGoals = parseDietGoalSetAndEdit(oneValidOneInvalidGoalString);
        assert dietGoals.get(0) instanceof UnhealthyDietGoal;
    }

    @Test
    void parseDietGoalSetEdit_noInput_throwAthletiException() {
        String invalidGoalString = " ";
        assertThrows(AthletiException.class, () -> parseDietGoalSetAndEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_inputHasNoTimeSpan_throwAthletiException() {
        String invalidGoalString = "fats/10";
        assertThrows(AthletiException.class, () -> parseDietGoalSetAndEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_oneValidOneInvalidGoal_throwAthletiException() {
        String invalidGoalString = "calories/60 protein/protine";
        assertThrows(AthletiException.class, () -> parseDietGoalSetAndEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_zeroTargetValue_throwAthletiException() {
        String invalidGoalString = "WEEKLY calories/0";
        assertThrows(AthletiException.class, () -> parseDietGoalSetAndEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_oneInvalidGoal_throwAthletiException() {
        String invalidGoalString = "WEEKLY calories/caloreis protein/protein";
        assertThrows(AthletiException.class, () -> parseDietGoalSetAndEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_repeatedDietGoal_throwAthletiException() {
        String invalidGoalString = "WEEKLY calories/1 calories/1";
        assertThrows(AthletiException.class, () -> parseDietGoalSetAndEdit(invalidGoalString));
    }

    @Test
    void parseDietGoalSetEdit_invalidNutrient_throwAthletiException() {
        String invalidGoalString = "WEEKLY calorie/1";
        assertThrows(AthletiException.class, () -> parseDietGoalSetAndEdit(invalidGoalString));
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
