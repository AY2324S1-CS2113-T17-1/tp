package athleticli.data.diet;

/**
 * DietGoalStub is use for isolation testing of the DietGoal abstract class
 */
public class DietGoalStub extends DietGoal {
    /**
     * Constructs a diet goal.
     *
     * @param timespan    The timespan of the diet goal.
     * @param nutrient    The nutrients of the diet goal.
     * @param targetValue The target value of the diet goal.
     */
    public DietGoalStub(TimeSpan timespan, String nutrient, int targetValue) {
        super(timespan, nutrient, targetValue);
    }
}
