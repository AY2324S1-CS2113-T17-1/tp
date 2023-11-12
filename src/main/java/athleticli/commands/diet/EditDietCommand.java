package athleticli.commands.diet;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.diet.Diet;
import athleticli.data.diet.DietList;
import athleticli.exceptions.AthletiException;
import athleticli.parser.Parameter;
import athleticli.parser.Parser;
import athleticli.ui.Message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Executes the edit diet command provided by the user.
 */
public class EditDietCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditDietCommand.class.getName());
    private final int index;
    private final HashMap<String, String> dietMap;

    /**
     * Constructor for EditDietCommand.
     *
     * @param index   Index of the diet to be edited.
     * @param dietMap Updated Diet.
     */
    public EditDietCommand(int index, HashMap<String, String> dietMap) {
        assert index > 0 : "Index should be greater than 0";
        assert !dietMap.isEmpty() : "Diet map should not be empty";
        this.index = index;
        this.dietMap = dietMap;
    }

    /**
     * Executes the edit diet command.
     *
     * @param data Data object containing the current list of diets.
     * @return String array containing the messages to be printed to the user.
     * @throws AthletiException If the index provided is out of bounds.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        logger.info("Editing diet at index " + index);
        DietList diets = data.getDiets();
        int size = diets.size();
        if (index > size) {
            logger.warning("Index out of bounds");
            throw new AthletiException(Message.MESSAGE_INVALID_DIET_INDEX);
        }
        Diet oldDiet = diets.get(index - 1);
        for (String key : dietMap.keySet()) {
            assert !java.util.Objects.equals(dietMap.get(key), "") : "Diet parameter should not be empty";
            switch (key) {
            case Parameter.CALORIES_SEPARATOR:
                oldDiet.setCalories(Integer.parseInt(dietMap.get(key)));
                break;
            case Parameter.PROTEIN_SEPARATOR:
                oldDiet.setProtein(Integer.parseInt(dietMap.get(key)));
                break;
            case Parameter.CARB_SEPARATOR:
                oldDiet.setCarb(Integer.parseInt(dietMap.get(key)));
                break;
            case Parameter.FAT_SEPARATOR:
                oldDiet.setFat(Integer.parseInt(dietMap.get(key)));
                break;
            case Parameter.DATETIME_SEPARATOR:
                LocalDateTime dateTime = Parser.parseDateTime(dietMap.get(key));
                oldDiet.setDateTime(dateTime);
                break;
            default:
                break;
            }
        }
        diets.set(index - 1, oldDiet);
        logger.info("Diet edited successfully");
        return new String[]{Message.MESSAGE_DIET_UPDATED, oldDiet.toString()};
    }
}
