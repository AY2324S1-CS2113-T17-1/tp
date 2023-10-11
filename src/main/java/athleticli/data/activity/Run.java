package athleticli.data.activity;

import java.time.LocalDateTime;

public class Run extends Activity{
    private int elevationGain;
    private int averagePace;

    public Run(String caption, int movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averagePace = this.calculateAveragePace();
    }

    public int calculateAveragePace() {
        return this.getMovingTime() / (this.getDistance()/1000);
    }

}
