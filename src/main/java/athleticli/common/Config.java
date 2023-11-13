package athleticli.common;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static java.util.Locale.ENGLISH;

/**
 * Defines string literals or configurations used for file storage.
 */
public class Config {
    public static final DateTimeFormatter DATE_TIME_PRETTY_FORMATTER =
            DateTimeFormatter.ofPattern("MMMM d, " + "yyyy 'at' h:mm a", ENGLISH);
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT)
                    .withLocale(ENGLISH);
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT)
                    .withLocale(ENGLISH);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss", ENGLISH);
    public static final String PATH_ACTIVITY = "./data/activity.txt";
    public static final String PATH_ACTIVITY_GOAL = "./data/activity_goal.txt";
    public static final String PATH_SLEEP = "./data/sleep.txt";
    public static final String PATH_SLEEP_GOAL = "./data/sleep_goal.txt";
    public static final String PATH_DIET = "./data/diet.txt";
    public static final String PATH_DIET_GOAL = "./data/diet_goal.txt";
}
