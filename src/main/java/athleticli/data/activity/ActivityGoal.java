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

    private double targetValue;
    private GoalType goalType;
    private Sport sport;

    /**
     * Constructs an activity goal.
     * @param date        The date of the activity goal.
     * @param period      The period of the activity goal.
     * @param goalType    The goal type of the activity goal.
     * @param sport       The sport of the activity goal.
     * @param targetValue The target value of the activity goal.
     */
    public ActivityGoal(LocalDate date, Period period, GoalType goalType, Sport sport, double targetValue) {
        super(date, period);
        this.targetValue = targetValue;
        this.goalType = goalType;
        this.sport = sport;
    }

    @Override
    public boolean isAchieved() {
        /*ActivityList activities = data.getActivities();
        switch(goalType) {
        case DISTANCE:
            return
        }*/
        return false;
    }
}
