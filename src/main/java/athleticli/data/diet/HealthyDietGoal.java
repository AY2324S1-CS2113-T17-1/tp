package athleticli.data.diet;

import athleticli.data.Data;

/**
 * HealthyDietGoal tracks nutrients goal that the user wants to increase his/her intake on.
 */
public class HealthyDietGoal extends DietGoal {

    private final boolean isHealthy;
    public static final String type = "healthy";

    /**
     * Constructs a diet goal with no current value.
     *
     * @param timeSpan    The timespan of the diet goal.
     * @param nutrient    The nutrients of the diet goal.
     * @param targetValue The target value of the diet goal.
     */
    public HealthyDietGoal(TimeSpan timeSpan, String nutrient, int targetValue) {
        super(timeSpan, nutrient, targetValue);
        isHealthy = true;
    }

    /**
     * Returns the type of diet goal of this class.
     *
     * @return the type of diet goal.
     */
    @Override
    public String getType() {
        return type;
    }


    /**
     * Returns the string representation of healthy diet goal.
     *
     * @param data A storage class to retrieve diet information.
     * @return The string representation of the healthy diet goal.
     */
    @Override
    public String toString(Data data) {
        return "[HEALTHY] " + super.toString(data);
    }
}
