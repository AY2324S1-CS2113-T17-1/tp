package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.DietGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        Set<String> currentDietGoalsNutrients = new HashSet<>();

        // Populate the set with current diet goal nutrients
        for (DietGoal dietGoal : currentDietGoals) {
            currentDietGoalsNutrients.add(dietGoal.getNutrients());
        }

        // Check against user new diet goals
        for (DietGoal userDietGoal : userNewDietGoals) {
            String userNewNutrient = userDietGoal.getNutrients();
            if (currentDietGoalsNutrients.contains(userNewNutrient)) {
                throw new AthletiException(String.format(Message.MESSAGE_DIETGOAL_ALREADY_EXISTED, userNewNutrient));
            }
        }

        // Add new diet goals to current diet goals
        currentDietGoals.addAll(userNewDietGoals);
        int dietGoalNum = currentDietGoals.size();
        return new String[]{Message.MESSAGE_DIETGOAL_LIST_HEADER, currentDietGoals.toString(),
                String.format(Message.MESSAGE_DIETGOAL_COUNT, dietGoalNum)};
    }

}
