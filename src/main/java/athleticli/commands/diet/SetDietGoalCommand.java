package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.DietGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.util.ArrayList;

/**
 * Executes the set-diet-goal commands provided by the user.
 */
public class SetDietGoalCommand extends Command {

    private final ArrayList<DietGoal> userNewDietGoals;

    /**
     * This is a constructor to set up the set diet goal command
     *
     * @param dietGoals This is a list consisting of new diet goals
     *                  to be added to the current goal list.
     */
    public SetDietGoalCommand(ArrayList<DietGoal> dietGoals) {
        userNewDietGoals = dietGoals;
    }

    /**
     * Updates the Diet Goal list.
     *
     * @param data The current data containing the different nutrients goal value.
     * @return The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        DietGoalList currentDietGoals = data.getDietGoals();
        verifyNewGoalsValid(currentDietGoals);
        addNewUserDietGoals(currentDietGoals);
        return generateSetDietGoalSuccessMessage(data, currentDietGoals);
    }

    private static String[] generateSetDietGoalSuccessMessage(Data data, DietGoalList currentDietGoals) {
        int dietGoalNum = currentDietGoals.size();
        return new String[]{Message.MESSAGE_DIET_GOAL_LIST_HEADER, currentDietGoals.toString(data),
                String.format(Message.MESSAGE_DIET_GOAL_COUNT, dietGoalNum)};
    }

    private void addNewUserDietGoals(DietGoalList currentDietGoals) {
        currentDietGoals.addAll(userNewDietGoals);
    }

    private void verifyNewGoalsValid(DietGoalList currentDietGoals) throws AthletiException {
        for (DietGoal userDietGoal : userNewDietGoals) {
            if (!currentDietGoals.isDietGoalUnique(userDietGoal)) {
                throw new AthletiException(String.format(Message.MESSAGE_DIET_GOAL_ALREADY_EXISTED,
                        userDietGoal.getNutrient()));
            } else if (!currentDietGoals.isDietGoalTypeValid(userDietGoal)) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_TYPE_CLASH);
            } else if (!currentDietGoals.isTargetValueConsistentWithTimeSpan(userDietGoal)) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_SCALING_WITH_TIME_SPAN);
            }
        }
    }

}
