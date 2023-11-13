package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a running activity consisting of relevant evaluation data.
 */
public class Run extends Activity {
    private static final String PACE_PRINT_FORMAT = "%d:%02d";
    private int elevationGain;
    private double averagePace;

    /**
     * Generates a new running activity with running specific stats.
     * averageSpeed is calculated automatically based on the distance and movingTime.
     *
     * @param movingTime duration of the activity in minutes.
     * @param distance distance covered in meters.
     * @param startDateTime start date and time of the activity.
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run").
     * @param elevationGain elevation gain in meters.
     */
    public Run(String caption, LocalTime movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averagePace = this.calculateAveragePace();
    }

    /**
     * Calculates the average pace of the run in minutes per km.
     *
     * @return average pace of the run in minutes per km. Return 0 if the distance is zero.
     */
    public double calculateAveragePace() {
        double time = getMovingTime().toSecondOfDay() / (double) Parameter.MINUTE_IN_SECONDS;
        double distance = getDistance() / (double) Parameter.KILOMETER_IN_METERS;

        if (distance == 0) {
            return 0;
        }

        return time / distance;
    }

    /**
     * Converts the average pace of the run to the user-friendly format mm:ss.
     *
     * @return average pace of run in mm:ss format.
     */
    public String convertAveragePaceToString() {
        int totalSeconds = (int) Math.round(averagePace * Parameter.MINUTE_IN_SECONDS);
        int minutes = totalSeconds / Parameter.MINUTE_IN_SECONDS;
        int seconds = totalSeconds % Parameter.MINUTE_IN_SECONDS;
        return String.format(PACE_PRINT_FORMAT, minutes, seconds);
    }

    /**
     * Returns a single line summary of the running activity.
     *
     * @return a string representation of the run.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.replace(0, Parameter.ACTIVITY_INDICATOR.length(), Parameter.RUN_INDICATOR);

        String paceOutput = convertAveragePaceToString() + Parameter.PACE_UNIT_TIME_PER_KILOMETER;
        int timePrefixIndex = result.indexOf(Parameter.TIME_PREFIX);
        if (timePrefixIndex != -1) {
            result.insert(timePrefixIndex,
                    Parameter.PACE_PREFIX + paceOutput + Parameter.ACTIVITY_OVERVIEW_SEPARATOR);
        } else {
            throw new AssertionError("Time prefix not found in run string representation");
        }

        return result.toString();
    }

    /**
     * Returns a string representation of the run used for storing the data.
     *
     * @return a string representation of the run.
     */
    @Override
    public String unparse() {
        StringBuilder commandArgs = new StringBuilder(super.unparse());
        commandArgs.replace(0, Parameter.ACTIVITY_STORAGE_INDICATOR.length(), Parameter.RUN_STORAGE_INDICATOR);
        commandArgs.append(Parameter.SPACE).append(Parameter.ELEVATION_SEPARATOR).append(elevationGain);
        return commandArgs.toString();
    }

    /**
     * Returns a detailed summary of the run.
     *
     * @return a multiline string representation of the run.
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String paceOutput = convertAveragePaceToString() + Parameter.PACE_UNIT_TIME_PER_KILOMETER;

        String header = "[Run - " + getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, "Avg Pace: " + paceOutput,
                COLUMN_WIDTH);
        String secondRow = formatTwoColumns("\t" + movingTimeOutput, "Elevation Gain: "
                + elevationGain + Parameter.DISTANCE_UNIT_METERS, COLUMN_WIDTH);

        return String.join(System.lineSeparator(), header, firstRow, secondRow);
    }

    public int getElevationGain() {
        return elevationGain;
    }

    public void setElevationGain(int elevationGain) {
        this.elevationGain = elevationGain;
    }

    /**
     * Sets the distance of the run and recalculates the average pace.
     *
     * @param distance Distance in meters.
     */
    @Override
    public void setDistance(int distance) {
        super.setDistance(distance);
        this.averagePace = this.calculateAveragePace();
    }

    /**
     * Sets the moving time of the run and recalculates the average pace.
     *
     * @param movingTime Moving time.
     */
    @Override
    public void setMovingTime(LocalTime movingTime) {
        super.setMovingTime(movingTime);
        this.averagePace = this.calculateAveragePace();
    }
}
