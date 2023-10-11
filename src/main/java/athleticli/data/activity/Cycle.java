package athleticli.data.activity;

import java.time.LocalDateTime;

public class Cycle extends Activity {

    private int elevationGain;
    private int averageSpeed;

    public Cycle(String caption, int movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averageSpeed = this.calculateAverageSpeed();
    }

    public int calculateAverageSpeed() {
        return (this.getDistance()/1000) / (this.getMovingTime()/60);
    }
}
