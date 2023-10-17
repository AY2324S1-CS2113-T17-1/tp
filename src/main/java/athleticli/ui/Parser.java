package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.commands.activity.AddActivityCommand;
import athleticli.commands.activity.DeleteActivityCommand;
import athleticli.commands.activity.EditActivityCommand;
import athleticli.commands.activity.ListActivityCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.ListDietCommand;
import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;
import athleticli.data.activity.Activity;
import athleticli.data.activity.Cycle;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.data.diet.Diet;
import athleticli.exceptions.AthletiException;
import athleticli.exceptions.UnknownCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Defines the basic methods for command parser.
 */
public class Parser {
    /**
     * Splits the raw user input into two parts, and then returns them. The first part is the command type,
     * while the second part is the command arguments. The second part can be empty.
     *
     * @param rawUserInput The raw user input.
     * @return A string array whose first element is the command type and the second element is the command
     *         arguments.
     */
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Parses the raw user input and returns the corresponding command object.
     *
     * @param rawUserInput The raw user input.
     * @return An object representing the command.
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
            return new AddActivityCommand(parseRunCycle(commandArgs, false));
        case CommandName.COMMAND_RUN:
            return new AddActivityCommand(parseRunCycle(commandArgs, true));
        case CommandName.COMMAND_SWIM:
            return new AddActivityCommand(parseSwim(commandArgs));
        case CommandName.COMMAND_ACTIVITY_DELETE:
            return new DeleteActivityCommand(parseActivityIndex(commandArgs));
        case CommandName.COMMAND_ACTIVITY_LIST:
            return new ListActivityCommand(parseActivityListDetail(commandArgs));
        case CommandName.COMMAND_ACTIVITY_EDIT:
            return new EditActivityCommand(parseActivityEdit(commandArgs), parseActivityEditIndex(commandArgs));
        case CommandName.COMMAND_RUN_EDIT:
            return new EditActivityCommand(parseRunEdit(commandArgs), parseActivityEditIndex(commandArgs));
        case CommandName.COMMAND_CYCLE_EDIT:
            return new EditActivityCommand(parseCycleEdit(commandArgs), parseActivityEditIndex(commandArgs));
        case CommandName.COMMAND_SWIM_EDIT:
            return new EditActivityCommand(parseSwimEdit(commandArgs), parseActivityEditIndex(commandArgs));
        case CommandName.COMMAND_DIET_ADD:
            return new AddDietCommand(parseDiet(commandArgs));
        case CommandName.COMMAND_DIET_DELETE:
            return new DeleteDietCommand(parseDietIndex(commandArgs));
        case CommandName.COMMAND_DIET_LIST:
            return new ListDietCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the index of an activity.
     * @param commandArgs The raw user input containing the index.
     * @return index                The parsed Integer index.
     * @throws AthletiException     If the input is not an integer.
     */
    private static int parseActivityIndex(String commandArgs) throws AthletiException {
        final String commandArgsTrimmed = commandArgs.trim();
        int index;
        try {
            index = Integer.parseInt(commandArgsTrimmed);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_INDEX_INVALID);
        }
        return index;
    }

    private static Activity parseActivityEdit(String arguments) throws AthletiException {
        try {
            return parseActivity(arguments.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    private static Activity parseRunEdit(String arguments) throws AthletiException {
        try {
            return parseRunCycle(arguments.split(" ", 2)[1], true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    private static Activity parseCycleEdit(String arguments) throws AthletiException {
        try {
            return parseRunCycle(arguments.split(" ", 2)[1], false);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    private static Activity parseSwimEdit(String arguments) throws AthletiException {
        try {
            return parseSwim(arguments.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    private static int parseActivityEditIndex(String arguments) throws AthletiException {
        try {
            return parseActivityIndex(arguments.split(" ", 2)[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the raw user input for viewing the activity list and returns whether the user wants the detailed view
     * @param commandArgs   The raw user input containing the arguments.
     * @return boolean      Whether the user wants the detailed view.
     */
    private static boolean parseActivityListDetail(String commandArgs) {
        return commandArgs.toLowerCase().contains(Parameter.detailFlag);
    }

    /**
     * Parses the raw user input for an activity and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the activity.
     * @throws AthletiException
     */
    public static Activity parseActivity(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.durationSeparator);
        final int distanceIndex = arguments.indexOf(Parameter.distanceSeparator);
        final int datetimeIndex = arguments.indexOf(Parameter.datetimeSeparator);

        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.durationSeparator.length(), distanceIndex).trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.distanceSeparator.length(), datetimeIndex).trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.datetimeSeparator.length()).trim();

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

    public static void checkMissingActivityArguments(int durationIndex, int distanceIndex,
                                                     int datetimeIndex) throws AthletiException {
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
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the activity.
     * @throws AthletiException
     */
    public static Activity parseRunCycle(String arguments, boolean isRun) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.durationSeparator);
        final int distanceIndex = arguments.indexOf(Parameter.distanceSeparator);
        final int datetimeIndex = arguments.indexOf(Parameter.datetimeSeparator);
        final int elevationIndex = arguments.indexOf(Parameter.elevationSeparator);

        checkMissingRunCycleArguments(durationIndex, distanceIndex, datetimeIndex, elevationIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.durationSeparator.length(), distanceIndex).trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.distanceSeparator.length(), datetimeIndex).trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.datetimeSeparator.length(), elevationIndex).trim();
        final String elevation =
                arguments.substring(elevationIndex + Parameter.elevationSeparator.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, elevation);

        final int durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = parseDateTime(datetime);
        final int elevationParsed = parseElevation(elevation);

        if (isRun) {
            return new Run(caption, durationParsed, distanceParsed, datetimeParsed, elevationParsed);
        } else {
            return new Cycle(caption, durationParsed, distanceParsed, datetimeParsed, elevationParsed);
        }
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

    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime) throws AthletiException {
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

    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime,
                                                   String elevation) throws AthletiException {
        checkEmptyActivityArguments(caption, duration, distance, datetime);
        if (elevation.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_EMPTY);
        }
    }

    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime,
                                                   int swimmingStyleIndex) throws AthletiException {
        checkEmptyActivityArguments(caption, duration, distance, datetime);
        if (swimmingStyleIndex == -1) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_MISSING);
        }
    }

    /**
     * Parses the raw user input for a swim and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return activity      An object representing the activity.
     * @throws AthletiException
     */
    public static Activity parseSwim(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.durationSeparator);
        final int distanceIndex = arguments.indexOf(Parameter.distanceSeparator);
        final int datetimeIndex = arguments.indexOf(Parameter.distanceSeparator);
        final int swimmingStyleIndex = arguments.indexOf(Parameter.swimmingStyleSeparator);

        checkMissingSwimArguments(durationIndex, distanceIndex, datetimeIndex, swimmingStyleIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.durationSeparator.length(), distanceIndex).trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.distanceSeparator.length(), datetimeIndex).trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.datetimeSeparator.length(), swimmingStyleIndex).trim();
        final String swimmingStyle =
                arguments.substring(swimmingStyleIndex + Parameter.swimmingStyleSeparator.length()).trim();

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

        final String startMarkerConstant = "/start";
        final String endMarkerConstant = "/end";

        int startMarkerPos = commandArgs.indexOf(startMarkerConstant);
        int endMarkerPos = commandArgs.indexOf(endMarkerConstant);

        if (startMarkerPos == -1 || endMarkerPos == -1) {
            throw new AthletiException("Please specify both the start and end time of your sleep.");
        }

        if (startMarkerPos > endMarkerPos) {
            throw new AthletiException("Please specify the start time of your sleep before the end time.");
        }

        String startTime =
                commandArgs.substring(startMarkerPos + startMarkerConstant.length(), endMarkerPos).trim();
        String endTime = commandArgs.substring(endMarkerPos + endMarkerConstant.length()).trim();

        if (startTime.isEmpty() || endTime.isEmpty()) {
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
        final String startMarkerConstant = "/start";
        final String endMarkerConstant = "/end";

        int startMarkerPos = commandArgs.indexOf(startMarkerConstant);
        int endMarkerPos = commandArgs.indexOf(endMarkerConstant);

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

        String startTime =
                commandArgs.substring(startMarkerPos + startMarkerConstant.length(), endMarkerPos).trim();
        String endTime = commandArgs.substring(endMarkerPos + endMarkerConstant.length()).trim();

        if (startTime.isEmpty() || endTime.isEmpty()) {
            throw new AthletiException("Please specify both the start and end time of your sleep.");
        }

        return new EditSleepCommand(index, startTime, endTime);
    }

    /**
     * Parses the raw user input for a diet and returns the corresponding diet object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the diet.
     * @throws AthletiException
     */
    public static Diet parseDiet(String commandArgs) throws AthletiException {
        final String caloriesMarkerConstant = "calories/";
        final String proteinMarkerConstant = "protein/";
        final String carbMarkerConstant = "carb/";
        final String fatMarkerConstant = "fat/";

        int caloriesMarkerPos = commandArgs.indexOf(caloriesMarkerConstant);
        int proteinMarkerPos = commandArgs.indexOf(proteinMarkerConstant);
        int carbMarkerPos = commandArgs.indexOf(carbMarkerConstant);
        int fatMarkerPos = commandArgs.indexOf(fatMarkerConstant);

        checkMissingDietArguments(caloriesMarkerPos, proteinMarkerPos, carbMarkerPos, fatMarkerPos);

        String calories =
                commandArgs.substring(caloriesMarkerPos + caloriesMarkerConstant.length(), proteinMarkerPos)
                        .trim();
        String protein =
                commandArgs.substring(proteinMarkerPos + proteinMarkerConstant.length(), carbMarkerPos)
                        .trim();
        String carb = commandArgs.substring(carbMarkerPos + carbMarkerConstant.length(), fatMarkerPos).trim();
        String fat = commandArgs.substring(fatMarkerPos + fatMarkerConstant.length()).trim();

        checkEmptyDietArguments(calories, protein, carb, fat);

        int caloriesParsed = parseCalories(calories);
        int proteinParsed = parseProtein(protein);
        int carbParsed = parseCarb(carb);
        int fatParsed = parseFat(fat);

        return new Diet(caloriesParsed, proteinParsed, carbParsed, fatParsed);
    }

    /**
     * Checks if the user input for a diet contains all the required arguments.
     *
     * @param caloriesMarkerPos The position of the calories marker.
     * @param proteinMarkerPos  The position of the protein marker.
     * @param carbMarkerPos     The position of the carb marker.
     * @param fatMarkerPos      The position of the fat marker.
     * @throws AthletiException
     */
    private static void checkMissingDietArguments(int caloriesMarkerPos, int proteinMarkerPos,
                                                  int carbMarkerPos,
                                                  int fatMarkerPos) throws AthletiException {
        if (caloriesMarkerPos == -1) {
            throw new AthletiException(Message.MESSAGE_CALORIES_MISSING);
        }
        if (proteinMarkerPos == -1) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_MISSING);
        }
        if (carbMarkerPos == -1) {
            throw new AthletiException(Message.MESSAGE_CARB_MISSING);
        }
        if (fatMarkerPos == -1) {
            throw new AthletiException(Message.MESSAGE_FAT_MISSING);
        }
    }

    /**
     * Checks if the user input for a diet is empty.
     *
     * @param calories The calories input.
     * @param protein  The protein input.
     * @param carb     The carb input.
     * @param fat      The fat input.
     * @throws AthletiException
     */
    private static void checkEmptyDietArguments(String calories, String protein, String carb,
                                                String fat) throws AthletiException {
        if (calories.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_CALORIES_EMPTY);
        }
        if (protein.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_EMPTY);
        }
        if (carb.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_CARB_EMPTY);
        }
        if (fat.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_FAT_EMPTY);
        }
    }

    /**
     * Parses the calories input for a diet.
     *
     * @param calories The calories input.
     * @return The parsed calories.
     * @throws AthletiException
     */
    private static int parseCalories(String calories) throws AthletiException {
        int caloriesParsed;
        try {
            caloriesParsed = Integer.parseInt(calories);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_CALORIES_INVALID);
        }
        return caloriesParsed;
    }

    /**
     * Parses the protein input for a diet.
     *
     * @param protein The protein input.
     * @return The parsed protein.
     * @throws AthletiException
     */
    public static int parseProtein(String protein) throws AthletiException {
        int proteinParsed;
        try {
            proteinParsed = Integer.parseInt(protein);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_INVALID);
        }
        return proteinParsed;
    }

    /**
     * Parses the carb input for a diet.
     *
     * @param carb The carb input.
     * @return The parsed carb.
     * @throws AthletiException
     */
    public static int parseCarb(String carb) throws AthletiException {
        int carbParsed;
        try {
            carbParsed = Integer.parseInt(carb);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_CARB_INVALID);
        }
        return carbParsed;
    }

    /**
     * Parses the fat input for a diet.
     *
     * @param fat The fat input.
     * @return The parsed fat.
     * @throws AthletiException
     */
    public static int parseFat(String fat) throws AthletiException {
        int fatParsed;
        try {
            fatParsed = Integer.parseInt(fat);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_FAT_INVALID);
        }
        return fatParsed;
    }

    /**
     * Parses the index of a diet.
     *
     * @param commandArgs The raw user input containing the index.
     * @return The parsed index.
     * @throws AthletiException
     */
    public static int parseDietIndex(String commandArgs) throws AthletiException {
        int index;
        try {
            index = Integer.parseInt(commandArgs.trim());
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIET_INDEX_TYPE_INVALID);
        }
        return index;
    }
}
