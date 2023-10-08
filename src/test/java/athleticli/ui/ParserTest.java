package athleticli.ui;

import static athleticli.ui.Parser.parseCommand;
import static athleticli.ui.Parser.splitCommandWordAndArgs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import athleticli.commands.ByeCommand;
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
}
