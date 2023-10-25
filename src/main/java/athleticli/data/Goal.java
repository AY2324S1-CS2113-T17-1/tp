package athleticli.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Defines the basic fields and methods for a goal.
 */
public abstract class Goal {
    /**
     * Defines different types of goal periods.
     */
    public enum Period {
        WEEKLY,
        MONTHLY
    }

    private LocalDate startDate;
    private LocalDate endDate;
    private Period period;

    public Goal(LocalDate date, Period period) {
        switch (period) {
        case WEEKLY:
            this.startDate = getFirstDayOfWeek(date);
            this.endDate = getLastDayOfWeek(date);
            break;
        case MONTHLY:
            this.startDate = getFirstDayOfMonth(date);
            this.endDate = getLastDayOfMonth(date);
            break;
        default:
        }
        this.period = period;
    }

    /**
     * Checks whether the date is between the period.
     *
     * @param date  The date to be matched.
     * @return      Whether the date is between the period.
     */
    public boolean checkDate(LocalDate date) {
        return !(date.isBefore(startDate) || date.isAfter(endDate));
    }

    /**
     * Calculates the first day of week in which the specified date falls.
     *
     * @param date  The specified date.
     * @return      The first day of week in which the specified date falls.
     */
    private static LocalDate getFirstDayOfWeek(LocalDate date) {
        // manually specify Monday as the start of the week
        // to avoid differences due to locale settings
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    /**
     * Calculates the last day of week in which the specified date falls.
     *
     * @param date  The specified date.
     * @return      The last day of week in which the specified date falls.
     */
    private static LocalDate getLastDayOfWeek(LocalDate date) {
        // manually specify Sunday as the end of the week
        // to avoid differences due to locale settings
        return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

    /**
     * Calculates the first day of month in which the specified date falls.
     *
     * @param date  The specified date.
     * @return      The first day of month in which the specified date falls.
     */
    private static LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * Calculates the last day of month in which the specified date falls.
     *
     * @param date  The specified date.
     * @return      The last day of month in which the specified date falls.
     */
    private static LocalDate getLastDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * Returns whether the goal is achieved.
     *
     * @param data  The current data containing all records.
     * @return      Whether the goal is achieved.
     */
    public abstract boolean isAchieved(Data data);
}
