package athleticli.commands.diet;

import java.time.LocalDate;
import java.util.stream.Stream;

import athleticli.commands.FindCommand;
import athleticli.data.Data;
import athleticli.data.diet.Diet;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;


/**
 * Finds diets matching the date.
 */
public class FindDietCommand extends FindCommand {
    public FindDietCommand(LocalDate date) {
        super(date);
    }

    /**
     * Returns the diets matching the date to be shown to the user.
     *
     * @param data The current data.
     * @return The messages to be shown to the user.
     * @throws AthletiException
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        var resultStream = data.getDiets()
                .find(date)
                .stream()
                .filter(Diet.class::isInstance)
                .map(Diet.class::cast)
                .map(Diet::toString);
        return Stream.concat(Stream.of(Message.MESSAGE_DIET_FIND), resultStream)
                .toArray(String[]::new);
    }
}
