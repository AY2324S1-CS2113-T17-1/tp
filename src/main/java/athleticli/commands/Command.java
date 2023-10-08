package athleticli.commands;

import athleticli.exceptions.AthletiException;

/**
 * Defines the basic methods of a command.
 */
public abstract class Command {
    /**
     * Executes the command and returns the messages to be shown to the user.
     *
     * @return                  The messages to be shown to the user.
     * @throws AthletiException
     */
    public abstract String[] execute() throws AthletiException;

    /**
     * Returns <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     *
     * @return <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     */
    public boolean isExit() {
        return false;
    }
}
