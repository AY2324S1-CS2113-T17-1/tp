package athleticli.data.sleep;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class SleepList extends ArrayList<Sleep> {
        public String toString() {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < this.size(); i++) {
                output.append(i+1 + ". " + this.get(i).toString() + "\n");
            }
            return output.toString();
        }
}
