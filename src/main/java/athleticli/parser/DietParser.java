package athleticli.parser;

import athleticli.data.Goal;
import athleticli.data.diet.Diet;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.HealthyDietGoal;
import athleticli.data.diet.UnhealthyDietGoal;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static athleticli.parser.Parser.getValueForMarker;
import static athleticli.parser.Parser.parseDateTime;
import static athleticli.parser.Parser.parseNonNegativeInteger;

/**
 * Defines the methods for Diet parser and Diet Goal parser
 */
public class DietParser {
    //@@author  yicheng-toh

    /**
     * @param commandArgsString User provided data to create goals for the nutrients defined.
     * @return a list of diet goals for further checking in the Set Diet Goal Command.
     * @throws AthletiException Invalid input by the user.
     */
    public static ArrayList<DietGoal> parseDietGoalSetAndEdit(String commandArgsString) throws AthletiException {
        ArrayList<DietGoal> dietGoals;
        try {
            validateCommandArgsString(commandArgsString);
            dietGoals = initializeTempDietGoals(commandArgsString);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_POSITIVE_INT);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INSUFFICIENT_INPUT);
        }
        return dietGoals;
    }

    private static void validateCommandArgsString(String commandArgsString) throws AthletiException {
        if (commandArgsString.trim().isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INSUFFICIENT_INPUT);
        }
        if (!commandArgsString.contains(" ")) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INSUFFICIENT_INPUT);
        }
    }

    private static ArrayList<DietGoal> initializeTempDietGoals(
            String commandArgsString) throws AthletiException {

        int nutrientStartingIndex;
        boolean isHealthy;
        String[] commandArgs = commandArgsString.split(Parameter.SPACE_SEPEARATOR);

        Goal.TimeSpan timespan = parsePeriod(commandArgs[Parameter.DIET_GOAL_TIME_SPAN_INDEX]);
        if (commandArgs[Parameter.DIET_GOAL_UNHEALTHY_FLAG_INDEX].equalsIgnoreCase(
                Parameter.UNHEALTHY_DIET_GOAL_FLAG)) {
            isHealthy = false;
            nutrientStartingIndex = Parameter.UNHEALTHY_DIET_GOAL_NUTRIENT_STARTING_INDEX;
        } else {
            isHealthy = true;
            nutrientStartingIndex = Parameter.HEALTHY_DIET_GOAL_NUTRIENT_STARTING_INDEX;
        }
        return createNewDietGoals(nutrientStartingIndex, commandArgs, isHealthy, timespan);
    }

    private static ArrayList<DietGoal> createNewDietGoals(int nutrientStartingIndex, String[] commandArgs,
            boolean isHealthy, Goal.TimeSpan timespan) throws AthletiException {

        ArrayList<DietGoal> dietGoals = new ArrayList<>();
        Set<String> recordedNutrients = new HashSet<>();

        String nutrient;
        String[] nutrientAndTargetValue;
        int targetValue;

        for (int i = nutrientStartingIndex; i < commandArgs.length; i++) {

            nutrientAndTargetValue = commandArgs[i].split(Parameter.DIET_GOAL_COMMAND_VALUE_SEPARATOR);
            nutrient = nutrientAndTargetValue[Parameter.DIET_GOAL_NUTRIENT_STARTING_INDEX];
            targetValue = Integer.parseInt(nutrientAndTargetValue[Parameter.DIET_GOAL_TARGET_VALUE_STARTING_INDEX]);

            validateDietGoalParameters(recordedNutrients, targetValue, nutrient);
            DietGoal dietGoal = createNewDietGoal(isHealthy, timespan, nutrient, targetValue);

            dietGoals.add(dietGoal);
            recordedNutrients.add(nutrient);
        }
        return dietGoals;
    }


    private static void validateDietGoalParameters(Set<String> recordedNutrients, int targetValue, String nutrient)
            throws AthletiException {
        if (targetValue <= 0) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_POSITIVE_INT);
        }
        if (!NutrientVerifier.verify(nutrient)) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INVALID_NUTRIENT);
        }
        if (recordedNutrients.contains(nutrient)) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_REPEATED_NUTRIENT);
        }
    }

    private static DietGoal createNewDietGoal(boolean isHealthy, Goal.TimeSpan timespan, String nutrient,
                                              int targetValue) {
        DietGoal dietGoal;
        if (isHealthy) {
            dietGoal = new HealthyDietGoal(timespan, nutrient, targetValue);
        } else {
            dietGoal = new UnhealthyDietGoal(timespan, nutrient, targetValue);
        }
        return dietGoal;
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
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_INCORRECT_INTEGER_FORMAT);
            }
            return deleteIndex;
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INCORRECT_INTEGER_FORMAT);
        }
    }

    /**
     * Parses the period input provided by the user
     * @param period            The raw user input containing the period.
     * @return                  The parsed Period object.
     * @throws AthletiException If the input format is invalid.
     */
    public static Goal.TimeSpan parsePeriod(String period) throws AthletiException {
        try {
            Goal.TimeSpan timePeriod = Goal.TimeSpan.valueOf(period.toUpperCase());
            //Diet goal only support up to period that is less than or equal to DIET_GOAL_TIME_SPAN_LIMIT
            if (timePeriod.getDays() > Parameter.DIET_GOAL_TIME_SPAN_LIMIT ){
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_PERIOD_INVALID);
            }
            return timePeriod.valueOf(period.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_PERIOD_INVALID);
        }
    }

    //@@author  nihalzp

    /**
     * Parses the raw user input for a diet and returns the corresponding diet object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the diet.
     * @throws AthletiException
     */
    public static Diet parseDiet(String commandArgs) throws AthletiException {
        checkMissingDietArguments(commandArgs);

        checkDuplicateDietArguments(commandArgs);

        final String calories = getValueForMarker(commandArgs, Parameter.CALORIES_SEPARATOR);
        final String protein = getValueForMarker(commandArgs, Parameter.PROTEIN_SEPARATOR);
        final String carb = getValueForMarker(commandArgs, Parameter.CARB_SEPARATOR);
        final String fat = getValueForMarker(commandArgs, Parameter.FAT_SEPARATOR);
        final String datetime = getValueForMarker(commandArgs, Parameter.DATETIME_SEPARATOR);

        checkEmptyDietArguments(calories, protein, carb, fat, datetime);

        int caloriesParsed = parseNonNegativeInteger(calories, Message.MESSAGE_CALORIES_INVALID,
                Message.MESSAGE_CALORIES_OVERFLOW);
        int proteinParsed = parseNonNegativeInteger(protein, Message.MESSAGE_PROTEIN_INVALID,
                Message.MESSAGE_PROTEIN_OVERFLOW);
        int carbParsed =
                parseNonNegativeInteger(carb, Message.MESSAGE_CARB_INVALID, Message.MESSAGE_CARB_OVERFLOW);
        int fatParsed =
                parseNonNegativeInteger(fat, Message.MESSAGE_FAT_INVALID, Message.MESSAGE_FAT_OVERFLOW);
        LocalDateTime datetimeParsed = parseDateTime(datetime);
        return new Diet(caloriesParsed, proteinParsed, carbParsed, fatParsed, datetimeParsed);
    }

    /**
     * Checks if marker is missing in the user input.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @param marker      The marker for the argument.
     * @return True if the argument is missing, false otherwise.
     */
    public static boolean isArgumentMissing(String commandArgs, String marker) {
        int markerPos = commandArgs.indexOf(marker);
        return markerPos == -1;
    }

    /**
     * Checks if marker is duplicated in the user input.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @param marker      The marker for the argument.
     * @return True if the argument is duplicated, false otherwise.
     */
    public static boolean isArgumentDuplicate(String commandArgs, String marker) {
        int markerPos = commandArgs.indexOf(marker);
        int lastMarkerPos = commandArgs.lastIndexOf(marker);
        return markerPos != lastMarkerPos;
    }

    /**
     * Checks if any of the arguments for a diet is missing.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @throws AthletiException
     */
    public static void checkMissingDietArguments(String commandArgs) throws AthletiException {
        if (isArgumentMissing(commandArgs, Parameter.CALORIES_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_CALORIES_MISSING);
        }
        if (isArgumentMissing(commandArgs, Parameter.PROTEIN_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_MISSING);
        }
        if (isArgumentMissing(commandArgs, Parameter.CARB_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_CARB_MISSING);
        }
        if (isArgumentMissing(commandArgs, Parameter.FAT_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_FAT_MISSING);
        }
        if (isArgumentMissing(commandArgs, Parameter.DATETIME_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_DIET_DATETIME_MISSING);
        }
    }

    /**
     * Checks if any of the arguments for a diet is duplicated.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @throws AthletiException
     */
    public static void checkDuplicateDietArguments(String commandArgs) throws AthletiException {
        if (isArgumentDuplicate(commandArgs, Parameter.CALORIES_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_CALORIES_ARG_DUPLICATE);
        }
        if (isArgumentDuplicate(commandArgs, Parameter.PROTEIN_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_ARG_DUPLICATE);
        }
        if (isArgumentDuplicate(commandArgs, Parameter.CARB_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_CARB_ARG_DUPLICATE);
        }
        if (isArgumentDuplicate(commandArgs, Parameter.FAT_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_FAT_ARG_DUPLICATE);
        }
        if (isArgumentDuplicate(commandArgs, Parameter.DATETIME_SEPARATOR)) {
            throw new AthletiException(Message.MESSAGE_DIET_ARG_DATETIME_DUPLICATE);
        }
    }

    /**
     * Checks if the user input for a diet is empty.
     *
     * @param calories The calories input.
     * @param protein  The protein input.
     * @param carb     The carb input.
     * @param fat      The fat input.
     * @param datetime The datetime input.
     * @throws AthletiException
     */
    public static void checkEmptyDietArguments(String calories, String protein, String carb, String fat,
                                               String datetime) throws AthletiException {
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
        if (datetime.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIET_DATETIME_EMPTY);
        }
    }

    /**
     * Parses the index of a diet.
     *
     * @param commandArgs The raw user input containing the index.
     * @return The parsed index.
     * @throws AthletiException If the input format is invalid.
     */
    public static int parseDietIndex(String commandArgs) throws AthletiException {
        if (commandArgs == null || commandArgs.trim().isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIET_INDEX_TYPE_INVALID);
        }

        String[] words = commandArgs.trim().split("\\s+", 2);  // Split into parts
        int parsedIndex = parseNonNegativeInteger(words[0], Message.MESSAGE_DIET_INDEX_TYPE_INVALID,
                Message.MESSAGE_INVALID_DIET_INDEX);

        if (parsedIndex == 0) {
            throw new AthletiException(Message.MESSAGE_DIET_INDEX_TYPE_INVALID);
        }
        return parsedIndex;
    }

    /**
     * Parses the raw user input for a sleep and returns the corresponding sleep object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the sleep.
     * @throws AthletiException If the input format is invalid.
     */
    public static HashMap<String, String> parseDietEdit(String arguments) throws AthletiException {
        checkDuplicateDietArguments(arguments);

        HashMap<String, String> dietMap = new HashMap<>();
        String calories = getValueForMarker(arguments, Parameter.CALORIES_SEPARATOR);
        String protein = getValueForMarker(arguments, Parameter.PROTEIN_SEPARATOR);
        String carb = getValueForMarker(arguments, Parameter.CARB_SEPARATOR);
        String fat = getValueForMarker(arguments, Parameter.FAT_SEPARATOR);
        String datetime = getValueForMarker(arguments, Parameter.DATETIME_SEPARATOR);
        if (!calories.isEmpty()) {
            int caloriesParsed = parseNonNegativeInteger(calories, Message.MESSAGE_CALORIES_INVALID,
                    Message.MESSAGE_CALORIES_OVERFLOW);
            dietMap.put(Parameter.CALORIES_SEPARATOR, Integer.toString(caloriesParsed));
        }
        if (!protein.isEmpty()) {
            int proteinParsed = parseNonNegativeInteger(protein, Message.MESSAGE_PROTEIN_INVALID,
                    Message.MESSAGE_PROTEIN_OVERFLOW);
            dietMap.put(Parameter.PROTEIN_SEPARATOR, Integer.toString(proteinParsed));
        }
        if (!carb.isEmpty()) {
            int carbParsed = parseNonNegativeInteger(carb, Message.MESSAGE_CARB_INVALID,
                    Message.MESSAGE_CARB_OVERFLOW);
            dietMap.put(Parameter.CARB_SEPARATOR, Integer.toString(carbParsed));
        }
        if (!fat.isEmpty()) {
            int fatParsed =
                    parseNonNegativeInteger(fat, Message.MESSAGE_FAT_INVALID, Message.MESSAGE_FAT_OVERFLOW);
            dietMap.put(Parameter.FAT_SEPARATOR, Integer.toString(fatParsed));
        }
        if (!datetime.isEmpty()) {
            LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);
            dietMap.put(Parameter.DATETIME_SEPARATOR, datetimeParsed.toString());
        }
        if (dietMap.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIET_NO_CHANGE_REQUESTED);
        }
        return dietMap;
    }
}
