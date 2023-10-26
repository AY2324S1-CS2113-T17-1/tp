package athleticli.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Defines the behavior of the CLI.
 */
public class Ui {
    private static Ui uiInstance;
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a <code>Ui</code> object, whose input <code>in</code>
     * and output <code>out</code> is the standard input and the standard
     * output, respectively.
     */
    private Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a <code>Ui</code> object, whose input is an <code>InputStream</code>
     * object <code>in</code> and output is an <code>PrintStream</code> object <code>out</code>.
     *
     * @param in    The <code>InputStream</code> accepting the user's input.
     * @param out   The <code>PrintStream</code> displaying the program's output.
     */
    private Ui(InputStream in, PrintStream out) {
        assert in != null : "Input stream `in` should not be null";
        assert out != null : "Print stream `out` should not be null";
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns the singleton instance of `Ui`.
     *
     * @return  The singleton instance of `Ui`.
     */
    public static Ui getInstance() {
        if (uiInstance == null) {
            uiInstance = new Ui();
        }
        return uiInstance;
    }

    /**
     * Returns the user's input.
     *
     * @return The user's input.
     */
    public String getUserCommand() {
        out.print(Message.PROMPT);
        return in.nextLine();
    }

    /**
     * Shows the <code>messages</code> in a beautiful format.
     *
     * @param messages  The messages to be shown.
     */
    public void showMessages(String... messages) {
        assert messages != null : "Messages should not be null";
        out.print(Message.LINE);
        for (String message : messages) {
            out.println(Message.PREFIX_MESSAGE + message);
        }
        out.println(Message.LINE);
    }

    /**
     * Shows message for exception <code>e</code>.
     *
     * @param e The exception whose message will be shown.
     */
    public void showException(Exception e) {
        assert e != null : "Exception `e` should not be null";
        showMessages(Message.PREFIX_EXCEPTION + e.getMessage());
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        showMessages(Message.MESSAGE_HELLO);
    }
}
