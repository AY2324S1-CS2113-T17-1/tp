package athleticli.exceptions;

/**
 * Wraps an <code>AthletiException</code> in <code>RuntimeExcpetion</code>
 * so that it can be thrown from inside a stream.
 */
public class WrappedAthletiException extends RuntimeException {
    private AthletiException cause;

    public WrappedAthletiException(AthletiException cause) {
        this.cause = cause;
    }

    @Override
    public AthletiException getCause() {
        return cause;
    }
}
