package athleticli.data.activity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import athleticli.data.Findable;
import athleticli.data.Goal;

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
     * @param timespan The timespan to be matched.
     * @return A list of activities within the timespan.
     */
    public ArrayList<Activity> filterByTimespan(Goal.Timespan timespan) {
        ArrayList<Activity> result = new ArrayList<>();
        for (Activity activity : this) {
            LocalDate activityDate = activity.getStartDateTime().toLocalDate();
            if (Goal.checkDate(activityDate, timespan)) {
                result.add(activity);
            }
        }
        return result;
    }

    /**
     * Returns the total distance of all activities in the list matching the specified activity class.
     * @param activityClass The activity class to be matched.
     * @return The total distance of all activities in the list matching the specified activity class.
     */
    public int getTotalDistance(Class<?> activityClass, Goal.Timespan timespan) {
        ArrayList<Activity> filteredActivities = filterByTimespan(timespan);
        int runningDistance = 0;
        for (Activity activity : filteredActivities) {
            if (activityClass.isInstance(activity)) {
                runningDistance += activity.getDistance();
            }
        }
        return runningDistance;
    }

    /**
     * Returns the total moving time in seconds of all activities in the list matching the specified activity class.
     * @param activityClass The activity class to be matched.
     * @return The total moving time of all activities in the list matching the specified activity class.
     */
    public int getTotalDuration(Class<?> activityClass, Goal.Timespan timespan) {
        ArrayList<Activity> filteredActivities = filterByTimespan(timespan);
        int movingTime = 0;
        for (Activity activity : filteredActivities) {
            if (activityClass.isInstance(activity)) {
                LocalTime duration = activity.getMovingTime();
                movingTime += duration.getHour() * 3600 + duration.getMinute() * 60 + duration.getSecond();
            }
        }
        return movingTime;
    }

}
