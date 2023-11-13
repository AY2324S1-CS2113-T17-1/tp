package athleticli.data.sleep;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import athleticli.exceptions.AthletiException;

import static athleticli.common.Config.DATE_TIME_PRETTY_FORMATTER;
import static athleticli.common.Config.DATE_FORMATTER;

/**
 * Represents a sleep record.
 */
public class Sleep {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    private final Duration sleepingDuration;

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

    public Duration getSleepingDuration() {
        return sleepingDuration;
    }

    /**
     * Calculate the sleeping duration based on start and end time.
     *
     * @return sleeping duration.
     * @throws AthletiException If any invalid input is provided.
     */
    private Duration calculateSleepingDuration() throws AthletiException {
        if (startDateTime == null || endDateTime == null) {
            throw new AthletiException("Cannot calculate duration with null start/end time");
        }
        Duration duration = Duration.between(startDateTime, endDateTime);
        if (duration.toMinutes() < 1 || duration.toDays() >= 7) {
            throw new AthletiException("Invalid sleep duration: less than 1 minute or more than 7 days");
        }
        return duration;
    }

    /**
     * Calculate the sleep date based on start time.
     * Factor in the possibility of sleeping past midnight.
     * We are assuming that user sleeps before 6am are counted as the previous day.
     * 
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
     * 
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


    /**
     * Returns a string representation of the sleeping duration.
     * 
     * @return String representation of the sleeping duration.
     */
    public String generateSleepingDurationStringOutput() {
        Duration tempDuration = sleepingDuration;
        String sleepingDurationOutput = "";
        if (tempDuration.toDays() != 0) {

            if (tempDuration.toDays() == 1) {
                sleepingDurationOutput += tempDuration.toDays() + " Day ";
            } else {
                sleepingDurationOutput += tempDuration.toDays() + " Days ";
            }

            tempDuration = tempDuration.minusDays(tempDuration.toDays());
        }
        if (tempDuration.toHours() != 0) {
            
            if (tempDuration.toHours() == 1) {
                sleepingDurationOutput += tempDuration.toHours() + " Hour ";
            } else {
                sleepingDurationOutput += tempDuration.toHours() + " Hours ";
            }

            tempDuration = tempDuration.minusHours(tempDuration.toHours());
        }
        if (tempDuration.toMinutes() != 0) {
            
            if (tempDuration.toMinutes() == 1) {
                sleepingDurationOutput += tempDuration.toMinutes() + " Minute ";
            } else {
                sleepingDurationOutput += tempDuration.toMinutes() + " Minutes ";
            }

            tempDuration = tempDuration.minusMinutes(tempDuration.toMinutes());
        }
        return "Sleeping Duration: " + sleepingDurationOutput;
    }

    /**
     * Returns a string representation of the start date time.
     * 
     * @return String representation of the start date time.
     */
    public String generateStartDateTimeStringOutput() {
        return "Start Time: " + startDateTime.format(DATE_TIME_PRETTY_FORMATTER);
    }

    /**
     * Returns a string representation of the end date time.
     * @return String representation of the end date time.
     */
    public String generateEndDateTimeStringOutput() {
        return "End Time: " + endDateTime.format(DATE_TIME_PRETTY_FORMATTER);
    }

    /**
     * Returns a string representation of the sleep date.
     * 
     * @return String representation of the sleep date.
     */
    public String generateSleepDateStringOutput() {
        return "Date: " + sleepDate.format(DATE_FORMATTER);
    }
}
