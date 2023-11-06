package athleticli.data.sleep;

import athleticli.data.Data;
import athleticli.data.Goal;

import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class SleepGoal extends Goal {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm",
        Locale.ENGLISH);

    public enum GoalType {
        DURATION, STARTTIME, ENDTIME
    }

    private final GoalType goalType;
    private int targetDuration;

    /**
     * Constructs a sleep goal.
     * @param timespan    The timespan of the sleep goal.
     * @param goalType    The goal type of the sleep goal.
     * @param targetValue The target duration of the sleep goal in minutes. (Used if goalType is DURATION)
     * @param targetTime  The target time of the sleep goal. (Used if goalType is STARTTIME or ENDTIME)
     */
    public SleepGoal(TimeSpan timespan, GoalType goalType, int targetDuration) {
        super(timespan);
        this.targetDuration = targetDuration;
        this.goalType = goalType;
    }

    /**
     * Examines whether the sleep goal is achieved.
     * @param data The data containing the sleep list.
     * @return Whether the sleep goal is achieved.
     */
    @Override
    public boolean isAchieved(Data data) throws IllegalStateException {
        int total = getCurrentValue(data);
        return total >= targetDuration;
    }

    /**
     * Returns the current value of the sleep goal metric.
     * @param data The data containing the sleep list.
     * @return The current value of the sleep goal metric.
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
     * @param data The data containing the sleep list.
     * @return The string representation of the sleep goal.
     */
    public String toString(Data data) {
        String goalTypeString = goalType.name();
        return(getTimeSpan().name().toLowerCase() + " " + goalTypeString.toLowerCase() + " " +
            goalTypeString.toLowerCase() + " :" + getCurrentValue(data) + "/" + targetDuration + " minutes");
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public int getTargetDuration() {
        return targetDuration;
    }

    public void setTargetDuration(int targetDuration) {
        this.targetDuration = targetDuration;
    }
}
