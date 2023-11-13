package athleticli.ui;

import athleticli.parser.CommandName;

public class Message {
    public static final String PROMPT = "> ";
    public static final String LINE = "____________________________________________________________\n";
    public static final String PREFIX_MESSAGE = " ";
    public static final String PREFIX_EXCEPTION = "OOPS!!! ";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String[] MESSAGE_HELLO = {"Hello! I'm AthletiCLI!", "What can I do for you?"};
    public static final String MESSAGE_SAVE = "File saved successfully!";
    public static final String MESSAGE_DURATION_MISSING =
            "Please specify the activity duration using \"duration/\"!";
    public static final String MESSAGE_DISTANCE_MISSING =
            "Please specify the activity distance using \"distance/\"!";
    public static final String MESSAGE_DATETIME_MISSING =
            "Please specify date and time of the activity using \"datetime/\"!";
    public static final String MESSAGE_EMPTY_ACTIVITY_LIST = "You have not tracked any activities yet! Time to do " +
            "some sports!";
    public static final String MESSAGE_CALORIES_MISSING =
            "Please specify the calories burned using \"calories/\"!";
    public static final String MESSAGE_ACTIVITYGOAL_SPORT_MISSING = "Please specify the sport using \"sport/\"!";
    public static final String MESSAGE_ACTIVITYGOAL_TYPE_MISSING = "Please specify the goal type using \"type/\"!";
    public static final String MESSAGE_ACTIVITYGOAL_PERIOD_MISSING = "Please specify the period using \"period/\"!";
    public static final String MESSAGE_ACTIVITYGOAL_TARGET_MISSING = "Please specify the target value using " +
            "\"target/\"!";
    public static final String MESSAGE_PROTEIN_MISSING =
            "Please specify the protein intake using \"protein/\"! Use \"protein/0\" if no protein was consumed.";
    public static final String MESSAGE_CARB_MISSING =
            "Please specify the carbohydrate intake using \"carb/\"! Use \"carb/0\" if no carbohydrate was consumed.";
    public static final String MESSAGE_FAT_MISSING = "Please specify the fat intake using \"fat/\"! Use \"fat/0\" if " +
            "no fat was consumed.";
    public static final String MESSAGE_DIET_DATETIME_MISSING =
            "Please specify the datetime of the diet using \"datetime/\"!";
    public static final String MESSAGE_CALORIES_ARG_DUPLICATE =
            "Please do not specify the calories burned more than once!";
    public static final String MESSAGE_PROTEIN_ARG_DUPLICATE =
            "Please do not specify the protein intake more than once!";
    public static final String MESSAGE_CARB_ARG_DUPLICATE =
            "Please do not specify the carbohydrate intake more than once!";
    public static final String MESSAGE_FAT_ARG_DUPLICATE =
            "Please do not specify the fat intake more than once!";
    public static final String MESSAGE_DIET_ARG_DATETIME_DUPLICATE =
            "Please do not specify the datetime of the diet more than once!";
    public static final String MESSAGE_CALORIES_OVERFLOW =
            "The calories consumed cannot be larger than " + Integer.MAX_VALUE + "!";
    public static final String MESSAGE_PROTEIN_OVERFLOW =
            "The protein intake cannot be larger than " + Integer.MAX_VALUE + "!";
    public static final String MESSAGE_CARB_OVERFLOW =
            "The carbohydrate intake cannot be larger than " + Integer.MAX_VALUE + "!";
    public static final String MESSAGE_FAT_OVERFLOW =
            "The fat intake cannot be larger than " + Integer.MAX_VALUE + "!";
    public static final String MESSAGE_CAPTION_EMPTY = "The caption of an activity cannot be empty!";
    public static final String MESSAGE_DURATION_EMPTY = "The duration of an activity cannot be empty!";
    public static final String MESSAGE_DISTANCE_EMPTY = "The distance of an activity cannot be empty!";
    public static final String MESSAGE_DATETIME_EMPTY = "The datetime of an activity cannot be empty!";
    public static final String MESSAGE_CALORIES_EMPTY = "The calories burned cannot be empty!";
    public static final String MESSAGE_PROTEIN_EMPTY = "The protein intake cannot be empty!";
    public static final String MESSAGE_CARB_EMPTY = "The carbohydrate intake cannot be empty!";
    public static final String MESSAGE_FAT_EMPTY = "The fat intake cannot be empty!";
    public static final String MESSAGE_DIET_DATETIME_EMPTY = "The datetime of a diet cannot be empty!";
    public static final String MESSAGE_DIET_UPDATED = "Ok, I've updated this diet:";
    public static final String MESSAGE_DURATION_INVALID =
            "The duration of an activity must be in the format \"HH:mm:ss\"!";
    public static final String MESSAGE_DISTANCE_INVALID =
            "The distance of an activity must be a positive integer!";
    public static final String MESSAGE_DISTANCE_NEGATIVE =
            "The distance of an activity cannot be negative!";
    public static final String MESSAGE_TARGET_NEGATIVE = "The target value cannot be negative. " +
            "You wanna make progress, not regress ;)";
    public static final String MESSAGE_TARGET_INVALID = "The target value of an activity goal must be a positive " +
            "integer!";
    public static final String MESSAGE_TARGET_TOO_LARGE =
            "The target value of an activity goal cannot be larger than " + Integer.MAX_VALUE + "!";
    public static final String MESSAGE_DATETIME_INVALID =
            "The datetime must be valid and in the format \"yyyy-MM-dd HH:mm\"!";
    public static final String MESSAGE_DATE_INVALID =
            "The date must be valid and in the format \"yyyy-MM-dd\"!";
    public static final String MESSAGE_CALORIES_INVALID =
            "The calories burned must be a non-negative integer!";
    public static final String MESSAGE_SPORT_INVALID = "The sport of an activity must be one of the following: " +
            "\"running\", \"cycling\", \"swimming\", \"general\"!";
    public static final String MESSAGE_TYPE_INVALID = "The type of an activity must be either \"distance\" or " +
            "\"duration\"!";
    public static final String MESSAGE_PERIOD_INVALID = "The period of an activity must be one of the " +
            "following: \"daily\", \"weekly\", \"monthly\", \"yearly\"!";
    public static final String MESSAGE_PROTEIN_INVALID = "The protein intake must be a non-negative integer!";
    public static final String MESSAGE_CARB_INVALID =
            "The carbohydrate intake must be a non-negative integer!";
    public static final String MESSAGE_FAT_INVALID = "The fat intake must be a non-negative integer!";
    public static final String MESSAGE_ACTIVITY_FIND = "I've found these activities:";
    public static final String MESSAGE_ACTIVITY_ADDED = "Well done! I've added this activity:";
    public static final String MESSAGE_ACTIVITY_DELETED = "Gotcha, I've deleted this activity:";
    public static final String MESSAGE_ACTIVITY_GOAL_ADDED = "Alright, I've added this activity goal:";
    public static final String MESSAGE_ACTIVITY_GOAL_DELETED = "Alright, I've deleted this activity goal:";
    public static final String MESSAGE_ACTIVITY_GOAL_EDITED = "Alright, I've edited this activity goal:";
    public static final String MESSAGE_NO_SUCH_GOAL_EXISTS = "No such goal exists.";
    public static final String MESSAGE_ACTIVITY_GOAL_LIST = "These are your activity goals:";
    public static final String MESSAGE_DIET_ADDED = "Well done! I've added this diet:";
    public static final String MESSAGE_ELEVATION_MISSING =
            "Please specify the elevation gain using \"elevation/\"!";
    public static final String MESSAGE_ELEVATION_EMPTY =
            "The elevation gain of an activity cannot be empty!";
    public static final String MESSAGE_ELEVATION_INVALID =
            "The elevation gain of an activity must be an integer!";
    public static final String MESSAGE_ACTIVITY_INDEX_INVALID = "The activity index must be an integer!";
    public static final String MESSAGE_ACTIVITY_INDEX_OUT_OF_BOUNDS = "The activity index does not exist, check your " +
            "list for the correct index!";
    public static final String MESSAGE_SWIMMINGSTYLE_MISSING =
            "Please specify the swimming style using \"style/\"!";
    public static final String MESSAGE_SWIMMINGSTYLE_INVALID =
            "The swimming style of an activity must be one of " +
                    "the following: \"butterfly\", \"backstroke\", \"breaststroke\", \"freestyle\"!";
    public static final String MESSAGE_ACTIVITY_COUNT =
            "You have tracked a total of %d activities. Keep pushing!";
    public static final String MESSAGE_ACTIVITY_LIST = "These are the activities you have tracked so far:";
    public static final String MESSAGE_ACTIVITY_EDIT_INVALID = "The format of the edit command is wrong! Please" +
            " provide the index and the updated parameters!";
    public static final String MESSAGE_ACTIVITY_UPDATED = "Ok, I've updated this activity:";
    public static final String MESSAGE_DIET_COUNT =
            "Now you have tracked a total of %d diets. Keep grinding!";
    public static final String MESSAGE_ACTIVITY_FIRST =
            "Now you have tracked your first activity. This is just the beginning!";
    public static final String MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_POSITIVE_INT = "The target value for nutrients " +
            "must be a positive integer!";
    public static final String MESSAGE_DIET_GOAL_INVALID_NUTRIENT = "Key word to nutrients goals has " +
            "to be one of the following: \"calories\", \"protein\", \"carb\", \"fats\"!";
    public static final String MESSAGE_DIET_GOAL_ALREADY_EXISTED = "Diet goal for %s has already existed. " +
            "Please edit the goal instead!";
    public static final String MESSAGE_DIET_GOAL_NOT_EXISTED = "Diet goal for %s and time period %s is not present. " +
            "Please add the goal before editing it!";
    public static final String MESSAGE_DIET_GOAL_COUNT = "Now you have %d diet goal(s).";
    public static final String MESSAGE_DIET_GOAL_NONE = "There are no goals at the moment. Add a diet goal to start.";
    public static final String MESSAGE_DIET_GOAL_LIST_HEADER = "These are your goal(s):\n";
    public static final String MESSAGE_DIET_GOAL_INCORRECT_INTEGER_FORMAT = "Please provide a positive integer.";
    public static final String MESSAGE_DIET_GOAL_EMPTY_DIET_GOAL_LIST = "There is no diet goals at the moment. " +
            "Please add one to continue.\n";
    public static final String MESSAGE_DIET_GOAL_DELETE_HEADER = "The following goal has been deleted:\n";
    public static final String MESSAGE_DIET_GOAL_OUT_OF_BOUND = "Unable to fetch diet goal. " +
            "Please enter a value from 1 to %d.";
    public static final String MESSAGE_DIET_GOAL_INSUFFICIENT_INPUT = "Please input the following keywords " +
            "to create or edit your diet goals:\n <DAILY/WEEKLY> [unhealthy] followed by \"calories\", \"protein\", " +
            "\"carb\", \"fats\" and then followed by the target value.\n" + "\te.g. WEEKLY calories/100\n"
            + "\te.g. WEEKLY unhealthy fats/100";
    public static final String MESSAGE_DIET_GOAL_TARGET_VALUE_NOT_SCALING_WITH_TIME_SPAN =
            "Please ensure your weekly diet goal target value is greater than your daily diet goal target value!";
    public static final String MESSAGE_DIET_GOAL_REPEATED_NUTRIENT = "Please ensure that there are " +
            "no repetitions for your diet goal nutrients.";
    public static final String MESSAGE_DIET_GOAL_LOAD_ERROR = "Some error has been encountered " +
            "while loading diet goals.";
    public static final String MESSAGE_DIET_GOAL_TYPE_CLASH = "You cannot have healthy goals and unhealthy goals "
            + "for the same nutrient.";
    public static final String MESSAGE_DIET_GOAL_PERIOD_INVALID = "The period of an activity must be one of the "
            + "following: \"daily\", \"weekly\"!";

    public static final String MESSAGE_DIET_FIRST =
            "Now you have tracked your first diet. This is just the beginning!";
    public static final String MESSAGE_INVALID_DIET_INDEX =
            "The diet index is invalid! Please enter a valid diet index!";
    public static final String MESSAGE_DIET_INDEX_TYPE_INVALID = "The diet index must be a positive integer!";
    public static final String MESSAGE_DIET_DELETED = "Noted. I've removed this diet:";
    public static final String MESSAGE_DIET_LIST = "Here are the diets in your list:";
    public static final String MESSAGE_DIET_FIND = "I've found these diets:";
    public static final String MESSAGE_DIET_NO_CHANGE_REQUESTED = "No change requested. Specify the appropriate " +
            "parameters to edit the diet.";


    /* Sleep Messages */
    public static final String MESSAGE_SLEEP_COUNT = "You have tracked a total of %d sleep records. Keep it up!";
    public static final String MESSAGE_SLEEP_FIRST = "You have tracked your first sleep record. This is just the " +
            "beginning!";

    public static final String MESSAGE_SLEEP_ADDED = "Well done! I've added this sleep record:";

    public static final String MESSAGE_SLEEP_EDITED = "Alright, I've changed this sleep record:";

    public static final String MESSAGE_SLEEP_DELETED = "Gotcha, I've deleted this sleep record:";

    public static final String MESSAGE_SLEEP_LIST = "Here are the sleep records in your list:\n";
    public static final String MESSAGE_SLEEP_LIST_EMPTY = "You have no sleep records in your list.";

    public static final String MESSAGE_SLEEP_FIND = "I've found these sleeps:";

    public static final String MESSAGE_SLEEP_GOAL_ADDED = "Alright, I've added this sleep goal:";
    public static final String MESSAGE_SLEEP_GOAL_EDITED = "Alright, I've edited this sleep goal:";
    public static final String MESSAGE_SLEEP_GOAL_LIST = "These are your sleep goals:";

    /* Sleep Error Messages */
    public static final String ERRORMESSAGE_SLEEP_EDIT_INDEX_OOBE =
            "The index of the sleep record you want to edit is out of bounds.";
    public static final String ERRORMESSAGE_SLEEP_DELETE_INDEX_OOBE =
            "The index of the sleep record you want to delete is out of bounds.";

    public static final String ERRORMESSAGE_SLEEP_OVERLAP =
            "The sleep record you are trying to input overlaps with an existing sleep record.";
    public static final String ERRORMESSAGE_DUPLICATE_SLEEP_GOAL =
            "You already have a goal for this type and period! Please edit the existing goal instead.";

    public static final String ERRORMESSAGE_PARSER_SLEEP_NO_START_END_DATETIME =
            "Please specify both the start and end time of your sleep.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_START_END_NON_CHRONOLOGICAL =
            "Please specify the start time of your sleep chronologically before the end time.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_INVALID_START_END_ORDER =
            "Please specify the /start before /end.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_INVALID_DATETIME =
            "Please specify the start and end time of your sleep in the format \"yyyy-MM-dd HH:mm\".";

    public static final String ERRORMESSAGE_PARSER_SLEEP_NO_INDEX =
            "Please specify the index of the sleep record";
    public static final String ERRORMESSAGE_PARSER_SLEEP_INVALID_INDEX =
            "Please specify the index of the sleep record as a positive integer.";

    public static final String ERRORMESSAGE_PARSER_SLEEP_GOAL_MISSING_PARAMETERS =
            "Please specify the type, period and target value of your sleep goal.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_PARAMETERS_ORDER =
            "Please specify the type, period and target value of your sleep goal in the correct order.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TYPE =
            "Please specify the type of your sleep goal as \"duration\".";
    public static final String ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_PERIOD =
            "The period must be one of the " +
                    "following: \"daily\", \"weekly\", \"monthly\", \"yearly\"!";
    public static final String ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_TARGET =
            "Please specify the target value of your sleep goal as a positive integer.";
    public static final String ERRORMESSAGE_PARSER_SLEEP_GOAL_INVALID_PARAMETERS =
            "Please specify the type, period and target value of your sleep goal.";

    public static final String MESSAGE_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_IO_EXCEPTION = "An I/O exception occurred.";
    public static final String MESSAGE_LOAD_EXCEPTION = "An exception occurred when loading %s.\n"
            + "Please quit AthletiCLI with `bye` command and fix it manually,\n"
            + "or start from empty lists by entering any other command.";

    /* Help Messages */
    public static final String HELP_ADD_ACTIVITY = CommandName.COMMAND_ACTIVITY
            + " CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME";
    public static final String HELP_ADD_RUN = CommandName.COMMAND_RUN
            + " CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION";
    public static final String HELP_ADD_SWIM = CommandName.COMMAND_SWIM
            + " CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME style/STYLE";
    public static final String HELP_ADD_CYCLE = CommandName.COMMAND_CYCLE
            + " CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION";
    public static final String HELP_DELETE_ACTIVITY = CommandName.COMMAND_ACTIVITY_DELETE
            + " INDEX";
    public static final String HELP_LIST_ACTIVITY = CommandName.COMMAND_ACTIVITY_LIST
            + " [-d]";
    public static final String HELP_EDIT_ACTIVITY = CommandName.COMMAND_ACTIVITY_EDIT
            + " INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME]";
    public static final String HELP_EDIT_RUN = CommandName.COMMAND_RUN_EDIT
            + " INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] " +
            "[elevation/ELEVATION]";
    public static final String HELP_EDIT_SWIM = CommandName.COMMAND_SWIM_EDIT
            + " INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] [style/STYLE]";
    public static final String HELP_EDIT_CYCLE = CommandName.COMMAND_CYCLE_EDIT
            + " INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] " +
            "[elevation/ELEVATION]";
    public static final String HELP_FIND_ACTIVITY = CommandName.COMMAND_ACTIVITY_FIND
            + " DATE";
    public static final String HELP_SET_ACTIVITY_GOAL = CommandName.COMMAND_ACTIVITY_GOAL_SET
            + " sport/SPORT type/TYPE period/PERIOD target/TARGET";
    public static final String HELP_EDIT_ACTIVITY_GOAL = CommandName.COMMAND_ACTIVITY_GOAL_EDIT
            + " sport/SPORT type/TYPE period/PERIOD target/TARGET";
    public static final String HELP_LIST_ACTIVITY_GOAL = CommandName.COMMAND_ACTIVITY_GOAL_LIST;
    public static final String HELP_DELETE_ACTIVITY_GOAL = CommandName.COMMAND_ACTIVITY_GOAL_DELETE
            + " sport/SPORT type/TYPE period/PERIOD";
    public static final String HELP_ADD_DIET = CommandName.COMMAND_DIET_ADD
            + " calories/CALORIES protein/PROTEIN carb/CARB fat/FAT datetime/DATETIME";
    public static final String HELP_EDIT_DIET = CommandName.COMMAND_DIET_EDIT
            + " INDEX [calories/CALORIES] [protein/PROTEIN] [carb/CARB] [fat/FAT] [datetime/DATETIME]";
    public static final String HELP_DELETE_DIET = CommandName.COMMAND_DIET_DELETE
            + " INDEX";
    public static final String HELP_LIST_DIET = CommandName.COMMAND_DIET_LIST;
    public static final String HELP_FIND_DIET = CommandName.COMMAND_DIET_FIND
            + " DATE";
    public static final String HELP_SET_DIET_GOAL = CommandName.COMMAND_DIET_GOAL_SET
            + " <DAILY/WEEKLY> [unhealthy] [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fats/FATS]";
    public static final String HELP_EDIT_DIET_GOAL = CommandName.COMMAND_DIET_GOAL_EDIT
            + " <DAILIY/WEEKLY> [unhealthy] [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fats/FATS]";
    public static final String HELP_LIST_DIET_GOAL = CommandName.COMMAND_DIET_GOAL_LIST;
    public static final String HELP_DELETE_DIET_GOAL = CommandName.COMMAND_DIET_GOAL_DELETE
            + " INDEX";
    public static final String HELP_ADD_SLEEP = CommandName.COMMAND_SLEEP_ADD
            + " start/START end/END";
    public static final String HELP_LIST_SLEEP = CommandName.COMMAND_SLEEP_LIST;
    public static final String HELP_DELETE_SLEEP = CommandName.COMMAND_SLEEP_DELETE
            + " INDEX";
    public static final String HELP_EDIT_SLEEP = CommandName.COMMAND_SLEEP_EDIT
            + " INDEX start/START end/END";
    public static final String HELP_FIND_SLEEP = CommandName.COMMAND_SLEEP_FIND
            + " DATE";
    
    public static final String HELP_SET_SLEEP_GOAL = CommandName.COMMAND_SLEEP_GOAL_SET
            + " type/TYPE period/PERIOD target/TARGET";
    public static final String HELP_EDIT_SLEEP_GOAL = CommandName.COMMAND_SLEEP_GOAL_EDIT
            + " type/TYPE period/PERIOD target/TARGET";
    public static final String HELP_LIST_SLEEP_GOAL = CommandName.COMMAND_SLEEP_GOAL_LIST;

    public static final String HELP_SAVE = CommandName.COMMAND_SAVE;
    public static final String HELP_BYE = CommandName.COMMAND_BYE;
    public static final String HELP_HELP = CommandName.COMMAND_HELP
            + " [COMMAND]";
    public static final String HELP_FIND = CommandName.COMMAND_FIND
            + " DATE";
    public static final String HELP_DETAILS =
            "Please check our user guide (https://ay2324s1-cs2113-t17-1.github.io/tp/) for details.";
    public static final String ACTIVITY_STORAGE_INVALID_INDICATOR = "Invalid activity indicator, file corrupted.";
    public static final String ACTIVITY_STORAGE_INVALID_FORMAT = "Invalid activity format, file corrupted.";
    public static final String MESSAGE_ACTIVITY_EDIT_EMPTY = "You have not specified any changes to the activity.";
    public static final String MESSAGE_SWIMMINGSTYLE_EMPTY = "The swimming style of an activity cannot be empty!";
    public static final String MESSAGE_ACTIVITY_INDEX_EMPTY = "The activity index cannot be empty!";
    public static final String MESSAGE_ACTIVITY_ORDER_INVALID = "The order of the parameters is wrong, please refer " +
            "to the User Guide for the correct order.";
    public static final String MESSAGE_ACTIVITY_LIST_END = "\nTo see more performance details about an activity, use " +
            "the -d flag";
    public static final String MESSAGE_DISTANCE_TOO_LARGE = "The distance of an activity cannot be larger than " +
            "1000km! You are not Forrest Gump!";
    public static final String MESSAGE_ELEVATION_TOO_LARGE = "The elevation of an activity cannot be larger than " +
            "10km! Mt. Everest is only 8.8km high!";
    public static final String MESSAGE_DUPLICATE_ACTIVITY_GOAL = "You already have a goal for this " +
            "sport, type and period! Please edit the existing goal instead.";
    public static final String MESSAGE_ACTIVITY_TYPE_MISMATCH = "The edit command does not match the type of " +
            "the activity you are trying to edit!";
    public static final String MESSAGE_DATE_FUTURE =
            "I like your optimism, but you cannot track events in the future!";
}
