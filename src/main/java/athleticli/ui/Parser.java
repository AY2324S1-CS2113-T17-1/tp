package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.commands.activity.AddActivityCommand;
import athleticli.data.activity.Activity;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.AthletiException;
import athleticli.exceptions.UnknownCommandException;

import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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

        case CommandName.COMMAND_ACTIVITY:
            return new AddActivityCommand(parseActivity(commandArgs));
        case CommandName.COMMAND_CYCLE:
        case CommandName.COMMAND_RUN:
            return new AddActivityCommand(parseRunCycle(commandArgs));
        case CommandName.COMMAND_SWIM:
            return new AddActivityCommand(parseSwim(commandArgs));
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the raw user input for an activity and returns the corresponding activity object.
     * @param arguments      The raw user input containing the arguments.
     * @return               An object representing the activity.
     * @throws AthletiException
     */
    public static Activity parseActivity(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf("duration/");
        final int distanceIndex = arguments.indexOf("distance/");
        final int datetimeIndex = arguments.indexOf("datetime/");

        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration = arguments.substring(durationIndex + 9, distanceIndex).trim();
        final String distance = arguments.substring(distanceIndex + 9, datetimeIndex).trim();
        final String datetime = arguments.substring(datetimeIndex + 9).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime);

        final int durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = parseDateTime(datetime);

        return new Activity(caption, durationParsed, distanceParsed, datetimeParsed);
    }

    public static int parseDuration(String duration) throws AthletiException {
        int durationParsed;
        try {
            durationParsed = Integer.parseInt(duration);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DURATION_INVALID);
        }
        return durationParsed;
    }

    public static LocalDateTime parseDateTime(String datetime) throws AthletiException {
        LocalDateTime datetimeParsed;
        try {
            datetimeParsed = LocalDateTime.parse(datetime.replace(" ", "T"));
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DATETIME_INVALID);
        }
        return datetimeParsed;
    }

    public static int parseDistance(String distance) throws AthletiException {
        int distanceParsed;
        try {
            distanceParsed = Integer.parseInt(distance);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_INVALID);
        }
        return distanceParsed;
    }

    public static void checkMissingActivityArguments(int durationIndex, int distanceIndex, int datetimeIndex)
            throws AthletiException {
        if (durationIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DURATION_MISSING);
        }
        if (distanceIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_MISSING);
        }
        if (datetimeIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DATETIME_MISSING);
        }
    }

    /**
     * Parses the raw user input for a run or cycle and returns the corresponding activity object.
     * @param arguments      The raw user input containing the arguments.
     * @return               An object representing the activity.
     * @throws AthletiException
     */
    public static Activity parseRunCycle(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf("duration/");
        final int distanceIndex = arguments.indexOf("distance/");
        final int datetimeIndex = arguments.indexOf("datetime/");
        final int elevationIndex = arguments.indexOf("elevation/");

        checkMissingRunCycleArguments(durationIndex, distanceIndex, datetimeIndex, elevationIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration = arguments.substring(durationIndex + 9, distanceIndex).trim();
        final String distance = arguments.substring(distanceIndex + 9, datetimeIndex).trim();
        final String datetime = arguments.substring(datetimeIndex + 9, elevationIndex).trim();
        final String elevation = arguments.substring(elevationIndex + 10).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, elevation);

        final int durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = parseDateTime(datetime);
        final int elevationParsed = parseElevation(elevation);

        return new Run(caption, durationParsed, distanceParsed, datetimeParsed, elevationParsed);
    }

    public static int parseElevation(String elevation) throws AthletiException {
        int elevationParsed;
        try {
            elevationParsed = Integer.parseInt(elevation);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_INVALID);
        }
        return elevationParsed;
    }

    public static void checkMissingRunCycleArguments(int durationIndex, int distanceIndex, int datetimeIndex,
                                                     int elevationIndex) throws AthletiException {
        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);
        if (elevationIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_MISSING);
        }
    }

    public static void checkMissingSwimArguments(int durationIndex, int distanceIndex, int datetimeIndex,
                                              int swimmingStyleIndex) throws AthletiException {
        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);
        if (swimmingStyleIndex == -1) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_MISSING);
        }
    }

    public static void checkEmptyActivityArguments(String caption, String duration, String distance, String datetime)
            throws AthletiException {
        if (caption.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_CAPTION_EMPTY);
        }
        if (duration.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DURATION_EMPTY);
        }
        if (distance.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_EMPTY);
        }
        if (datetime.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DATETIME_EMPTY);
        }
    }

    public static void checkEmptyActivityArguments(String caption, String duration, String distance, String datetime,
                                                   String elevation) throws AthletiException {
        checkEmptyActivityArguments(caption, duration, distance, datetime);
        if (elevation.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_EMPTY);
        }
    }

    public static void checkEmptyActivityArguments(String caption, String duration, String distance, String datetime,
                                                   int swimmingStyleIndex) throws AthletiException {
        checkEmptyActivityArguments(caption, duration, distance, datetime);
        if (swimmingStyleIndex == -1) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_MISSING);
        }
    }

    /**
     * Parses the raw user input for a swim and returns the corresponding activity object.
     * @param arguments      The raw user input containing the arguments.
     * @return activity      An object representing the activity.
     * @throws AthletiException
     */
    public static Activity parseSwim(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf("duration/");
        final int distanceIndex = arguments.indexOf("distance/");
        final int datetimeIndex = arguments.indexOf("datetime/");
        final int swimmingStyleIndex = arguments.indexOf("style/");

        checkMissingSwimArguments(durationIndex, distanceIndex, datetimeIndex, swimmingStyleIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration = arguments.substring(durationIndex + 9, distanceIndex).trim();
        final String distance = arguments.substring(distanceIndex + 9, datetimeIndex).trim();
        final String datetime = arguments.substring(datetimeIndex + 9, swimmingStyleIndex).trim();
        final String swimmingStyle = arguments.substring(swimmingStyleIndex + 6).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, swimmingStyleIndex);

        final int durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = parseDateTime(datetime);
        final Swim.SwimmingStyle swimmingStyleParsed = parseSwimmingStyle(swimmingStyle);

        Swim swim = new Swim(caption, durationParsed, distanceParsed, datetimeParsed, swimmingStyleParsed);
        return swim;
    }

    public static Swim.SwimmingStyle parseSwimmingStyle(String swimmingStyle) throws AthletiException {
        try {
            return Swim.SwimmingStyle.valueOf(swimmingStyle);
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_INVALID);
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
