package athleticli.parser;

import java.time.LocalDateTime;

import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

public class SleepParser {
    //@@author  DaDevChia
    /**
     * Parses the raw user input for an add sleep command and returns the corresponding command object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the slee0 add command.
     * @throws AthletiException
     */
    public static AddSleepCommand parseSleepAdd(String commandArgs) throws AthletiException {

        final int startTimeIndex = commandArgs.indexOf(Parameter.START_TIME_SEPARATOR);
        final int endTimeIndex = commandArgs.indexOf(Parameter.END_TIME_SEPARATOR);

        if (startTimeIndex == -1 || endTimeIndex == -1 || startTimeIndex > endTimeIndex) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        final String startTimeStr =
                commandArgs.substring(startTimeIndex + Parameter.START_TIME_SEPARATOR.length(), endTimeIndex)
                        .trim();
        final String endTimeStr =
                commandArgs.substring(endTimeIndex + Parameter.END_TIME_SEPARATOR.length()).trim();

        if (startTimeStr.isEmpty() || endTimeStr.isEmpty()) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        // Convert the strings to LocalDateTime
        final LocalDateTime startTime = Parser.parseDateTime(startTimeStr);
        final LocalDateTime endTime = Parser.parseDateTime(endTimeStr);

        //Check if the start time is before the end time
        if (startTime.isAfter(endTime)) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_END_BEFORE_START);
        }

        return new AddSleepCommand(startTime, endTime);
    }

    /**
     * Parses the raw user input for a delete sleep command and returns the corresponding command object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the sleep delete command.
     * @throws AthletiException
     */
    public static DeleteSleepCommand parseSleepDelete(String commandArgs) throws AthletiException {
        int index;

        try {
            index = Integer.parseInt(commandArgs.trim());
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_DELETE_NO_INDEX);
        }

        return new DeleteSleepCommand(index);
    }

    /**
     * Parses the raw user input for an edit sleep command and returns the corresponding command object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the sleep edit command.
     * @throws AthletiException
     */
    public static EditSleepCommand parseSleepEdit(String commandArgs) throws AthletiException {
        final int startTimeIndex = commandArgs.indexOf(Parameter.START_TIME_SEPARATOR);
        final int endTimeIndex = commandArgs.indexOf(Parameter.END_TIME_SEPARATOR);
        int index;

        if (startTimeIndex == -1 || endTimeIndex == -1 || startTimeIndex > endTimeIndex) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        try {
            index = Integer.parseInt(commandArgs.substring(0, startTimeIndex).trim());
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_EDIT_NO_INDEX);
        }

        String startTimeStr =
                commandArgs.substring(startTimeIndex + Parameter.START_TIME_SEPARATOR.length(), endTimeIndex)
                        .trim();
        String endTimeStr =
                commandArgs.substring(endTimeIndex + Parameter.END_TIME_SEPARATOR.length()).trim();

        if (startTimeStr.isEmpty() || endTimeStr.isEmpty()) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        // Convert the strings to LocalDateTime
        LocalDateTime startTime;
        LocalDateTime endTime;
        startTime = Parser.parseDateTime(startTimeStr);
        endTime = Parser.parseDateTime(endTimeStr);

        //Check if the start time is before the end time
        if (startTime.isAfter(endTime)) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_END_BEFORE_START);
        }

        return new EditSleepCommand(index, startTime, endTime);
    }
}
