/**
 * To be implemented in future version of AthletiCLI.
 */
package athleticli.data.sleep;

import static athleticli.storage.Config.PATH_SLEEP_GOAL;

import athleticli.data.StorableList;

public class SleepGoalList extends StorableList<SleepGoal> {
    /**
     * Constructs a sleep goal list.
     */
    public SleepGoalList() {
        super(PATH_SLEEP_GOAL);
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
