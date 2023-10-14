package athleticli.exceptions;

public class EmptyArgumentException extends AthletiException{
    public EmptyArgumentException() {
        super("Please enter some information to your command!");
    }

}
