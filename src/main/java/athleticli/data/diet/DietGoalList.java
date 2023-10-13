package athleticli.data.diet;

import java.util.ArrayList;

public class DietGoalList extends ArrayList<DietGoal> {
    public DietGoalList() {
        super();
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(i + 1).append(". ").append(get(i).toString());
        }
        return result.toString();
    }
}