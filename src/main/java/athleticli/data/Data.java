package athleticli.data;

import java.io.IOException;

import athleticli.data.activity.ActivityGoalList;
import athleticli.data.activity.ActivityList;
import athleticli.data.diet.DietGoalList;
import athleticli.data.diet.DietList;
import athleticli.data.sleep.SleepGoalList;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;

/**
 * Defines the basic fields and methods of data.
 */
public class Data {
    private static Data dataInstance;
    private ActivityList activities = new ActivityList();
    private ActivityGoalList activityGoals = new ActivityGoalList();
    private DietList diets = new DietList();
    private DietGoalList dietGoals = new DietGoalList();
    private SleepList sleeps = new SleepList();
    private SleepGoalList sleepGoals = new SleepGoalList();

    /**
     * Returns the singleton instance of `Data`.
     *
     * @return  The singleton instance of `Data`.
     */
    public static Data getInstance() {
        if (dataInstance == null) {
            dataInstance = new Data();
        }
        return dataInstance;
    }

    /**
     * Loads data from files.
     */
    public void load() throws AthletiException {
        activities.load();
        activityGoals.load();
        diets.load();
        dietGoals.load();
        sleeps.load();
        sleepGoals.load();
    }

    /**
     * Saves data to files.
     */
    public void save() throws IOException {
        activities.save();
        activityGoals.save();
        diets.save();
        dietGoals.save();
        sleeps.save();
        sleepGoals.save();
    }

    /**
     * Clears all lists.
     */
    public void clear() {
        activities.clear();
        activityGoals.clear();
        diets.clear();
        dietGoals.clear();
        sleeps.clear();
        sleepGoals.clear();
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
