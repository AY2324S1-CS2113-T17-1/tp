package athleticli;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Parser;
import athleticli.ui.Ui;

/**
 * Defines the basic structure and the behavior of AthletiCLI.
 */
public class AthletiCLI {
    private static Logger logger = Logger.getLogger(AthletiCLI.class.getName());
    private Ui ui;
    private Data data;

    /**
     * Constructs an <code>AthletiCLI</code> object.
     */
    public AthletiCLI() {
        ui = new Ui();
        data = new Data();
        LogManager.getLogManager().reset();
        try {
            logger.addHandler(new FileHandler("%t/athleticli-log.txt"));
        } catch(IOException e) {
            logger.addHandler(new ConsoleHandler());
        }
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
        System.out.println(getClass().getClassLoader().getResource("logging.properties"));
        logger.entering(getClass().getName(), "run");
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            final String rawUserInput = ui.getUserCommand();
            try {
                logger.info("Command read: " + rawUserInput);
                final Command command = Parser.parseCommand(rawUserInput);
                final String[] feedback = command.execute(data);
                ui.showMessages(feedback);
                logger.info("Command executed successfully");
                isExit = command.isExit();
            } catch (AthletiException e) {
                ui.showException(e);
                logger.warning("Exception caught: " + e);
            }
        }
        logger.exiting(getClass().getName(), "run");
    }
}
