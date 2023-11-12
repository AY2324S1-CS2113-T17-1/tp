package athleticli.parser;

import athleticli.common.Config;
import athleticli.data.Goal;
import athleticli.data.diet.Diet;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.HealthyDietGoal;
import athleticli.data.diet.UnhealthyDietGoal;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static athleticli.parser.Parser.getValueForMarker;

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

        Goal.TimeSpan timespan = ActivityParser.parsePeriod(commandArgs[Parameter.DIET_GOAL_TIME_SPAN_INDEX]);
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

    //@@author  nihalzp

    /**
     * Parses the raw user input for a diet and returns the corresponding diet object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the diet.
     * @throws AthletiException
     */
    public static Diet parseDiet(String commandArgs) throws AthletiException {
        int caloriesMarkerPos = commandArgs.indexOf(Parameter.CALORIES_SEPARATOR);
        int proteinMarkerPos = commandArgs.indexOf(Parameter.PROTEIN_SEPARATOR);
        int carbMarkerPos = commandArgs.indexOf(Parameter.CARB_SEPARATOR);
        int fatMarkerPos = commandArgs.indexOf(Parameter.FAT_SEPARATOR);
        int datetimeMarkerPos = commandArgs.indexOf(Parameter.DATETIME_SEPARATOR);

        checkMissingDietArguments(caloriesMarkerPos, proteinMarkerPos, carbMarkerPos, fatMarkerPos,
                datetimeMarkerPos);

        final String calories = getValueForMarker(commandArgs, Parameter.CALORIES_SEPARATOR);
        final String protein = getValueForMarker(commandArgs, Parameter.PROTEIN_SEPARATOR);
        final String carb = getValueForMarker(commandArgs, Parameter.CARB_SEPARATOR);
        final String fat = getValueForMarker(commandArgs, Parameter.FAT_SEPARATOR);
        final String datetime = getValueForMarker(commandArgs, Parameter.DATETIME_SEPARATOR);

        checkEmptyDietArguments(calories, protein, carb, fat, datetime);

        int caloriesParsed = parseCalories(calories);
        int proteinParsed = parseProtein(protein);
        int carbParsed = parseCarb(carb);
        int fatParsed = parseFat(fat);
        LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);
        return new Diet(caloriesParsed, proteinParsed, carbParsed, fatParsed, datetimeParsed);
    }

    /**
     * Checks if the user input for a diet contains all the required arguments.
     *
     * @param caloriesMarkerPos The position of the calories marker.
     * @param proteinMarkerPos  The position of the protein marker.
     * @param carbMarkerPos     The position of the carb marker.
     * @param fatMarkerPos      The position of the fat marker.
     * @param datetimeMarkerPos The position of the datetime marker.
     * @throws AthletiException
     */
    public static void checkMissingDietArguments(int caloriesMarkerPos, int proteinMarkerPos,
                                                 int carbMarkerPos, int fatMarkerPos,
                                                 int datetimeMarkerPos) throws AthletiException {
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
        if (datetimeMarkerPos == -1) {
            throw new AthletiException(Message.MESSAGE_DIET_DATETIME_MISSING);
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
     * Parses the calories input for a diet.
     *
     * @param calories The calories input.
     * @return The parsed calories.
     * @throws AthletiException
     */
    public static int parseCalories(String calories) throws AthletiException {
        BigInteger caloriesParsed;
        try {
            caloriesParsed = new BigInteger(calories);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_CALORIES_INVALID);
        }
        if (caloriesParsed.signum() < 0) {
            throw new AthletiException(Message.MESSAGE_CALORIES_INVALID);
        }
        if (caloriesParsed.compareTo(BigInteger.valueOf(Config.MAX_INPUT_NUMBER_ALLOWED)) > 0) {
            throw new AthletiException(Message.MESSAGE_CALORIE_OVERFLOW);
        }
        return caloriesParsed.intValue();
    }

    /**
     * Parses the protein input for a diet.
     *
     * @param protein The protein input.
     * @return The parsed protein.
     * @throws AthletiException
     */
    public static int parseProtein(String protein) throws AthletiException {
        BigInteger proteinParsed;
        try {
            proteinParsed = new BigInteger(protein);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_INVALID);
        }
        if (proteinParsed.signum() < 0) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_INVALID);
        }
        if (proteinParsed.compareTo(BigInteger.valueOf(Config.MAX_INPUT_NUMBER_ALLOWED)) > 0) {
            throw new AthletiException(Message.MESSAGE_PROTEIN_OVERFLOW);
        }
        return proteinParsed.intValue();
    }

    /**
     * Parses the carb input for a diet.
     *
     * @param carb The carb input.
     * @return The parsed carb.
     * @throws AthletiException
     */
    public static int parseCarb(String carb) throws AthletiException {
        BigInteger carbParsed;
        try {
            carbParsed = new BigInteger(carb);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_CARB_INVALID);
        }
        if (carbParsed.signum() < 0) {
            throw new AthletiException(Message.MESSAGE_CARB_INVALID);
        }
        if (carbParsed.compareTo(BigInteger.valueOf(Config.MAX_INPUT_NUMBER_ALLOWED)) > 0) {
            throw new AthletiException(Message.MESSAGE_CARB_OVERFLOW);
        }
        return carbParsed.intValue();
    }

    /**
     * Parses the fat input for a diet.
     *
     * @param fat The fat input.
     * @return The parsed fat.
     * @throws AthletiException
     */
    public static int parseFat(String fat) throws AthletiException {
        BigInteger fatParsed;
        try {
            fatParsed = new BigInteger(fat);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_FAT_INVALID);
        }
        if (fatParsed.signum() < 0) {
            throw new AthletiException(Message.MESSAGE_FAT_INVALID);
        }
        if (fatParsed.compareTo(BigInteger.valueOf(Config.MAX_INPUT_NUMBER_ALLOWED)) > 0) {
            throw new AthletiException(Message.MESSAGE_FAT_OVERFLOW);
        }
        return fatParsed.intValue();
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
        BigInteger indexParsed;
        try {
            indexParsed = new BigInteger(words[0]);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIET_INDEX_TYPE_INVALID);
        }
        if (indexParsed.signum() < 0 || indexParsed.signum() == 0) {
            throw new AthletiException(Message.MESSAGE_DIET_INDEX_TYPE_INVALID);
        }
        if (indexParsed.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new AthletiException(Message.MESSAGE_INVALID_DIET_INDEX);
        }
        return indexParsed.intValue();
    }

    /**
     * Parses the raw user input for a sleep and returns the corresponding sleep object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the sleep.
     * @throws AthletiException If the input format is invalid.
     */
    public static HashMap<String, String> parseDietEdit(String arguments) throws AthletiException {
        HashMap<String, String> dietMap = new HashMap<>();
        String calories = getValueForMarker(arguments, Parameter.CALORIES_SEPARATOR);
        String protein = getValueForMarker(arguments, Parameter.PROTEIN_SEPARATOR);
        String carb = getValueForMarker(arguments, Parameter.CARB_SEPARATOR);
        String fat = getValueForMarker(arguments, Parameter.FAT_SEPARATOR);
        String datetime = getValueForMarker(arguments, Parameter.DATETIME_SEPARATOR);
        if (!calories.isEmpty()) {
            int caloriesParsed = parseCalories(calories);
            dietMap.put(Parameter.CALORIES_SEPARATOR, Integer.toString(caloriesParsed));
        }
        if (!protein.isEmpty()) {
            int proteinParsed = parseProtein(protein);
            dietMap.put(Parameter.PROTEIN_SEPARATOR, Integer.toString(proteinParsed));
        }
        if (!carb.isEmpty()) {
            int carbParsed = parseCarb(carb);
            dietMap.put(Parameter.CARB_SEPARATOR, Integer.toString(carbParsed));
        }
        if (!fat.isEmpty()) {
            int fatParsed = parseFat(fat);
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
