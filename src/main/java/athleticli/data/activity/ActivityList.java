package athleticli.data.activity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import athleticli.data.Findable;

public class ActivityList extends ArrayList<Activity> implements Serializable, Findable {
    /**
     * Returns a list of activities matching the date.
     *
     * @param date The date to be matched.
     * @return A list of activities matching the date.
     */
    @Override
    public ArrayList<Object> find(LocalDate date) {
        return null;
    }
}
