package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.ListDietCommand;
import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;
import athleticli.exceptions.AthletiException;
import athleticli.exceptions.UnknownCommandException;
import org.junit.jupiter.api.Test;

import static athleticli.ui.Parser.parseCommand;
import static athleticli.ui.Parser.parseDietGoalSetEdit;
import static athleticli.ui.Parser.splitCommandWordAndArgs;
import static athleticli.ui.Parser.verifyValidNutrients;

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
    void parseCommand_unknownCommand_expectUnknownCommandException() {
        final String unknownCommand = "hello";
        assertThrows(UnknownCommandException.class, () -> parseCommand(unknownCommand));
    }

    @Test
    void parseCommand_byeCommand_expectByeCommand() throws AthletiException {
        final String byeCommand = "bye";
        assertInstanceOf(ByeCommand.class, parseCommand(byeCommand));
    }

    @Test
    void parseCommand_addSleepCommand_expectAddSleepCommand() throws AthletiException {
        final String addSleepCommandString = "add-sleep /start 10:00 PM /end 6:00 AM";
        assertInstanceOf(AddSleepCommand.class, parseCommand(addSleepCommandString));
    }

    @Test
    void parseCommand_editSleepCommand_expectEditSleepCommand() throws AthletiException {
        final String editSleepCommandString = "edit-sleep 1 /start 10:00 PM /end 6:00 AM";
        assertInstanceOf(EditSleepCommand.class, parseCommand(editSleepCommandString));
    }

    @Test
    void parseCommand_deleteSleepCommand_expectDeleteSleepCommand() throws AthletiException {
        final String deleteSleepCommandString = "delete-sleep 1";
        assertInstanceOf(DeleteSleepCommand.class, parseCommand(deleteSleepCommandString));
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
    void verifyNutrient_validNutrient_returnTrue() {
        assertTrue(verifyValidNutrients("calories"));
    }

    @Test
    void verifyNutrient_validNutrient_returnFalse() {
        assertFalse(verifyValidNutrients("invalidNutrients"));
    }

    @Test
    void parseDietGoalSet_oneValidGoal_oneGoalInList() throws AthletiException {
        String oneValidGoalString = "calories/60";
        assertDoesNotThrow(() -> parseDietGoalSetEdit(oneValidGoalString));
    }

    @Test
    void parseDietGoalSet_oneValidOneInvalidGoal_throwAthletiException(){
        String oneValidOneInvalidGoalString = "calories/60 protein/protine";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(oneValidOneInvalidGoalString));
    }
    @Test
    void parseDietGoalSet_zeroTargetValue_throwAthletiException(){
        String zeroTargetValueGoalString = "calories/0";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(zeroTargetValueGoalString));
    }
    @Test
    void parseDietGoalSet_oneInvalidGoal_throwAthlethiException(){
        String invalidGoalString = "calories/caloreis protein/protein";
        assertThrows(AthletiException.class, () -> parseDietGoalSetEdit(invalidGoalString));
    }
}
