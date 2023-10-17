package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.DietGoalList;
import athleticli.ui.Message;

/**
 * Executes the list diet goal commands provided by the user.
 */
public class ListDietGoalCommand extends Command {

    /**
     * Constructor for ListDietGoalCommand.
     */
    public ListDietGoalCommand() {
    }

    /**
     * Iterate and returns the string representation for each goal.
     *
     * @param data The current data containing the diet goal list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) {
        DietGoalList dietGoalList = data.getDietGoals();
        int dietGoalNum = dietGoalList.size();
        if (dietGoalNum == 0) {
            return new String[]{Message.MESSAGE_DIETGOAL_NONE};
        }
        return new String[]{Message.MESSAGE_DIETGOAL_LIST_HEADER, dietGoalList.toString(),
                String.format(Message.MESSAGE_DIETGOAL_COUNT, dietGoalNum)};
    }
}
