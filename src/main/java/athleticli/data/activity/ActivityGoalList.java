package athleticli.data.activity;

import static athleticli.storage.Config.PATH_ACTIVITY_GOAL;

import athleticli.data.StorableList;

public class ActivityGoalList extends StorableList<ActivityGoal> {
    /**
     * Constructs an activity goal list.
     */
    public ActivityGoalList() {
        super(PATH_ACTIVITY_GOAL);
    }

    /**
     * Parses an activity goal from a string.
     *
     * @param s The string to be parsed.
     * @return The activity goal parsed from the string.
     */
    @Override
    public ActivityGoal parse(String s) {
        // TODO
        return null;
    }

    /**
     * Unparses an activity goal to a string.
     *
     * @param activityGoal The activity goal to be parsed.
     * @return The string unparsed from the activity goal.
     */
    @Override
    public String unparse(ActivityGoal activityGoal) {
        // TODO
        return null;
    }
}
