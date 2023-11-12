package athleticli.parser;


public class Parameter {
    public static final String SPACE = " ";

    /*  For Sleep and Activity */
    public static final String START_TIME_SEPARATOR = "start/";
    public static final String END_TIME_SEPARATOR = "end/";

    /* For Acitivity */
    public static final String ACTIVITY_INDICATOR = "[Activity]";
    public static final String RUN_INDICATOR = "[Run]";
    public static final String CYCLE_INDICATOR = "[Cycle]";
    public static final String SWIM_INDICATOR = "[Swim]";
    public static final String SPORT_SEPARATOR = "sport/";
    public static final String TYPE_SEPARATOR = "type/";
    public static final String PERIOD_SEPARATOR = "period/";
    public static final String TARGET_SEPARATOR = "target/";
    public static final String DURATION_SEPARATOR = "duration/";
    public static final String CAPTION_SEPARATOR = "caption/";
    public static final String DISTANCE_SEPARATOR = "distance/";
    public static final String DATETIME_SEPARATOR = "datetime/";
    public static final String ELEVATION_SEPARATOR = "elevation/";
    public static final String SWIMMING_STYLE_SEPARATOR = "style/";
    public static final String ACTIVITY_STORAGE_INDICATOR = "[Activity]:";
    public static final String RUN_STORAGE_INDICATOR = "[Run]:";
    public static final String CYCLE_STORAGE_INDICATOR = "[Cycle]:";
    public static final String SWIM_STORAGE_INDICATOR = "[Swim]:";
    public static final String DETAIL_FLAG = "-d";
    public static final String DISTANCE_UNIT_METERS = " m";
    public static final String DISTANCE_UNIT_KILOMETERS = " km";
    public static final String SPEED_UNIT_KILOMETERS_PER_HOUR = " km/h";
    public static final String PACE_UNIT_TIME_PER_KILOMETER = " /km";
    public static final String TIME_UNIT_HOURS = "h";
    public static final String TIME_UNIT_MINUTES = "m";
    public static final String TIME_UNIT_SECONDS = "s";
    public static final String DISTANCE_PREFIX = "Distance: ";
    public static final String TIME_PREFIX = "Time: ";
    public static final String ELEVATION_PREFIX = "Elevation Gain: ";
    public static final String PACE_PREFIX = "Pace: ";
    public static final String LAPS_PREFIX = "Laps: ";
    public static final String STYLE_PREFIX = "Style: ";
    public static final String LAP_TIME_PREFIX = "Lap Time: ";
    public static final String AVG_LAP_TIME_PREFIX = "Avg. Lap Time: ";
    public static final String SPEED_PREFIX = "Speed: ";
    public static final String AVG_SPEED_PREFIX = "Avg. Speed: ";
    public static final String ACTIVITY_OVERVIEW_SEPARATOR = " | ";
    public static final int KILOMETER_IN_METERS = 1000;
    public static final int HOUR_IN_SECONDS = 3600;
    public static final int MINUTE_IN_SECONDS = 60;

    /* For Diet */
    public static final String CALORIES_SEPARATOR = "calories/";
    public static final String PROTEIN_SEPARATOR = "protein/";
    public static final String CARB_SEPARATOR = "carb/";
    public static final String FAT_SEPARATOR = "fat/";

    public static final String NUTRIENTS_CALORIES = "calories";
    public static final String NUTRIENTS_PROTEIN = "protein";
    public static final String NUTRIENTS_FATS = "fats";
    public static final String NUTRIENTS_CARB = "carb";
    public static final String UNHEALTHY_DIET_GOAL_FLAG = "unhealthy";
    public static final String DIET_GOAL_COMMAND_VALUE_SEPARATOR = "/";
    public static final String SPACE_SEPEARATOR = "\\s+";
    public static final int DIET_GOAL_TIME_SPAN_INDEX = 0;
    public static final int DIET_GOAL_UNHEALTHY_FLAG_INDEX = 1;
    public static final int HEALTHY_DIET_GOAL_NUTRIENT_STARTING_INDEX = 1;
    public static final int UNHEALTHY_DIET_GOAL_NUTRIENT_STARTING_INDEX = 2;
    public static final int DIET_GOAL_NUTRIENT_STARTING_INDEX = 0;
    public static final int DIET_GOAL_TARGET_VALUE_STARTING_INDEX = 1;
    public static final int DIET_GOAL_TIME_SPAN_LIMIT = 7;
}
