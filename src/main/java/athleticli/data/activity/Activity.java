package athleticli.data.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a physical activity consisting of basic sports data.
 */
public class Activity {

    private String description;
    private String caption;
    private int movingTime;
    private int distance;
    private int calories;
    private LocalDateTime startDateTime;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovingTime() {
        return movingTime;
    }

    public void setMovingTime(int movingTime) {
        this.movingTime = movingTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}