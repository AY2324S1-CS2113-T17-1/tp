package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;

import athleticli.data.diet.DietGoal;
import athleticli.data.diet.DietGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.util.ArrayList;

/**
 * Executes the edit-diet-goal commands provided by the user.
 */
public class EditDietGoalCommand extends Command {
    private final ArrayList<DietGoal> userUpdatedDietGoals;

    /**
     * This is a constructor to set up the edit diet goal command.
     *
     * @param dietGoals This is a list consisting of updated existing diet goals.
     *                  to be added to the current goal list.
     */
    public EditDietGoalCommand(ArrayList<DietGoal> dietGoals) {
        userUpdatedDietGoals = dietGoals;
    }

    /**
     * Updates the Diet Goal List.
     *
     * @param data The current data containing the different nutrient goals.
     * @return The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        DietGoalList currentDietGoals = data.getDietGoals();
        verifyEditedGoalsValid(currentDietGoals);
        updateUserGoals(currentDietGoals);
        return generateEditDietGoalSuccessMessage(data, currentDietGoals);
    }

    private static String[] generateEditDietGoalSuccessMessage(Data data, DietGoalList currentDietGoals) {
        int dietGoalNum = currentDietGoals.size();
        return new String[]{Message.MESSAGE_DIET_GOAL_LIST_HEADER, currentDietGoals.toString(data),
                String.format(Message.MESSAGE_DIET_GOAL_COUNT, dietGoalNum)};
    }

    private void updateUserGoals(DietGoalList currentDietGoals) {
        int newTargetValue;
        for (DietGoal userUpdatedDietGoal : userUpdatedDietGoals) {
            for (DietGoal currentDietGoal : currentDietGoals) {
                if (!userUpdatedDietGoal.getNutrient().equals(currentDietGoal.getNutrient())) {
                    continue;
                }
                //update new target value to the current goal
                newTargetValue = userUpdatedDietGoal.getTargetValue();
                currentDietGoal.setTargetValue(newTargetValue);
            }
        }
    }

    private void verifyEditedGoalsValid(DietGoalList currentDietGoals) throws AthletiException {
        for (DietGoal userDietGoal : userUpdatedDietGoals) {
            boolean isDietGoalExisted = false;
            currentDietGoals.isDietGoalTypeValid(userDietGoal);

            if (currentDietGoals.isDietGoalUnique(userDietGoal)) {
                throw new AthletiException(String.format(Message.MESSAGE_DIET_GOAL_NOT_EXISTED,
                        userDietGoal.getNutrient(), userDietGoal.getTimeSpan().toString()));
            }
            if (!currentDietGoals.isDietGoalTypeValid(userDietGoal)) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_TYPE_CLASH);
            }
            if (!currentDietGoals.isTargetValueConsistentWithTimeSpan(userDietGoal)) {
                throw new AthletiException(Message.MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_SCALING_WITH_TIME_SPAN);
            }
            isDietGoalExisted = true;

            if (!isDietGoalExisted) {
                throw new AthletiException(String.format(Message.MESSAGE_DIET_GOAL_NOT_EXISTED,
                        userDietGoal.getNutrient(), userDietGoal.getTimeSpan().toString()));
            }
        }
    }
}

