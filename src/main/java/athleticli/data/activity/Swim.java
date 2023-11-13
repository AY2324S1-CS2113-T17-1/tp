package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a swimming activity consisting of relevant evaluation data.
 */
public class Swim extends Activity {
    private static final int METERS_PER_LAP = 50;
    private int laps;
    private SwimmingStyle style;
    private int averageLapTime;

    public enum SwimmingStyle {
        BUTTERFLY,
        BACKSTROKE,
        BREASTSTROKE,
        FREESTYLE
    }

    /**
     * Generates a new swimming activity with swimming specific stats.
     * By default, calories is 0, i.e., not tracked.
     * averageLapTime is calculated automatically based on the movingTime and laps.
     *
     * @param movingTime duration of the activity in HH:mm:ss format.
     * @param distance distance covered in meters.
     * @param startDateTime start date and time of the activity.
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run").
     * @param style swimming style.
     */
    public Swim(String caption, LocalTime movingTime, int distance, LocalDateTime startDateTime, SwimmingStyle style) {
        super(caption, movingTime, distance, startDateTime);
        this.laps = this.calculateLaps();
        this.style = style;
        this.averageLapTime = this.calculateAverageLapTime();
    }

    /**
     * Calculates the average lap time in seconds.
     *
     * @return average lap time in seconds. Return 0 if the movingTime is zero.
     */
    public int calculateAverageLapTime() {
        if (laps == 0) {
            return 0;
        }

        return this.getMovingTime().toSecondOfDay() / laps;
    }

    /**
     * Calculates the number of laps.
     *
     * @return number of laps.
     */
    public int calculateLaps() {
        return this.getDistance() / METERS_PER_LAP;
    }

    /**
     * Returns a short string representation of the swim.
     *
     * @return a string representation of the swim.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.replace(0, Parameter.ACTIVITY_INDICATOR.length(), Parameter.SWIM_INDICATOR);

        String averageLapTimeOutput = averageLapTime + Parameter.TIME_UNIT_SECONDS;
        int timePrefixIndex = result.indexOf(Parameter.TIME_PREFIX);
        if (timePrefixIndex != -1) {
            result.insert(timePrefixIndex,
                    Parameter.LAP_TIME_PREFIX + averageLapTimeOutput + Parameter.ACTIVITY_OVERVIEW_SEPARATOR);
        } else {
            throw new AssertionError("Time prefix not found in swim string representation");
        }

        return result.toString();
    }

    /**
     * Returns a detailed summary of the swim.
     *
     * @return a multiline string representation of the swim.
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String lapsOutput = generateLapsStringOutput();
        String styleOutput = generateStyleStringOutput();
        String averageLapTimeOutput = generateAverageLapTimeStringOutput();

        String header = "[Swim - " + getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, movingTimeOutput, COLUMN_WIDTH);
        String secondRow = formatTwoColumns("\t" + lapsOutput, styleOutput, COLUMN_WIDTH);
        String thirdRow = formatTwoColumns("\t" + Parameter.AVG_LAP_TIME_PREFIX + averageLapTimeOutput, "",
                COLUMN_WIDTH);

        return String.join(System.lineSeparator(), header, firstRow, secondRow, thirdRow);
    }

    /**
     * Returns a string representation of the average lap time of a swim.
     *
     * @return a string representation of the average lap time.
     */
    public String generateAverageLapTimeStringOutput() {
        return averageLapTime + Parameter.TIME_UNIT_SECONDS;
    }

    /**
     * Returns a string representation of the number of laps of a swim.
     *
     * @return a string representation of the number of laps of a swim.
     */
    public String generateLapsStringOutput() {
        return Parameter.LAPS_PREFIX + laps;
    }

    /**
     * Returns a string representation of the swimming style.
     *
     * @return a string representation of the swimming style.
     */
    public String generateStyleStringOutput() {
        return Parameter.STYLE_PREFIX + getStyle();
    }

    /**
     * Returns a string representation of the swim used for storing the data.
     * @return a string representation of the swim.
     */
    @Override
    public String unparse() {
        StringBuilder commandArgs = new StringBuilder(super.unparse());
        commandArgs.replace(0, Parameter.ACTIVITY_STORAGE_INDICATOR.length(), Parameter.SWIM_STORAGE_INDICATOR);
        commandArgs.append(Parameter.SPACE).append((Parameter.SWIMMING_STYLE_SEPARATOR)).append(style);
        return commandArgs.toString();
    }

    public SwimmingStyle getStyle() {
        return style;
    }

    public void setStyle(SwimmingStyle style) {
        this.style = style;
    }

    /**
     * Sets the distance of the swim and recalculates the total laps and average lap time.
     *
     * @param distance Distance in meters.
     */
    @Override
    public void setDistance(int distance) {
        super.setDistance(distance);
        laps = calculateLaps();
        this.averageLapTime = this.calculateAverageLapTime();
    }

    /**
     * Sets the moving time of the swim and recalculates the average lap time.
     *
     * @param movingTime Moving time in LocalTime format.
     */
    @Override
    public void setMovingTime(LocalTime movingTime) {
        super.setMovingTime(movingTime);
        this.averageLapTime = this.calculateAverageLapTime();
    }
}
