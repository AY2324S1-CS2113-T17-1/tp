package athleticli.data.activity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

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
        ArrayList<Object> result = new ArrayList<>();
        for (Activity activity : this) {
            if (activity.getStartDateTime().toLocalDate().equals(date)) {
                result.add(activity);
            }
        }
        return result;
    }

    /**
     * Sorts the activities in the list by date.
     */
    public void sort() {
        this.sort(Comparator.comparing(Activity::getStartDateTime).reversed());
    }

    /**
     * Returns a list of activities within the timespan.
     * @param startDate The start date of the timespan.
     * @param endDate   The end date of the timespan.
     * @return A list of activities within the timespan.
     */
    public ArrayList<Object> filterByTimespan(LocalDate startDate, LocalDate endDate) {
        ArrayList<Object> result = new ArrayList<>();
        for (Activity activity : this) {
            LocalDate activityDate = activity.getStartDateTime().toLocalDate();
            if (activityDate.isAfter(startDate.minusDays(1)) &&
                    activityDate.isBefore(endDate.plusDays(1))) {
                result.add(activity);
            }
        }
        return result;
    }
}
