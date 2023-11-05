package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a swimming activity consisting of relevant evaluation data.
 */
public class Swim extends Activity {
    private final int laps;
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
     * @param movingTime duration of the activity in HH:mm:ss format
     * @param distance distance covered in meters
     * @param startDateTime start date and time of the activity
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run")
     * @param style swimming style
     */
    public Swim(String caption, LocalTime movingTime, int distance, LocalDateTime startDateTime, SwimmingStyle style) {
        super(caption, movingTime, distance, startDateTime);
        this.laps = this.calculateLaps();
        this.style = style;
        this.averageLapTime = this.calculateAverageLapTime();
    }

    /**
     * Calculates the average lap time in seconds.
     * @return average lap time in seconds
     */
    public int calculateAverageLapTime() {
        return this.getMovingTime().toSecondOfDay() / this.laps;
    }

    /**
     * Calculates the number of laps.
     * @return number of laps
     */
    public int calculateLaps() {
        return this.getDistance() / 50;
    }

    public int getLaps() {
        return laps;
    }

    public int getAverageLapTime() {
        return averageLapTime;
    }

    /**
     * Returns a short string representation of the swim.
     * @return a string representation of the swim
     */
    @Override
    public String toString() {
        String result = super.toString();
        result = result.replace("[Activity]", "[Swim]");
        String averageLapTimeOutput = this.averageLapTime + "s";
        result = result.replace("Time: ", "Avg Lap Time: " + averageLapTimeOutput + " | Time: ");
        return result;
    }

    /**
     * Returns a detailed summary of the swim.
     * @return a multiline string representation of the swim
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();

        int columnWidth = getColumnWidth();
        String header = "[Swim - " + this.getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, movingTimeOutput, columnWidth);
        String secondRow = formatTwoColumns("\tLaps: " + this.getLaps(), "Style: "
                + this.getStyle(), columnWidth);
        String thirdRow = formatTwoColumns("\tAvg Lap Time: " + averageLapTime + " s", "Calories: " +
                this.getCalories() + " kcal", columnWidth);

        return String.join(System.lineSeparator(), header, firstRow, secondRow, thirdRow);
    }

    /**
     * Returns a string representation of the swim used for storing the data.
     * @return a string representation of the swim
     */
    @Override
    public String unparse() {
        String commandArgs = super.unparse();
        commandArgs = commandArgs.replace(Parameter.ACTIVITY_STORAGE_INDICATOR, Parameter.SWIM_STORAGE_INDICATOR);
        commandArgs += " " + Parameter.SWIMMING_STYLE_SEPARATOR + this.style;
        return commandArgs;
    }

    public SwimmingStyle getStyle() {
        return style;
    }

    public void setStyle(SwimmingStyle style) {
        this.style = style;
    }

    @Override
    public void setDistance(int distance) {
        super.setDistance(distance);
        this.averageLapTime = this.calculateAverageLapTime();
    }

    @Override
    public void setMovingTime(LocalTime movingTime) {
        super.setMovingTime(movingTime);
        this.averageLapTime = this.calculateAverageLapTime();
    }

}
