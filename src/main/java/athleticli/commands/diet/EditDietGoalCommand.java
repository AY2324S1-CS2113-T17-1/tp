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
 * Executes the edit-diet-goal commands provided by the user.
 */
public class EditDietGoalCommand extends Command {
    private final ArrayList<DietGoal> userUpdatedDietGoals;

    /**
     * This is a constructor to set up the edit diet goal command
     *
     * @param dietGoals This is a list consisting of updated existing diet goals
     *                  to be added to the current goal list.
     */
    public EditDietGoalCommand(ArrayList<DietGoal> dietGoals) {
        userUpdatedDietGoals = dietGoals;
    }

    /**
     * Updates the Diet Goal List.
     *
     * @param data The current data containing the different nutrients' new goal value.
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

        // Check if user edited diet goals is in records previously
        boolean isNutrientGoalInCurrentDietGoalList;
        for (DietGoal userDietGoal : userUpdatedDietGoals) {
            String userNewNutrient = userDietGoal.getNutrients();
            isNutrientGoalInCurrentDietGoalList = currentDietGoalsNutrients.contains(userNewNutrient);
            if (!isNutrientGoalInCurrentDietGoalList) {
                throw new AthletiException(String.format(Message.MESSAGE_DIETGOAL_NOT_EXISTED, userNewNutrient));
            }
        }

        // Edit updated goals to current diet goals
        int newTargetValue;
        for(DietGoal userUpdatedDietGoal : userUpdatedDietGoals){
            for(DietGoal currentDietGoal: currentDietGoals){
                if (!userUpdatedDietGoal.getNutrients().equals(currentDietGoal.getNutrients())){
                    continue;
                }
                //update new target value to the current goal
                newTargetValue = userUpdatedDietGoal.getTargetValue();
                currentDietGoal.setTargetValue(newTargetValue);
            }
        }

        return new String[]{"These are your goals:\n", currentDietGoals.toString()};
    }
}
