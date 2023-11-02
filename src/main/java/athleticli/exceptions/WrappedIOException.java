package athleticli.exceptions;

import java.io.IOException;

/**
 * Wraps an <code>IOException</code> in <code>RuntimeExcpetion</code> so that it can be thrown from inside a stream.
 */
public class WrappedIOException extends RuntimeException {
    private IOException cause;

    public WrappedIOException(IOException cause) {
        this.cause = cause;
    }

    @Override
    public IOException getCause() {
        return cause;
    }
}
