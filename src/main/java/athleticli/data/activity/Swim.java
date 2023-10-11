package athleticli.data.activity;

import java.time.LocalDateTime;

public class Swim extends Activity {
    private int laps;
    private SwimmingStyle style;

    public enum SwimmingStyle {
        BUTTERFLY,
        BACKSTROKE,
        BREASTSTROKE,
        FREESTYLE
    }

    public Swim(String caption, int movingTime, int distance, LocalDateTime startDateTime, int laps, SwimmingStyle style) {
        super(caption, movingTime, distance, startDateTime);
        this.laps = laps;
        this.style = style;
    }

}
