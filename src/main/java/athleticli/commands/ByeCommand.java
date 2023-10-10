package athleticli.commands;

import athleticli.data.Data;
import athleticli.ui.Message;

public class ByeCommand extends Command {
    /**
     * Returns <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     *
     * @return <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the bye message to be shown to the user.
     *
     * @return      The messages to be shown to the user.
     */
    public String[] execute(Data data) {
        return new String[] {Message.MESSAGE_BYE};
    }
}
