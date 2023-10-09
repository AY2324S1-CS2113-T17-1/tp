package athleticli.data;

import athleticli.data.activity.ActivityGoalList;
import athleticli.data.activity.ActivityList;
import athleticli.data.diet.DietGoalList;
import athleticli.data.diet.MealList;
import athleticli.data.sleep.SleepGoalList;
import athleticli.data.sleep.SleepList;

/**
 * Defines the basic fields and methods of data.
 */
public class Data {
    private ActivityList activities;
    private ActivityGoalList activityGoals;
    private MealList meals;
    private DietGoalList dietGoals;
    private SleepList sleeps;
    private SleepGoalList sleepGoals;

    /**
     * Constructs an empty <code>Data</code> object.
     */
    public Data() {
        this.activities = new ActivityList();
        this.activityGoals = new ActivityGoalList();
        this.meals = new MealList();
        this.dietGoals = new DietGoalList();
        this.sleeps = new SleepList();
        this.sleepGoals = new SleepGoalList();
    }
}
