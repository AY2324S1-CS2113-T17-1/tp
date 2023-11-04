package athleticli.parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import athleticli.data.Goal;
import athleticli.data.diet.Diet;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.HealthyDietGoal;
import athleticli.data.diet.UnhealthyDietGoal;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

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
    public static ArrayList<DietGoal> parseDietGoalSetEdit(String commandArgsString) throws AthletiException {
        if (commandArgsString.trim().isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INSUFFICIENT_INPUT);
        }
        try {
            String[] commandArgs;
            if (!commandArgsString.contains(" ")) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_INSUFFICIENT_INPUT);
            }

            commandArgs = commandArgsString.split("\\s+");

            ArrayList<DietGoal> dietGoals = initializeIntermediateDietGoals(commandArgs);

            return dietGoals;
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_POSITIVE_INT);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INSUFFICIENT_INPUT);
        }
    }

    private static ArrayList<DietGoal> initializeIntermediateDietGoals(String[] commandArgs) throws AthletiException {
        String[] nutrientAndTargetValue;
        String nutrient;
        int targetValue;
        int nutrientStartingIndex = 1;
        boolean isHealthy = true;

        Goal.TimeSpan timespan = ActivityParser.parsePeriod(commandArgs[0]);
        if (commandArgs[1].toLowerCase().equals("unhealthy")) {
            isHealthy = false;
            nutrientStartingIndex += 1;
        }

        ArrayList<DietGoal> dietGoals = new ArrayList<>();
        Set<String> recordedNutrients = new HashSet<>();

        for (int i = nutrientStartingIndex; i < commandArgs.length; i++) {
            nutrientAndTargetValue = commandArgs[i].split("/");
            nutrient = nutrientAndTargetValue[0];
            targetValue = Integer.parseInt(nutrientAndTargetValue[1]);
            if (targetValue <= 0) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_POSITIVE_INT);
            }
            if (!NutrientVerifier.verify(nutrient)) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_INVALID_NUTRIENT);
            }
            if (recordedNutrients.contains(nutrient)) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_REPEATED_NUTRIENT);
            }
            DietGoal dietGoal;
            if (isHealthy) {
                dietGoal = new HealthyDietGoal(timespan, nutrient, targetValue);
            } else {
                dietGoal = new UnhealthyDietGoal(timespan, nutrient, targetValue);
            }
            dietGoals.add(dietGoal);
            recordedNutrients.add(nutrient);
        }
        return dietGoals;
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

        String calories = commandArgs.substring(caloriesMarkerPos + Parameter.CALORIES_SEPARATOR.length(),
                proteinMarkerPos).trim();
        String protein =
                commandArgs.substring(proteinMarkerPos + Parameter.PROTEIN_SEPARATOR.length(), carbMarkerPos)
                        .trim();
        String carb =
                commandArgs.substring(carbMarkerPos + Parameter.CARB_SEPARATOR.length(), fatMarkerPos).trim();
        String fat = commandArgs.substring(fatMarkerPos + Parameter.FAT_SEPARATOR.length(), datetimeMarkerPos)
                .trim();
        String datetime =
                commandArgs.substring(datetimeMarkerPos + Parameter.DATETIME_SEPARATOR.length()).trim();

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
        int caloriesParsed;
        try {
            caloriesParsed = Integer.parseInt(calories);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_CALORIES_INVALID);
        }
        if (caloriesParsed < 0) {
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
        if (proteinParsed < 0) {
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
        if (carbParsed < 0) {
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
        if (fatParsed < 0) {
            throw new AthletiException(Message.MESSAGE_FAT_INVALID);
        }
        return fatParsed;
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
        int index;
        try {
            index = Integer.parseInt(words[0]);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DIET_INDEX_TYPE_INVALID);
        }
        if (index < 1) {
            throw new AthletiException(Message.MESSAGE_DIET_INDEX_TYPE_INVALID);
        }
        return index;
    }

    /**
     * Parses the value for a specific marker in a given argument string.
     *
     * @param arguments The raw user input containing the arguments.
     * @param marker    The marker whose value is to be retrieved.
     * @return The value associated with the given marker, or an empty string if the marker is not found.
     */
    public static String getValueForMarker(String arguments, String marker) {
        String patternString = "";

        if (marker.equals(Parameter.DATETIME_SEPARATOR)) {
            // Special handling for datetime to capture the date and time
            patternString = marker + "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})";
        } else {
            // For other markers, capture a sequence of non-whitespace characters
            patternString = marker + "(\\S+)";
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(arguments);

        if (matcher.find()) {
            return matcher.group(1);
        }

        // Return empty string if no match is found
        return "";
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
            int caloriesParsed = Integer.parseInt(calories);
            dietMap.put(Parameter.CALORIES_SEPARATOR, Integer.toString(caloriesParsed));
        }
        if (!protein.isEmpty()) {
            int proteinParsed = Integer.parseInt(protein);
            dietMap.put(Parameter.PROTEIN_SEPARATOR, Integer.toString(proteinParsed));
        }
        if (!carb.isEmpty()) {
            int carbParsed = Integer.parseInt(carb);
            dietMap.put(Parameter.CARB_SEPARATOR, Integer.toString(carbParsed));
        }
        if (!fat.isEmpty()) {
            int fatParsed = Integer.parseInt(fat);
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
