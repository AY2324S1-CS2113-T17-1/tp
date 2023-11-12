package athleticli.parser;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.commands.FindCommand;
import athleticli.commands.HelpCommand;
import athleticli.commands.SaveCommand;
import athleticli.commands.activity.AddActivityCommand;
import athleticli.commands.activity.DeleteActivityCommand;
import athleticli.commands.activity.DeleteActivityGoalCommand;
import athleticli.commands.activity.EditActivityCommand;
import athleticli.commands.activity.EditActivityGoalCommand;
import athleticli.commands.activity.FindActivityCommand;
import athleticli.commands.activity.ListActivityCommand;
import athleticli.commands.activity.ListActivityGoalCommand;
import athleticli.commands.activity.SetActivityGoalCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.DeleteDietGoalCommand;
import athleticli.commands.diet.EditDietCommand;
import athleticli.commands.diet.EditDietGoalCommand;
import athleticli.commands.diet.FindDietCommand;
import athleticli.commands.diet.ListDietCommand;
import athleticli.commands.diet.ListDietGoalCommand;
import athleticli.commands.diet.SetDietGoalCommand;
import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.EditSleepGoalCommand;
import athleticli.commands.sleep.FindSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;
import athleticli.commands.sleep.ListSleepGoalCommand;
import athleticli.commands.sleep.SetSleepGoalCommand;
import athleticli.data.activity.Activity;
import athleticli.data.activity.Cycle;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static athleticli.common.Config.DATE_FORMATTER;
import static athleticli.common.Config.DATE_TIME_FORMATTER;

/**
 * Defines the basic methods for command parser.
 */
public class Parser {
    private static final String INVALID_YEAR = "0000";

    /**
     * Splits the raw user input into two parts, and then returns them. The first part is the command type,
     * while the second part is the command arguments. The second part can be empty.
     *
     * @param rawUserInput The raw user input.
     * @return A string array whose first element is the command type and the second element is the command
     *         arguments.
     */
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        assert rawUserInput != null : "`rawUserInput` should not be null";
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Parses the raw user input and returns the corresponding command object.
     *
     * @param rawUserInput The raw user input.
     * @return An object representing the command.
     * @throws AthletiException If the command is invalid
     */
    public static Command parseCommand(String rawUserInput) throws AthletiException {
        assert rawUserInput != null : "`rawUserInput` should not be null";
        final String[] commandTypeAndParams = splitCommandWordAndArgs(rawUserInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {

        /* General */
        case CommandName.COMMAND_BYE:
            return new ByeCommand();
        case CommandName.COMMAND_HELP:
            return new HelpCommand(commandArgs);
        case CommandName.COMMAND_SAVE:
            return new SaveCommand();
        case CommandName.COMMAND_FIND:
            return new FindCommand(parseDate(commandArgs));

        /* Sleep Management */
        case CommandName.COMMAND_SLEEP_ADD:
            return new AddSleepCommand(SleepParser.parseSleep(commandArgs));
        case CommandName.COMMAND_SLEEP_LIST:
            return new ListSleepCommand();
        case CommandName.COMMAND_SLEEP_EDIT:
            return new EditSleepCommand(SleepParser.parseSleepIndex(commandArgs),
                    SleepParser.parseSleep(commandArgs));
        case CommandName.COMMAND_SLEEP_DELETE:
            return new DeleteSleepCommand(SleepParser.parseSleepIndex(commandArgs));
        case CommandName.COMMAND_SLEEP_FIND:
            return new FindSleepCommand(parseDate(commandArgs));

        /*  Sleep Goal Management */
        case CommandName.COMMAND_SLEEP_GOAL_LIST:
            return new ListSleepGoalCommand();
        case CommandName.COMMAND_SLEEP_GOAL_SET:
            return new SetSleepGoalCommand(SleepParser.parseSleepGoal(commandArgs));
        case CommandName.COMMAND_SLEEP_GOAL_EDIT:
            return new EditSleepGoalCommand(SleepParser.parseSleepGoal(commandArgs));

        /* Activity Management */
        case CommandName.COMMAND_ACTIVITY:
            return new AddActivityCommand(ActivityParser.parseActivity(commandArgs));
        case CommandName.COMMAND_CYCLE:
            return new AddActivityCommand(ActivityParser.parseRunCycle(commandArgs, false));
        case CommandName.COMMAND_RUN:
            return new AddActivityCommand(ActivityParser.parseRunCycle(commandArgs, true));
        case CommandName.COMMAND_SWIM:
            return new AddActivityCommand(ActivityParser.parseSwim(commandArgs));
        case CommandName.COMMAND_ACTIVITY_DELETE:
            return new DeleteActivityCommand(ActivityParser.parseActivityIndex(commandArgs));
        case CommandName.COMMAND_ACTIVITY_LIST:
            return new ListActivityCommand(ActivityParser.parseActivityListDetail(commandArgs));
        case CommandName.COMMAND_ACTIVITY_EDIT:
            return new EditActivityCommand(ActivityParser.parseActivityEditIndex(commandArgs),
                    ActivityParser.parseActivityEdit(commandArgs), Activity.class);
        case CommandName.COMMAND_RUN_EDIT:
            return new EditActivityCommand(ActivityParser.parseActivityEditIndex(commandArgs),
                    ActivityParser.parseRunCycleEdit(commandArgs), Run.class);
        case CommandName.COMMAND_CYCLE_EDIT:
            return new EditActivityCommand(ActivityParser.parseActivityEditIndex(commandArgs),
                    ActivityParser.parseRunCycleEdit(commandArgs), Cycle.class);
        case CommandName.COMMAND_SWIM_EDIT:
            return new EditActivityCommand(ActivityParser.parseActivityEditIndex(commandArgs),
                    ActivityParser.parseSwimEdit(commandArgs), Swim.class);
        case CommandName.COMMAND_ACTIVITY_FIND:
            return new FindActivityCommand(parseDate(commandArgs));

        /* Activity Goal Management */
        case CommandName.COMMAND_ACTIVITY_GOAL_SET:
            return new SetActivityGoalCommand(ActivityParser.parseActivityGoal(commandArgs));
        case CommandName.COMMAND_ACTIVITY_GOAL_DELETE:
            return new DeleteActivityGoalCommand(ActivityParser.parseDeleteActivityGoal(commandArgs));
        case CommandName.COMMAND_ACTIVITY_GOAL_EDIT:
            return new EditActivityGoalCommand(ActivityParser.parseActivityGoal(commandArgs));
        case CommandName.COMMAND_ACTIVITY_GOAL_LIST:
            return new ListActivityGoalCommand();

        /* Diet Management */
        case CommandName.COMMAND_DIET_ADD:
            return new AddDietCommand(DietParser.parseDiet(commandArgs));
        case CommandName.COMMAND_DIET_EDIT:
            return new EditDietCommand(DietParser.parseDietIndex(commandArgs),
                    DietParser.parseDietEdit(commandArgs));
        case CommandName.COMMAND_DIET_DELETE:
            return new DeleteDietCommand(DietParser.parseDietIndex(commandArgs));
        case CommandName.COMMAND_DIET_LIST:
            return new ListDietCommand();
        case CommandName.COMMAND_DIET_FIND:
            return new FindDietCommand(parseDate(commandArgs));

        /* Diet Goal Management */
        case CommandName.COMMAND_DIET_GOAL_SET:
            return new SetDietGoalCommand(DietParser.parseDietGoalSetAndEdit(commandArgs));
        case CommandName.COMMAND_DIET_GOAL_EDIT:
            return new EditDietGoalCommand(DietParser.parseDietGoalSetAndEdit(commandArgs));
        case CommandName.COMMAND_DIET_GOAL_LIST:
            return new ListDietGoalCommand();
        case CommandName.COMMAND_DIET_GOAL_DELETE:
            return new DeleteDietGoalCommand(DietParser.parseDietGoalDelete(commandArgs));

        default:
            throw new AthletiException(Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the raw date time input provided by the user.
     *
     * @param datetime The raw user input containing the date time.
     * @return datetimeParsed   The parsed LocalDateTime object.
     * @throws AthletiException If the input format is invalid.
     */
    public static LocalDateTime parseDateTime(String datetime) throws AthletiException {
        if (datetime.startsWith(INVALID_YEAR)) {
            throw new AthletiException(Message.MESSAGE_DATETIME_INVALID);
        }
        LocalDateTime datetimeParsed;
        try {
            datetimeParsed = LocalDateTime.parse(datetime.replace("T", " "), DATE_TIME_FORMATTER);
            if (datetimeParsed.isAfter(LocalDateTime.now())) {
                throw new AthletiException(Message.MESSAGE_DATE_FUTURE);
            }
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DATETIME_INVALID);
        }
        return datetimeParsed;
    }

    public static LocalDate parseDate(String date) throws AthletiException {
        if (date.startsWith(INVALID_YEAR)) {
            throw new AthletiException(Message.MESSAGE_DATE_INVALID);
        }
        try {
            LocalDate dateParsed = LocalDate.parse(date, DATE_FORMATTER);
            if (dateParsed.isAfter(LocalDate.now())) {
                throw new AthletiException(Message.MESSAGE_DATE_FUTURE);
            }
            return dateParsed;
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DATE_INVALID);
        }
    }

    /**
     * Parses the raw integer input provided by the user.
     *
     * @param integer         The raw user input containing the integer.
     * @param invalidMessage  The message to be displayed if the input is invalid.
     * @param overflowMessage The message to be displayed if the input is too large.
     * @return integerParsed  The parsed integer.
     * @throws AthletiException If the input format is invalid.
     */
    public static int parseNonNegativeInteger(String integer, String invalidMessage,
                                              String overflowMessage) throws AthletiException {
        java.math.BigInteger integerParsed;
        try {
            integerParsed = new java.math.BigInteger(integer);
        } catch (NumberFormatException e) {
            throw new AthletiException(invalidMessage);
        }
        if (integerParsed.signum() < 0) {
            throw new AthletiException(invalidMessage);
        }
        if (integerParsed.compareTo(java.math.BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new AthletiException(overflowMessage);
        }
        return integerParsed.intValue();
    }

    /**
     * Parses the value for a specific marker in a given argument string.
     *
     * @param arguments The raw user input containing the arguments.
     * @param marker    The marker whose value is to be retrieved.
     * @return The value associated with the given marker, or an empty string if the marker is not found.
     */
    public static String getValueForMarker(String arguments, String marker) {
        String patternString;

        if (marker.equals(Parameter.DATETIME_SEPARATOR)) {
            // Capture one or two words following the DATETIME_SEPARATOR
            patternString = Pattern.quote(marker) + "(\\S+)(?:\\s+(\\S+))?";
        } else {
            patternString = Pattern.quote(marker) + "(\\S+)";
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(arguments);

        if (matcher.find()) {
            if (marker.equals(Parameter.DATETIME_SEPARATOR)) {
                String firstPart = matcher.group(1);
                String secondPart = matcher.group(2);
                if (secondPart != null) {
                    return firstPart + " " + secondPart;
                } else {
                    return firstPart;
                }
            } else {
                return matcher.group(1);
            }
        }

        // Return empty string if no match is found
        return "";
    }
}
