package logging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerClass {

    private Logger logger;

    public LoggerClass() {

        logger = Logger.getLogger(LoggerClass.class.getName());
        Formatter simpleFormatter;
        Handler fileHandler = null;
        try {
            //Creating file and fileHandler
            String fileName = new SimpleDateFormat("'logs\\'yyyyMMddHHmm'KaEnteng.log'").format(new Date());
            File file = new File(fileName);
            fileHandler = new FileHandler(fileName);

            // Creating SimpleFormatter
            simpleFormatter = new SimpleFormatter();

            //Assigning handlers to LOGGER object
            logger.addHandler(fileHandler);

            // Setting formatter to the handler
            fileHandler.setFormatter(simpleFormatter);

            if (file.createNewFile()) {
                logger.log(Level.CONFIG, "File Created at: {0}", file.getAbsolutePath());

            } else {
                logger.log(Level.SEVERE, "Error in creating the file");
            }

            //Setting levels to handlers and LOGGER
            fileHandler.setLevel(Level.ALL);
            logger.setLevel(Level.ALL);

            logger.log(Level.CONFIG, "Loging configuration done.");

        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Error occured in FileHandler.", exception);
        }
    }

    public Logger getLoggerClass() {
        return logger;
    }

}
