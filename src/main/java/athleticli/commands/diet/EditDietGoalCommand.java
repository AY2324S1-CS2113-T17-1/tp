package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.exceptions.AthletiException;

/**
 * Executes the edit-diet-goal commands provided by the user.
 */
public class EditDietGoalCommand extends Command {

    /**
     * Updates the activity list.
     * @param data        The current data containing the different nutrients' new goal value.
     * @return            The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {

        return new String[0];
    }
}
