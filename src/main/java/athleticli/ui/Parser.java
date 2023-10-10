package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.exceptions.AthletiException;
import athleticli.exceptions.UnknownCommandException;

/**
 * Defines the basic methods for command parser.
 */
public class Parser {
    /**
     * Splits the raw user input into two parts, and then returns them.
     * The first part is the command type, while the second part is the command arguments.
     * The second part can be empty.
     *
     * @param rawUserInput  The raw user input.
     * @return              A string array whose first element is the command type
     *                      and the second element is the command arguments.
     */
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" };
    }

    /**
     * Parses the raw user input and returns the corresponding command object.
     *
     * @param rawUserInput      The raw user input.
     * @return                  An object representing the command.
     * @throws AthletiException
     */
    public static Command parseCommand(String rawUserInput) throws AthletiException {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(rawUserInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        try {
            switch (commandType) {
            case CommandName.COMMAND_BYE:
                return new ByeCommand();
            default:
                throw new UnknownCommandException();
            }
        } catch (AthletiException e) {
            throw e;
        }
    }
}
