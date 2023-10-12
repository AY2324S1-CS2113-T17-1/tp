package athleticli.commands.activity;

import athleticli.data.activity.ActivityList;
import athleticli.data.activity.Run;
import athleticli.exceptions.EmptyArgumentException;
import athleticli.exceptions.UnknownCommandException;
import athleticli.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Executes the add activity commands provided by the user.
 */
public class AddActivityCommand {

    private String command;
    private String argument;

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
    public AddActivityCommand(String command, String argument, ActivityList activityList, Ui ui) {
        ActivityType activityType = ActivityType.valueOf(command.toUpperCase());
        this.command = command;
        this.argument = argument;
        this.activityList = activityList;
        this.ui = ui;
    }

    /**
     * Executes the given command and updates the activity list.
     * In case of formatting issues or invalid commands, user will be informed.
     * @return ActivityList List of activities with the applied modifications.
     */
    public ActivityList execute() {
        try {
            ActivityType activityType = getActivityType(command);
            switch(activityType) {
            case ACTIVITY:
                activityList = addActivity();
                break;
            case CYCLE:
            case RUN:
            case SWIM:
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | EmptyArgumentException e) {
            this.ui.showException(e);
        }
        return activityList;
    }


    /**
     * Adds a general activity to the activity list.
     * @return ActivityList List of activities with the added activity.
     * @throws UnknownCommandException If the command is not valid.
     * @throws EmptyArgumentException If the provided argument is empty.
     * */
    public ActivityList addActivity() throws UnknownCommandException, EmptyArgumentException {
        if (this.argument == null || this.argument.isEmpty()) {
            throw new EmptyArgumentException();
        }
        try {
            ArrayList<String> separators = new ArrayList<String>();
            separators.add(" duration/");
            separators.add(" distance/");
            separators.add(" datetime/");
            separators.add(" elevation/");

            ArrayList<String> params = new ArrayList<String>();
            for (String separator : separators) {
                params.add(this.argument.split(separator)[0]);
                this.argument = this.argument.split(separator)[1];
            }

            int elevationGain = Integer.parseInt(this.argument);
            String caption = params.get(0);
            int duration = Integer.parseInt(params.get(1));
            int distance = Integer.parseInt(params.get(2));
            LocalDateTime datetime = LocalDateTime.parse(params.get(3).replace(" ", "T"));
            this.activityList.add(new Run(caption, duration, distance, datetime, elevationGain));

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
}
