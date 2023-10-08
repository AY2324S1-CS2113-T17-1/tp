package athleticli;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Parser;
import athleticli.ui.Ui;

/**
 * Defines the basic structure and the behavior of AthletiCLI.
 */
public class AthletiCLI {
    private Ui ui;
    private Data data;

    /**
     * Constructs an <code>AthletiCLI</code> object.
     */
    public AthletiCLI() {
        ui = new Ui();
        data = new Data();
    }

    /**
     * Creates an `AthletiCLI` object and runs it.
     *
     * @param args  Arguments obtained from the command line.
     */
    public static void main(String[] args) {
        new AthletiCLI().run();
    }

    /**
     * Displays the welcome interface, continuously reads user input
     * and executes corresponding instructions until exiting.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            final String rawUserInput = ui.getUserCommand();
            try {
                final Command command = Parser.parseCommand(rawUserInput);
                final String[] feedback = command.execute(data);
                ui.showMessages(feedback);
                isExit = command.isExit();
            } catch (AthletiException e) {
                ui.showException(e);
            }
        }
    }
}
