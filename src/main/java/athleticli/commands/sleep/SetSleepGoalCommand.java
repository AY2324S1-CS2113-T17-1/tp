package athleticli.commands.sleep;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.SleepGoal;
import athleticli.data.sleep.SleepGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Represents a command which sets a sleep goal.
 */
public class SetSleepGoalCommand extends Command {
    private final SleepGoal sleepGoal;

    /**
     * Constructor for SetSleepGoalCommand.
     * 
     * @param sleepGoal Sleep goal to be added.
     */
    public SetSleepGoalCommand(SleepGoal sleepGoal) {
        this.sleepGoal = sleepGoal;
    }

    /**
     * Updates the sleep goal list.
     * 
     * @param data The current data containing the sleep goal list.
     * @return The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        SleepGoalList sleepGoals = data.getSleepGoals();

        if (sleepGoals.isDuplicate(sleepGoal.getGoalType(), sleepGoal.getTimeSpan())) {
            throw new AthletiException(Message.ERRORMESSAGE_DUPLICATE_SLEEP_GOAL);
        }

        sleepGoals.add(this.sleepGoal);
        return new String[]{Message.MESSAGE_SLEEP_GOAL_ADDED, this.sleepGoal.toString(data)};
    }
}
