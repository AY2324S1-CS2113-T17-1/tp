package athleticli.data.activity;

import java.time.LocalDateTime;

public class Swim extends Activity {
    private final int laps;
    private final SwimmingStyle style;
    private final int averageLapTime;

    public enum SwimmingStyle {
        BUTTERFLY,
        BACKSTROKE,
        BREASTSTROKE,
        FREESTYLE
    }

    public Swim(String caption, int movingTime, int distance, LocalDateTime startDateTime, SwimmingStyle style) {
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
        int laps = this.calculateLaps();
        return this.getMovingTime() * 60 / laps;
    }

    public int calculateLaps() {
        return this.getDistance() / 50;
    }

    public int getLaps() {
        return laps;
    }

    public int getAverageLapTime() {
        return averageLapTime;
    }

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
        String firstRow = formatTwoColumns("\tDistance: " + distanceOutput, "Moving Time: " +
                movingTimeOutput, columnWidth);
        String secondRow = formatTwoColumns("\tAvg Lap Time: " + averageLapTime + " s", "Calories: " +
                this.getCalories() + " kcal", columnWidth);

        return String.join(System.lineSeparator(), header, firstRow, secondRow);
    }

}
