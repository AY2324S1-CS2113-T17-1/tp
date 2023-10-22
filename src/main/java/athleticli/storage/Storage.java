package athleticli.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import athleticli.data.Data;

/**
 * Defines the basic methods for file storage.
 */
public class Storage {
    /**
     * Returns the data read from the file, or an empty <code>Data</code>
     * object if the file does not exist or cannot be parsed properly.
     *
     * @return  The data read from the file, or an empty <code>Data</code> object.
     */
    public static Data load() {
        try (var fileInputStream = new FileInputStream(Config.PATH_SAVE);
            var objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Data) objectInputStream.readObject();
        } catch (Exception e) {
            return new Data();
        }
    }

    /**
     * Saves the data into the file.
     *
     * @param data             The data to be saved.
     * @throws IOException
     */
    public static void save(Data data) throws IOException {
        File file = new File(Config.PATH_SAVE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        var fileOutputStream = new FileOutputStream(file, false);
        var objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
    }
}
