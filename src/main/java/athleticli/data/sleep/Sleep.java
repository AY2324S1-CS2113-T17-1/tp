package athleticli.data.sleep;
/**
 * Represents a sleep record.
 */
public class Sleep {
    private String from;
    private String to;

    /**
     * Constructor for Sleep.
     * @param from Start time of the sleep.
     * @param to End time of the sleep.
     */
    public Sleep(String from, String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * toString method for Sleep.
     * @return String representation of the sleep record.
     */
    public String toString() {
        return "sleep from " + from + " to " + to;
    }
}
