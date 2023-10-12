package athleticli.data.activity;

import java.time.LocalDateTime;

/**
 * Represents a cycling activity consisting of relevant evaluation data.
 */
public class Cycle extends Activity {

    private int elevationGain;
    private float averageSpeed;

    /**
     * Generates a new cycling activity with cycling specific stats.
     * By default, calories is 0, i.e., not tracked.
     * averageSpeed is calculated automatically based on the distance and movingTime.
     * @param movingTime duration of the activity in minutes
     * @param distance distance covered in meters
     * @param startDateTime start date and time of the activity
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run")
     * @param elevationGain elevation gain in meters
     */
    public Cycle(String caption, int movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averageSpeed = this.calculateAverageSpeed();
    }

    /**
     * Calculates the average speed of the cycle in km/h.
     * @return average speed of the cycle in km/H
     */
    public float calculateAverageSpeed() {
        float dist = (float) this.getDistance();
        float time = (float) this.getMovingTime();
        return (dist/1000) / (time/60);
    }
}
