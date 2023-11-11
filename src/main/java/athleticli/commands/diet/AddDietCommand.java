package athleticli.commands.diet;


import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.Diet;
import athleticli.data.diet.DietList;
import athleticli.ui.Message;

/**
 * Executes the add diet commands provided by the user.
 */
public class AddDietCommand extends Command {
    private final Diet diet;

    /**
     * Constructor for AddDietCommand.
     *
     * @param diet Diet to be added.
     */
    public AddDietCommand(Diet diet) {
        this.diet = diet;
    }

    /**
     * Updates the diet list.
     *
     * @param data The current data containing the diet list.
     * @return The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        DietList diets = data.getDiets();
        diets.add(this.diet);
        int size = diets.size();
        String countMessage;
        if (size > 1) {
            countMessage = String.format(Message.MESSAGE_DIET_COUNT, size);
        } else {
            countMessage = Message.MESSAGE_DIET_FIRST;
        }
        return new String[]{Message.MESSAGE_DIET_ADDED, this.diet.toString(), countMessage};
    }
}
