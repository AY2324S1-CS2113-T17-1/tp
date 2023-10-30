package athleticli.data.sleep;

import static athleticli.storage.Config.PATH_SLEEP;

import java.time.LocalDate;
import java.util.ArrayList;

import athleticli.data.Findable;
import athleticli.data.StorableList;

/**
 * Represents a list of sleep records.
 */
public class SleepList extends StorableList<Sleep> implements Findable<Sleep> {
    /**
     * Constructs a sleep list with its storage path.
     */
    public SleepList() {
        super(PATH_SLEEP);
    }

    /**
     * Returns a list of sleeps matching the date.
     *
     * @param date The date to be matched.
     * @return A list of sleeps matching the date.
     */
    @Override
    public ArrayList<Sleep> find(LocalDate date) {
        // TODO
        return null;
    }

    /**
     * Parses a sleep from a string.
     *
     * @param s The string to be parsed.
     * @return The sleep parsed from the string.
     */
    @Override
    public Sleep parse(String s) {
        // TODO
        return null;
    }

    /**
     * Unparses a sleep to a string.
     *
     * @param sleep The sleep to be parsed.
     * @return The string unparsed from the sleep.
     */
    @Override
    public String unparse(Sleep sleep) {
        // TODO
        return null;
    }
}
