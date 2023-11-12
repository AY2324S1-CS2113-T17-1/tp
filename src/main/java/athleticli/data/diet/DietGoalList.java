package athleticli.data.diet;

import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.data.StorableList;
import athleticli.exceptions.AthletiException;
import athleticli.parser.NutrientVerifier;
import athleticli.parser.Parameter;
import athleticli.ui.Message;

import static athleticli.common.Config.PATH_DIET_GOAL;

/**
 * Represents a list of diet goals.
 */
public class DietGoalList extends StorableList<DietGoal> {

    private final String unparseMessage;

    /**
     * Constructs a diet goal list.
     */
    public DietGoalList() {
        super(PATH_DIET_GOAL);
        unparseMessage = "dietGoal %s %s %s %s";
    }

    /**
     * Returns a string representation of the diet goal list.
     *
     * @param data A storage class to retrieve diet information.
     * @return A string representation of the diet goal list.
     */
    public String toString(Data data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append("\t").append(i + 1).append(". ").append(get(i).toString(data));
            if (i != size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Checks if diet goal of the same nutrients and time span existed in the list.
     *
     * @param dietGoal
     * @return boolean value to indicate if it is not in the list.
     */
    public boolean isDietGoalUnique(DietGoal dietGoal) {
        for (int i = 0; i < size(); i++) {
            if (get(i).isSameNutrient(dietGoal) && get(i).isSameTimeSpan(dietGoal)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a diet goal has clashing type as those existed in the list.
     * The type of diet goals are 'healthy' and 'unhealthy'.
     *
     * @param dietGoal
     * @return boolean value to indicate if the type is valid.
     */
    public boolean isDietGoalTypeValid(DietGoal dietGoal) {
        for (int i = 0; i < size(); i++) {
            if (get(i).isSameNutrient(dietGoal) && !get(i).isSameType(dietGoal)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the diet goals in the list follow the order where the longer the time span,
     * the greater the target value.
     *
     * @return boolean value indicating that the target value is larger for similar diet goals with longer time span.
     */
    public boolean isTargetValueConsistentWithTimeSpan(DietGoal newDietGoal) {
        DietGoal storedDietGoal;
        for (int i = 0; i < size(); i++) {
            storedDietGoal = get(i);
            if (!storedDietGoal.isSameNutrient(newDietGoal)) {
                continue;
            }
            boolean isTimeSpanGreater =
                    storedDietGoal.getTimeSpan().getDays() > newDietGoal.getTimeSpan().getDays();
            boolean isTimeSpanEqual = storedDietGoal.getTimeSpan().getDays() == newDietGoal.getTimeSpan().getDays();
            boolean isTimeSpanLess = storedDietGoal.getTimeSpan().getDays() < newDietGoal.getTimeSpan().getDays();
            boolean isTargetValueGreater = storedDietGoal.getTargetValue() > newDietGoal.getTargetValue();
            boolean isTargetValueLess = storedDietGoal.getTargetValue() < newDietGoal.getTargetValue();
            //Goals with the same time span can take on different values due to goal editing.
            if (isTimeSpanEqual) {
                continue;
            }
            if (isTimeSpanGreater && isTargetValueGreater) {
                continue;
            }
            if (isTimeSpanLess && isTargetValueLess) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * Parses a diet goal from a string.
     *
     * @param s The string to be parsed.
     * @return The diet goal parsed from the string.
     */
    @Override
    public DietGoal parse(String s) throws AthletiException {
        try {
            DietGoal dietGoal = null;
            String[] dietGoalDetails = s.split("\\s+");
            String dietGoalTimeSpanString = dietGoalDetails[1];
            String dietGoalNutrientString = dietGoalDetails[2];
            String dietGoalTargetValueString = dietGoalDetails[3];
            String dietGoalType = dietGoalDetails[4];
            int dietGoalTargetValue = Integer.parseInt(dietGoalTargetValueString);
            int dietGoalTimeSpanValue = Goal.TimeSpan.valueOf(dietGoalTimeSpanString.toUpperCase()).getDays();

            verifyParseParameters(dietGoalNutrientString, dietGoalTimeSpanValue);
            dietGoal = createParseNewDietGoal(dietGoalType, dietGoalTimeSpanString,
                    dietGoalNutrientString, dietGoalTargetValue);
            validateParseDietGoal(dietGoal);
            return dietGoal;

        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_LOAD_ERROR);
        }
    }

    private void validateParseDietGoal(DietGoal dietGoal) throws AthletiException {
        if (!isDietGoalUnique(dietGoal)) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_REPEATED_NUTRIENT);
        }
        if (!isDietGoalTypeValid(dietGoal)) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_TYPE_CLASH);
        }
    }

    private static DietGoal createParseNewDietGoal(String dietGoalType, String dietGoalTimeSpanString,
            String dietGoalNutrientString, int dietGoalTargetValue) throws AthletiException {
        DietGoal dietGoal;
        if (dietGoalType.toLowerCase().equals(HealthyDietGoal.TYPE)) {
            dietGoal = new HealthyDietGoal(Goal.TimeSpan.valueOf(dietGoalTimeSpanString.toUpperCase()),
                    dietGoalNutrientString, dietGoalTargetValue);
        } else if (dietGoalType.toLowerCase().equals(UnhealthyDietGoal.TYPE)) {
            dietGoal = new UnhealthyDietGoal(Goal.TimeSpan.valueOf(dietGoalTimeSpanString.toUpperCase()),
                    dietGoalNutrientString, dietGoalTargetValue);
        } else {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_LOAD_ERROR);
        }
        return dietGoal;
    }

    private static void verifyParseParameters(String dietGoalNutrientString, int dietGoalTimeSpanValue)
            throws AthletiException {
        if (!NutrientVerifier.verify(dietGoalNutrientString)) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_INVALID_NUTRIENT);
        }
        //Diet goal only support up to period that is less than or equal to DIET_GOAL_TIME_SPAN_LIMIT
        if (dietGoalTimeSpanValue > Parameter.DIET_GOAL_TIME_SPAN_LIMIT) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_PERIOD_INVALID);
        }
    }

    /**
     * Unparses a diet goal to a string.
     *
     * @param dietGoal The diet goal to be parsed.
     * @return The string unparsed from the diet goal.
     */
    @Override
    public String unparse(DietGoal dietGoal) {
        /*
         * diet goal has nutrient, target value, date. there rest are calculated on the spot.
         * */
        return String.format(unparseMessage, dietGoal.getTimeSpan(), dietGoal.getNutrient(),
                dietGoal.getTargetValue(), dietGoal.getType());

    }
}
