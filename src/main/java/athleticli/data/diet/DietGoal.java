package athleticli.data.diet;

import athleticli.data.Data;
import athleticli.data.Goal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a diet goal.
 */
public class DietGoal extends Goal {
    private String nutrient;
    private int targetValue;

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
                case "fats":
                    currentValue += diet.getFat();
                    break;
                case "calories":
                    currentValue += diet.getProtein();
                    break;
                case "protein":
                    currentValue += diet.getProtein();
                    break;
                case "carb":
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
     * Returns the string representation of the diet goal.
     *
     * @param data A storage class to retrieve diet information.
     * @return The string representation of the diet goal.
     */
    public String toString(Data data) {
        return nutrient + " intake progress: (" + getCurrentValue(data) + "/" + targetValue + ")\n";
    }
}
