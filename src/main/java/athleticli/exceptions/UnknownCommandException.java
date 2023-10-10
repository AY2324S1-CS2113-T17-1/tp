package athleticli.exceptions;

public class UnknownCommandException extends AthletiException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
