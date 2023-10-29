package athleticli.commands;

import java.io.IOException;

import athleticli.data.Data;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

public class SaveCommand extends Command {
    /**
     * Saves the data into the file.
     *
     * @param data The current data.
     * @return The messages to be shown to the user.
     * @throws AthletiException
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        assert data != null;
        try {
            data.save();
        } catch (IOException e) {
            throw new AthletiException(Message.MESSAGE_IO_EXCEPTION);
        }
        return new String[] {Message.MESSAGE_SAVE};
    }
}
