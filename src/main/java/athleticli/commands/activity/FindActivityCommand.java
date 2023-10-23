package athleticli.commands.activity;

import java.time.LocalDate;
import java.util.stream.Stream;

import athleticli.commands.FindCommand;
import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

public class FindActivityCommand extends FindCommand {
    public FindActivityCommand(LocalDate date) {
        super(date);
    }

    /**
     * Returns the activities matching the date to be shown to the user.
     *
     * @param data The current data.
     * @return The messages to be shown to the user.
     * @throws AthletiException
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        var resultStream = data.getActivities()
                .find(date)
                .stream()
                .filter(Activity.class::isInstance)
                .map(Activity.class::cast)
                .map(Activity::toString);
        return Stream.concat(Stream.of(Message.MESSAGE_ACTIVITY_FIND), resultStream)
                .toArray(String[]::new);
    }
}
