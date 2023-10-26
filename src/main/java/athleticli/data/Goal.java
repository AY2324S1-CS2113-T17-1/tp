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
        DAILY {
            /**
             * Returns the number of days in a day.
             *
             * @return The number of days in a day.
             */
            @Override
            public long toDays() {
                return 1;
            }
        },
        WEEKLY {
            /**
             * Returns the number of days in a week.
             *
             * @return  The number of days in a week.
             */
            @Override
            public long toDays() {
                return 7;
            }
        },
        MONTHLY {
            /**
             * Returns the number of days in a month.
             *
             * @return  The number of days in a month.
             */
            @Override
            public long toDays() {
                // A monthly goal always counts data within the last 30 days.
                return 30;
            }
        },
        YEARLY {
            /**
             * Returns the number of days in a year.
             *
             * @return  The number of days in a year.
             */
            @Override
            public long toDays() {
                // A yearly goal always counts data within the last 365 days.
                return 365;
            }
        };

        /**
         * Returns the number of days in the timespan.
         *
         * @return  The number of days in the timespan.
         */
        abstract public long toDays();
    }

    /**
     * Returns the timespan of this goal.
     *
     * @return  The timespan of this goal.
     */
    public Timespan getTimespan() {
        return timespan;
    }

    private Timespan timespan;

    public Goal(Timespan timespan) {
        this.timespan = timespan;
    }

    /**
     * Checks whether the date is between the timespan.
     *
     * @param date  The date to be matched.
     * @return      Whether the date is between the timespan.
     */
    public boolean checkDate(LocalDate date) {
        final LocalDate endDate = LocalDate.now();
        final LocalDate startDate = endDate.minusDays(timespan.toDays() - 1);
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
