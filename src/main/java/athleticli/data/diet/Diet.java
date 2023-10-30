package athleticli.data.diet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Defines the basic fields and methods of a diet.
 */
public class Diet {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMMM d, " + "yyyy 'at' h:mm a", Locale.ENGLISH);
    private int calories;
    private int protein;
    private int carb;
    private int fat;
    private LocalDateTime dateTime;

    /**
     * Constructs a <code>Diet</code> object.
     *
     * @param calories The caloric value of the diet in cal.
     * @param protein  Protein intake in grams.
     * @param carb     Carbohydrate intake in grams.
     * @param fat      Fat intake in grams.
     * @param dateTime The date and time of the diet.
     */
    public Diet(int calories, int protein, int carb, int fat, LocalDateTime dateTime) {
        assert calories >= 0 : "Calories cannot be negative";
        this.calories = calories;
        assert protein >= 0 : "Protein cannot be negative";
        this.protein = protein;
        assert carb >= 0 : "Carb cannot be negative";
        this.carb = carb;
        assert fat >= 0 : "Fat cannot be negative";
        this.fat = fat;
        this.dateTime = dateTime;
    }

    /**
     * Returns the caloric value of the diet in cal.
     *
     * @return The caloric value of the diet in cal.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets the caloric value of the diet in cal.
     *
     * @param calories The caloric value of the diet in cal.
     */
    public void setCalories(int calories) {
        assert calories >= 0 : "Calories cannot be negative";
        this.calories = calories;
    }

    /**
     * Returns the protein intake in grams.
     *
     * @return Protein intake in grams.
     */
    public int getProtein() {
        return protein;
    }

    /**
     * Sets the protein intake in grams.
     *
     * @param protein Protein intake in grams.
     */
    public void setProtein(int protein) {
        assert protein >= 0 : "Protein cannot be negative";
        this.protein = protein;
    }

    /**
     * Returns the carbohydrate intake in grams.
     *
     * @return Carbohydrate intake in grams.
     */
    public int getCarb() {
        return carb;
    }

    /**
     * Sets the carbohydrate intake in grams.
     *
     * @param carb Carbohydrate intake in grams.
     */
    public void setCarb(int carb) {
        assert carb >= 0 : "Carb cannot be negative";
        this.carb = carb;
    }

    /**
     * Returns the fat intake in grams.
     *
     * @return Fat intake in grams.
     */
    public int getFat() {
        return fat;
    }

    /**
     * Sets the fat intake in grams.
     *
     * @param fat Fat intake in grams.
     */
    public void setFat(int fat) {
        assert fat >= 0 : "Fat cannot be negative";
        this.fat = fat;
    }

    /**
     * Returns the date and time of the diet.
     *
     * @return The date and time of the diet.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the diet.
     *
     * @param dateTime The date and time of the diet.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a string representation of the diet.
     *
     * @return A string representation of the diet.
     */

    @Override
    public String toString() {
        return "Calories: " + calories + " Protein: " + protein + " Carb: " + carb + " Fat: " + fat +
                       " Date: " + dateTime.format(DATE_TIME_FORMATTER);
    }
}
