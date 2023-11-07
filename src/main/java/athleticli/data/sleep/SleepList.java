package athleticli.data.sleep;

import static athleticli.common.Config.PATH_SLEEP;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import athleticli.data.Findable;
import athleticli.data.StorableList;
import athleticli.data.Goal;
import athleticli.exceptions.AthletiException;
import athleticli.parser.SleepParser;
import athleticli.parser.Parameter;

/**
 * Represents a list of sleep records.
 */
public class SleepList extends StorableList<Sleep> implements Findable<Sleep> {
    /**
     * Constructs a sleep list with its storage path.
     */
    public SleepList() {
        super(PATH_SLEEP);
    }

    /**
     * Returns a list of sleeps matching the date.
     *
     * @param date The date to be matched.
     * @return A list of sleeps matching the date.
     */
    @Override
    public ArrayList<Sleep> find(LocalDate date) {
        ArrayList<Sleep> result = new ArrayList<>();
        for (Sleep sleep : this) {
            if (sleep.getStartDateTime().toLocalDate().equals(date)) {
                result.add(sleep);
            }
        }
        return result;
    }

    /**
     * Sorts the sleep entries in the list by date.
     */
    public void sort() {
        this.sort(Comparator.comparing(Sleep::getToDateTime).reversed());
    }


    /**
     * Returns a list of sleeps within the time span.
     *
     * @param timeSpan The time span to be matched.
     * @return A list of sleeps within the time span.
     */
    public ArrayList<Sleep> filterByTimespan(Goal.TimeSpan timeSpan) {
        ArrayList<Sleep> result = new ArrayList<>();
        for (Sleep sleep : this) {
            LocalDate sleepDate = sleep.getStartDateTime().toLocalDate();
            if (Goal.checkDate(sleepDate, timeSpan)) {
                result.add(sleep);
            }
        }
        return result;
    }

    /**
     * Returns the average sleep duration of the sleep list.
     * @param timeSpan The time span to be matched.
     * @return The average sleep duration of the sleep list in seconds.
     */
    public int getTotalSleepDuration(Goal.TimeSpan timeSpan) {
        ArrayList<Sleep> filteredSleepList = filterByTimespan(timeSpan);
        int totalSleepDuration = 0;
        for (Sleep sleep : filteredSleepList) {
            LocalTime sleepDuration = sleep.getSleepingTime();
            totalSleepDuration += sleepDuration.toSecondOfDay();
        }
        return totalSleepDuration;
    }

    /**
     * Parses a sleep from a string.
     *
     * @param s The string to be parsed.
     * @return The sleep parsed from the string.
     */
    @Override
    public Sleep parse(String s) throws AthletiException {
        return SleepParser.parseSleep(s);
    }

    /**
     * Unparses a sleep to a string.
     *
     * @param sleep The sleep to be parsed.
     * @return The string unparsed from the sleep.
     */
    @Override
    public String unparse(Sleep sleep) {
        String commandArgs = "";
        commandArgs += " " + Parameter.START_TIME_SEPARATOR + sleep.getStartDateTime();
        commandArgs += " " + Parameter.END_TIME_SEPARATOR + sleep.getToDateTime();
        return commandArgs;
    }
}
