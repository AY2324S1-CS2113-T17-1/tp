package athleticli.parser;

import athleticli.commands.ByeCommand;
import athleticli.commands.Command;
import athleticli.commands.FindCommand;
import athleticli.commands.HelpCommand;
import athleticli.commands.SaveCommand;
import athleticli.commands.diet.AddDietCommand;
import athleticli.commands.diet.DeleteDietCommand;
import athleticli.commands.diet.DeleteDietGoalCommand;
import athleticli.commands.diet.EditDietCommand;
import athleticli.commands.diet.EditDietGoalCommand;
import athleticli.commands.diet.FindDietCommand;
import athleticli.commands.diet.ListDietCommand;
import athleticli.commands.diet.ListDietGoalCommand;
import athleticli.commands.diet.SetDietGoalCommand;
import athleticli.commands.sleep.FindSleepCommand;
import athleticli.commands.sleep.ListSleepCommand;
import athleticli.commands.sleep.SetSleepGoalCommand;
import athleticli.commands.sleep.EditSleepGoalCommand;
import athleticli.commands.sleep.ListSleepGoalCommand;

import athleticli.commands.activity.AddActivityCommand;
import athleticli.commands.activity.DeleteActivityCommand;
import athleticli.commands.activity.EditActivityCommand;
import athleticli.commands.activity.FindActivityCommand;
import athleticli.commands.activity.ListActivityCommand;
import athleticli.commands.activity.SetActivityGoalCommand;
import athleticli.commands.activity.EditActivityGoalCommand;
import athleticli.commands.activity.ListActivityGoalCommand;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Defines the basic methods for command parser.
 */
public class Parser {
    /**
     * Splits the raw user input into two parts, and then returns them. The first part is the command type,
     * while the second part is the command arguments. The second part can be empty.
     *
     * @param rawUserInput The raw user input.
     * @return A string array whose first element is the command type and the second element is the command
     *         arguments.
     */
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        assert rawUserInput != null : "`rawUserInput` should not be null";
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Parses the raw user input and returns the corresponding command object.
     *
     * @param rawUserInput The raw user input.
     * @return An object representing the command.
     * @throws AthletiException
     */
    public static Command parseCommand(String rawUserInput) throws AthletiException {
        assert rawUserInput != null : "`rawUserInput` should not be null";
        final String[] commandTypeAndParams = splitCommandWordAndArgs(rawUserInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case CommandName.COMMAND_BYE:
            return new ByeCommand();
        case CommandName.COMMAND_HELP:
            return new HelpCommand(commandArgs);
        case CommandName.COMMAND_SAVE:
            return new SaveCommand();
        case CommandName.COMMAND_FIND:
            return new FindCommand(parseDate(commandArgs));
        /* Sleep Management */
        case CommandName.COMMAND_SLEEP_ADD:
            return SleepParser.parseSleepAdd(commandArgs);
        case CommandName.COMMAND_SLEEP_LIST:
            return new ListSleepCommand();
        case CommandName.COMMAND_SLEEP_EDIT:
            return SleepParser.parseSleepEdit(commandArgs);
        case CommandName.COMMAND_SLEEP_DELETE:
            return SleepParser.parseSleepDelete(commandArgs);
        case CommandName.COMMAND_SLEEP_FIND:
            return new FindSleepCommand(parseDate(commandArgs));
        
        case CommandName.COMMAND_SLEEP_GOAL_LIST:
            return new ListSleepGoalCommand();
        case CommandName.COMMAND_SLEEP_GOAL_SET:
            return new SetSleepGoalCommand(SleepParser.parseSleepGoal(commandArgs));
        case CommandName.COMMAND_SLEEP_GOAL_EDIT:
            return new EditSleepGoalCommand(SleepParser.parseSleepGoal(commandArgs));
        
        /* Activity Management */
        case CommandName.COMMAND_ACTIVITY:
            return new AddActivityCommand(ActivityParser.parseActivity(commandArgs));
        case CommandName.COMMAND_CYCLE:
            return new AddActivityCommand(ActivityParser.parseRunCycle(commandArgs, false));
        case CommandName.COMMAND_RUN:
            return new AddActivityCommand(ActivityParser.parseRunCycle(commandArgs, true));
        case CommandName.COMMAND_SWIM:
            return new AddActivityCommand(ActivityParser.parseSwim(commandArgs));
        case CommandName.COMMAND_ACTIVITY_DELETE:
            return new DeleteActivityCommand(ActivityParser.parseActivityIndex(commandArgs));
        case CommandName.COMMAND_ACTIVITY_LIST:
            return new ListActivityCommand(ActivityParser.parseActivityListDetail(commandArgs));
        case CommandName.COMMAND_ACTIVITY_EDIT:
            return new EditActivityCommand(ActivityParser.parseActivityEditIndex(commandArgs),
                    ActivityParser.parseActivityEdit(commandArgs));
        case CommandName.COMMAND_RUN_EDIT:
        case CommandName.COMMAND_CYCLE_EDIT:
            return new EditActivityCommand(ActivityParser.parseActivityEditIndex(commandArgs),
                    ActivityParser.parseRunCycleEdit(commandArgs));
        case CommandName.COMMAND_SWIM_EDIT:
            return new EditActivityCommand(ActivityParser.parseActivityEditIndex(commandArgs),
                    ActivityParser.parseSwimEdit(commandArgs));
        case CommandName.COMMAND_ACTIVITY_FIND:
            return new FindActivityCommand(parseDate(commandArgs));
        case CommandName.COMMAND_ACTIVITY_GOAL_SET:
            return new SetActivityGoalCommand(ActivityParser.parseActivityGoal(commandArgs));
        case CommandName.COMMAND_ACTIVITY_GOAL_EDIT:
            return new EditActivityGoalCommand(ActivityParser.parseActivityGoal(commandArgs));
        case CommandName.COMMAND_ACTIVITY_GOAL_LIST:
            return new ListActivityGoalCommand();
        /* Diet Management */
        case CommandName.COMMAND_DIET_GOAL_SET:
            return new SetDietGoalCommand(DietParser.parseDietGoalSetEdit(commandArgs));
        case CommandName.COMMAND_DIET_GOAL_EDIT:
            return new EditDietGoalCommand(DietParser.parseDietGoalSetEdit(commandArgs));
        case CommandName.COMMAND_DIET_GOAL_LIST:
            return new ListDietGoalCommand();
        case CommandName.COMMAND_DIET_GOAL_DELETE:
            return new DeleteDietGoalCommand(DietParser.parseDietGoalDelete(commandArgs));
        case CommandName.COMMAND_DIET_ADD:
            return new AddDietCommand(DietParser.parseDiet(commandArgs));
        case CommandName.COMMAND_DIET_EDIT:
            return new EditDietCommand(DietParser.parseDietIndex(commandArgs), DietParser.parseDietEdit(commandArgs));
        case CommandName.COMMAND_DIET_DELETE:
            return new DeleteDietCommand(DietParser.parseDietIndex(commandArgs));
        case CommandName.COMMAND_DIET_LIST:
            return new ListDietCommand();
        case CommandName.COMMAND_DIET_FIND:
            return new FindDietCommand(parseDate(commandArgs));
        default:
            throw new AthletiException(Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the raw date time input provided by the user.
     *
     * @param datetime The raw user input containing the date time.
     * @return datetimeParsed   The parsed LocalDateTime object.
     * @throws AthletiException If the input format is invalid.
     */
    public static LocalDateTime parseDateTime(String datetime) throws AthletiException {
        LocalDateTime datetimeParsed;
        try {
            datetimeParsed = LocalDateTime.parse(datetime.replace(" ", "T"));
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DATETIME_INVALID);
        }
        return datetimeParsed;
    }

    public static LocalDate parseDate(String date) throws AthletiException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DATE_INVALID);
        }
    }

}
