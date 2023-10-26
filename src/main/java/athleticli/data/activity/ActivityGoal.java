package athleticli.data.activity;

import athleticli.data.Data;
import athleticli.data.Goal;

import java.io.Serializable;

public class ActivityGoal extends Goal implements Serializable {

    public enum GoalType {
        DISTANCE, DURATION // can be extended
    }
    public enum Sport {
        RUNNING, CYCLING, SWIMMING, GENERAL
    }

    private int targetValue;
    private final GoalType goalType;
    private final Sport sport;

    /**
     * Constructs an activity goal.
     * @param timespan    The timespan of the activity goal.
     * @param goalType    The goal type of the activity goal.
     * @param sport       The sport of the activity goal.
     * @param targetValue The target value of the activity goal.
     */
    public ActivityGoal(Timespan timespan, GoalType goalType, Sport sport, int targetValue) {
        super(timespan);
        this.targetValue = targetValue;
        this.goalType = goalType;
        this.sport = sport;
    }

    /**
     * Examines whether the activity goal is achieved.
     * @param data The data containing the activity list.
     * @return Whether the activity goal is achieved.
     */
    @Override
    public boolean isAchieved(Data data) throws IllegalStateException {
        int total = getCurrentValue(data);
        return total >= targetValue;
    }

    /**
     * Returns the current value of the activity goal metric.
     * @param data The data containing the activity list.
     * @return The current value of the activity goal metric.
     */
    public int getCurrentValue(Data data) throws IllegalStateException {
        ActivityList activities = data.getActivities();
        Class<?> activityClass = getActivityClass();
        int total;
        switch(goalType) {
        case DISTANCE:
            total = activities.getTotalDistance(activityClass, this.getTimespan());
            break;
        case DURATION:
            total = activities.getTotalDuration(activityClass, this.getTimespan());
            total = total / 60;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + goalType);
        }
        return total;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    /**
     * Returns the class of the activity associated with the activity goal.
     * @return The class of the activity.
     */
    public Class<?> getActivityClass() {
        switch (this.sport) {
        case RUNNING:
            return Run.class;
        case CYCLING:
            return Cycle.class;
        case SWIMMING:
            return Swim.class;
        default:
            return Activity.class;
        }
    }

    /**
     * Returns the string representation of the activity goal including progress information.
     * @return The string representation of the activity goal.
     */
    public String toString(Data data) {
        String goalTypeString = goalType.name();
        String sportString = sport.name();
        return (getTimespan().name().toLowerCase() + " " + sportString.toLowerCase() + " " + goalTypeString.toLowerCase()
                + ": " + getCurrentValue(data) + " / " + targetValue);
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public Sport getSport() {
        return sport;
    }

    public int getTargetValue() {
        return targetValue;
    }

}
