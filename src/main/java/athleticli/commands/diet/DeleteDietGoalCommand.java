package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.DietGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.io.IOException;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Executes the delete-diet-goal commands provided by the user.
 */
public class DeleteDietGoalCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDietGoalCommand.class.getName());
    private final int deleteIndex;

    /**
     * This is a constructor to set up the delete diet goal command.
     *
     * @param deleteIndex Index of the diet goal to be deleted in the users' perspective.
     */
    public DeleteDietGoalCommand(int deleteIndex) {
        //deleteIndex that is less than or equal to zero would result in exception
        assert deleteIndex >= 1: "'deleteIndex' should have the value of 1 minimally.";
        this.deleteIndex = deleteIndex;
        LogManager.getLogManager().reset();
        try {
            logger.addHandler(new FileHandler("%t/athleticli-log.txt"));
        } catch(IOException e) {
            logger.addHandler(new ConsoleHandler());
        }
    }

    /**
     * Deletes a goal from the Diet Goal List.
     *
     * @param data The current data containing the different nutrient goals.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        logger.log(Level.FINE, "Executing delete command for diet goals");
        DietGoalList dietGoals = data.getDietGoals();
        if (dietGoals.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIET_GOAL_EMPTY_DIET_GOAL_LIST);
        }
        try {
            DietGoal dietGoalRemoved = dietGoals.get(deleteIndex - 1);
            dietGoals.remove(deleteIndex - 1);
            logger.log(Level.FINE, String.format("Diet goals %s has been successfully removed",
                    dietGoalRemoved.getNutrient()));
            return new String[]{Message.MESSAGE_DIET_GOAL_DELETE_HEADER,
                    dietGoalRemoved.toString(data)};
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(String.format(Message.MESSAGE_DIET_GOAL_OUT_OF_BOUND, dietGoals.size()));
        }
    }
}
