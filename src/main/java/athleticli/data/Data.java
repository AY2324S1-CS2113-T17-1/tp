package athleticli.data;

import athleticli.data.activity.ActivityGoalList;
import athleticli.data.activity.ActivityList;
import athleticli.data.diet.DietGoalList;
import athleticli.data.diet.DietList;
import athleticli.data.sleep.SleepGoalList;
import athleticli.data.sleep.SleepList;

/**
 * Defines the basic fields and methods of data.
 */
public class Data {
    private ActivityList activities;
    private ActivityGoalList activityGoals;
    private DietList diets;
    private DietGoalList dietGoals;
    private SleepList sleeps;
    private SleepGoalList sleepGoals;

    /**
     * Constructs an empty <code>Data</code> object.
     */
    public Data() {
        this.activities = new ActivityList();
        this.activityGoals = new ActivityGoalList();
        this.diets = new DietList();
        this.dietGoals = new DietGoalList();
        this.sleeps = new SleepList();
        this.sleepGoals = new SleepGoalList();
        this.dietGoals = new DietGoalList();
    }

    /**
     * Get all the objects
     */

    public ActivityList getActivities() {
        return activities;
    }

    public ActivityGoalList getActivityGoals() {
        return activityGoals;
    }

    public DietList getDiets() {
        return diets;
    }

    public DietGoalList getDietGoals() {
        return dietGoals;
    }

    public SleepList getSleeps() {
        return sleeps;
    }

    public SleepGoalList getSleepGoals() {
        return sleepGoals;
    }


    /**
     * Set all the objects
     */
    public void setActivities(ActivityList activities) {
        this.activities = activities;
    }

    public void setActivityGoals(ActivityGoalList activityGoals) {
        this.activityGoals = activityGoals;
    }

    public void setDiets(DietList diets) {
        this.diets = diets;
    }

    public void setDietGoals(DietGoalList dietGoals) {
        this.dietGoals = dietGoals;
    }

    public void setSleeps(SleepList sleeps) {
        this.sleeps = sleeps;
    }

    public void setSleepGoals(SleepGoalList sleepGoals) {
        this.sleepGoals = sleepGoals;
    }
  
}
