package athleticli.commands.activity;

import athleticli.data.activity.ActivityList;
import athleticli.data.activity.Run;
import athleticli.exceptions.EmptyArgumentException;
import athleticli.exceptions.UnknownCommandException;
import athleticli.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddActivityCommand {

    private String command;
    private String argument;

    private ActivityList activityList;
    private Ui ui;

    public enum ActivityType {
        ACTIVITY, RUN, CYCLE, SWIM
    }

    public AddActivityCommand(String command, String argument, ActivityList activityList, Ui ui) {
        ActivityType activityType = ActivityType.valueOf(command.toUpperCase());
        this.command = command;
        this.argument = argument;
        this.activityList = activityList;
        this.ui = ui;
    }

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

    public ActivityType getActivityType(String command) throws UnknownCommandException {
        try {
            return ActivityType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }
}
