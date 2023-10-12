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

    public int calculateAverageLapTime() {
        return this.getDistance() / this.getMovingTime();
    }

    public int calculateLaps() {
        return this.getDistance() / 50;
    }

}
