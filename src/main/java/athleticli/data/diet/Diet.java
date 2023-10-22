package athleticli.data.diet;

import java.io.Serializable;

/**
 * Defines the basic fields and methods of a diet.
 */
public class Diet implements Serializable {
    private int calories;
    private int protein;
    private int carb;
    private int fat;

    /**
     * Constructs a <code>Diet</code> object.
     *
     * @param calories The caloric value of the diet in cal.
     * @param protein  Protein intake in grams.
     * @param carb     Carbohydrate intake in grams.
     * @param fat      Fat intake in grams.
     */
    public Diet(int calories, int protein, int carb, int fat) {
        this.calories = calories;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
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
        this.fat = fat;
    }

    /**
     * Returns a string representation of the diet.
     *
     * @return A string representation of the diet.
     */
    @Override
    public String toString() {
        return "Calories: " + calories + " Protein: " + protein + " Carb: " + carb + " Fat: " + fat;
    }
}
