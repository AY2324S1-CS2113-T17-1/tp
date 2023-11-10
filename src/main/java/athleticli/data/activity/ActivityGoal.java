package athleticli.data.activity;

import athleticli.data.Data;
import athleticli.data.Goal;
import athleticli.parser.Parameter;

/**
 * Represents an activity goal.
 */
public class ActivityGoal extends Goal {
    public enum GoalType {
        DISTANCE, DURATION
    }
    public enum Sport {
        RUNNING, CYCLING, SWIMMING, GENERAL
    }

    private int targetValue;
    private final GoalType goalType;
    private final Sport sport;

    /**
     * Constructs an activity goal.
     *
     * @param timeSpan    Time span of the activity goal.
     * @param goalType    Goal type of the activity goal.
     * @param sport       Sport related to the activity goal.
     * @param targetValue Target value of the activity goal.
     */
    public ActivityGoal(TimeSpan timeSpan, GoalType goalType, Sport sport, int targetValue) {
        super(timeSpan);
        this.targetValue = targetValue;
        this.goalType = goalType;
        this.sport = sport;
    }

    /**
     * Examines whether the activity goal is achieved.
     *
     * @param data Data containing the activity list.
     * @return Whether the activity goal is achieved.
     */
    @Override
    public boolean isAchieved(Data data) throws IllegalStateException {
        return getCurrentValue(data) >= targetValue;
    }

    /**
     * Returns the current value of the activity goal metric.
     *
     * @param data Data containing the activity list.
     * @return Current value of the activity goal metric.
     * @throws IllegalStateException If the goal type is not supported.
     */
    public int getCurrentValue(Data data) {
        ActivityList activities = data.getActivities();
        Class<?> activityClass = getActivityClass();

        switch (goalType) {
        case DISTANCE:
            return activities.getTotalDistance(activityClass, this.getTimeSpan());
        case DURATION:
            int totalDuration = activities.getTotalDuration(activityClass, this.getTimeSpan());
            return convertToMinutes(totalDuration);
        default:
            throw new IllegalStateException("Unexpected value: " + goalType);
        }
    }

    /**
     * Converts the given seconds to minutes.
     *
     * @param seconds Seconds to be converted.
     * @return Minutes converted from the given seconds.
     */
    private int convertToMinutes(int seconds) {
        return seconds / Parameter.MINUTE_IN_SECONDS;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    /**
     * Returns the class of the activity associated with the activity goal.
     *
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
        case GENERAL:
            return Activity.class;
        default:
            throw new IllegalStateException("Unexpected value: " + this.sport);
        }
    }

    /**
     * Returns the string representation of the activity goal including progress information.
     *
     * @param data Data containing the activity list.
     * @return The string representation of the activity goal.
     */
    public String toString(Data data) {
        String goalTypeString = goalType.name().toLowerCase();
        String sportString = sport.name().toLowerCase();
        String timeSpanString = getTimeSpan().name().toLowerCase();

        return String.format("%s %s %s: %d / %d", timeSpanString, sportString, goalTypeString,
                getCurrentValue(data), targetValue);
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
