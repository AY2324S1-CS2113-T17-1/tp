package athleticli.ui;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.commands.HelpCommand;
import athleticli.commands.SaveCommand;
import athleticli.commands.activity.AddActivityCommand;
import athleticli.commands.activity.DeleteActivityCommand;
import athleticli.commands.activity.EditActivityCommand;
import athleticli.commands.activity.ListActivityCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.DeleteDietGoalCommand;
import athleticli.commands.diet.EditDietGoalCommand;
import athleticli.commands.diet.ListDietCommand;
import athleticli.commands.diet.ListDietGoalCommand;
import athleticli.commands.diet.SetDietGoalCommand;

import athleticli.commands.sleep.AddSleepCommand;
import athleticli.commands.sleep.DeleteSleepCommand;
import athleticli.commands.sleep.EditSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;

import athleticli.data.activity.Activity;
import athleticli.data.activity.Cycle;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;

import athleticli.data.diet.DietGoal;
import athleticli.data.diet.Diet;

import athleticli.exceptions.AthletiException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines the basic methods for command parser.
 */
public class Parser {
    private static DateTimeFormatter sleepTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final String CALORIES_MARKER = "calories";
    private static final String PROTEIN_MARKER = "protein";
    private static final String CARB_MARKER = "carb";
    private static final String FAT_MARKER = "fats";

    /**
     * Splits the raw user input into two parts, and then returns them. The first part is the command type,
     * while the second part is the command arguments. The second part can be empty.
     *
     * @param rawUserInput The raw user input.
     * @return A string array whose first element is the command type
     *     and the second element is the command arguments.
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
     * @throws AthletiException
     */
    public static Command parseCommand(String rawUserInput) throws AthletiException {
        assert rawUserInput != null : "`rawUserInput` should not be null";
        final String[] commandTypeAndParams = splitCommandWordAndArgs(rawUserInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case CommandName.COMMAND_BYE:
            return new ByeCommand();
        case CommandName.COMMAND_HELP:
            return new HelpCommand(commandArgs);
        case CommandName.COMMAND_SAVE:
            return new SaveCommand();
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
        case CommandName.COMMAND_DIET_GOAL_SET:
            return new SetDietGoalCommand(parseDietGoalSetEdit(commandArgs));
        case CommandName.COMMAND_DIET_GOAL_EDIT:
            return new EditDietGoalCommand(parseDietGoalSetEdit(commandArgs));
        case CommandName.COMMAND_DIET_GOAL_LIST:
            return new ListDietGoalCommand();
        case CommandName.COMMAND_DIET_GOAL_DELETE:
            return new DeleteDietGoalCommand(parseDietGoalDelete(commandArgs));
        case CommandName.COMMAND_DIET_ADD:
            return new AddDietCommand(parseDiet(commandArgs));
        case CommandName.COMMAND_DIET_DELETE:
            return new DeleteDietCommand(parseDietIndex(commandArgs));
        case CommandName.COMMAND_DIET_LIST:
            return new ListDietCommand();
        default:
            throw new AthletiException(Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the index of an activity.
     * @param commandArgs The raw user input containing the index.
     * @return index                The parsed Integer index.
     * @throws AthletiException     If the input is not an integer.
     */
    public static int parseActivityIndex(String commandArgs) throws AthletiException {
        final String commandArgsTrimmed = commandArgs.trim();
        int index;
        try {
            index = Integer.parseInt(commandArgsTrimmed);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_INDEX_INVALID);
        }
        return index;
    }

    /**
     * Parses the provided updated activity for the edit command.
     * @param arguments             The raw user input containing the updated activity.
     * @return activity             The parsed Activity object.
     * @throws AthletiException     If the input format is invalid.
     */
    public static Activity parseActivityEdit(String arguments) throws AthletiException {
        try {
            return parseActivity(arguments.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the provided updated run for the edit command
     * @param arguments             The raw user input containing the updated run.
     * @return activity             The parsed run object.
     * @throws AthletiException     If the input format is invalid.
     */
    public static Activity parseRunEdit(String arguments) throws AthletiException {
        try {
            return parseRunCycle(arguments.split(" ", 2)[1], true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the provided updated cycle for the edit command
     * @param arguments             The raw user input containing the updated cycle.
     * @return activity             The parsed cycle object.
     * @throws AthletiException     If the input format is invalid.
     */
    public static Activity parseCycleEdit(String arguments) throws AthletiException {
        try {
            return parseRunCycle(arguments.split(" ", 2)[1], false);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the provided update swim for the edit command
     * @param arguments             The raw user input containing the updated swim.
     * @return activity             The parsed swim object.
     * @throws AthletiException     If the input format is invalid.
     */
    public static Activity parseSwimEdit(String arguments) throws AthletiException {
        try {
            return parseSwim(arguments.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the index of an activity update for the edit command.
     * @param arguments           The raw user input containing the index.
     * @return index              The parsed Integer index.
     * @throws AthletiException   If the input format is invalid
     */
    public static int parseActivityEditIndex(String arguments) throws AthletiException {
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
    public static boolean parseActivityListDetail(String commandArgs) {
        return commandArgs.toLowerCase().contains(Parameter.DETAIL_FLAG);
    }

    /**
     * Parses the raw user input for an activity and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the activity.
     * @throws AthletiException
     */
    public static Activity parseActivity(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);

        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex).trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex).trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime);

        final int durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = parseDateTime(datetime);

        return new Activity(caption, durationParsed, distanceParsed, datetimeParsed);
    }

    /**
     * Parses the raw activity duration input provided by the user.
     * @param duration          The raw user input containing the duration.
     * @return durationParsed   The parsed Integer duration.
     * @throws AthletiException If the input is not an integer.
     */
    public static int parseDuration(String duration) throws AthletiException {
        int durationParsed;
        try {
            durationParsed = Integer.parseInt(duration);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DURATION_INVALID);
        }
        return durationParsed;
    }

    /**
     * Parses the raw date time input provided by the user.
     * @param datetime          The raw user input containing the date time.
     * @return datetimeParsed   The parsed LocalDateTime object.
     * @throws AthletiException If the input format is invalid.
     */
    public static LocalDateTime parseDateTime(String datetime) throws AthletiException {
        LocalDateTime datetimeParsed;
        try {
            datetimeParsed = LocalDateTime.parse(datetime.replace(" ", "T"));
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DATETIME_INVALID);
        }
        return datetimeParsed;
    }

    /**
     * Parses the raw activity distance input provided by the user.
     * @param distance              The raw user input containing the distance.
     * @return distanceParsed       The parsed Integer distance.
     * @throws AthletiException     If the input is not an integer.
     */
    public static int parseDistance(String distance) throws AthletiException {
        int distanceParsed;
        try {
            distanceParsed = Integer.parseInt(distance);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_INVALID);
        }
        return distanceParsed;
    }

    /**
     * Checks if the raw user input is missing any arguments for creating an activity.
     * @param durationIndex     The position of the duration separator.
     * @param distanceIndex     The position of the distance separator.
     * @param datetimeIndex     The position of the datetime separator.
     * @throws AthletiException If any of the arguments are missing.
     */
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
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseRunCycle(String arguments, boolean isRun) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);
        final int elevationIndex = arguments.indexOf(Parameter.ELEVATION_SEPARATOR);

        checkMissingRunCycleArguments(durationIndex, distanceIndex, datetimeIndex, elevationIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex).trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex).trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length(), elevationIndex).trim();
        final String elevation =
                arguments.substring(elevationIndex + Parameter.ELEVATION_SEPARATOR.length()).trim();

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

    /**
     * Parses the raw elevation input provided by the user.
     * @param elevation             The raw user input containing the elevation.
     * @return elevationParsed      The parsed Integer elevation.
     * @throws AthletiException     If the input is not an integer.
     */
    public static int parseElevation(String elevation) throws AthletiException {
        int elevationParsed;
        try {
            elevationParsed = Integer.parseInt(elevation);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_INVALID);
        }
        return elevationParsed;
    }

    /**
     * Checks if the raw user input is missing any arguments for creating a run or cycle.
     * @param durationIndex     The position of the duration separator.
     * @param distanceIndex     The position of the distance separator.
     * @param datetimeIndex     The position of the datetime separator.
     * @param elevationIndex    The position of the elevation separator.
     * @throws AthletiException If any of the arguments are missing.
     */
    public static void checkMissingRunCycleArguments(int durationIndex, int distanceIndex, int datetimeIndex,
                                                     int elevationIndex) throws AthletiException {
        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);
        if (elevationIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_MISSING);
        }
    }

    /**
     * Checks if the raw user input is missing any arguments for creating a swim.
     * @param durationIndex         The position of the duration separator.
     * @param distanceIndex         The position of the distance separator.
     * @param datetimeIndex         The position of the datetime separator.
     * @param swimmingStyleIndex    The position of the swimming style separator.
     * @throws AthletiException If any of the arguments are missing.
     */
    public static void checkMissingSwimArguments(int durationIndex, int distanceIndex, int datetimeIndex,
                                                 int swimmingStyleIndex) throws AthletiException {
        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);
        if (swimmingStyleIndex == -1) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_MISSING);
        }
    }

    /**
     * Checks if the raw user input includes any empty arguments for creating an activity.
     * @param caption           The caption of the activity.
     * @param duration          The duration of the activity.
     * @param distance          The distance of the activity.
     * @param datetime          The datetime of the activity.
     * @throws AthletiException If any of the arguments are empty.
     */
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

    /**
     * Checks if the raw user input includes any empty arguments for creating a cycle or run.
     * @param caption           The caption of the activity.
     * @param duration          The duration of the activity.
     * @param distance          The distance of the activity.
     * @param datetime          The datetime of the activity.
     * @param elevation         The elevation of the activity.
     * @throws AthletiException If any of the arguments are empty.
     */
    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime,
                                                   String elevation) throws AthletiException {
        checkEmptyActivityArguments(caption, duration, distance, datetime);
        if (elevation.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_EMPTY);
        }
    }

    /**
     * Checks if the raw user input includes any empty arguments for creating a swim.
     * @param caption               The caption of the activity.
     * @param duration              The duration of the activity.
     * @param distance              The distance of the activity.
     * @param datetime              The datetime of the activity.
     * @param swimmingStyleIndex    The position of the swimming style separator.
     * @throws AthletiException     If any of the arguments are empty.
     */
    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime, int swimmingStyleIndex) throws AthletiException {
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
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);
        final int swimmingStyleIndex = arguments.indexOf(Parameter.SWIMMING_STYLE_SEPARATOR);

        checkMissingSwimArguments(durationIndex, distanceIndex, datetimeIndex, swimmingStyleIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex).trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex).trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length(), swimmingStyleIndex).trim();
        final String swimmingStyle =
                arguments.substring(swimmingStyleIndex + Parameter.SWIMMING_STYLE_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, swimmingStyleIndex);

        final int durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = parseDateTime(datetime);
        final Swim.SwimmingStyle swimmingStyleParsed = parseSwimmingStyle(swimmingStyle);

        return new Swim(caption, durationParsed, distanceParsed, datetimeParsed, swimmingStyleParsed);
    }

    /**
     * Parses the raw user input for a swimming style and returns the corresponding swimming style object.
     * @param swimmingStyle     The raw user input containing the swimming style.
     * @return swimmingStyle    An object representing the swimming style.
     * @throws AthletiException If the input format is invalid.
     */
    public static Swim.SwimmingStyle parseSwimmingStyle(String swimmingStyle) throws AthletiException {
        try {
            return Swim.SwimmingStyle.valueOf(swimmingStyle.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_INVALID);
        }
    }


    /**
     * Parses the raw user input for an add sleep command and returns the corresponding command object.
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the slee0 add command.
     * @throws AthletiException
     */
    public static AddSleepCommand parseSleepAdd(String commandArgs) throws AthletiException {

        final String startMarkerConstant = "start/";
        final String endMarkerConstant = "end/";

        int startMarkerPos = commandArgs.indexOf(startMarkerConstant);
        int endMarkerPos = commandArgs.indexOf(endMarkerConstant);

        if (startMarkerPos == -1 || endMarkerPos == -1 || startMarkerPos > endMarkerPos) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        String startTimeStr =
                commandArgs.substring(startMarkerPos + startMarkerConstant.length(), endMarkerPos).trim();
        String endTimeStr = commandArgs.substring(endMarkerPos + endMarkerConstant.length()).trim();

        if (startTimeStr.isEmpty() || endTimeStr.isEmpty()) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        // Convert the strings to LocalDateTime
        LocalDateTime startTime;
        LocalDateTime endTime;
        try {
            startTime = LocalDateTime.parse(startTimeStr, sleepTimeFormatter);
            endTime = LocalDateTime.parse(endTimeStr, sleepTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_INVALID_DATE_TIME_FORMAT);
        }

        //Check if the start time is before the end time
        if (startTime.isAfter(endTime)) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_END_BEFORE_START);
        }

        return new AddSleepCommand(startTime, endTime);
    }

    /**
     * Parses the raw user input for a delete sleep command and returns the corresponding command object.
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
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the sleep edit command.
     * @throws AthletiException
     */
    public static EditSleepCommand parseSleepEdit(String commandArgs) throws AthletiException {
        final String startMarkerConstant = "start/";
        final String endMarkerConstant = "end/";

        int startMarkerPos = commandArgs.indexOf(startMarkerConstant);
        int endMarkerPos = commandArgs.indexOf(endMarkerConstant);
        int index;

        if (startMarkerPos == -1 || endMarkerPos == -1 || startMarkerPos > endMarkerPos) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        try {
            index = Integer.parseInt(commandArgs.substring(0, startMarkerPos).trim());
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_EDIT_NO_INDEX);
        }

        String startTimeStr =
                commandArgs.substring(startMarkerPos + startMarkerConstant.length(), endMarkerPos).trim();
        String endTimeStr = commandArgs.substring(endMarkerPos + endMarkerConstant.length()).trim();

        if (startTimeStr.isEmpty() || endTimeStr.isEmpty()) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME);
        }

        // Convert the strings to LocalDateTime
        LocalDateTime startTime;
        LocalDateTime endTime;
        try {
            startTime = LocalDateTime.parse(startTimeStr, sleepTimeFormatter);
            endTime = LocalDateTime.parse(endTimeStr, sleepTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_INVALID_DATE_TIME_FORMAT);
        }

        //Check if the start time is before the end time
        if (startTime.isAfter(endTime)) {
            throw new AthletiException(Message.ERRORMESSAGE_PARSER_SLEEP_END_BEFORE_START);
        }

        return new EditSleepCommand(index, startTime, endTime);
    }

    /**
     * @param commandArgs User provided data to create goals for the nutrients defined.
     * @return a list of diet goals for further checking in the Set Diet Goal Command.
     * @throws AthletiException Invalid input by the user.
     */
    public static ArrayList<DietGoal> parseDietGoalSetEdit(String commandArgs) throws AthletiException {
        if (commandArgs.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIETGOAL_INSUFFICIENT_INPUT);
        }
        try {
            String[] nutrientAndTargetValues;
            if (commandArgs.contains(" ")) {
                nutrientAndTargetValues = commandArgs.split("\\s+");
            } else {
                nutrientAndTargetValues = new String[]{commandArgs};
            }
            String[] nutrientAndTargetValue;
            String nutrient;
            int targetValue;

            ArrayList<DietGoal> dietGoals = new ArrayList<>();
            Set<String> recordedNutrients = new HashSet<>();

            for (int i = 0; i < nutrientAndTargetValues.length; i++) {
                nutrientAndTargetValue = nutrientAndTargetValues[i].split("/");
                nutrient = nutrientAndTargetValue[0];
                targetValue = Integer.parseInt(nutrientAndTargetValue[1]);
                if (targetValue == 0) {
                    throw new AthletiException(Message.MESSAGE_DIETGOAL_TARGET_VALUE_NOT_POSITIVE_INT);
                }
                if (!verifyValidNutrients(nutrient)) {
                    throw new AthletiException(Message.MESSAGE_DIETGOAL_INVALID_NUTRIENT);
                }
                if (recordedNutrients.contains(nutrient)) {
                    throw new AthletiException(Message.MESSSAGE_DIETGOAL_REPEATED_NUTRIENT);
                }
                DietGoal dietGoal = new DietGoal(nutrient, targetValue);
                dietGoals.add(dietGoal);
                recordedNutrients.add(nutrient);

            }

            return dietGoals;

        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIETGOAL_TARGET_VALUE_NOT_POSITIVE_INT);
        }
    }

    /**
     * @param nutrient The nutrient that is provided by the user.
     * @return boolean value depending on whether the nutrient is defined in our user guide.
     */
    public static boolean verifyValidNutrients(String nutrient) {
        return nutrient.equals(CALORIES_MARKER) || nutrient.equals(PROTEIN_MARKER)
                || nutrient.equals(CARB_MARKER) || nutrient.equals(FAT_MARKER);
    }

    /**
     * @param deleteIndexString Index of the goal to be deleted in String format
     * @return Index of the goal in integer format in users' perspective.
     * @throws AthletiException Catch invalid characters and numbers.
     */
    public static int parseDietGoalDelete(String deleteIndexString) throws AthletiException {
        try {
            int deleteIndex = Integer.parseInt(deleteIndexString.trim());
            if (deleteIndex <= 0) {
                throw new AthletiException(Message.MESSAGE_DIETGOAL_INCORRECT_INTEGER_FORMAT);
            }
            return deleteIndex;
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIETGOAL_INCORRECT_INTEGER_FORMAT);
        }

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
