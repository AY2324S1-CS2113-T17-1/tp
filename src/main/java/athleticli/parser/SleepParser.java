package athleticli.parser;

import java.time.LocalDateTime;

import athleticli.data.Goal;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepGoal;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

public class SleepParser {
    //@@author  DaDevChia

    /* Sleep Management */

    /**
     * Parses the raw user input for an add sleep command and returns the corresponding command object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the sleep add command.
     * @throws AthletiException
     */
    public static Sleep parseSleep(String commandArgs) throws AthletiException {
        final int startDatetimeIndex = commandArgs.indexOf(Parameter.START_TIME_SEPARATOR);
        final int endDatetimeIndex = commandArgs.indexOf(Parameter.END_TIME_SEPARATOR);

        if (startDatetimeIndex == -1 || endDatetimeIndex == -1) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        if (startDatetimeIndex > endDatetimeIndex) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_INVALID_START_END_ORDER);
        }

        final String startDatetimeStr =
                commandArgs.substring(startDatetimeIndex + Parameter.START_TIME_SEPARATOR.length(), endDatetimeIndex)
                        .trim();
        final String endDatetimeStr =
                commandArgs.substring(endDatetimeIndex + Parameter.END_TIME_SEPARATOR.length()).trim();

        if (startDatetimeStr == null || startDatetimeStr.isEmpty() 
            || endDatetimeStr == null || endDatetimeStr.isEmpty()) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        final LocalDateTime startDatetime = Parser.parseDateTime(startDatetimeStr);
        final LocalDateTime endDatetime = Parser.parseDateTime(endDatetimeStr);

        if (startDatetime == null || endDatetime == null) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_INVALID_DATETIME);
        }

        if (startDatetime.isEqual(endDatetime) || startDatetime.isAfter(endDatetime)) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_START_END_NON_CHRONOLOGICAL);
        }

        return new Sleep(startDatetime, endDatetime);
    }

    /**
     * Parses the raw user input the sleep index and returns the corresponding index.
     * 
     * @param commandArgs The raw user input containing the arguments.
     * @return The index of the sleep to be edited.
     * @throws AthletiException If the index is invalid.
     */
    public static int parseSleepIndex(String commandArgs) throws AthletiException {
        final String indexStr = commandArgs.split("(?<=\\d)(?=\\D)", 2)[0].trim();
        
        if (indexStr == null || indexStr.isEmpty()) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_INDEX);
        }

        int index;
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_INVALID_INDEX);
        }

        if (index <= 0) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_INVALID_INDEX);
        }

        return index;
    }

    /*  Sleep Goal Management */

    /**
     * Parses the raw user input for a sleep goal and returns the corresponding sleep goal object.
     * 
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the sleep goal.
     * @throws AthletiException If the sleep goal is invalid.
     */
    public static SleepGoal parseSleepGoal(String commandArgs) throws AthletiException {
        final int goalTypeIndex = commandArgs.indexOf(Parameter.TYPE_SEPARATOR);
        final int periodIndex = commandArgs.indexOf(Parameter.PERIOD_SEPARATOR);
        final int targetValueIndex = commandArgs.indexOf(Parameter.TARGET_SEPARATOR);

        if (goalTypeIndex == -1 || periodIndex == -1 || targetValueIndex == -1) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_MISSING_PARAMETERS);
        }

        if (goalTypeIndex > periodIndex || periodIndex > targetValueIndex) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_PARAMETERS_ORDER);
        }

        final String type = commandArgs.substring(goalTypeIndex + Parameter.TYPE_SEPARATOR.length(), periodIndex)
                .trim();
        final String period = commandArgs.substring(periodIndex + Parameter.PERIOD_SEPARATOR.length(), targetValueIndex)
                .trim();
        final String target = commandArgs.substring(targetValueIndex + Parameter.TARGET_SEPARATOR.length()).trim();

        final SleepGoal.GoalType goalType = parseGoalType(type);
        final Goal.TimeSpan timeSpan = parsePeriod(period);
        final int targetParsed = parseTarget(target);

        return new SleepGoal(goalType, timeSpan, targetParsed);
    }

    /**
     * Parses the raw user input for a sleep goal index and returns the corresponding index.
     * 
     * @param type The string representing the type of the sleep goal.
     * @return The type of the sleep goal.
     * @throws AthletiException If the type is invalid.
     */
    private static SleepGoal.GoalType parseGoalType(String type) throws AthletiException {
        switch (type) {
        case "duration":
            return SleepGoal.GoalType.DURATION;
        default:
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TYPE);
        }
    }

    /**
     * Parses the raw user input for a sleep goal period and returns the corresponding period.
     * 
     * @param period The string representing the period of the sleep goal.
     * @return The period of the sleep goal.
     * @throws AthletiException If the period is invalid.
     */
    private static Goal.TimeSpan parsePeriod(String period) throws AthletiException {
        try {
            return Goal.TimeSpan.valueOf(period.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_PERIOD);
        }
    }

    /**
     * Parses the raw user input for a sleep goal target and returns the corresponding target.
     * 
     * @param target The string representing the target of the sleep goal.
     * @return The target of the sleep goal.
     * @throws AthletiException If the target is invalid.
     */
    private static int parseTarget(String target) throws AthletiException {
        int targetParsed;
        try {
            targetParsed = Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TARGET);
        }
        if (targetParsed <= 0) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TARGET);
        }
        return targetParsed;
    }
}
