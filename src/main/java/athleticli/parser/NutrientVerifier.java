package athleticli.parser;

import java.util.Set;

/**
 * Verify the nutrient from a list of approved nutrients to be log in diet and diet goals
 */
public class NutrientVerifier {
    public static final Set<String> VERIFIED_NUTRIENTS = Set.of("fats", "carb", "protein", "calories");

    /**
     * Verifies if a nutrient is approved.
     *
     * @param nutrient
     * @return boolean value if it is found in the approved list.
     */
    public static boolean verify(String nutrient) {
        return VERIFIED_NUTRIENTS.contains(nutrient);
    }
}
