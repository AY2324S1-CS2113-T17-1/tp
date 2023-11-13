package athleticli.commands;

import java.time.LocalDate;
import java.util.stream.Stream;

import athleticli.commands.activity.FindActivityCommand;
import athleticli.commands.diet.FindDietCommand;
import athleticli.commands.sleep.FindSleepCommand;
import athleticli.data.Data;
import athleticli.exceptions.AthletiException;

public class FindCommand extends Command {
    protected LocalDate date;

    public FindCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the records to be shown to the user.
     *
     * @param data The current data.
     * @return The records to be shown to the user.
     * @throws AthletiException
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        var activities = Stream.of(new FindActivityCommand(date).execute(data));
        var diets = Stream.of(new FindDietCommand(date).execute(data));
        var sleeps = Stream.of(new FindSleepCommand(date).execute(data));
        return Stream.of(activities, diets, sleeps)
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .toArray(String[]::new);
    }
}
