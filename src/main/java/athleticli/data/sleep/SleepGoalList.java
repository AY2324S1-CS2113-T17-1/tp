package athleticli.data.sleep;

import athleticli.data.Data;
import athleticli.data.StorableList;

import static athleticli.storage.Config.PATH_SLEEP_GOAL;

/**
 * Represents a list of sleep goals.
 */
public class SleepGoalList extends StorableList<SleepGoal> {
    /**
     * Constructs a sleep goal list.
     */
    public SleepGoalList() {
        super(PATH_SLEEP_GOAL);
    }

    /**
     * Returns a string representation of the sleep goal list.
     * 
     * @param data The data containing the sleep goal list.
     * @return A string representation of the sleep goal list.
     */
    public String toString(Data data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(i + 1).append(". ").append(get(i).toString(data));
            if (i != size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Parses a sleep goal from a string.
     *
     * @param s The string to be parsed.
     * @return The sleep goal parsed from the string.
     */
    @Override
    public SleepGoal parse(String s) {
        // TODO
        return null;
    }

    /**
     * Unparses a sleep goal to a string.
     *
     * @param sleepGoal The sleep goal to be parsed.
     * @return The string unparsed from the sleep goal.
     */
    @Override
    public String unparse(SleepGoal sleepGoal) {
        // TODO
        return null;
    }
}
