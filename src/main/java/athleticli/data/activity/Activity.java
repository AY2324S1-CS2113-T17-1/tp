package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

import static athleticli.common.Config.DATE_TIME_FORMATTER;
import static athleticli.common.Config.TIME_FORMATTER;

/**
 * Represents a physical activity consisting of basic sports data.
 */
public class Activity {
    private static final int columnWidth = 40;

    private String description;
    private String caption;
    private LocalTime movingTime;

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
    public Activity(String caption, LocalTime movingTime, int distance, LocalDateTime startDateTime) {
        this.movingTime = movingTime;
        this.distance = distance;
        this.startDateTime = startDateTime;
        this.caption = caption;
    }

    public LocalTime getMovingTime() {
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

    public int getCalories() {
        return this.calories;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    /**
     * Returns a single line summary of the activity.
     * @return a string representation of the activity
     */
    @Override
    public String toString() {
        String movingTimeOutput = generateShortMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        return "[Activity] " + caption + " | " + distanceOutput + " | " + movingTimeOutput + " | " +
                startDateTimeOutput;
    }

    /**
     * Returns distance in user-friendly output format.
     * @return a string representation of the distance
     */
    public String generateDistanceStringOutput() {
        double distanceInKm = distance / 1000.0;
        return "Distance: " + String.format(Locale.ENGLISH, "%.2f", distanceInKm)
                + " km";
    }

    /**
     * Returns moving time in user-friendly output format.
     * @return a string representation of the moving time
     */
    public String generateMovingTimeStringOutput() {
        return "Time: " + movingTime.format(TIME_FORMATTER);
    }

    /**
     * Returns a short representation of the moving time with the format depending on the duration.
     * @return a string representation of the moving time
     */
    public String generateShortMovingTimeStringOutput() {
        String output = "";
        if (movingTime.getHour() > 0) {
            output += movingTime.getHour() + "h " + movingTime.getMinute() + "m";
        } else {
            output += movingTime.getMinute() + "m " + movingTime.getSecond() + "s";
        }
        return "Time: " + output;
    }

    /**
     * Returns start date and time in user-friendly output format.
     * @return a string representation of the start date and time
     */
    public String generateStartDateTimeStringOutput() {
        return startDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * Returns a detailed summary of the activity.
     * @return a multiline string representation of the activity
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();

        String header = "[Activity - " + this.getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, movingTimeOutput, columnWidth);
        String secondRow = formatTwoColumns("\tCalories: " +
                this.getCalories() + " kcal", "...", columnWidth);

        return String.join(System.lineSeparator(), header, firstRow, secondRow);
    }

    /**
     * Formats two strings into two columns of equal width.
     * @param left String to be placed in the left column
     * @param right String to be placed in the right column
     * @param columnWidth width of each column
     * @return a formatted string with two columns of equal width
     */
    public String formatTwoColumns(String left, String right, int columnWidth) {
        return String.format("%-" + columnWidth + "s%s", left, right);
    }

    /**
     * Returns a string representation of the activity used for storing the data.
     * @return a string representation of the activity
     */
    public String unparse() {
        String commandArgs = Parameter.ACTIVITY_STORAGE_INDICATOR;
        commandArgs += " " + this.getCaption();
        commandArgs += " " + Parameter.DURATION_SEPARATOR + this.getMovingTime().format(TIME_FORMATTER);
        commandArgs += " " + Parameter.DISTANCE_SEPARATOR + this.getDistance();
        commandArgs += " " + Parameter.DATETIME_SEPARATOR + this.getStartDateTime();
        return commandArgs;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setMovingTime(LocalTime movingTime) {
        this.movingTime = movingTime;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
}
