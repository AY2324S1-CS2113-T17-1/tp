package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a running activity consisting of relevant evaluation data.
 */
public class Run extends Activity {
    private int elevationGain;
    private double averagePace;
    private final int steps;

    /**
     * Generates a new running activity with running specific stats.
     * By default, calories is 0, i.e., not tracked.
     * averageSpeed is calculated automatically based on the distance and movingTime.
     * @param movingTime duration of the activity in minutes
     * @param distance distance covered in meters
     * @param startDateTime start date and time of the activity
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run")
     * @param elevationGain elevation gain in meters
     */
    public Run(String caption, LocalTime movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averagePace = this.calculateAveragePace();
        this.steps = 0;
    }

    /**
     * Calculates the average pace of the run in minutes per km.
     * @return average pace of the run in minutes per km
     */
    public double calculateAveragePace() {
        double time = (double) this.getMovingTime().toSecondOfDay() / 60;
        double distance = (double) this.getDistance() / 1000;
        return time / distance;
    }

    /**
     * Converts the average pace of the run to the user-friendly format mm:ss.
     * @return average pace of run in mm:ss format
     */
    public String convertAveragePaceToString() {
        int totalSeconds = (int) Math.round(this.averagePace*60);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    /**
     * Returns a single line summary of the running activity.
     * @return a string representation of the run
     */
    @Override
    public String toString() {
        String result = super.toString();
        result = result.replace("[Activity]", "[Run]");
        String paceOutput = this.convertAveragePaceToString() + " /km";
        result = result.replace("Time: ", "Pace: " + paceOutput + " | Time: ");
        return result;
    }

    /**
     * Returns a string representation of the run used for storing the data.
     * @return a string representation of the run
     */
    @Override
    public String unparse() {
        String commandArgs = super.unparse();
        commandArgs = commandArgs.replace(Parameter.ACTIVITY_STORAGE_INDICATOR, Parameter.RUN_STORAGE_INDICATOR);
        commandArgs += " " + Parameter.ELEVATION_SEPARATOR + this.elevationGain;
        return commandArgs;
    }

    /**
     * Returns a detailed summary of the run.
     * @return a multiline string representation of the run
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String paceOutput = this.convertAveragePaceToString() + " /km";

        int columnWidth = getColumnWidth();

        String header = "[Run - " + this.getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, "Avg Pace: " + paceOutput,
                columnWidth);
        String secondRow = formatTwoColumns("\t" + movingTimeOutput, "Elevation Gain: " +
                elevationGain + " m", columnWidth);
        String thirdRow = formatTwoColumns("\tCalories: " + this.getCalories() + " kcal", "Steps: " +
                        this.steps, columnWidth);

        return String.join(System.lineSeparator(), header, firstRow, secondRow, thirdRow);
    }

    public int getElevationGain() {
        return elevationGain;
    }

    public void setElevationGain(int elevationGain) {
        this.elevationGain = elevationGain;
    }

    @Override
    public void setDistance(int distance) {
        super.setDistance(distance);
        this.averagePace = this.calculateAveragePace();
    }

    @Override
    public void setMovingTime(LocalTime movingTime) {
        super.setMovingTime(movingTime);
        this.averagePace = this.calculateAveragePace();
    }

}
