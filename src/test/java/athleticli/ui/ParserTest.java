package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.ListDietCommand;
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
import java.time.format.DateTimeFormatter;

import static athleticli.ui.Parser.parseCommand;
import static athleticli.ui.Parser.parseDietGoalSetEdit;
import static athleticli.ui.Parser.splitCommandWordAndArgs;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    void parseCommand_addDietCommand_expectAddDietCommand() throws AthletiException {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 fat/4";
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
        final String addDietCommandString = "add-diet protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingProteinExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingCarbExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingFatExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyCaloriesExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/ protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyProteinExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/ carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyCarbExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/ fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyFatExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 fat/";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidCaloriesExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/abc protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidProteinExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/abc carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidCarbExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/abc fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidFatExpectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 fat/abc";
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
    void parseDietGoalSet_oneValidGoal_oneGoalInList() {
        String oneValidGoalString = "calories/60";
        assertDoesNotThrow(() -> parseDietGoalSetEdit(oneValidGoalString));
    }

    @Test
    void parseDietGoalSet_oneValidOneInvalidGoal_throwAthletiException() {
        String oneValidOneInvalidGoalString = "calories/60 protein/protine";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(oneValidOneInvalidGoalString));
    }

    @Test
    void parseDietGoalSet_zeroTargetValue_throwAthletiException() {
        String zeroTargetValueGoalString = "calories/0";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(zeroTargetValueGoalString));
    }

    @Test
    void parseDietGoalSet_oneInvalidGoal_throwAthlethiException() {
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
    void parseActivityEdit_validInput_returnActivityEdit() throws AthletiException {
        String validInput = "1 Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00";
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
        String validInput = "2 Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000";
        assertDoesNotThrow(() -> Parser.parseRunEdit(validInput));
    }

    @Test
    void parseCycleEdit_validInput_returnRunEdit() {
        String validInput = "2 Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000";
        assertDoesNotThrow(() -> Parser.parseCycleEdit(validInput));
    }

    @Test
    void parseCycleEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> Parser.parseCycleEdit(invalidInput));
    }

    @Test
    void parseSwimEdit_validInput_noExceptionThrown() throws AthletiException {
        String validInput = "2 Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 style/freestyle";
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
        String validInput = "Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00";
        Activity actual = Parser.parseActivity(validInput);
        LocalDateTime time = LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Activity expected = new Activity("Morning Run", 60, 10000, time);
        assertEquals(actual.getCaption(), expected.getCaption());
        assertEquals(actual.getMovingTime(), expected.getMovingTime());
        assertEquals(actual.getDistance(), expected.getDistance());
        assertEquals(actual.getStartDateTime(), expected.getStartDateTime());
    }

    @Test
    void parseDuration_validInput_durationParsed() throws AthletiException {
        String validInput = "60";
        int actual = Parser.parseDuration(validInput);
        int expected = 60;
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
        LocalDateTime expected = LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
        assertThrows(AthletiException.class, () -> Parser.checkMissingActivityArguments(-1,
                1,1));
    }

    @Test
    void checkMissingActivityArguments_noMissingArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkMissingActivityArguments(1, 1, 1));
    }

    @Test
    void parseRunCycle_validInput_activityParsed() throws AthletiException {
        String validInput = "Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00 elevation/60";
        Run actual = (Run) Parser.parseRunCycle(validInput, true);
        LocalDateTime time = LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Run expected = new Run("Morning Run", 60, 10000, time, 60);
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
        assertThrows(AthletiException.class, () -> Parser.checkMissingRunCycleArguments(1,
                1,1,-1));
    }

    @Test
    void checkMissingRunCycleArguments_noMissingArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkMissingRunCycleArguments(1, 1, 1, 1));
    }

    @Test
    void checkMissingSwimArguments_missingStyle_throwAthletiException() {
        assertThrows(AthletiException.class, () -> Parser.checkMissingSwimArguments(1,
                1,1, -1));
    }

    @Test
    void checkMissingSwimArguments_noMissingArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkMissingSwimArguments(1, 1, 1, 1));
    }

    @Test
    void checkEmptyActivityArguments_emptyCaption_throwAthletiException() {
        assertThrows(AthletiException.class, () -> Parser.checkEmptyActivityArguments("",
                " "," ", " "));
    }

    @Test
    void checkEmptyActivityArguments_noEmptyArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> Parser.checkEmptyActivityArguments("1", "1", "1", "1"));
    }

    @Test
    void parseSwim_validInput_swimParsed() throws AthletiException {
        String validInput = "Evening Swim duration/120 distance/20000 datetime/2021-09-01 18:00 style/freestyle";
        Swim actual = (Swim) Parser.parseSwim(validInput);
        LocalDateTime time = LocalDateTime.parse("2021-09-01 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Swim expected = new Swim("Evening Swim", 120, 20000, time, Swim.SwimmingStyle.FREESTYLE);
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
}
