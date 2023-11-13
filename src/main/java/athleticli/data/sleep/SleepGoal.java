package athleticli.data.sleep;

import athleticli.data.Data;
import athleticli.data.Goal;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a sleep goal.
 */
public class SleepGoal extends Goal {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm",
        Locale.ENGLISH);

    public enum GoalType {
        DURATION
    }

    private final GoalType goalType;
    private int target;

    /**
     * Constructs a sleep goal.
     * 
     * @param timeSpan    The time span of the sleep goal.
     * @param goalType    The goal type of the sleep goal.
     * @param targetValue The target value of the sleep goal in minutes. (Used if goalType is DURATION)
     */
    public SleepGoal(GoalType goalType, TimeSpan timeSpan, int target) {
        super(timeSpan);
        this.target = target;
        this.goalType = goalType;
    }

    /**
     * Examines whether the sleep goal is achieved.
     * 
     * @param data The data containing the sleep list.
     * @return Whether the sleep goal is achieved.
     * @throws IllegalStateException if the goal type is invalid
     */
    @Override
    public boolean isAchieved(Data data) throws IllegalStateException {
        int total = getCurrentValue(data);
        return total >= target;
    }

    /**
     * Returns the current value of the sleep goal metric.
     * 
     * @param data The data containing the sleep list.
     * @return The current value of the sleep goal metric.
     * @throws IllegalStateException if the goal type is invalid
     */
    public int getCurrentValue(Data data) throws IllegalStateException {
        SleepList sleeps = data.getSleeps();
        int total;
        switch(goalType) {
        case DURATION:
            total = sleeps.getTotalSleepDuration(this.getTimeSpan());
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + goalType);
        }
        return total;
    }

    /**
     * Returns the string representation of the sleep goal.
     * 
     * @param data The data containing the sleep list.
     * @return The string representation of the sleep goal.
     */
    public String toString(Data data) {
        String goalTypeString = goalType.name();
        return(getTimeSpan().name().toLowerCase() + " " + goalTypeString.toLowerCase() + " " +
             ": " + getCurrentValue(data) + "/" + target + " minutes");
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public int getTargetValue() {
        return target;
    }

    public void setTargetValue(int target) {
        this.target = target;
    }
}
