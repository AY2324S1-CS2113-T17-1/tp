package athleticli.parser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import athleticli.data.Goal;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.Cycle;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

public class ActivityParser {
    //@@author  AlWo223
    /**
     * Parses the index of an activity.
     *
     * @param commandArgs The raw user input containing the index.
     * @return index                The parsed Integer index.
     * @throws AthletiException If the input is not an integer.
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
     *
     * @param arguments The raw user input containing the updated activity.
     * @return activity             The parsed Activity object.
     * @throws AthletiException If the input format is invalid.
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
     *
     * @param arguments The raw user input containing the updated run.
     * @return activity             The parsed run object.
     * @throws AthletiException If the input format is invalid.
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
     *
     * @param arguments The raw user input containing the updated cycle.
     * @return activity             The parsed cycle object.
     * @throws AthletiException If the input format is invalid.
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
     *
     * @param arguments The raw user input containing the updated swim.
     * @return activity             The parsed swim object.
     * @throws AthletiException If the input format is invalid.
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
     *
     * @param arguments The raw user input containing the index.
     * @return index              The parsed Integer index.
     * @throws AthletiException If the input format is invalid
     */
    public static int parseActivityEditIndex(String arguments) throws AthletiException {
        try {
            return parseActivityIndex(arguments.split(" ", 2)[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the raw user input for viewing the activity list and returns whether the user wants the detailed
     * view
     *
     * @param commandArgs The raw user input containing the arguments.
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
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseActivity(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);

        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex)
                        .trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex)
                        .trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime);

        final LocalTime durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);

        return new Activity(caption, durationParsed, distanceParsed, datetimeParsed);
    }

    /**
     * Parses the raw activity duration input provided by the user.
     *
     * @param duration The raw user input containing the duration.
     * @return durationParsed   The parsed LocalTime duration.
     * @throws AthletiException If the input is not an integer.
     */
    public static LocalTime parseDuration(String duration) throws AthletiException {
        LocalTime durationParsed;
        try {
            durationParsed = LocalTime.parse(duration);
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DURATION_INVALID);
        }
        return durationParsed;
    }

    /**
     * Parses the raw activity distance input provided by the user.
     *
     * @param distance The raw user input containing the distance.
     * @return distanceParsed       The parsed Integer distance.
     * @throws AthletiException If the input is not an integer.
     */
    public static int parseDistance(String distance) throws AthletiException {
        int distanceParsed;
        try {
            distanceParsed = Integer.parseInt(distance);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_INVALID);
        }
        if (distanceParsed < 0) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_NEGATIVE);
        }
        return distanceParsed;
    }

    /**
     * Checks if the raw user input is missing any arguments for creating an activity.
     *
     * @param durationIndex The position of the duration separator.
     * @param distanceIndex The position of the distance separator.
     * @param datetimeIndex The position of the datetime separator.
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
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex)
                        .trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex)
                        .trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length(), elevationIndex)
                        .trim();
        final String elevation =
                arguments.substring(elevationIndex + Parameter.ELEVATION_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, elevation);

        final LocalTime durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);
        final int elevationParsed = parseElevation(elevation);

        if (isRun) {
            return new Run(caption, durationParsed, distanceParsed, datetimeParsed, elevationParsed);
        } else {
            return new Cycle(caption, durationParsed, distanceParsed, datetimeParsed, elevationParsed);
        }
    }

    /**
     * Checks if the raw user input is missing any arguments for creating a run or cycle.
     *
     * @param durationIndex  The position of the duration separator.
     * @param distanceIndex  The position of the distance separator.
     * @param datetimeIndex  The position of the datetime separator.
     * @param elevationIndex The position of the elevation separator.
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
     *
     * @param durationIndex      The position of the duration separator.
     * @param distanceIndex      The position of the distance separator.
     * @param datetimeIndex      The position of the datetime separator.
     * @param swimmingStyleIndex The position of the swimming style separator.
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
     *
     * @param caption  The caption of the activity.
     * @param duration The duration of the activity.
     * @param distance The distance of the activity.
     * @param datetime The datetime of the activity.
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
     *
     * @param caption   The caption of the activity.
     * @param duration  The duration of the activity.
     * @param distance  The distance of the activity.
     * @param datetime  The datetime of the activity.
     * @param elevation The elevation of the activity.
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
     *
     * @param caption            The caption of the activity.
     * @param duration           The duration of the activity.
     * @param distance           The distance of the activity.
     * @param datetime           The datetime of the activity.
     * @param swimmingStyleIndex The position of the swimming style separator.
     * @throws AthletiException If any of the arguments are empty.
     */
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
     * @return activity         An object representing the activity.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseSwim(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);
        final int swimmingStyleIndex = arguments.indexOf(Parameter.SWIMMING_STYLE_SEPARATOR);

        checkMissingSwimArguments(durationIndex, distanceIndex, datetimeIndex, swimmingStyleIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex)
                        .trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex)
                        .trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length(), swimmingStyleIndex)
                        .trim();
        final String swimmingStyle =
                arguments.substring(swimmingStyleIndex + Parameter.SWIMMING_STYLE_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, swimmingStyleIndex);

        final LocalTime durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);
        final Swim.SwimmingStyle swimmingStyleParsed = parseSwimmingStyle(swimmingStyle);

        return new Swim(caption, durationParsed, distanceParsed, datetimeParsed, swimmingStyleParsed);
    }

    /**
     * Parses the raw user input for a swimming style and returns the corresponding swimming style object.
     *
     * @param swimmingStyle The raw user input containing the swimming style.
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
     * Parses the raw user input for adding an activity goal and returns the corresponding activity goal object.
     * @param commandArgs       The raw user input containing the arguments.
     * @return activityGoal     An object representing the activity goal.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityGoal parseActivityGoal(String commandArgs) throws AthletiException {
        final int sportIndex = commandArgs.indexOf(Parameter.SPORT_SEPARATOR);
        final int typeIndex = commandArgs.indexOf(Parameter.TYPE_SEPARATOR);
        final int periodIndex = commandArgs.indexOf(Parameter.PERIOD_SEPARATOR);
        final int targetIndex = commandArgs.indexOf(Parameter.TARGET_SEPARATOR);

        checkMissingActivityGoalArguments(sportIndex, typeIndex, periodIndex, targetIndex);

        final String sport = commandArgs.substring(sportIndex + Parameter.SPORT_SEPARATOR.length(), typeIndex).trim();
        final String type =
                commandArgs.substring(typeIndex + Parameter.TYPE_SEPARATOR.length(), periodIndex).trim();
        final String period =
                commandArgs.substring(periodIndex + Parameter.PERIOD_SEPARATOR.length(), targetIndex).trim();
        final String target = commandArgs.substring(targetIndex + Parameter.TARGET_SEPARATOR.length()).trim();

        final ActivityGoal.Sport sportParsed = parseSport(sport);
        final ActivityGoal.GoalType typeParsed = parseGoalType(type);
        final Goal.TimeSpan periodParsed = parsePeriod(period);
        final int targetParsed = parseTarget(target);

        return new ActivityGoal(periodParsed, typeParsed, sportParsed, targetParsed);
    }

    /**
     * Parses the sport input provided by the user.
     * @param sport                 The raw user input containing the sport.
     * @return sportParsed          The parsed Sport object.
     * @throws AthletiException     If the input format is invalid.
     */
    public static ActivityGoal.Sport parseSport(String sport) throws AthletiException {
        try {
            return ActivityGoal.Sport.valueOf(sport.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_SPORT_INVALID);
        }
    }

    /**
     * Checks if the raw user input is missing any arguments for creating an activity goal.
     * @param sportIndex        The position of the sport separator.
     * @param targetIndex       The position of the target separator.
     * @param periodIndex       The position of the period separator.
     * @param valueIndex        The position of the value separator.
     * @throws AthletiException If any of the arguments are missing.
     */
    public static void checkMissingActivityGoalArguments(int sportIndex, int targetIndex, int periodIndex,
            int valueIndex) throws AthletiException {
        if (sportIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_SPORT_MISSING);
        }
        if (targetIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_TARGET_MISSING);
        }
        if (periodIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_PERIOD_MISSING);
        }
        if (valueIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_TARGET_MISSING);
        }
    }

    /**
     * Parses the raw elevation input provided by the user.
     *
     * @param elevation The raw user input containing the elevation.
     * @return elevationParsed      The parsed Integer elevation.
     * @throws AthletiException If the input is not an integer.
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
     * Parses the goal type input provided by the user.
     * @param type                The raw user input containing the goal type.
     * @return goalParsed         The parsed GoalType object.
     * @throws AthletiException   If the input format is invalid.
     */
    public static ActivityGoal.GoalType parseGoalType(String type) throws AthletiException {
        try {
            return ActivityGoal.GoalType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_TYPE_INVALID);
        }
    }

    /**
     * Parses the period input provided by the user
     * @param period            The raw user input containing the period.
     * @return periodParsed     The parsed Period object.
     * @throws AthletiException If the input format is invalid.
     */
    public static Goal.TimeSpan parsePeriod(String period) throws AthletiException {
        try {
            return Goal.TimeSpan.valueOf(period.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_PERIOD_INVALID);
        }
    }

    /**
     * Parses the target input provided by the user.
     * @param target            The raw user input containing the target value.
     * @return targetParsed     The parsed Integer target value.
     * @throws AthletiException If the input is not a positive number.
     */
    public static int parseTarget(String target) throws AthletiException {
        int targetParsed;
        try {
            targetParsed = Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_TARGET_INVALID);
        }
        if (targetParsed < 0) {
            throw new AthletiException(Message.MESSAGE_TARGET_NEGATIVE);
        }
        return targetParsed;
    }
}
