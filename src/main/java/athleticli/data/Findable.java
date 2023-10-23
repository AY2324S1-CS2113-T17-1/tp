package athleticli.data;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Findable {
    /**
     * Returns a list of objects matching the date.
     *
     * @param date  The date to be matched.
     * @return      A list of objects matching the date.
     */
    public ArrayList<Object> find(LocalDate date);
}
