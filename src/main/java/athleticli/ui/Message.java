package athleticli.ui;

public class Message {
    public static final String PROMPT = "> ";
    public static final String LINE = "____________________________________________________________\n";
    public static final String PREFIX_MESSAGE = " ";
    public static final String PREFIX_EXCEPTION = "OOPS!!! ";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String[] MESSAGE_HELLO = {"Hello! I'm AthletiCLI!", "What can I do for you?"};
    public static final String MESSAGE_CAPTION_MISSING = "The caption of an activity cannot be empty!";
    public static final String MESSAGE_DURATION_MISSING =
            "Please specify the activity duration using \"duration/\"!";
    public static final String MESSAGE_DISTANCE_MISSING =
            "Please specify the activity duration using \"distance/\"!";
    public static final String MESSAGE_DATETIME_MISSING =
            "Please specify the activity duration using \"datetime/\"!";
    public static final String MESSAGE_CALORIES_MISSING =
            "Please specify the calories burned using \"calories/\"!";
    public static final String MESSAGE_PROTEIN_MISSING =
            "Please specify the protein intake using \"protein/\"!";
    public static final String MESSAGE_CARB_MISSING =
            "Please specify the carbohydrate intake using \"carbs/\"!";
    public static final String MESSAGE_FAT_MISSING = "Please specify the fat intake using \"fat/\"!";
    public static final String MESSAGE_CAPTION_EMPTY = "The caption of an activity cannot be empty!";
    public static final String MESSAGE_DURATION_EMPTY = "The duration of an activity cannot be empty!";
    public static final String MESSAGE_DISTANCE_EMPTY = "The distance of an activity cannot be empty!";
    public static final String MESSAGE_DATETIME_EMPTY = "The datetime of an activity cannot be empty!";
    public static final String MESSAGE_CALORIES_EMPTY = "The calories burned cannot be empty!";
    public static final String MESSAGE_PROTEIN_EMPTY = "The protein intake cannot be empty!";
    public static final String MESSAGE_CARB_EMPTY = "The carbohydrate intake cannot be empty!";
    public static final String MESSAGE_FAT_EMPTY = "The fat intake cannot be empty!";
    public static final String MESSAGE_DURATION_INVALID =
            "The duration of an activity must be a positive integer!";
    public static final String MESSAGE_DISTANCE_INVALID =
            "The distance of an activity must be a positive integer!";
    public static final String MESSAGE_DATETIME_INVALID =
            "The datetime of an activity must be in the format " + "\"yyyy-MM-dd HH:mm\"!";
    public static final String MESSAGE_CALORIES_INVALID =
            "The calories burned must be a non-negative integer!";
    public static final String MESSAGE_PROTEIN_INVALID = "The protein intake must be a non-negative integer!";
    public static final String MESSAGE_CARB_INVALID =
            "The carbohydrate intake must be a non-negative integer!";
    public static final String MESSAGE_FAT_INVALID = "The fat intake must be a non-negative integer!";
    public static final String MESSAGE_ACTIVITY_ADDED = "Well done! I've added this activity:";
    public static final String MESSAGE_DIET_ADDED = "Well done! I've added this diet:";
    public static final String MESSAGE_ELEVATION_MISSING =
            "Please specify the elevation gain using \"elevation/\"!";
    public static final String MESSAGE_ELEVATION_EMPTY =
            "The elevation gain of an activity cannot be empty!";
    public static final String MESSAGE_ELEVATION_INVALID =
            "The elevation gain of an activity must be an integer!";
    public static final String MESSAGE_SWIMMINGSTYLE_MISSING =
            "Please specify the swimming style using \"style/\"!";
    public static final String MESSAGE_SWIMMINGSTYLE_INVALID =
            "The swimming style of an activity must be one of " +
                    "the following: \"butterfly\", \"backstroke\", \"breaststroke\", \"freestyle\"!";
    public static final String MESSAGE_ACTIVITY_COUNT =
            "Now you have tracked a total of %d activities. Keep pushing!";
    public static final String MESSAGE_DIET_COUNT =
            "Now you have tracked a total of %d diets. Keep grinding!";
    public static final String MESSAGE_ACTIVITY_FIRST =
            "Now you have tracked your first activity. This is just the beginning!";

    public static final String MESSAGE_DIETGOAL_TARGET_VALUE_NOT_POSITIVE_INT = "The target value for nutrients " +
            "must be a positive integer!";
    public static final String MESSAGE_DIETGOAL_INVALID_NUTRIENT = "Key word to nutrients goals has to be one of the " +
            "following: \"calories\", \"protein\", \"carb\", \"fats\"!";
    public static final String MESSAGE_DIETGOAL_ALREADY_EXISTED = "Diet goal for %s has already existed. " +
            "Please edit the goal instead!";
    public static final String MESSAGE_DIETGOAL_NOT_EXISTED = "Diet goal for %s is not present. " +
            "Please add the goal before editing it!";
    public static final String MESSAGE_DIETGOAL_COUNT = "Now you have %d diet goal(s).";
    public static final String MESSAGE_DIETGOAL_NONE = "There are no goals at the moment. Add a diet goal to start.";
    public static final String MESSAGE_DIETGOAL_LIST_HEADER = "These are your goal(s):\n";

    public static final String MESSAGE_DIET_FIRST =
            "Now you have tracked your first diet. This is just the beginning!";
    public static final String MESSAGE_INVALID_DIET_INDEX =
            "The diet index is invalid! Please enter a valid diet index!";
    public static final String MESSAGE_DIET_INDEX_TYPE_INVALID = "The diet index must be an integer!";
    public static final String MESSAGE_DIET_DELETED = "Noted. I've removed this diet:";
    public static final String MESSAGE_DIET_LIST = "Here are the diets in your list:";

    
    public static final String MESSAGE_SLEEP_DELETE_INVALID_INDEX = "Invalid index. Please enter a valid index.";
    public static final String MESSAGE_SLEEP_DELETE_RETURN = "Got it. I've deleted this sleep record at index %d: %s";
    public static final String MESSAGE_SLEEP_EDIT_RETURN = "Got it. I've changed this sleep record at index %d:";
    public static final String MESSAGE_SLEEP_LIST = "Here are the sleep records in your list:\n";
    public static final String MESSAGE_SLEEP_LIST_EMPTY = "You have no sleep records in your list.";
    public static final String MESSAGE_SLEEP_ADD_RETURN_1 = "Got it. I've added this sleep record:";
    public static final String MESSAGE_SLEEP_ADD_RETURN_2 = "Now you have %d sleep records in the list.";

    public static final String ERRORMESSAGE_PARSER_SLEEP_INVALID_DATE_TIME_FORMAT = 
        "Invalid date-time format. Please use dd-MM-yyyy HH:mm.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME = 
        "Please specify both the start and end time of your sleep.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_END_BEFORE_START = 
        "Please specify the start time of your sleep before the end time.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_DELETE_NO_INDEX = 
        "Please specify the index of the sleep record you want to delete.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_EDIT_NO_INDEX = 
        "Please specify the index of the sleep record you want to edit.";
    public static final String ERRORMESSAGE_SLEEP_EDIT_INDEX_OOBE = 
        "The index of the sleep record you want to edit is out of bounds.";
    public static final String ERRORMESSAGE_SLEEP_DELETE_INDEX_OOBE =
        "The index of the sleep record you want to delete is out of bounds.";


    public static final String MESSAGE_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means :-(";
}
