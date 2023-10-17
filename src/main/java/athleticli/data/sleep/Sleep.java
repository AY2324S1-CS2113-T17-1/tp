package athleticli.data.sleep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a sleep record.
 */
public class Sleep {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for Sleep.
     *
     * @param from Start time of the sleep.
     * @param to End time of the sleep.
     */
    public Sleep(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * toString method for Sleep.
     *
     * @return String representation of the sleep record.
     */
    public String toString() {
        return "sleep record from " + from.format(DATE_TIME_FORMATTER) + " to " + to.format(DATE_TIME_FORMATTER);
    }
}
