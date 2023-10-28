package athleticli.data;

import java.time.LocalDate;

/**
 * Defines the basic fields and methods for a goal.
 */
public abstract class Goal {
    /**
     * Defines different types of timespans.
     */
    public enum Timespan {
        DAILY(1),
        WEEKLY(7),
        MONTHLY(30),
        YEARLY(365);

        private final long days;

        Timespan(long days) {
            this.days = days;
        }

        /**
         * Returns the number of days in the timespan.
         *
         * @return  The number of days in the timespan.
         */
        public long getDays() {
            return days;
        }
    }

    private Timespan timespan;

    public Goal(Timespan timespan) {
        this.timespan = timespan;
    }

    /**
     * Returns the timespan of this goal.
     *
     * @return  The timespan of this goal.
     */
    public Timespan getTimespan() {
        return timespan;
    }

    /**
     * Checks whether the date is between the timespan.
     *
     * @param date     The date to be matched.
     * @param timespan The timespan of the goal.
     * @return         Whether the date is between the timespan.
     */
    public static boolean checkDate(LocalDate date, Timespan timespan) {
        final LocalDate endDate = LocalDate.now();
        final LocalDate startDate = endDate.minusDays(timespan.getDays() - 1);
        return !(date.isBefore(startDate) || date.isAfter(endDate));
    }

    /**
     * Returns whether the goal is achieved.
     *
     * @param data  The current data containing all records.
     * @return      Whether the goal is achieved.
     */
    public abstract boolean isAchieved(Data data);

}
