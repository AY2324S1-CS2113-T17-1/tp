package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.DeleteDietGoalCommand;
import athleticli.commands.diet.EditDietGoalCommand;
import athleticli.commands.diet.ListDietCommand;
import athleticli.commands.diet.ListDietGoalCommand;
import athleticli.commands.diet.SetDietGoalCommand;
import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;
import athleticli.data.activity.Activity;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static athleticli.ui.Parser.checkEmptyDietArguments;
import static athleticli.ui.Parser.checkMissingDietArguments;
import static athleticli.ui.Parser.parseCalories;
import static athleticli.ui.Parser.parseCarb;
import static athleticli.ui.Parser.parseCommand;
import static athleticli.ui.Parser.parseDiet;
import static athleticli.ui.Parser.parseDietGoalDelete;
import static athleticli.ui.Parser.parseDietGoalSetEdit;
import static athleticli.ui.Parser.parseDietIndex;
import static athleticli.ui.Parser.parseFat;
import static athleticli.ui.Parser.parseProtein;
import static athleticli.ui.Parser.splitCommandWordAndArgs;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void splitCommandWordAndArgs_noArgs_expectTwoParts() {
        final String commandWithNoArgs = "bye";
        assertEquals(splitCommandWordAndArgs(commandWithNoArgs).length, 2);
    }

    @Test
    void splitCommandWordAndArgs_multipleArgs_expectTwoParts() {
        final String commandWithMultipleArgs = "set-diet-goal calories/1 carb/3";
        assertEquals(splitCommandWordAndArgs(commandWithMultipleArgs).length, 2);
    }

    @Test
    void parseCommand_unknownCommand_expectAthletiException() {
        final String unknownCommand = "hello";
        assertThrows(AthletiException.class, () -> parseCommand(unknownCommand));
    }

    @Test
    void parseCommand_byeCommand_expectByeCommand() throws AthletiException {
        final String byeCommand = "bye";
        assertInstanceOf(ByeCommand.class, parseCommand(byeCommand));
    }

    @Test
    void parseCommand_addSleepCommand_expectAddSleepCommand() throws AthletiException {
        final String addSleepCommandString = "add-sleep start/06-10-2021 10:00 end/07-10-2021 06:00";
        assertInstanceOf(AddSleepCommand.class, parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_addSleepCommand_missingStartExpectAthletiException() {
        final String addSleepCommandString = "add-sleep end/07-10-2021 06:00";
        assertThrows(AthletiException.class, () -> parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_addSleepCommand_missingEndExpectAthletiException() {
        final String addSleepCommandString = "add-sleep start/07-10-2021 06:00";
        assertThrows(AthletiException.class, () -> parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_addSleepCommand_missingBothExpectAthletiException() {
        final String addSleepCommandString = "add-sleep start/ end/";
        assertThrows(AthletiException.class, () -> parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_addSleepCommand_invalidDatetimeExpectAthletiException() {
        final String addSleepCommandString = "add-sleep start/07-10-2021 06:00 end/07-10-2021 05:00";
        assertThrows(AthletiException.class, () -> parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_editSleepCommand_expectEditSleepCommand() throws AthletiException {
        final String editSleepCommandString = "edit-sleep 1 start/06-10-2021 10:00 end/07-10-2021 06:00";
        assertInstanceOf(EditSleepCommand.class, parseCommand(editSleepCommandString));
    }

    @Test
    void parseCommand_editSleepCommand_missingStartExpectAthletiException() {
        final String editSleepCommandString = "edit-sleep 1 end/07-10-2021 06:00";
        assertThrows(AthletiException.class, () -> parseCommand(editSleepCommandString));
    }

    @Test
    void parseCommand_editSleepCommand_missingEndExpectAthletiException() {
        final String editSleepCommandString = "edit-sleep 1 start/07-10-2021 06:00";
        assertThrows(AthletiException.class, () -> parseCommand(editSleepCommandString));
    }

    @Test
    void parseCommand_editSleepCommand_missingBothExpectAthletiException() {
        final String editSleepCommandString = "edit-sleep 1 start/ end/";
        assertThrows(AthletiException.class, () -> parseCommand(editSleepCommandString));
    }

    @Test
    void parseCommand_editSleepCommand_invalidDatetimeExpectAthletiException() {
        final String editSleepCommandString = "edit-sleep 1 start/07-10-2021 07:00 end/07-10-2021 06:00";
        assertThrows(AthletiException.class, () -> parseCommand(editSleepCommandString));
    }

    @Test
    void parseCommand_editSleepCommand_invalidIndexExpectAthletiException() {
        final String editSleepCommandString = "edit-sleep abc start/06-10-2021 10:00 end/07-10-2021 06:00";
        assertThrows(AthletiException.class, () -> parseCommand(editSleepCommandString));
    }

    @Test
    void parseCommand_deleteSleepCommand_expectDeleteSleepCommand() throws AthletiException {
        final String deleteSleepCommandString = "delete-sleep 1";
        assertInstanceOf(DeleteSleepCommand.class, parseCommand(deleteSleepCommandString));
    }

    @Test
    void parseCommand_deleteSleepCommand_invalidIndexExpectAthletiException() {
        final String deleteSleepCommandString = "delete-sleep abc";
        assertThrows(AthletiException.class, () -> parseCommand(deleteSleepCommandString));
    }

    @Test
    void parseCommand_listSleepCommand_expectListSleepCommand() throws AthletiException {
        final String listSleepCommandString = "list-sleep";
        assertInstanceOf(ListSleepCommand.class, parseCommand(listSleepCommandString));
    }

    @Test
    void parseCommand_setDietGoalCommand_expectSetDietGoalCommand() throws AthletiException {
        final String setDietGoalCommandString = "set-diet-goal calories/1 protein/2 carb/3";
        assertInstanceOf(SetDietGoalCommand.class, parseCommand(setDietGoalCommandString));
    }

    @Test
    void parseCommand_editDietCommand_expectEditDietGoalCommand() throws AthletiException {
        final String editDietGoalCommandString = "edit-diet-goal calories/1 protein/2 carb/3";
        assertInstanceOf(EditDietGoalCommand.class, parseCommand(editDietGoalCommandString));
    }

    @Test
    void parseCommand_listDietGoalCommand_expectListDietGoalCommand() throws AthletiException {
        final String listDietCommandString = "list-diet-goal";
        assertInstanceOf(ListDietGoalCommand.class, parseCommand(listDietCommandString));
    }

    @Test
    void parseCommand_deleteDietGoalCommand_expectDeleteDietGoalCommand() throws AthletiException {
        final String deleteDietGoalCommandString = "delete-diet-goal 1";
        assertInstanceOf(DeleteDietGoalCommand.class, parseCommand(deleteDietGoalCommandString));
    }

    @Test
    void parseCommand_addDietCommand_expectAddDietCommand() throws AthletiException {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertInstanceOf(AddDietCommand.class, parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_deleteDietCommand_expectDeleteDietCommand() throws AthletiException {
        final String deleteDietCommandString = "delete-diet 1";
        assertInstanceOf(DeleteDietCommand.class, parseCommand(deleteDietCommandString));
    }

    @Test
    void parseCommand_listDietCommand_expectListDietCommand() throws AthletiException {
        final String listDietCommandString = "list-diet";
        assertInstanceOf(ListDietCommand.class, parseCommand(listDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingCaloriesExpectAthletiException() {
        final String addDietCommandString = "add-diet protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingProteinExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingCarbExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingFatExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingDateTimeExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyCaloriesExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/ protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyProteinExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/ carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyCarbExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/ fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyFatExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/3 fat/ datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyDateTimeExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 fat/4 datetime/";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidCaloriesExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/abc protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidProteinExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/abc carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidCarbExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/abc fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidFatExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/3 fat/abc datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidDateTimeFormatExpectAthletiException() {
        final String addDietCommandString1 = "add-diet calories/1 protein/2 carb/3 fat/4 datetime/2023-10-06";
        final String addDietCommandString2 = "add-diet calories/1 protein/2 carb/3 fat/4 datetime/10:00";
        final String addDietCommandString3 =
                "add-diet calories/1 protein/2 carb/3 fat/4 datetime/16-10-2023 10:00:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString1));
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString2));
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString3));
    }

    @Test
    void parseCommand_addDietCommand_negativeCaloriesExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/-1 protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_negativeProteinExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/-2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_negativeCarbExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/-3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_negativeFatExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/3 fat/-4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_deleteDietCommand_invalidIndexExpectAthletiException() {
        final String deleteDietCommandString = "delete-diet abc";
        assertThrows(AthletiException.class, () -> parseCommand(deleteDietCommandString));
    }

    @Test
    void parseCommand_deleteDietCommand_emptyIndexExpectAthletiException() {
        final String deleteDietCommandString = "delete-diet";
        assertThrows(AthletiException.class, () -> parseCommand(deleteDietCommandString));
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
    void parseDiet_emptyInput_throwAthletiException() {
        String emptyInput = "";
        assertThrows(AthletiException.class, () -> parseDiet(emptyInput));
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
    void parseActivityIndex_validIndex_returnIndex() throws AthletiException {
        int expected = 5;
        int actual = Parser.parseActivityIndex("5");
        assertEquals(expected, actual);
    }

    @Test
    void parseActivityIndex_invalidIndex_throwAthletiException() {
        assertThrows(AthletiException.class, () -> Parser.parseActivityIndex("abc"));
    }

    @Test
    void parseActivityEdit_validInput_returnActivityEdit() {
        String validInput = "1 Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00";
        assertDoesNotThrow(() -> Parser.parseActivityEdit(validInput));
    }

    @Test
    void parseActivityEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> Parser.parseActivityEdit(invalidInput));
    }

    @Test
    void parseRunEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> Parser.parseRunEdit(invalidInput));
    }

    @Test
    void parseRunEdit_validInput_returnRunEdit() {
        String validInput =
                "2 Evening Ride duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 elevation/1000";
        assertDoesNotThrow(() -> Parser.parseRunEdit(validInput));
    }

    @Test
    void parseCycleEdit_validInput_returnRunEdit() {
        String validInput =
                "2 Evening Ride duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 elevation/1000";
        assertDoesNotThrow(() -> Parser.parseCycleEdit(validInput));
    }

    @Test
    void parseCycleEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> Parser.parseCycleEdit(invalidInput));
    }

    @Test
    void parseSwimEdit_validInput_noExceptionThrown() {
        String validInput =
                "2 Evening Ride duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 style/freestyle";
        assertDoesNotThrow(() -> Parser.parseSwimEdit(validInput));
    }

    @Test
    void parseSwimEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> Parser.parseRunEdit(invalidInput));
    }

    @Test
    void parseActivityEditIndex_validInput_returnIndex() throws AthletiException {
        int expected = 5;
        int actual = Parser.parseActivityEditIndex("5");
        assertEquals(expected, actual);
    }

    @Test
    void parseActivityListDetail_flagPresent_returnTrue() throws AthletiException {
        String input = "list-activity -d";
        assertTrue(Parser.parseActivityListDetail(input));
    }

    @Test
    void parseActivityListDetail_flagAbsent_returnFalse() throws AthletiException {
        String input = "list-activity";
        assertFalse(Parser.parseActivityListDetail(input));
    }

    @Test
    void parseActivity_validInput_activityParsed() throws AthletiException {
        String validInput = "Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00";
        Activity actual = Parser.parseActivity(validInput);
        LocalTime duration = LocalTime.parse("01:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime time =
                LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Activity expected = new Activity("Morning Run", duration, 10000, time);
        assertEquals(actual.getCaption(), expected.getCaption());
        assertEquals(actual.getMovingTime(), expected.getMovingTime());
        assertEquals(actual.getDistance(), expected.getDistance());
        assertEquals(actual.getStartDateTime(), expected.getStartDateTime());
    }

    @Test
    void parseDuration_validInput_durationParsed() throws AthletiException {
        String validInput = "01:00:00";
        LocalTime actual = Parser.parseDuration(validInput);
        LocalTime expected = LocalTime.parse("01:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        assertEquals(actual, expected);
    }

    @Test
    void parseDuration_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> Parser.parseDuration(invalidInput));
    }

    @Test
    void parseDateTime_validInput_dateTimeParsed() throws AthletiException {
        String validInput = "2021-09-01 06:00";
        LocalDateTime actual = Parser.parseDateTime(validInput);
        LocalDateTime expected =
                LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(actual, expected);
    }

    @Test
    void parseDateTime_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> Parser.parseDateTime(invalidInput));
    }

    @Test
    void parseDistance_validInput_distanceParsed() throws AthletiException {
        String validInput = "10000";
        int actual = Parser.parseDistance(validInput);
        int expected = 10000;
        assertEquals(actual, expected);
    }

    @Test
    void parseDistance_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> Parser.parseDistance(invalidInput));
    }

    @Test
    void checkMissingActivityArguments_missingDuration_throwAthletiException() {
        assertThrows(AthletiException.class, () -> Parser.checkMissingActivityArguments(-1, 1, 1));
    }

    @Test
    void checkMissingActivityArguments_noMissingArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkMissingActivityArguments(1, 1, 1));
    }

    @Test
    void parseRunCycle_validInput_activityParsed() throws AthletiException {
        String validInput =
                "Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00 elevation/60";
        Run actual = (Run) Parser.parseRunCycle(validInput, true);
        LocalTime movingTime = LocalTime.parse("01:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime time =
                LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Run expected = new Run("Morning Run", movingTime, 10000, time, 60);
        assertEquals(actual.getCaption(), expected.getCaption());
        assertEquals(actual.getMovingTime(), expected.getMovingTime());
        assertEquals(actual.getDistance(), expected.getDistance());
        assertEquals(actual.getStartDateTime(), expected.getStartDateTime());
        assertEquals(actual.getElevationGain(), expected.getElevationGain());
    }

    @Test
    void parseElevation_validInput_elevationParsed() throws AthletiException {
        String validInput = "60";
        int actual = Parser.parseElevation(validInput);
        int expected = 60;
        assertEquals(actual, expected);
    }

    @Test
    void parseElevation_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> Parser.parseElevation(invalidInput));
    }

    @Test
    void checkMissingRunCycleArguments_missingElevation_throwAthletiException() {
        assertThrows(AthletiException.class, () -> Parser.checkMissingRunCycleArguments(1, 1, 1, -1));
    }

    @Test
    void checkMissingRunCycleArguments_noMissingArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkMissingRunCycleArguments(1, 1, 1, 1));
    }

    @Test
    void checkMissingSwimArguments_missingStyle_throwAthletiException() {
        assertThrows(AthletiException.class, () -> Parser.checkMissingSwimArguments(1, 1, 1, -1));
    }

    @Test
    void checkMissingSwimArguments_noMissingArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkMissingSwimArguments(1, 1, 1, 1));
    }

    @Test
    void checkEmptyActivityArguments_emptyCaption_throwAthletiException() {
        assertThrows(AthletiException.class, () -> Parser.checkEmptyActivityArguments("", " ", " ", " "));
    }

    @Test
    void checkEmptyActivityArguments_noEmptyArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkEmptyActivityArguments("1", "1", "1", "1"));
    }

    @Test
    void parseSwim_validInput_swimParsed() throws AthletiException {
        String validInput =
                "Evening Swim duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 style/freestyle";
        Swim actual = (Swim) Parser.parseSwim(validInput);
        LocalTime movingTime = LocalTime.parse("02:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime time =
                LocalDateTime.parse("2021-09-01 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Swim expected = new Swim("Evening Swim", movingTime, 20000, time, Swim.SwimmingStyle.FREESTYLE);
        assertEquals(actual.getCaption(), expected.getCaption());
        assertEquals(actual.getMovingTime(), expected.getMovingTime());
        assertEquals(actual.getDistance(), expected.getDistance());
        assertEquals(actual.getStartDateTime(), expected.getStartDateTime());
        assertEquals(actual.getStyle(), expected.getStyle());
    }

    @Test
    void parseSwimmingStyle_validInput_styleParsed() throws AthletiException {
        String validInput = "freestyle";
        Swim.SwimmingStyle actual = Parser.parseSwimmingStyle(validInput);
        Swim.SwimmingStyle expected = Swim.SwimmingStyle.FREESTYLE;
        assertEquals(actual, expected);
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
