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
import static athleticli.ui.Parser.splitCommandWordAndArgs;
import static org.junit.jupiter.api.Assertions.*;


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

    // test exceptions for diet
    @Test
    void parseCommand_addDietCommand_missingCalories_expectAthletiException() {
        final String addDietCommandString = "add-diet protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingProtein_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingCarb_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_missingFat_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyCalories_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/ protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyProtein_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/ carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyCarb_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/ fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_emptyFat_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 fat/";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidCalories_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/abc protein/2 carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidProtein_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/abc carb/3 fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidCarb_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/abc fat/4";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_addDietCommand_invalidFat_expectAthletiException() {
        final String addDietCommandString = "add-diet calories/1 protein/2 carb/3 fat/abc";
        assertThrows(AthletiException.class, () -> parseCommand(addDietCommandString));
    }

    @Test
    void parseCommand_deleteDietCommand_invalidIndex_expectAthletiException() {
        final String deleteDietCommandString = "delete-diet abc";
        assertThrows(AthletiException.class, () -> parseCommand(deleteDietCommandString));
    }

    @Test
    void parseCommand_deleteDietCommand_emptyIndex_expectAthletiException() {
        final String deleteDietCommandString = "delete-diet";
        assertThrows(AthletiException.class, () -> parseCommand(deleteDietCommandString));
    }
}
