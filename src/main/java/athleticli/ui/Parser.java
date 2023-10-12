package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.exceptions.AthletiException;
import athleticli.exceptions.UnknownCommandException;

import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;

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

            case CommandName.COMMAND_SLEEP_ADD:
                return parseSleepAdd(commandArgs);

            case CommandName.COMMAND_SLEEP_LIST:
                return new ListSleepCommand();
            
            case CommandName.COMMAND_SLEEP_EDIT:
                return parseSleepEdit(commandArgs);
            
            case CommandName.COMMAND_SLEEP_DELETE:
                return parseSleepDelete(commandArgs);

            default:
                throw new UnknownCommandException();
            }
        } catch (AthletiException e) {
            throw e;
        }
    }

    public static AddSleepCommand parseSleepAdd(String commandArgs) throws AthletiException {

        final String STARTMARKER = "/start";
        final String ENDMARKER = "/end";

        int startMarkerPos = commandArgs.indexOf(STARTMARKER);
        int endMarkerPos = commandArgs.indexOf(ENDMARKER);

        if (startMarkerPos == -1 || endMarkerPos == -1) {
            throw new AthletiException("Please specify both the start and end time of your sleep.");
        }

        if (startMarkerPos > endMarkerPos) {
            throw new AthletiException("Please specify the start time of your sleep before the end time.");
        }
    
        String startTime = commandArgs.substring(startMarkerPos + STARTMARKER.length(), endMarkerPos).trim();
        String endTime = commandArgs.substring(endMarkerPos + ENDMARKER.length()).trim();

        if(startTime.isEmpty() || endTime.isEmpty()) {
            throw new AthletiException("Please specify both the start and end time of your sleep.");
        }

        return new AddSleepCommand(startTime, endTime);
    }

    public static DeleteSleepCommand parseSleepDelete(String commandArgs) throws AthletiException {
        int index;

        try {
            index = Integer.parseInt(commandArgs.trim());
        } catch (NumberFormatException e) {
            throw new AthletiException("Please specify the index of the sleep record you want to delete.");
        }

        return new DeleteSleepCommand(index);
    }

    public static EditSleepCommand parseSleepEdit(String commandArgs) throws AthletiException {
        final String STARTMARKER = "/start";
        final String ENDMARKER = "/end";

        int startMarkerPos = commandArgs.indexOf(STARTMARKER);
        int endMarkerPos = commandArgs.indexOf(ENDMARKER);

        int index;

        if (startMarkerPos == -1 || endMarkerPos == -1) {
            throw new AthletiException("Please specify both the start and end time of your sleep.");
        }

        if (startMarkerPos > endMarkerPos) {
            throw new AthletiException("Please specify the start time of your sleep before the end time.");
        }

        try {
            index = Integer.parseInt(commandArgs.substring(0, startMarkerPos).trim());
        } catch (NumberFormatException e) {
            throw new AthletiException("Please specify the index of the sleep record you want to edit.");
        }

        String startTime = commandArgs.substring(startMarkerPos + STARTMARKER.length(), endMarkerPos).trim();
        String endTime = commandArgs.substring(endMarkerPos + ENDMARKER.length()).trim();

        if (startTime.isEmpty() || endTime.isEmpty()) {
            throw new AthletiException("Please specify both the start and end time of your sleep.");
        }

        return new EditSleepCommand(index, startTime, endTime);
    }

}
