package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.Diet;
import athleticli.data.diet.DietList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.util.logging.Logger;

/**
 * Executes the add diet commands provided by the user.
 */
public class DeleteDietCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDietCommand.class.getName());
    private final int index;

    /**
     * Constructor for AddDietCommand.
     *
     * @param index Diet to be added.
     */
    public DeleteDietCommand(int index) {
        assert index > 0 : "Index cannot be less than 1";
        this.index = index;
    }

    /**
     * Updates the diet list.
     *
     * @param data The current data containing the diet list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        logger.info("Deleting diet at index " + index);
        DietList dietList = data.getDiets();
        int size = dietList.size();
        if (index > size) {
            logger.warning("Index out of bounds");
            throw new AthletiException(Message.MESSAGE_INVALID_DIET_INDEX);
        }
        Diet oldDiet = dietList.get(index - 1);
        dietList.remove(index - 1);
        logger.info("Diet deleted successfully");
        return new String[]{Message.MESSAGE_DIET_DELETED, oldDiet.toString(),
                String.format(Message.MESSAGE_DIET_COUNT, size - 1)};
    }
}
