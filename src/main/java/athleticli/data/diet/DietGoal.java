package athleticli.data.diet;

/**
 * Represents a diet goal.
 */
public class DietGoal {
    private String nutrients;
    private int targetValue;
    private int currentValue;
    private boolean isGoalAchieved;

    /**
     * Constructs a diet goal with no current value.
     *
     * @param nutrients   The nutrients of the diet goal.
     * @param targetValue The target value of the diet goal.
     */
    public DietGoal(String nutrients, int targetValue) {
        this.nutrients = nutrients;
        this.targetValue = targetValue;
        currentValue = 0;
        isGoalAchieved = false;
    }

    /**
     * Constructs a diet goal with a current value.
     *
     * @param nutrients    The nutrients of the diet goal.
     * @param targetValue  The target value of the diet goal.
     * @param currentValue The current value of the diet goal.
     */
    public DietGoal(String nutrients, int targetValue, int currentValue) {
        this.nutrients = nutrients;
        this.targetValue = targetValue;
        this.currentValue = currentValue;
        isGoalAchieved = currentValue >= targetValue;
    }

    /**
     * Returns the nutrients of the diet goal.
     *
     * @return The nutrients of the diet goal.
     */
    public String getNutrients() {
        return nutrients;
    }

    /**
     * Sets the nutrients of the diet goal.
     *
     * @param nutrients The nutrients of the diet goal.
     */
    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }

    /**
     * Returns the target value of the diet goal.
     *
     * @return The target value of the diet goal.
     */
    public int getTargetValue() {
        return targetValue;
    }

    /**
     * Sets the target value of the diet goal.
     *
     * @param targetValue The target value of the diet goal.
     */
    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
        setIsGoalAchieved(currentValue >= targetValue);
    }

    /**
     * Returns the current value of the diet goal.
     *
     * @return The current value of the diet goal.
     */
    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets the current value of the diet goal.
     *
     * @param currentValue The current value of the diet goal.
     */
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        if (!isGoalAchieved && currentValue >= targetValue) {
            setIsGoalAchieved(true);
        } else if (isGoalAchieved && currentValue < targetValue) {
            setIsGoalAchieved(false);
        }
    }

    /**
     * Returns whether the diet goal is achieved.
     *
     * @return Whether the diet goal is achieved.
     */
    public boolean getIsGoalAchieved() {
        return isGoalAchieved;
    }

    /**
     * Sets whether the diet goal is achieved.
     *
     * @param isGoalAchieved Whether the diet goal is achieved.
     */
    private void setIsGoalAchieved(boolean isGoalAchieved) {
        this.isGoalAchieved = isGoalAchieved;
    }

    /**
     * Returns the string representation of the diet goal.
     *
     * @return The string representation of the diet goal.
     */
    @Override
    public String toString() {
        return nutrients + " intake progress: (" + currentValue + "/" + targetValue + ")\n";
    }
}
