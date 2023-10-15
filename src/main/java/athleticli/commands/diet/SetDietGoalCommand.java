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
        String userNewNutrient;
        String currentDietGoalsNutrient;
        for (int i = 0; i < userNewDietGoals.size(); i++) {
            userNewNutrient = userNewDietGoals.get(i).getNutrients();
            for (int j = 0; j < currentDietGoals.size(); j++) {
                currentDietGoalsNutrient = currentDietGoals.get(j).getNutrients();
                if (userNewNutrient.equals(currentDietGoalsNutrient)) {
                    throw new AthletiException(String.format(
                            Message.MESSAGE_DIETGOAL_ALREADY_EXISTED, userNewNutrient));
                }
            }
        }
        for (int k = 0; k < userNewDietGoals.size(); k++){
            currentDietGoals.add(userNewDietGoals.get(k));
        }
        return new String[]{"These are your goals:\n", currentDietGoals.toString()};
    }
}
