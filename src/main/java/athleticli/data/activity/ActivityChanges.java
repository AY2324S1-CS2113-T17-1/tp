package athleticli.data.activity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import athleticli.data.activity.Swim.SwimmingStyle;

/**
 * Represents an object that tracks changes to an activity.
 */
public class ActivityChanges {
    private String caption;
    private int distance;
    private LocalTime duration;
    private LocalDateTime startDateTime;
    private int elevation;
    private SwimmingStyle swimmingStyle;

    /**
     * Constructor for ActivityChanges.
     */
    public ActivityChanges() {

    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public SwimmingStyle getSwimmingStyle() {
        return swimmingStyle;
    }

    public void setSwimmingStyle(SwimmingStyle swimmingStyle) {
        this.swimmingStyle = swimmingStyle;
    }
}
