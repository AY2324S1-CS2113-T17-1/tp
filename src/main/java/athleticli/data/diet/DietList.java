package athleticli.data.diet;

import static athleticli.storage.Config.PATH_DIET;

import athleticli.data.Findable;
import athleticli.data.StorableList;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Represents a list of diets.
 */
public class DietList extends StorableList<Diet> implements Findable {
    /**
     * Constructs a diet list.
     */
    public DietList() {
        super(PATH_DIET);
    }

    /**
     * Returns a string representation of the diet list.
     *
     * @return A string representation of the diet list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(i + 1).append(". ").append(get(i).toString());
            if (i != size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Returns a list of diets matching the date.
     *
     * @param date The date to be matched.
     * @return A list of diets matching the date.
     */
    @Override
    public ArrayList<Diet> find(LocalDate date) {
        ArrayList<Diet> result = new ArrayList<>();
        for (Diet diet : this) {
            if (diet.getDateTime().toLocalDate().equals(date)) {
                result.add(diet);
            }
        }
        return result;
    }

    /**
     * Parses a diet from a string.
     *
     * @param s The string to be parsed.
     * @return The diet parsed from the string.
     */
    @Override
    public Diet parse(String s) {
        // TODO
        return null;
    }

    /**
     * Unparses a diet to a string.
     *
     * @param diet The diet to be parsed.
     * @return The string unparsed from the diet.
     */
    @Override
    public String unparse(Diet diet) {
        // TODO
        return null;
    }
}
