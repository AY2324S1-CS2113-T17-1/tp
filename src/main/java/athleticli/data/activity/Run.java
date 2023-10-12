package athleticli.data.activity;

import java.time.LocalDateTime;

/**
 * Represents a running activity consisting of relevant evaluation data.
 */
public class Run extends Activity{
    private int elevationGain;
    private double averagePace;

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
    public Run(String caption, int movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averagePace = this.calculateAveragePace();
    }

    /**
     * Calculates the average pace of the run in minutes per km.
     * @return average pace of the run in minutes per km
     */
    public double calculateAveragePace() {
        double time = (double) this.getMovingTime();
        double distance = (double) this.getDistance()/1000;
        return time / distance;
    }

    /**
     * Converts the average pace of the run to the user-friendly format mm:ss.
     * @return average pace of run in mm:ss format
     */
    public String convertAveragePaceToString() {
        int minutes = (int) (this.averagePace / 60);
        int seconds = (int) (this.averagePace % 60);
        return String.format("%d:%02d", minutes, seconds);
    }

}
