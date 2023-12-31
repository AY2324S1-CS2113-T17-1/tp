package athleticli.data.activity;

import static athleticli.common.Config.PATH_ACTIVITY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import athleticli.data.Findable;
import athleticli.data.StorableList;
import athleticli.data.Goal;
import athleticli.exceptions.AthletiException;
import athleticli.parser.ActivityParser;
import athleticli.parser.Parameter;
import athleticli.ui.Message;

/**
 * Represents a list of activities.
 */
public class ActivityList extends StorableList<Activity> implements Findable {
    /**
     * Constructs an empty activity list.
     */
    public ActivityList() {
        super(PATH_ACTIVITY);
    }

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
     * Returns a list of activities within the time span.
     *
     * @param timeSpan Time span to be matched.
     * @return A list of activities within the time span.
     */
    public ArrayList<Activity> filterByTimespan(Goal.TimeSpan timeSpan) {
        ArrayList<Activity> result = new ArrayList<>();
        for (Activity activity : this) {
            LocalDate activityDate = activity.getStartDateTime().toLocalDate();
            if (Goal.checkDate(activityDate, timeSpan)) {
                result.add(activity);
            }
        }
        return result;
    }

    /**
     * Returns the total distance of all activities in the list matching the specified activity class.
     *
     * @param activityClass The activity class to be matched.
     * @param timeSpan Timespan to be matched.
     * @return The total distance of all activities in the list matching the specified activity class and timespan.
     */
    public int getTotalDistance(Class<?> activityClass, Goal.TimeSpan timeSpan) {
        ArrayList<Activity> filteredActivities = filterByTimespan(timeSpan);
        int totalDistance = 0;
        for (Activity activity : filteredActivities) {
            if (activityClass.isInstance(activity)) {
                totalDistance += activity.getDistance();
            }
        }
        return totalDistance;
    }

    /**
     * Returns the total moving time in seconds of all activities in the list matching the specified activity class.
     *
     * @param activityClass Activity class to be matched.
     * @param timeSpan Timespan to be matched.
     * @return The total moving time of all activities in the list matching the specified activity class.
     */
    public int getTotalDuration(Class<?> activityClass, Goal.TimeSpan timeSpan) {
        ArrayList<Activity> filteredActivities = filterByTimespan(timeSpan);
        int movingTime = 0;
        for (Activity activity : filteredActivities) {
            if (activityClass.isInstance(activity)) {
                movingTime += activity.getMovingTime().toSecondOfDay();
            }
        }
        return movingTime;
    }

    /**
     * Parses an activity from a string.
     *
     * @param s The string to be parsed.
     * @return The activity parsed from the string.
     * @throws AthletiException If the string is invalid or an unknown indicator is found.
     */
    @Override
    public Activity parse(String s) throws AthletiException {
        String[] parts = s.split(" ", 2);

        try {
            String indicator = parts[0];
            String arguments = parts[1];

            switch (indicator) {
            case Parameter.ACTIVITY_STORAGE_INDICATOR:
                return ActivityParser.parseActivity(arguments);
            case Parameter.RUN_STORAGE_INDICATOR:
                return ActivityParser.parseRunCycle(arguments, true);
            case Parameter.CYCLE_STORAGE_INDICATOR:
                return ActivityParser.parseRunCycle(arguments, false);
            case Parameter.SWIM_STORAGE_INDICATOR:
                return ActivityParser.parseSwim(arguments);
            default:
                throw new AthletiException(Message.ACTIVITY_STORAGE_INVALID_INDICATOR);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.ACTIVITY_STORAGE_INVALID_FORMAT);
        }
    }

    /**
     * Unparses an activity to a string.
     *
     * @param activity The activity to be parsed.
     * @return The string unparsed from the activity.
     */
    @Override
    public String unparse(Activity activity) {
        return activity.unparse();
    }
}
