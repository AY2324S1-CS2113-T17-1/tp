package athleticli.data.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a physical activity consisting of basic sports data.
 */
public class Activity {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("\"MMMM d, yyyy 'at' h:mm a\"");
    public enum ActivityType {
        ACTIVITY, RUN, SWIM, CYCLE
    }

    private String description;
    private String caption;
    private int movingTime;
    private int distance;
    private int calories;
    private LocalDateTime startDateTime;

    /**
     * Generates a new general sports activity with some basic stats.
     * By default, calories is 0, i.e., not tracked.
     * @param movingTime duration of the activity in minutes
     * @param distance distance covered in meters
     * @param startDateTime start date and time of the activity
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run")
     */
    public Activity(String caption, int movingTime, int distance, LocalDateTime startDateTime) {
        this.movingTime = movingTime;
        this.distance = distance;
        this.startDateTime = startDateTime;
        this.caption = caption;
    }

    public int getMovingTime() {
        return movingTime;
    }

    public int getDistance() {
        return distance;
    }

    public String getCaption() {
        return caption;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Returns a single line summary of the activity.
     * @return a string representation of the activity
     */
    @Override
    public String toString() {
        int movingTimeHours = movingTime / 60;
        int movingTimeMinutes = movingTime % 60;
        double distanceInKm = distance / 1000.0;
        String movingTimeOutput = "Time: " + movingTimeHours + "h " + movingTimeMinutes + "m";
        String distanceOutput = "Distance: " + String.format("%.2f", distanceInKm).replace(",", ".") + " km";
        String startDateTimeOutput = startDateTime.format(DATE_TIME_FORMATTER);
        String result = "[Activity] " + caption + " | " + distanceOutput + " | " + movingTimeOutput + " | " +
                startDateTimeOutput;
        return result;
    }

}
