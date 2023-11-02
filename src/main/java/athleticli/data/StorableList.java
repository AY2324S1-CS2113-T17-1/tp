package athleticli.data;

import static athleticli.ui.Message.MESSAGE_LOAD_EXCEPTION;

import java.io.IOException;
import java.util.ArrayList;

import athleticli.exceptions.AthletiException;
import athleticli.exceptions.WrappedAthletiException;
import athleticli.storage.Storage;

public abstract class StorableList<T> extends ArrayList<T> {
    private String path;

    /**
     * Constructs an empty list with its storage path.
     */
    public StorableList(String path) {
        this.path = path;
    }

    /**
     * Saves to a file.
     */
    public void save() throws IOException {
        Storage.save(path, this.stream().map(this::unparse));
    }

    /**
     * Loads from a file.
     */
    public void load() throws AthletiException {
        try {
            Storage.load(path).map(s -> {
                try {
                    return parse(s);
                } catch (AthletiException e) {
                    throw new WrappedAthletiException(e);
                }
            }).forEachOrdered(this::add);
        } catch (IOException | WrappedAthletiException e) {
            throw new AthletiException(String.format(MESSAGE_LOAD_EXCEPTION, path));
        }
    }

    /**
     * Parses a T object from a string.
     *
     * @param s     The string to be parsed.
     * @return      The T object parsed from the string.
     */
    public abstract T parse(String s) throws AthletiException;

    /**
     * Unparses a T object to a string.
     *
     * @param t     The T object to be parsed.
     * @return      The string unparsed from the T object.
     */
    public abstract String unparse(T t);
}
