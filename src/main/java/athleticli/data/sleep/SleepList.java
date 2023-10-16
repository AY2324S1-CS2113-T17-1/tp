package athleticli.data.sleep;

import java.util.ArrayList;

/**
 * Represents a list of sleep records.
 */
public class SleepList extends ArrayList<Sleep> {
    /**
     * toString method for SleepList.
     * @return String representation of the sleep list.
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            output.append((i + 1) + ". " + this.get(i).toString() + "\n");
        }
        return output.toString();
    }
}
