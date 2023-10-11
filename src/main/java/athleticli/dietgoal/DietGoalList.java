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

    public int getSize() {
        return dietGoals.size();
    }
}


