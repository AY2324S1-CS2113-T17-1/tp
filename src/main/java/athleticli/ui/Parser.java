package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.data.activity.Activity;
import athleticli.exceptions.AthletiException;
import athleticli.exceptions.UnknownCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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

    public static Activity parseActivity(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf("duration/");
        final int distanceIndex = arguments.indexOf("distance/");
        final int datetimeIndex = arguments.indexOf("datetime/");

        if (durationIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DURATION_MISSING);
        }
        if (distanceIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_MISSING);
        }
        if (datetimeIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DATETIME_MISSING);
        }

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration = arguments.substring(durationIndex + 9, distanceIndex).trim();
        final String distance = arguments.substring(distanceIndex + 9, datetimeIndex).trim();
        final String datetime = arguments.substring(datetimeIndex + 9).trim();
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
        Activity activity = parseActivityArguments(caption, duration, distance, datetime);
    }

    public static Activity parseActivityArguments(String caption, String duration, String distance, String datetime)
            throws AthletiException {
        final int durationParsed;
        final int distanceParsed;
        final LocalDateTime datetimeParsed;
        try {
            durationParsed = Integer.parseInt(duration);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DURATION_INVALID);
        }
        try {
            distanceParsed = Integer.parseInt(distance);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_INVALID);
        }
        try {
            datetimeParsed = LocalDateTime.parse(datetime.replace(" ", "T"));
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DATETIME_INVALID);
        }
        return new Activity(caption, durationParsed, distanceParsed, datetimeParsed);
    }

}
