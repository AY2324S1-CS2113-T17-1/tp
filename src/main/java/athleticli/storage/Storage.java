package athleticli.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

import athleticli.exceptions.WrappedIOException;

/**
 * Defines the basic methods for file storage.
 */
public class Storage {
    /**
     * Saves strings into a file.
     *
     * @param path      The path to the file.
     * @param items     The stream of strings.
     * @throws IOException
     */
    public static void save(String path, Stream<String> items) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        try {
            items.filter(Objects::nonNull).forEachOrdered(str -> {
                try {
                    fileWriter.write(str);
                } catch (IOException e) {
                    throw new WrappedIOException(e);
                }
            });
        } catch (WrappedIOException e) {
            throw e.getCause();
        }
        fileWriter.close();
    }

    public static Stream<String> load(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return Files.lines(Path.of(path));
    }
}
