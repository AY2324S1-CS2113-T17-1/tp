package athleticli.data.sleep;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import athleticli.exceptions.AthletiException;

import static athleticli.common.Config.DATE_TIME_FORMATTER;
import static athleticli.common.Config.DATE_FORMATTER;

/**
 * Represents a sleep record.
 */
public class Sleep {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    private LocalTime sleepingDuration;

    private final LocalDate sleepDate;

    /**
     * Generates a new sleep record with some basic stats.
     *
     * @param startDateTime Start time of the sleep.
     * @param toDateTime    End time of the sleep.
     * @throws AthletiException If any invalid input is provided.
     */
    public Sleep(LocalDateTime startDateTime, LocalDateTime toDateTime) throws AthletiException {
        this.startDateTime = startDateTime;
        this.endDateTime = toDateTime;
        this.sleepingDuration = calculateSleepingDuration();
        this.sleepDate = calculateSleepDate();
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDate getSleepDate() {
        return sleepDate;
    }

    public LocalTime getSleepingTime() {
        return sleepingDuration;
    }

    /**
     * Calculate the sleeping duration based on start and end time.
     * Factor in the possibility of sleeping past midnight.
     *
     * @return sleeping duration.
     * @throws AthletiException If any invalid input is provided.
     */
    private LocalTime calculateSleepingDuration() throws AthletiException {
        if (startDateTime == null || endDateTime == null) {
            throw new AthletiException("Cannot calculate duration with null start/end time");
        }
        Duration duration = Duration.between(startDateTime, endDateTime);
        long seconds = duration.getSeconds();
        if (duration.toMinutes() < 1 || duration.toDays() > 7) {
            throw new AthletiException("Invalid sleep duration: less than 1 minute or more than 7 days");
        }
        return LocalTime.ofSecondOfDay(seconds);
    }

    /**
     * Calculate the sleep date based on start time.
     * Factor in the possibility of sleeping past midnight.
     * We are assuming that the user sleeps before 6am even if the user sleeps past midnight.
     * @return sleep date.
     */
    private LocalDate calculateSleepDate() {
        if (startDateTime.getHour() < 6) {
            return startDateTime.toLocalDate().minusDays(1);
        } else {
            return startDateTime.toLocalDate();
        }
    }

    /**
     * Returns a single line summary of the sleep record.
     * @return String representation of the sleep record.
     */
    @Override
    public String toString() {
        String sleepingDurationOutput = generateSleepingDurationStringOutput();
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String toDateTimeOutput = generateEndDateTimeStringOutput();
        String sleepDateOutput = generateSleepDateStringOutput();
        return "[Sleep]" + " | " + sleepDateOutput + " | " + startDateTimeOutput +
            " | " + toDateTimeOutput + " | " + sleepingDurationOutput;
    }

    public String generateSleepingDurationStringOutput() {
        String sleepingDurationOutput = "";
        if (sleepingDuration.getHour() != 0) {
            sleepingDurationOutput += sleepingDuration.getHour() + " Hours ";
        }
        if (sleepingDuration.getMinute() != 0) {
            sleepingDurationOutput += sleepingDuration.getMinute() + " Minutes";
        }
        return "Sleeping Duration: " + sleepingDurationOutput;
    }

    public String generateStartDateTimeStringOutput() {
        return "Start Time: " + startDateTime.format(DATE_TIME_FORMATTER);
    }

    public String generateEndDateTimeStringOutput() {
        return "End Time: " + endDateTime.format(DATE_TIME_FORMATTER);
    }

    public String generateSleepDateStringOutput() {
        return "Date: " + sleepDate.format(DATE_FORMATTER);
    }
}
