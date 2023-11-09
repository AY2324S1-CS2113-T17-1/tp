package athleticli;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.commands.SaveCommand;
import athleticli.data.Data;
import athleticli.exceptions.AthletiException;
import athleticli.parser.Parser;
import athleticli.ui.Ui;

/**
 * Defines the basic structure and the behavior of AthletiCLI.
 */
public class AthletiCLI {
    private static Logger logger = Logger.getLogger(AthletiCLI.class.getName());
    private static Ui ui = Ui.getInstance();
    private static Data data = Data.getInstance();

    private static Thread runSaveCommand = new Thread(() -> {
        try {
            final String[] feedback = new SaveCommand().execute(data);
            ui.showMessages(feedback);
        } catch (AthletiException e) {
            ui.showException(e);
        }
    });

    /**
     * Constructs an <code>AthletiCLI</code> object.
     */
    private AthletiCLI() {
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
    private void run() {
        logger.entering(getClass().getName(), "run");
        ui.showWelcome();
        try {
            data.load();
        } catch (AthletiException e) {
            ui.showException(e);
            data.clear();
        }
        boolean isExit = false;
        boolean isShutdownHookAdded = false;
        while (!isExit) {
            final String rawUserInput = ui.getUserCommand();
            try {
                logger.info("Command read: " + rawUserInput);
                final Command command = Parser.parseCommand(rawUserInput);
                final String[] feedback = command.execute(data);
                ui.showMessages(feedback);
                logger.info("Command executed successfully");
                isExit = command.isExit();
                /* add shutdown hook if the first valid command is not exit */
                if (!isExit && !isShutdownHookAdded) {
                    /* save data when the JVM begins its shutdown sequence */
                    Runtime.getRuntime().addShutdownHook(runSaveCommand);
                    isShutdownHookAdded = true;
                }
            } catch (AthletiException e) {
                ui.showException(e);
                logger.warning("Exception caught: " + e);
            }
        }
        logger.exiting(getClass().getName(), "run");
    }
}
