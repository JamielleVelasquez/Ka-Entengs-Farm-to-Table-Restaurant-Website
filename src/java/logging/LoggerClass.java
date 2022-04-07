package logging;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.*;

public class LoggerClass {

    private Logger logger;

    public LoggerClass() {

        logger = Logger.getLogger(LoggerClass.class.getName());

        Handler consoleHandler = null;
        Handler fileHandler = null;
        try {
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            PrintWriter writer = new PrintWriter("\\KaEnteng.log");
            writer.print("");
            writer.close();
            fileHandler = new FileHandler("\\KaEnteng.log");

            //Assigning handlers to LOGGER object
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

            //Setting levels to handlers and LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            logger.setLevel(Level.ALL);

            logger.config("Configuration done.");

            //Console handler removed
            logger.removeHandler(consoleHandler);

            logger.log(Level.FINE, "Finer logged");
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }

        logger.finer("Finest example on LOGGER handler completed.");
    }

    public Logger getLoggerClass() {
        return logger;
    }

}
