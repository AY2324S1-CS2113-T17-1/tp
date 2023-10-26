package athleticli.data.activity;

import athleticli.data.Data;
import athleticli.data.Goal;

import java.io.Serializable;
import java.time.LocalDate;

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
     * @param date        The date of the activity goal.
     * @param period      The period of the activity goal.
     * @param goalType    The goal type of the activity goal.
     * @param sport       The sport of the activity goal.
     * @param targetValue The target value of the activity goal.
     */
    public ActivityGoal(LocalDate date, Period period, GoalType goalType, Sport sport, int targetValue) {
        super(date, period);
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
    public boolean isAchieved(Data data) {
        ActivityList activities = data.getActivities();
        Class<?> activityClass = getActivityClass();
        int total;
        switch(goalType) {
        case DISTANCE:
            total = activities.getTotalDistance(activityClass, this.getStartDate(), this.getEndDate());
            break;
        case DURATION:
            total = activities.getTotalDuration(activityClass, this.getStartDate(), this.getEndDate());
            total = total / 60;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + goalType);
        }
        return total >= targetValue;
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

}
