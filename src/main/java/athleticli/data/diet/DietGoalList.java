package athleticli.data.diet;

import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.data.StorableList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import static athleticli.storage.Config.PATH_DIET_GOAL;

/**
 * Represents a list of diet goals.
 */
public class DietGoalList extends StorableList<DietGoal> {
    /**
     * Constructs a diet goal list.
     */
    public DietGoalList() {
        super(PATH_DIET_GOAL);
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
     * Parses a diet goal from a string.
     *
     * @param s The string to be parsed.
     * @return The diet goal parsed from the string.
     */
    @Override
    public DietGoal parse(String s) throws AthletiException {
        try {
            String[] dietGoalDetails = s.split("\\s+");
            System.out.println(dietGoalDetails);
            String dietGoalTimeSpanString = dietGoalDetails[1];
            String dietGoalNutrientString = dietGoalDetails[2];
            String dietGoalTargetValueString = dietGoalDetails[3];
            int dietGoalTargetValue = Integer.parseInt(dietGoalTargetValueString);

            return new DietGoal(Goal.TimeSpan.valueOf(dietGoalTimeSpanString.toUpperCase()),
                    dietGoalNutrientString, dietGoalTargetValue);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_LOAD_ERROR);
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
        return "dietGoal " + dietGoal.getTimeSpan() + " " + dietGoal.getNutrient()
                + " " + dietGoal.getTargetValue();

    }
}
