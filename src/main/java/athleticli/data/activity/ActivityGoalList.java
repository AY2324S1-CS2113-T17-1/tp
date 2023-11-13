package athleticli.data.activity;

import athleticli.data.Goal;
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
     * Example output: "sport/running type/distance period/weekly target/8000".
     *
     * @param activityGoal Activity goal to be parsed.
     * @return The string unparsed from the activity goal.
     */
    @Override
    public String unparse(ActivityGoal activityGoal) {
        return Parameter.SPORT_SEPARATOR + activityGoal.getSport()
                + Parameter.SPACE + Parameter.TYPE_SEPARATOR + activityGoal.getGoalType()
                + Parameter.SPACE + Parameter.PERIOD_SEPARATOR + activityGoal.getTimeSpan()
                + Parameter.SPACE + Parameter.TARGET_SEPARATOR + activityGoal.getTargetValue();
    }

    /**
     * Checks if there is a duplicate activity goal with the same goal type, sport and timespan.
     *
     * @param goalType Goal type of the activity goal.
     * @param sport    Sport associated with the activity goal.
     * @param timeSpan Time span of the activity goal.
     * @return Whether the activity goal is a duplicate.
     */
    public boolean isDuplicate(ActivityGoal.GoalType goalType, ActivityGoal.Sport sport, Goal.TimeSpan timeSpan) {
        return this.stream().anyMatch(activityGoal -> activityGoal.getGoalType() == goalType
                && activityGoal.getSport() == sport
                && activityGoal.getTimeSpan() == timeSpan);
    }
}
