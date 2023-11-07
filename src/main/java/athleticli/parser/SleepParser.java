package athleticli.parser;

import java.time.LocalDateTime;

import athleticli.data.Goal;
import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepGoal;
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

    public static SleepGoal parseSleepGoal(String commandArgs) throws AthletiException {
        final int goalTypeIndex = commandArgs.indexOf(Parameter.TYPE_SEPARATOR);
        final int periodIndex = commandArgs.indexOf(Parameter.PERIOD_SEPARATOR);
        final int targetValueIndex = commandArgs.indexOf(Parameter.TARGET_SEPARATOR);

        checkMissingSleepGoalParameters(goalTypeIndex, periodIndex, targetValueIndex);

        if (goalTypeIndex > periodIndex || periodIndex > targetValueIndex) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_PARAMETERS);
        }

        final String type = commandArgs.substring(goalTypeIndex + Parameter.TYPE_SEPARATOR.length(), periodIndex).trim();
        final String period = commandArgs.substring(periodIndex + Parameter.PERIOD_SEPARATOR.length(), targetValueIndex)
                .trim();
        final String target = commandArgs.substring(targetValueIndex + Parameter.TARGET_SEPARATOR.length()).trim();

        final SleepGoal.GoalType goalType = parseGoalType(type);
        final Goal.TimeSpan timeSpan = parsePeriod(period);
        final int targetParsed = parseTarget(target);

        return new SleepGoal(goalType, timeSpan, targetParsed);
    }

    private static void checkMissingSleepGoalParameters(int goalTypeIndex, int periodIndex, int targetValueIndex)
            throws AthletiException {
        if (goalTypeIndex == -1 || periodIndex == -1 || targetValueIndex == -1) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_MISSING_PARAMETERS);
        }
    }

    private static void checkMissingSleepParameters(int startTimeIndex, int endTimeIndex) throws AthletiException {
        if (startTimeIndex == -1 || endTimeIndex == -1) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_MISSING_PARAMETERS);
        }
    }

    private static SleepGoal.GoalType parseGoalType(String type) throws AthletiException {
        switch (type) {
        case "duration":
            return SleepGoal.GoalType.DURATION;
        case "starttime":
            return SleepGoal.GoalType.STARTTIME;
        case "endtime":
            return SleepGoal.GoalType.ENDTIME;
        default:
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TYPE);
        }
    }

    private static Goal.TimeSpan parsePeriod(String period) throws AthletiException {
        try {
            return Goal.TimeSpan.valueOf(period.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_PERIOD);
        }
    }

    private static int parseTarget(String target) throws AthletiException {
        int targetParsed;
        try {
            targetParsed = Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TARGET);
        }
        if (targetParsed < 0) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TARGET);
        }
        return targetParsed;
    }

    public static Sleep parseSleep(String s) throws AthletiException {
        final int startTimeIndex = s.indexOf(Parameter.START_TIME_SEPARATOR);
        final int endTimeIndex = s.indexOf(Parameter.END_TIME_SEPARATOR);

        checkMissingSleepParameters(startTimeIndex, endTimeIndex);

        final String startTimeStr =
                s.substring(startTimeIndex + Parameter.START_TIME_SEPARATOR.length(), endTimeIndex).trim();
        final String endTimeStr = s.substring(endTimeIndex + Parameter.END_TIME_SEPARATOR.length()).trim();

        LocalDateTime startTime = Parser.parseDateTime(startTimeStr);
        LocalDateTime endTime = Parser.parseDateTime(endTimeStr);

        return new Sleep(startTime, endTime);
    }
    
}
