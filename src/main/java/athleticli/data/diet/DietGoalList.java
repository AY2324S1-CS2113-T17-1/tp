package athleticli.data.diet;

import java.util.ArrayList;

/**
 * Represents a list of diet goals.
 */
public class DietGoalList extends ArrayList<DietGoal> {
    /**
     * Constructs a diet goal list.
     */
    public DietGoalList() {
        super();
    }

    /**
     * Returns a string representation of the diet goal list.
     *
     * @return A string representation of the diet goal list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(i + 1).append(". ").append(get(i).toString());
        }
        return result.toString();
    }
}
