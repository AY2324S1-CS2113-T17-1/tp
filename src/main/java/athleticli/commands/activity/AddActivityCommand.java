package athleticli.commands.activity;

import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityList;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.EmptyArgumentException;
import athleticli.exceptions.UnknownCommandException;
import athleticli.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Executes the add activity commands provided by the user.
 */
public class AddActivityCommand {

    private String argument;
    private ActivityType activityType;

    private ActivityList activityList;
    private Ui ui;

    public enum ActivityType {
        ACTIVITY, RUN, CYCLE, SWIM
    }

    /**
     * Constructor of Add Activity Command.
     * @param command Command specifying the type of activity to be added.
     * @param argument Arguments required for the specific command.
     * */
    public AddActivityCommand(String command, String argument, ActivityList activityList, Ui ui){
        try {
            this.activityType = ActivityType.valueOf(command.toUpperCase());
            this.argument = argument;
            this.activityList = activityList;
            this.ui = ui;
        } catch (IllegalArgumentException e) {
            ui.showException(new UnknownCommandException());
        }
    }

    /**
     * Executes the given command and updates the activity list.
     * In case of formatting issues or invalid commands, user will be informed.
     * @return ActivityList List of activities with the applied modifications.
     */
    public ActivityList execute() {
        try {
            checkEmptyArgument();

            switch(this.activityType) {
            case ACTIVITY:
                addActivity();
                break;
            case CYCLE:
            case RUN:
                addRunCycle();
                break;
            case SWIM:
                addSwim();
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | EmptyArgumentException e) {
            this.ui.showException(e);
        }
        return activityList;
    }


    /**
     * Adds a running activity to the activity list.
     * @return ActivityList List of activities with the added run.
     * @throws UnknownCommandException If the command is not valid.
     * @throws EmptyArgumentException If the provided argument is empty.
     * */
    public ActivityList addRunCycle() throws UnknownCommandException, EmptyArgumentException {
        try {
            ArrayList<String> separators = new ArrayList<String>(Arrays.asList(" duration/", " distance/", " datetime/", " elevation/"));
            ArrayList<String> params = extractParameters(separators);

            String caption = params.get(0);
            int duration = Integer.parseInt(params.get(1));
            int distance = Integer.parseInt(params.get(2));
            LocalDateTime datetime = LocalDateTime.parse(params.get(3).replace(" ", "T"));
            int elevationGain = Integer.parseInt(params.get(4));

            this.activityList.add(new Run(caption, duration, distance, datetime, elevationGain));

            return this.activityList;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException |
                 DateTimeParseException e) {
            throw new UnknownCommandException();
        }
    }

    /**
     * Adds a general activity to the activity list.
     * @return ActivityList List of activities with the added activity.
     * @throws UnknownCommandException If the command is not valid.
     * @throws EmptyArgumentException If the provided argument is empty.
     * */
    public ActivityList addActivity() throws UnknownCommandException, EmptyArgumentException {
        try {
            ArrayList<String> separators = new ArrayList<String>();
            separators.addAll(Arrays.asList(" duration/", " distance/", " datetime/"));
            ArrayList<String> params = extractParameters(separators);

            String caption = params.get(0);
            int duration = Integer.parseInt(params.get(1));
            int distance = Integer.parseInt(params.get(2));
            LocalDateTime datetime = LocalDateTime.parse(params.get(3).replace(" ", "T"));

            this.activityList.add(new Activity(caption, duration, distance, datetime));

            return this.activityList;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException |
                 DateTimeParseException e) {
            throw new UnknownCommandException();
        }
    }

    /**
     * Translates the raw command into a value of ActivityType enum.
     * @param command
     * @return command in the form of ActivityType enum.
     * @throws UnknownCommandException If the command is not valid.
     */
    public ActivityType getActivityType(String command) throws UnknownCommandException {
        try {
            return ActivityType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

    /**
     * Extracts the different parameters from the argument string.
     * @param separators List of separators, i.e. the specific formatting, used to split the argument string.
     * @return List of parameters.
     */
    private ArrayList<String> extractParameters(ArrayList<String> separators) throws ArrayIndexOutOfBoundsException {
        ArrayList<String> params = new ArrayList<String>();
        for (String separator : separators) {
            params.add(this.argument.split(separator)[0]);
            this.argument = this.argument.split(separator)[1];
        }
        params.add(this.argument);
        return params;
    }

    /**
     * Adds a swimming activity to the activity list.
     * @return ActivityList List of activities with the added swim.
     * @throws UnknownCommandException If the command is not valid.
     * @throws EmptyArgumentException If the provided argument is empty.
     * */
    public ActivityList addSwim() throws UnknownCommandException, EmptyArgumentException {
        try {
            ArrayList<String> separators = new ArrayList<String>(Arrays.asList(" duration/", " distance/", " datetime/", " style/"));
            ArrayList<String> params = extractParameters(separators);

            String caption = params.get(0);
            int duration = Integer.parseInt(params.get(1));
            int distance = Integer.parseInt(params.get(2));
            LocalDateTime datetime = LocalDateTime.parse(params.get(3).replace(" ", "T"));
            Swim.SwimmingStyle style = Swim.SwimmingStyle.valueOf(params.get(4));
            this.activityList.add(new Swim(caption, duration, distance, datetime, style));

            return this.activityList;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException |
                 DateTimeParseException e) {
            throw new UnknownCommandException();
        }
    }

    private void checkEmptyArgument() throws EmptyArgumentException {
        if (this.argument == null || this.argument.isEmpty()) {
            throw new EmptyArgumentException();
        }
    }
}
