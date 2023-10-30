package athleticli.data;

import java.io.IOException;
import java.util.ArrayList;

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
    public void load() {
        try {
            Storage.load(path).map(this::parse).forEachOrdered(this::add);
        } catch (IOException e) {
            this.clear();
        }
    }

    /**
     * Parses a T object from a string.
     *
     * @param s     The string to be parsed.
     * @return      The T object parsed from the string.
     */
    public abstract T parse(String s);

    /**
     * Unparses a T object to a string.
     *
     * @param t     The T object to be parsed.
     * @return      The string unparsed from the T object.
     */
    public abstract String unparse(T t);
}
