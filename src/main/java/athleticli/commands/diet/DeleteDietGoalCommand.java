package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.DietGoal;
import athleticli.data.diet.DietGoalList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

public class DeleteDietGoalCommand extends Command {
    private final int deleteIndex;

    public DeleteDietGoalCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    public String[] execute(Data data) throws AthletiException {
        DietGoalList dietGoals = data.getDietGoals();
        if (dietGoals.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DIETGOAL_EMPTY_DIETGOALLIST);
        }
        try {
            DietGoal dietGoalRemoved = dietGoals.get(deleteIndex - 1);
            dietGoals.remove(deleteIndex - 1);
            return new String[]{Message.MESSAGE_DIETGOAL_DELETE_HEADER,
                    dietGoalRemoved.toString()};
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(String.format(Message.MESSAGE_DIETGOAL_OUT_OF_BOUND, dietGoals.size()));
        }
    }
}
