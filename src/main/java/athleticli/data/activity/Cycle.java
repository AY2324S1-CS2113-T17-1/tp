package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.util.Locale;
import java.time.LocalTime;

/**
 * Represents a cycling activity consisting of relevant evaluation data.
 */
public class Cycle extends Activity {
    private static final String SPEED_PRINT_FORMAT = "%.2f";
    private int elevationGain;
    private double averageSpeed;

    /**
     * Generates a new cycling activity with cycling specific stats.
     * averageSpeed is calculated automatically based on the distance and movingTime.
     *
     * @param movingTime duration of the activity in minutes.
     * @param distance distance covered in meters.
     * @param startDateTime start date and time of the activity.
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run").
     * @param elevationGain elevation gain in meters.
     */
    public Cycle(String caption, LocalTime movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averageSpeed = this.calculateAverageSpeed();
    }

    /**
     * Calculates the average speed of the cycle in km/h.
     *
     * @return average speed of the cycle in km/h. Return 0 if the movingTime is zero.
     */
    public double calculateAverageSpeed() {
        double distanceInKm = this.getDistance() / (double) Parameter.KILOMETER_IN_METERS;
        double timeInHours = this.getMovingTime().toSecondOfDay() / (double) Parameter.HOUR_IN_SECONDS;

        if (timeInHours == 0) {
            return 0;
        }

        return distanceInKm / timeInHours;
    }

    /**
     * Returns a single line summary of the cycling activity.
     *
     * @return a string representation of the cycle.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.replace(0, Parameter.ACTIVITY_INDICATOR.length(), Parameter.CYCLE_INDICATOR);

        String speedOutput = generateSpeedStringOutput();
        int timePrefixIndex = result.indexOf(Parameter.TIME_PREFIX);
        if (timePrefixIndex != -1) {
            result.insert(timePrefixIndex,
                    Parameter.SPEED_PREFIX + speedOutput + Parameter.ACTIVITY_OVERVIEW_SEPARATOR);
        } else {
            throw new AssertionError("Time prefix not found in cycle string representation");
        }

        return result.toString();
    }

    /**
     * Returns a string representation of the average speed of the cycle.
     *
     * @return a string representation of the average speed of the cycle.
     */
    public String generateSpeedStringOutput() {
        return String.format(Locale.ENGLISH, SPEED_PRINT_FORMAT, this.averageSpeed) +
                Parameter.SPEED_UNIT_KILOMETERS_PER_HOUR;
    }

    /**
     * Returns a string representation of the elevation gain of the cycle.
     *
     * @return a string representation of the elevation gain of the cycle.
     */
    public String generateElevationGainStringOutput() {
        return Parameter.ELEVATION_PREFIX + elevationGain + Parameter.DISTANCE_UNIT_METERS;
    }

    /**
     * Returns a detailed summary of the cycle.
     *
     * @return a multiline string representation of the cycle.
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String speedOutput = generateSpeedStringOutput();
        String elevationOutput = generateElevationGainStringOutput();

        String header = "[Cycle - " + getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, elevationOutput, COLUMN_WIDTH);
        String secondRow = formatTwoColumns("\t" + movingTimeOutput, Parameter.AVG_SPEED_PREFIX + speedOutput,
                COLUMN_WIDTH);

        return String.join(System.lineSeparator(), header, firstRow, secondRow);
    }

    /**
     * Returns a string representation of the cycle used for storing the data.
     *
     * @return a string representation of the cycle.
     */
    @Override
    public String unparse() {
        StringBuilder commandArgs = new StringBuilder(super.unparse());
        commandArgs.replace(0, Parameter.ACTIVITY_STORAGE_INDICATOR.length(), Parameter.CYCLE_STORAGE_INDICATOR);
        commandArgs.append(Parameter.SPACE).append(Parameter.ELEVATION_SEPARATOR).append(elevationGain);
        return commandArgs.toString();
    }

    public int getElevationGain() {
        return this.elevationGain;
    }

    public void setElevationGain(int elevationGain) {
        this.elevationGain = elevationGain;
    }

    /**
     * Sets the distance of the cycle and recalculates the average speed.
     *
     * @param distance Distance in meters.
     */
    @Override
    public void setDistance(int distance) {
        super.setDistance(distance);
        this.averageSpeed = this.calculateAverageSpeed();
    }

    /**
     * Sets the moving time of the cycle and recalculates the average speed.
     *
     * @param movingTime Moving time in LocalTime format.
     */
    @Override
    public void setMovingTime(LocalTime movingTime) {
        super.setMovingTime(movingTime);
        this.averageSpeed = this.calculateAverageSpeed();
    }
}
