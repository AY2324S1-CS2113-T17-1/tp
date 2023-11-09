package athleticli.data.diet;

import athleticli.data.Data;

/**
 * UnhealthyDietGoal tracks nutrients goal that the user wants to reduce his/her intake on.
 */
public class UnhealthyDietGoal extends DietGoal {

    public static final String TYPE = "unhealthy";
    private final boolean isHealthy;

    /**
     * Constructs a diet goal with no current value.
     *
     * @param timeSpan    The timespan of the diet goal.
     * @param nutrient    The nutrients of the diet goal.
     * @param targetValue The target value of the diet goal.
     */
    public UnhealthyDietGoal(TimeSpan timeSpan, String nutrient, int targetValue) {
        super(timeSpan, nutrient, targetValue);
        isHealthy = false;
    }

    @Override
    public boolean isAchieved(Data data) {
        int currentValue = getCurrentValue(data);
        return currentValue <= targetValue;
    }

    /**
     * Returns the type of diet goal of this class.
     *
     * @return the type of diet goal.
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Returns string indicator to indicate if unhealthy goal has its limit reached.
     *
     * @param data A storage class to retrieve diet information.
     * @return String indicator if the goal is not achieved.
     */
    protected String getSymbol(Data data) {
        if (isAchieved(data)) {
            return "";
        }
        return "[Not Achieved]";
    }

    /**
     * Returns the string representation of the unhealthy diet goal.
     *
     * @param data A storage class to retrieve diet information.
     * @return The string representation of the unhealthy diet goal.
     */
    @Override
    public String toString(Data data) {
        return "[UNHEALTHY] " + super.toString(data);
    }
}
