package athleticli.parser;

import athleticli.commands.ByeCommand;
import athleticli.commands.activity.DeleteActivityGoalCommand;
import athleticli.commands.activity.EditActivityGoalCommand;
import athleticli.commands.activity.ListActivityGoalCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.DeleteDietGoalCommand;
import athleticli.commands.diet.EditDietCommand;
import athleticli.commands.diet.EditDietGoalCommand;
import athleticli.commands.diet.FindDietCommand;
import athleticli.commands.diet.ListDietCommand;
import athleticli.commands.diet.ListDietGoalCommand;
import athleticli.commands.diet.SetDietGoalCommand;
import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static athleticli.common.Config.DATE_TIME_FORMATTER;
import static athleticli.parser.Parser.getValueForMarker;
import static athleticli.parser.Parser.parseCommand;
import static athleticli.parser.Parser.parseDate;
import static athleticli.parser.Parser.parseNonNegativeInteger;
import static athleticli.parser.Parser.splitCommandWordAndArgs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        final String addSleepCommandString = "add-sleep start/2023-10-06 10:00 end/2023-10-06 11:00";
        assertInstanceOf(AddSleepCommand.class, parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_addSleepCommand_missingStartExpectAthletiException() {
        final String addSleepCommandString = "add-sleep end/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_addSleepCommand_missingEndExpectAthletiException() {
        final String addSleepCommandString = "add-sleep start/2023-10-06 10:00";
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
        final String editSleepCommandString = "edit-sleep 1 start/2023-10-06 10:00 end/2023-10-06 11:00";
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
        final String setDietGoalCommandString = "set-diet-goal weekly calories/1 protein/2 carb/3";
        assertInstanceOf(SetDietGoalCommand.class, parseCommand(setDietGoalCommandString));
    }

    @Test
    void parseCommand_editDietCommand_expectEditDietGoalCommand() throws AthletiException {
        final String editDietGoalCommandString = "edit-diet-goal weekly calories/1 protein/2 carb/3";
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
    void parseCommand_addDietCommand_duplicatedCaloriesExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 calories/2 protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_duplicatedProteinExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 protein/3 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_duplicatedCarbExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/3 carb/4 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_duplicatedFatExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/3 fat/4 fat/5 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_duplicatedDateTimeExpectAthletiException() {
        final String addDietCommandString =
                "add-diet calories/1 protein/2 carb/3 fat/4 datetime/2023-10-06 10:00 datetime/2023-10-06 " +
                        "11:00";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_deleteDietCommand_expectDeleteDietCommand() throws AthletiException {
        final String deleteDietCommandString = "delete-diet 1";
        assertInstanceOf(DeleteDietCommand.class, parseCommand(deleteDietCommandString));
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
    void parseCommand_listDietCommand_expectListDietCommand() throws AthletiException {
        final String listDietCommandString = "list-diet";
        assertInstanceOf(ListDietCommand.class, parseCommand(listDietCommandString));
    }

    @Test
    void parseCommand_editDietCommand_expectEditDietCommand() throws AthletiException {
        final String editDietCommandString =
                "edit-diet 1 calories/1 protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertInstanceOf(EditDietCommand.class, parseCommand(editDietCommandString));
    }

    @Test
    void parseCommand_editDietCommand_invalidCaloriesExpectAthletiException() {
        final String editDietCommandString =
                "edit-diet 1 calories/abc protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString));
    }

    @Test
    void parseCommand_editDietCommand_invalidProteinExpectAthletiException() {
        final String editDietCommandString =
                "edit-diet 1 calories/1 protein/abc carb/3 fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString));
    }

    @Test
    void parseCommand_editDietCommand_invalidCarbExpectAthletiException() {
        final String editDietCommandString =
                "edit-diet 1 calories/1 protein/2 carb/abc fat/4 datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString));
    }

    @Test
    void parseCommand_editDietCommand_invalidFatExpectAthletiException() {
        final String editDietCommandString =
                "edit-diet 1 calories/1 protein/2 carb/3 fat/abc datetime/2023-10-06 10:00";
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString));
    }

    @Test
    void parseCommand_editDietCommandMissingAllArgs_expectAthletiException() {
        final String editDietCommandString = "edit-diet 1";
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString));
    }

    @Test
    void parseCommand_findDietCommand_expectFindDietCommand() throws AthletiException {
        final String findDietCommandString = "find-diet 2021-09-01";
        assertInstanceOf(FindDietCommand.class, parseCommand(findDietCommandString));
    }

    @Test
    void parseCommand_findDietCommand_missingDateExpectAthletiException() {
        final String findDietCommandString = "find-diet";
        assertThrows(AthletiException.class, () -> parseCommand(findDietCommandString));
    }

    @Test
    void parseCommand_findDietCommand_invalidDateExpectAthletiException() {
        final String findDietCommandString1 = "find-diet 2021-09-01 06:00";
        final String findDietCommandString2 = "find-diet 2021-09-01 06:00:00";
        final String findDietCommandString3 = "find-diet 2021-09-01 06:00:00.000";
        final String findDietCommandString4 = "find-diet 01-09-2021";
        final String findDietCommandString5 = "find-diet 09-30-2021";
        final String findDietCommandString6 = "find-diet abc";
        assertThrows(AthletiException.class, () -> parseCommand(findDietCommandString1));
        assertThrows(AthletiException.class, () -> parseCommand(findDietCommandString2));
        assertThrows(AthletiException.class, () -> parseCommand(findDietCommandString3));
        assertThrows(AthletiException.class, () -> parseCommand(findDietCommandString4));
        assertThrows(AthletiException.class, () -> parseCommand(findDietCommandString5));
        assertThrows(AthletiException.class, () -> parseCommand(findDietCommandString6));
    }

    @Test
    void parseCommand_editDietCommand_invalidDateTimeFormatExpectAthletiException() {
        final String editDietCommandString1 =
                "edit-diet 1 calories/1 protein/2 carb/3 fat/4 datetime/2023-10-06";
        final String editDietCommandString2 = "edit-diet 1 calories/1 protein/2 carb/3 fat/4 datetime/10:00";
        final String editDietCommandString3 =
                "edit-diet 1 calories/1 protein/2 carb/3 fat/4 datetime/16-10-2023 10:00:00";
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString1));
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString2));
        assertThrows(AthletiException.class, () -> parseCommand(editDietCommandString3));
    }

    @Test
    void parseDateTime_validInput_dateTimeParsed() throws AthletiException {
        String validInput = "2021-09-01 06:00";
        LocalDateTime actual = Parser.parseDateTime(validInput);
        LocalDateTime expected = LocalDateTime.parse("2021-09-01 06:00", DATE_TIME_FORMATTER);
        assertEquals(actual, expected);
    }

    @Test
    void parseDateTime_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> Parser.parseDateTime(invalidInput));
    }

    @Test
    void parseDateTime_futureDateTime_throwAthletiException() {
        LocalDateTime futureDateTime = LocalDateTime.now().plusHours(1);
        assertThrows(AthletiException.class, () -> Parser.parseDateTime(futureDateTime.toString()));
    }

    @Test
    void parseDateTime_invalidInputWithSecond_throwAthletiException() {
        String invalidInput = "2021-09-01 06:00:00";
        assertThrows(AthletiException.class, () -> Parser.parseDateTime(invalidInput));
    }

    @Test
    void parseDateTime_invalidYear_throwAthletiException() {
        String invalidInput = "0000-01-01 00:01";
        assertThrows(AthletiException.class, () -> Parser.parseDateTime(invalidInput));
    }

    @Test
    void parseDateTime_invalidLeapYear_throwAthletiException() {
        String invalidInput = "2021-02-29 00:01";
        assertThrows(AthletiException.class, () -> Parser.parseDateTime(invalidInput));
    }

    @Test
    void parseDate_validInput_dateParsed() throws AthletiException {
        String validInput = "2021-09-01";
        LocalDate actual = parseDate(validInput);
        LocalDate expected = LocalDate.parse("2021-09-01");
        assertEquals(actual, expected);
    }

    @Test
    void parseDate_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> parseDate(invalidInput));
    }

    @Test
    void parseDate_invalidInputWithTime_throwAthletiException() {
        String invalidInput = "2021-09-01 06:00";
        assertThrows(AthletiException.class, () -> parseDate(invalidInput));
    }

    @Test
    void parseDate_futureDate_throwAthletiException() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertThrows(AthletiException.class, () -> parseDate(futureDate.toString()));
    }

    @Test
    void parseDate_invalidYear_throwAthletiException() {
        String invalidInput = "0000-01-01";
        assertThrows(AthletiException.class, () -> parseDate(invalidInput));
    }

    @Test
    void parseDate_invalidLeapYear_throwAthletiException() {
        String invalidInput = "2021-02-29";
        assertThrows(AthletiException.class, () -> parseDate(invalidInput));
    }

    @Test
    void parseCommand_editActivityGoalCommand_expectEditActivityGoalCommand() throws AthletiException {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance period/weekly target/20000";
        assertInstanceOf(EditActivityGoalCommand.class, parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandMissingSport_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal type/distance period/weekly target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandMissingType_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running period/weekly target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandMissingPeriod_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandMissingTarget_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandEmptySport_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/ type/distance period/weekly target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandEmptyType_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/ period/weekly target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandEmptyPeriod_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance period/ target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandEmptyTarget_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance period/weekly target/";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidSport_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/abc type/distance period/weekly target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidType_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/abc period/weekly target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidPeriod_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance period/abc target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidTarget_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance period/weekly target/abc";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidSportAndType_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/abc type/abc period/weekly target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidSportAndPeriod_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/abc type/distance period/abc target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidSportAndTarget_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/abc type/distance period/weekly target/abc";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidTypeAndPeriod_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/abc period/abc target/20000";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidTypeAndTarget_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/abc period/weekly target/abc";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_editActivityGoalCommandInvalidPeriodAndTarget_expectAthletiException() {
        final String editActivityGoalCommandString =
                "edit-activity-goal sport/running type/distance period/abc target/abc";
        assertThrows(AthletiException.class, () -> parseCommand(editActivityGoalCommandString));
    }

    @Test
    void parseCommand_listActivityGoalCommand_expectListActivityGoalCommand() throws AthletiException {
        final String listActivityGoalCommandString = "list-activity-goal";
        assertInstanceOf(ListActivityGoalCommand.class, parseCommand(listActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommand_expectDeleteActivityGoalCommand() throws AthletiException {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/running type/distance period/weekly";
        assertInstanceOf(DeleteActivityGoalCommand.class, parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandMissingSport_expectAthletiException() {
        final String deleteActivityGoalCommandString = "delete-activity-goal type/distance period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandMissingType_expectAthletiException() {
        final String deleteActivityGoalCommandString = "delete-activity-goal sport/running period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandMissingPeriod_expectAthletiException() {
        final String deleteActivityGoalCommandString = "delete-activity-goal sport/running type/distance";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandEmptySport_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/ type/distance period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandEmptyType_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/running type/ period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandEmptyPeriod_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/running type/distance period/";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandInvalidSport_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/abc type/distance period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandInvalidType_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/running type/abc period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandInvalidPeriod_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/running type/distance period/abc";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandInvalidSportAndType_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/abc type/abc period/weekly";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandInvalidSportAndPeriod_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/abc type/distance period/abc";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandInvalidTypeAndPeriod_expectAthletiException() {
        final String deleteActivityGoalCommandString =
                "delete-activity-goal sport/running type/abc period/abc";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseCommand_deleteActivityGoalCommandInvalidSportAndTypeAndPeriod_expectAthletiException() {
        final String deleteActivityGoalCommandString = "delete-activity-goal sport/abc type/abc period/abc";
        assertThrows(AthletiException.class, () -> parseCommand(deleteActivityGoalCommandString));
    }

    @Test
    void parseNonNegativeInteger_validInput_integerParsed() throws AthletiException {
        String validInput = "123";
        int actual = parseNonNegativeInteger(validInput, "invalid", "overflow");
        assertEquals(123, actual);
    }

    @Test
    void parseNonNegativeInteger_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class,
                () -> parseNonNegativeInteger(invalidInput, "invalid", "overflow"));
    }

    @Test
    void parseNonNegativeInteger_negativeInput_throwAthletiException() {
        String negativeInput = "-1";
        assertThrows(AthletiException.class,
                () -> parseNonNegativeInteger(negativeInput, "invalid", "overflow"));
    }

    @Test
    void parseNonNegativeInteger_overflowInput_throwAthletiException() {
        String overflowInput = "2147483648";
        assertThrows(AthletiException.class,
                () -> parseNonNegativeInteger(overflowInput, "invalid", "overflow"));
    }

    @Test
    void parseNonNegativeInteger_zeroInput_integerParsed() throws AthletiException {
        String zeroInput = "0";
        int actual = parseNonNegativeInteger(zeroInput, "invalid", "overflow");
        assertEquals(0, actual);
    }

    @Test
    void parseNonNegativeInteger_emptyInput_throwAthletiException() {
        String emptyInput = "";
        assertThrows(AthletiException.class,
                () -> parseNonNegativeInteger(emptyInput, "invalid", "overflow"));
    }

    @Test
    void parseNonNegativeInteger_floatingPointInput_throwAthletiException() {
        String floatingPointInput = "1.0";
        assertThrows(AthletiException.class,
                () -> parseNonNegativeInteger(floatingPointInput, "invalid", "overflow"));
    }

    @Test
    void getValueForMarker_validInput_returnValue() {
        String validInput = "2 calories/1 protein/2 carb/3 fat/4 datetime/2023-10-06 10:00";
        String caloriesActual = getValueForMarker(validInput, Parameter.CALORIES_SEPARATOR);
        String proteinActual = getValueForMarker(validInput, Parameter.PROTEIN_SEPARATOR);
        String carbActual = getValueForMarker(validInput, Parameter.CARB_SEPARATOR);
        String fatActual = getValueForMarker(validInput, Parameter.FAT_SEPARATOR);
        String datetimeActual = getValueForMarker(validInput, Parameter.DATETIME_SEPARATOR);
        assertEquals("1", caloriesActual);
        assertEquals("2", proteinActual);
        assertEquals("3", carbActual);
        assertEquals("4", fatActual);
        assertEquals("2023-10-06 10:00", datetimeActual);
    }

    @Test
    void getValueForMarker_invalidInput_returnEmptyString() {
        String invalidInput = "2 calorie/1 proteins/2 carbs/3 fats/4 date/2023-10-06";
        String caloriesActual = getValueForMarker(invalidInput, Parameter.CALORIES_SEPARATOR);
        String proteinActual = getValueForMarker(invalidInput, Parameter.PROTEIN_SEPARATOR);
        String carbActual = getValueForMarker(invalidInput, Parameter.CARB_SEPARATOR);
        String fatActual = getValueForMarker(invalidInput, Parameter.FAT_SEPARATOR);
        String datetimeActual = getValueForMarker(invalidInput, Parameter.DATETIME_SEPARATOR);
        assertEquals("", caloriesActual);
        assertEquals("", proteinActual);
        assertEquals("", carbActual);
        assertEquals("", fatActual);
        assertEquals("", datetimeActual);
    }
}
