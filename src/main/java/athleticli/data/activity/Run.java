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

    public String convertAveragePaceToString() {
        int minutes = this.averagePace / 60;
        int seconds = this.averagePace % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

}
