package athleticli.commands.sleep;

import java.time.LocalDate;
import java.util.stream.Stream;

import athleticli.commands.FindCommand;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

public class FindSleepCommand extends FindCommand {
    public FindSleepCommand(LocalDate date) {
        super(date);
    }

    /**
     * Returns the sleeps matching the date to be shown to the user.
     *
     * @param data The current data.
     * @return The messages to be shown to the user.
     * @throws AthletiException If any errors occur in finding the sleep entry.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        var resultStream = data.getSleeps()
                .find(date)
                .stream()
                .filter(Sleep.class::isInstance)
                .map(Sleep.class::cast)
                .map(Sleep::toString);
        return Stream.concat(Stream.of(Message.MESSAGE_SLEEP_FIND), resultStream)
                .toArray(String[]::new);
    }
}
