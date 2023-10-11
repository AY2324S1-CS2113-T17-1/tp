package athleticli.dietgoal;

import java.util.ArrayList;

public class DietGoalList {
    ArrayList<DietGoal> dietGoals;

    public DietGoalList() {
        dietGoals = new ArrayList<DietGoal>();
    }

    public void addGoal(DietGoal dietGoal) {
        dietGoals.add(dietGoal);
    }

    public DietGoal getGoal(int index) {
        return dietGoals.get(index);
    }

    public void removeGoal(int index) {
        dietGoals.remove(index);
    }

    public int getSize() {
        return dietGoals.size();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dietGoals.size(); i++) {
            result.append(i + 1).append(". ").append(dietGoals.get(i).toString());
        }
        return result.toString();
    }
}


