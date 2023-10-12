package athleticli.dietgoal;

public class DietGoal {
    private String nutrients;
    private int targetValue;
    private int currentValue;
    private boolean isGoalAchieved;


    public DietGoal(String nutrients, int targetValue) {
        this.nutrients = nutrients;
        this.targetValue = targetValue;
        currentValue = 0;
        isGoalAchieved = false;
    }

    public DietGoal(String nutrients, int targetValue, int currentValue) {
        this.nutrients = nutrients;
        this.targetValue = targetValue;
        this.currentValue = currentValue;
        isGoalAchieved = currentValue >= targetValue;
    }

    public String getNutrients() {
        return nutrients;
    }

    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
        setIsGoalAchieved(currentValue >= targetValue);
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        if (!isGoalAchieved && currentValue >= targetValue) {
            setIsGoalAchieved(true);
        } else if (isGoalAchieved && currentValue < targetValue) {
            setIsGoalAchieved(false);
        }
    }

    public boolean getIsGoalAchieved() {
        return isGoalAchieved;
    }

    private void setIsGoalAchieved(boolean isGoalAchieved) {
        this.isGoalAchieved = isGoalAchieved;
    }

    @Override
    public String toString() {
        return nutrients + " intake progress: (" + currentValue
                + "/" + targetValue + ")\n";
    }
}

