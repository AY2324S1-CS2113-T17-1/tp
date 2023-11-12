package athleticli.commands;

import static java.util.Map.entry;

import java.util.Map;

import athleticli.data.Data;
import athleticli.exceptions.AthletiException;
import athleticli.parser.CommandName;
import athleticli.ui.Message;

public class HelpCommand extends Command {
    private static final String[] HELP_ALL = {
        /* Activity Management */
        "\nActivity Management:",
        Message.HELP_ADD_ACTIVITY,
        Message.HELP_ADD_RUN,
        Message.HELP_ADD_SWIM,
        Message.HELP_ADD_CYCLE,
        Message.HELP_DELETE_ACTIVITY,
        Message.HELP_LIST_ACTIVITY,
        Message.HELP_EDIT_ACTIVITY,
        Message.HELP_EDIT_RUN,
        Message.HELP_EDIT_SWIM,
        Message.HELP_EDIT_CYCLE,
        Message.HELP_FIND_ACTIVITY,
        Message.HELP_SET_ACTIVITY_GOAL,
        Message.HELP_EDIT_ACTIVITY_GOAL,
        Message.HELP_DELETE_ACTIVITY_GOAL,
        Message.HELP_LIST_ACTIVITY_GOAL,
        /* Diet Management */
        "\nDiet Management:",
        Message.HELP_ADD_DIET,
        Message.HELP_EDIT_DIET,
        Message.HELP_DELETE_DIET,
        Message.HELP_LIST_DIET,
        Message.HELP_FIND_DIET,
        /* Sleep Management */
        "\nSleep Management:",
        Message.HELP_ADD_SLEEP,
        Message.HELP_LIST_SLEEP,
        Message.HELP_DELETE_SLEEP,
        Message.HELP_EDIT_SLEEP,
        Message.HELP_FIND_SLEEP,
        /* Misc */
        "\nMisc:",
        Message.HELP_FIND,
        Message.HELP_SAVE,
        Message.HELP_BYE,
        Message.HELP_HELP,
        "\n" + Message.HELP_DETAILS
    };
    private static final Map<String, String> HELP_MESSAGES = Map.ofEntries(
            /* Activity Management */
            entry(CommandName.COMMAND_ACTIVITY, Message.HELP_ADD_ACTIVITY),
            entry(CommandName.COMMAND_RUN, Message.HELP_ADD_RUN),
            entry(CommandName.COMMAND_SWIM, Message.HELP_ADD_SWIM),
            entry(CommandName.COMMAND_CYCLE, Message.HELP_ADD_CYCLE),
            entry(CommandName.COMMAND_ACTIVITY_DELETE, Message.HELP_DELETE_ACTIVITY),
            entry(CommandName.COMMAND_ACTIVITY_LIST, Message.HELP_LIST_ACTIVITY),
            entry(CommandName.COMMAND_ACTIVITY_EDIT, Message.HELP_EDIT_ACTIVITY),
            entry(CommandName.COMMAND_RUN_EDIT, Message.HELP_EDIT_RUN),
            entry(CommandName.COMMAND_SWIM_EDIT, Message.HELP_EDIT_SWIM),
            entry(CommandName.COMMAND_CYCLE_EDIT, Message.HELP_EDIT_CYCLE),
            entry(CommandName.COMMAND_ACTIVITY_FIND, Message.HELP_FIND_ACTIVITY),
            entry(CommandName.COMMAND_ACTIVITY_GOAL_SET, Message.HELP_SET_ACTIVITY_GOAL),
            entry(CommandName.COMMAND_ACTIVITY_GOAL_EDIT, Message.HELP_EDIT_ACTIVITY_GOAL),
            entry(CommandName.COMMAND_ACTIVITY_GOAL_DELETE, Message.HELP_DELETE_ACTIVITY_GOAL),
            entry(CommandName.COMMAND_ACTIVITY_GOAL_LIST, Message.HELP_LIST_ACTIVITY_GOAL),
            /* Diet Management */
            entry(CommandName.COMMAND_DIET_ADD, Message.HELP_ADD_DIET),
            entry(CommandName.COMMAND_DIET_EDIT, Message.HELP_EDIT_DIET),
            entry(CommandName.COMMAND_DIET_DELETE, Message.HELP_DELETE_DIET),
            entry(CommandName.COMMAND_DIET_LIST, Message.HELP_LIST_DIET),
            entry(CommandName.COMMAND_DIET_FIND, Message.HELP_FIND_DIET),
            /* Sleep Management */
            entry(CommandName.COMMAND_SLEEP_ADD, Message.HELP_ADD_SLEEP),
            entry(CommandName.COMMAND_SLEEP_LIST, Message.HELP_LIST_SLEEP),
            entry(CommandName.COMMAND_SLEEP_DELETE, Message.HELP_DELETE_SLEEP),
            entry(CommandName.COMMAND_SLEEP_EDIT, Message.HELP_EDIT_SLEEP),
            entry(CommandName.COMMAND_SLEEP_FIND, Message.HELP_FIND_SLEEP),
            /* Misc */
            entry(CommandName.COMMAND_FIND, Message.HELP_FIND),
            entry(CommandName.COMMAND_SAVE, Message.HELP_SAVE),
            entry(CommandName.COMMAND_BYE, Message.HELP_BYE),
            entry(CommandName.COMMAND_HELP, Message.HELP_HELP)
    );

    private String command;
    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Returns the help messages to be shown to the user.
     *
     * @param data The current data.
     * @return The messages to be shown to the user.
     * @throws AthletiException
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        if (HELP_MESSAGES.containsKey(command)) {
            return new String[] {"Usage: " + HELP_MESSAGES.get(command)};
        } else {
            return HELP_ALL;
        }
    }
}
