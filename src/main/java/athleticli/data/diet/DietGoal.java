package athleticli.data.diet;

import athleticli.data.Data;
import athleticli.data.Goal;

import java.time.LocalDate;
import java.util.ArrayList;

import athleticli.parser.Parameter;

/**
 * Represents a diet goal.
 */
public abstract class DietGoal extends Goal {
    protected String nutrient;
    protected int targetValue;
    protected final String type;
    protected final String achievedSymbol;
    protected final String unachievedSymbol;
    private final String dietGoalStringRepresentation;

    /**
     * Constructs a diet goal with no current value.
     *
     * @param timespan    The timespan of the diet goal.
     * @param nutrient    The nutrients of the diet goal.
     * @param targetValue The target value of the diet goal.
     */
    public DietGoal(TimeSpan timespan, String nutrient, int targetValue) {
        super(timespan);
        this.nutrient = nutrient;
        this.targetValue = targetValue;
        type = "";
        achievedSymbol = "[Achieved]";
        unachievedSymbol = "";
        dietGoalStringRepresentation = "%s %s %s intake progress: (%d/%d)\n";
    }

    /**
     * Returns the nutrients of the diet goal.
     *
     * @return The nutrients of the diet goal.
     */
    public String getNutrient() {
        return nutrient;
    }

    /**
     * Sets the nutrients of the diet goal.
     *
     * @param nutrient The nutrient of the diet goal.
     */
    public void setNutrient(String nutrient) {
        this.nutrient = nutrient;
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
    }

    /**
     * Returns the current value of the diet goal from dietList.
     *
     * @param data A storage class to retrieve diet information.
     * @return The current value of the diet goal.
     */
    public int getCurrentValue(Data data) {
        return updateCurrentValue(data);
    }

    /**
     * Returns the type of diet goal.
     *
     * @return the type of diet goal.
     */
    public String getType() {
        return type;
    }

    private int updateCurrentValue(Data data) {
        int currentValue = 0;
        DietList diets = data.getDiets();
        int numDays = getTimeSpan().getDays();
        ArrayList<LocalDate> dates = getPastDates(numDays);
        ArrayList<Diet> dietRecords;
        for (LocalDate date : dates) {
            dietRecords = diets.find(date);
            for (Diet diet : dietRecords) {
                switch (nutrient) {
                case Parameter.NUTRIENTS_FATS:
                    currentValue += diet.getFat();
                    break;
                case Parameter.NUTRIENTS_CALORIES:
                    currentValue += diet.getCalories();
                    break;
                case Parameter.NUTRIENTS_PROTEIN:
                    currentValue += diet.getProtein();
                    break;
                case Parameter.NUTRIENTS_CARB:
                    currentValue += diet.getCarb();
                    break;
                default:
                    currentValue += 0;

                }
            }
        }
        return currentValue;
    }

    private ArrayList<LocalDate> getPastDates(int numDays) {
        ArrayList<LocalDate> pastDates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < numDays; i++) {
            LocalDate pastDate = currentDate.minusDays(i);
            pastDates.add(pastDate);
        }
        return pastDates;
    }

    /**
     * Returns whether the diet goal is achieved.
     *
     * @param data A storage class to retrieve diet information.
     * @return Whether the diet goal is achieved.
     */
    public boolean isAchieved(Data data) {
        int currentValue = getCurrentValue(data);
        return currentValue >= targetValue;
    }

    /**
     * Returns the symbol to indicate if a diet goal is achieved.
     *
     * @param data A storage class to retrieve diet information.
     * @return A string symbol indicating that the goal is achieved.
     */
    protected String getSymbol(Data data) {
        if (isAchieved(data)) {
            return achievedSymbol;
        }
        return unachievedSymbol;
    }

    /**
     * Checks if the other diet goals are of the same type.
     *
     * @param dietGoal
     * @return
     */
    public boolean isSameType(DietGoal dietGoal) {
        return dietGoal.getType().equals(getType());
    }

    /**
     * Checks if the other diet goals are of the same nutrient.
     *
     * @param dietGoal
     * @return
     */
    public boolean isSameNutrient(DietGoal dietGoal) {
        return dietGoal.getNutrient().equals(getNutrient());
    }

    /**
     * Checks if the other diet goals are of the same time span.
     *
     * @param dietGoal
     * @return
     */
    public boolean isSameTimeSpan(DietGoal dietGoal) {
        return dietGoal.getTimeSpan().getDays() == getTimeSpan().getDays();
    }

    /**
     * Returns the string representation of the diet goal.
     *
     * @param data A storage class to retrieve diet information.
     * @return The string representation of the diet goal.
     */
    public String toString(Data data) {
        return String.format(dietGoalStringRepresentation, getSymbol(data), getTimeSpan().name(), nutrient,
                getCurrentValue(data), targetValue);


    }
}
