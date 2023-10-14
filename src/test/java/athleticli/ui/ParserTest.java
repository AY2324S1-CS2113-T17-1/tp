package athleticli.ui;

import static athleticli.ui.Parser.parseCommand;
import static athleticli.ui.Parser.splitCommandWordAndArgs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import athleticli.commands.ByeCommand;
import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;

import athleticli.exceptions.AthletiException;
import athleticli.exceptions.UnknownCommandException;

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
}
