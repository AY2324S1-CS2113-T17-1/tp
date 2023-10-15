package athleticli.data.sleep;

public class Sleep {
    private String from;
    private String to;

    public Sleep(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "sleep from " + from + " to " + to;
    }
}
