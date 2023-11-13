package athleticli.data;

import java.time.LocalDate;

/**
 * Defines the basic fields and methods for a goal.
 */
public abstract class Goal {
    /**
     * Defines different types of time spans.
     */
    public enum TimeSpan {
        DAILY(1),
        WEEKLY(7),
        MONTHLY(30),
        YEARLY(365);

        private final int days;

        TimeSpan(int days) {
            this.days = days;
        }

        /**
         * Returns the number of days in the time span.
         *
         * @return  The number of days in the time span.
         */
        public int getDays() {
            return days;
        }
    }

    private TimeSpan timeSpan;

    public Goal(TimeSpan timeSpan) {
        this.timeSpan = timeSpan;
    }

    /**
     * Returns the time span of this goal.
     *
     * @return  The time span of this goal.
     */
    public TimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * Checks whether the date is between the time span.
     *
     * @param date     The date to be matched.
     * @param timeSpan The time span of the goal.
     * @return         Whether the date is between the time span.
     */
    public static boolean checkDate(LocalDate date, TimeSpan timeSpan) {
        final LocalDate endDate = LocalDate.now();
        final LocalDate startDate = endDate.minusDays(timeSpan.getDays() - 1);
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
