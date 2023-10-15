package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.DietList;
import athleticli.ui.Message;

/**
 * Executes the list diet commands provided by the user.
 */
public class ListDietCommand extends Command {

    /**
     * Constructor for ListDietCommand.
     */
    public ListDietCommand() {
    }

    /**
     * Updates the diet list.
     *
     * @param data The current data containing the diet list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) {
        DietList dietList = data.getDiets();
        int size = dietList.size();
        return new String[]{Message.MESSAGE_DIET_LIST, dietList.toString(),
                String.format(Message.MESSAGE_DIET_COUNT, size)};
    }
}
