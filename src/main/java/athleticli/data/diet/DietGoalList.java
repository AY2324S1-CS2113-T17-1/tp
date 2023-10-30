package athleticli.data.diet;

import athleticli.data.Data;
import athleticli.data.StorableList;

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
    public DietGoal parse(String s) {
        // TODO
        return null;
    }

    /**
     * Unparses a diet goal to a string.
     *
     * @param dietGoal The diet goal to be parsed.
     * @return The string unparsed from the diet goal.
     */
    @Override
    public String unparse(DietGoal dietGoal) {
        // TODO
        return null;
    }
}
