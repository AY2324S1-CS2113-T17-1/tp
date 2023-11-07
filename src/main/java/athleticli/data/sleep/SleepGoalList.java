package athleticli.data.sleep;

import athleticli.data.StorableList;
import athleticli.exceptions.AthletiException;
import athleticli.parser.SleepParser;
import athleticli.parser.Parameter;

import static athleticli.common.Config.PATH_SLEEP_GOAL;

/**
 * Represents a list of sleep goals.
 */
public class SleepGoalList extends StorableList<SleepGoal> {
    /**
     * Constructs a sleep goal list.
     */
    public SleepGoalList() {
        super(PATH_SLEEP_GOAL);
    }

    /**
     * Parses a sleep goal from a string.
     *
     * @param arguments The string to be parsed.
     * @return The sleep goal parsed from the string.
     */
    @Override
    public SleepGoal parse(String arguments) throws AthletiException {
        return SleepParser.parseSleepGoal(arguments.toLowerCase());
    }

    /**
     * Unparses a sleep goal to a string.
     *
     * @param sleepGoal The sleep goal to be parsed.
     * @return The string unparsed from the sleep goal.
     */
    @Override
    public String unparse(SleepGoal sleepGoal) {
        String commandArgs = "";
        commandArgs += Parameter.TYPE_SEPARATOR + sleepGoal.getGoalType();
        commandArgs += " " + Parameter.PERIOD_SEPARATOR + sleepGoal.getTimeSpan();
        commandArgs += " " + Parameter.TARGET_SEPARATOR + sleepGoal.getTargetValue();
        return commandArgs;
    }
}
