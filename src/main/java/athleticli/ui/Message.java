package athleticli.ui;

public class Message {
    public static final String PROMPT = "> ";
    public static final String LINE = "____________________________________________________________\n";
    public static final String PREFIX_MESSAGE = " ";
    public static final String PREFIX_EXCEPTION = "OOPS!!! ";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String[] MESSAGE_HELLO = {"Hello! I'm AthletiCLI!", "What can I do for you?"};
    public static final String MESSAGE_CAPTION_MISSING = "The caption of an activity cannot be empty!";
    public static final String MESSAGE_DURATION_MISSING = "Please specify the activity duration using \"duration/\"!";
    public static final String MESSAGE_DISTANCE_MISSING = "Please specify the activity duration using \"distance/\"!";
    public static final String MESSAGE_DATETIME_MISSING = "Please specify the activity duration using \"datetime/\"!";
    public static final String MESSAGE_CAPTION_EMPTY = "The caption of an activity cannot be empty!";
    public static final String MESSAGE_DURATION_EMPTY = "The duration of an activity cannot be empty!";
    public static final String MESSAGE_DISTANCE_EMPTY = "The distance of an activity cannot be empty!";
    public static final String MESSAGE_DATETIME_EMPTY = "The datetime of an activity cannot be empty!";
    public static final String MESSAGE_DURATION_INVALID = "The duration of an activity must be a positive integer!";
    public static final String MESSAGE_DISTANCE_INVALID = "The distance of an activity must be a positive integer!";
    public static final String MESSAGE_DATETIME_INVALID = "The datetime of an activity must be in the format " +
            "\"yyyy-MM-dd HH:mm\"!";
    public static final String MESSAGE_ACTIVITY_ADDED = "Well done! I've added this activity:";
    public static final String MESSAGE_ELEVATION_MISSING = "Please specify the elevation gain using \"elevation/\"!";
    public static final String MESSAGE_ELEVATION_EMPTY = "The elevation gain of an activity cannot be empty!";
    public static final String MESSAGE_ELEVATION_INVALID = "The elevation gain of an activity must be an integer!";
    public static final String MESSAGE_SWIMMINGSTYLE_MISSING = "Please specify the swimming style using \"style/\"!";
    public static final String MESSAGE_SWIMMINGSTYLE_INVALID = "The swimming style of an activity must be one of " +
            "the following: \"butterfly\", \"backstroke\", \"breaststroke\", \"freestyle\"!";
    public static final String MESSAGE_ACTIVITY_COUNT = "Now you have tracked a total of %d activities. Keep pushing!";
    public static final String MESSAGE_ACTIVITY_FIRST = "Now you have tracked your first activity. This is just the " +
            "beginning!";
}
