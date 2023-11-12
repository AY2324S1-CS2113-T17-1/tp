package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

import static athleticli.common.Config.DATE_TIME_PRETTY_FORMATTER;
import static athleticli.common.Config.TIME_FORMATTER;
import static athleticli.parser.Parameter.ACTIVITY_INDICATOR;
import static athleticli.parser.Parameter.ACTIVITY_OVERVIEW_SEPARATOR;
import static athleticli.parser.Parameter.DISTANCE_PREFIX;
import static athleticli.parser.Parameter.DISTANCE_UNIT_KILOMETERS;
import static athleticli.parser.Parameter.DISTANCE_UNIT_METERS;
import static athleticli.parser.Parameter.KILOMETER_IN_METERS;
import static athleticli.parser.Parameter.SPACE;
import static athleticli.parser.Parameter.TIME_PREFIX;
import static athleticli.parser.Parameter.TIME_UNIT_HOURS;
import static athleticli.parser.Parameter.TIME_UNIT_MINUTES;
import static athleticli.parser.Parameter.TIME_UNIT_SECONDS;

/**
 * Represents a physical activity consisting of basic sports data.
 */
public class Activity {
    public static final int COLUMN_WIDTH = 40;
    private static final String DISTANCE_PRINT_FORMAT = "%.2f";
    private String caption;
    private LocalTime movingTime;
    private int distance;
    private LocalDateTime startDateTime;

    /**
     * Generates a new general sports activity with some basic stats.
     *
     * @param movingTime Duration of the activity in minutes.
     * @param distance Distance covered in meters.
     * @param startDateTime Start date and time of the activity.
     * @param caption Caption of the activity chosen by the user (e.g., "Morning Run").
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

    /**
     * Returns a single line summary of the activity.
     *
     * @return a string representation of the activity.
     */
    @Override
    public String toString() {
        String movingTimeOutput = generateShortMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String startDateTimeOutput = generateStartDateTimeStringOutput();

        String output = String.join(ACTIVITY_OVERVIEW_SEPARATOR, caption, distanceOutput, movingTimeOutput,
                startDateTimeOutput);
        return ACTIVITY_INDICATOR + SPACE + output;
    }

    /**
     * Returns distance in user-friendly output format.
     * Assumes distance is given in meters.
     * If the distance is less than 1 km, the distance is displayed in meters.
     *
     * @return a string representation of the distance.
     */
    public String generateDistanceStringOutput() {
        StringBuilder output = new StringBuilder(DISTANCE_PREFIX);
        if (distance < KILOMETER_IN_METERS) {
            output.append(distance);
            output.append(DISTANCE_UNIT_METERS);
        } else {
            double distanceInKm = distance / 1000.0;
            output.append(String.format(Locale.ENGLISH, DISTANCE_PRINT_FORMAT, distanceInKm));
            output.append(DISTANCE_UNIT_KILOMETERS);
        }
        return output.toString();
    }

    /**
     * Returns moving time in user-friendly output format.
     *
     * @return a string representation of the moving time.
     */
    public String generateMovingTimeStringOutput() {
        return TIME_PREFIX + movingTime.format(TIME_FORMATTER);
    }

    /**
     * Returns a short representation of the moving time with the format depending on the duration.
     * Format is "Xh Ym" if hours are present, otherwise "Ym Zs".
     *
     * @return a string representation of the moving time.
     */
    public String generateShortMovingTimeStringOutput() {
        StringBuilder output = new StringBuilder(TIME_PREFIX);
        if (movingTime.getHour() > 0) {
            output.append(movingTime.getHour()).append(TIME_UNIT_HOURS + SPACE);
            output.append(movingTime.getMinute()).append(TIME_UNIT_MINUTES);
        } else {
            output.append(movingTime.getMinute()).append(TIME_UNIT_MINUTES + SPACE);
            output.append(movingTime.getSecond()).append(TIME_UNIT_SECONDS);
        }
        return output.toString();
    }

    /**
     * Returns start date and time in user-friendly output format.
     *
     * @return a string representation of the start date and time.
     */
    public String generateStartDateTimeStringOutput() {
        return startDateTime.format(DATE_TIME_PRETTY_FORMATTER);
    }

    /**
     * Returns a detailed summary of the activity.
     *
     * @return a multiline string representation of the activity.
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();

        String header = "[Activity - " + getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, movingTimeOutput, COLUMN_WIDTH);

        return String.join(System.lineSeparator(), header, firstRow);
    }

    /**
     * Formats two strings into two columns of equal width.
     * If a string is longer than the specified columnWidth, it will exceed the column.
     *
     * @param left String to be placed in the left column.
     * @param right String to be placed in the right column.
     * @param columnWidth Width of each column, should be a positive Integer.
     * @return a formatted string with two columns of equal width.
     */
    public String formatTwoColumns(String left, String right, int columnWidth) {
        return String.format("%-" + columnWidth + "s%s", left, right);
    }

    /**
     * Returns a string representation of the activity used for storing the data.
     *
     * @return a string representation of the activity.
     */
    public String unparse() {
        return Parameter.ACTIVITY_STORAGE_INDICATOR + SPACE + getCaption() +
                SPACE + Parameter.DURATION_SEPARATOR + getMovingTime().format(TIME_FORMATTER) +
                SPACE + Parameter.DISTANCE_SEPARATOR + getDistance() +
                SPACE + Parameter.DATETIME_SEPARATOR + getStartDateTime();
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
