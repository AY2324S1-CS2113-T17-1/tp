package athleticli.data.activity;

import athleticli.data.Data;
import athleticli.data.StorableList;
import athleticli.exceptions.AthletiException;
import athleticli.parser.ActivityParser;
import athleticli.parser.Parameter;

import static athleticli.common.Config.PATH_ACTIVITY_GOAL;

/**
 * Represents a list of activity goals.
 */
public class ActivityGoalList extends StorableList<ActivityGoal> {
    /**
     * Constructs an activity goal list.
     */
    public ActivityGoalList() {
        super(PATH_ACTIVITY_GOAL);
    }

    /**
     * Returns a string representation of the activity goal list.
     *
     * @param data The data containing the activity goal list.
     * @return A string representation of the activity goal list.
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
     * Parses an activity goal from a string.
     *
     * @param arguments The string to be parsed.
     * @return The activity goal parsed from the string.
     */
    @Override
    public ActivityGoal parse(String arguments) throws AthletiException {
        return ActivityParser.parseActivityGoal(arguments);
    }

    /**
     * Unparses an activity goal to a string.
     *
     * @param activityGoal The activity goal to be parsed.
     * @return The string unparsed from the activity goal.
     */
    @Override
    public String unparse(ActivityGoal activityGoal) {
        String commandArgs = "";
        commandArgs += Parameter.SPORT_SEPARATOR + activityGoal.getSport();
        commandArgs += " " + Parameter.TYPE_SEPARATOR + activityGoal.getGoalType();
        commandArgs += " " + Parameter.PERIOD_SEPARATOR + activityGoal.getTimeSpan();
        commandArgs += " " + Parameter.TARGET_SEPARATOR + activityGoal.getTargetValue();
        return commandArgs;
    }
}
