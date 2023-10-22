package athleticli.commands;

import java.io.IOException;

import athleticli.data.Data;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;
import athleticli.storage.Storage;

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
     * Saves data and returns the bye message to be shown to the user.
     *
     * @return      The messages to be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        try {
            Storage.save(data);
        } catch (IOException e) {
            throw new AthletiException(Message.MESSAGE_IO_EXCEPTION);
        }
        return new String[] {Message.MESSAGE_BYE};
    }
}
