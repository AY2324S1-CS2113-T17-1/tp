package athleticli.data.sleep;

import java.time.LocalDate;
import java.util.ArrayList;

import athleticli.data.Findable;

/**
 * Represents a list of sleep records.
 */
public class SleepList extends ArrayList<Sleep> implements Findable {
    /**
     * Returns a list of sleeps matching the date.
     *
     * @param date The date to be matched.
     * @return A list of sleeps matching the date.
     */
    @Override
    public ArrayList<Object> find(LocalDate date) {
        // TODO
        return null;
    }
}
