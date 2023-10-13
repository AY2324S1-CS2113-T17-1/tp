package athleticli.data.activity;

import java.time.LocalDateTime;

public class Swim extends Activity {
    private int laps;
    private SwimmingStyle style;
    private int averageLapTime;

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
        return this.getMovingTime()*60 / laps;
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

}
