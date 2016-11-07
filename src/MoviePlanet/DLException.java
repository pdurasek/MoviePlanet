package Exercise6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DLException extends Throwable
{
    private static final Logger LOGGER = Logger.getLogger(DLException.class.getName());
    private static FileHandler fh = null;
    private String logMsg = "Unable to complete operation";

    public DLException(Exception exception, ArrayList<String> causeList)
    {
        initLogger();
        logMsg = "Description: " + exception.getMessage();
        if (causeList.size() > 0)
        {
            logMsg += ". Cause: ";
            for (int i = 0; i < causeList.size(); i++)
            {
                logMsg += ", " + causeList.get(i);
            }
        }
        log(logMsg);
    }

    public DLException(Exception exception, ArrayList<String> causeList, String... exceptionData)
    {
        initLogger();
        log(getLogMsg(causeList, exceptionData));
    }

    private void initLogger()
    {
        if (fh == null)
        {
            try
            {
                fh = new FileHandler("ExceptionLog.log", true);
                fh.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fh);
                LOGGER.setUseParentHandlers(false);
            }
            catch (IOException e)
            {
                System.out.println("Error while creating the log file!");
            }
        }
        else
        {
            System.out.println("Already exists!");
        }
    }

    private String getLogMsg(ArrayList<String> causeList, String... messages)
    {
        logMsg = "Description: " + messages[0] + ". Error code: " + messages[1] + ". State code: " + messages[2];
        if (causeList.size() > 0)
        {
            logMsg += ". Cause: ";
            for (int i = 0; i < causeList.size(); i++)
            {
                logMsg += ", " + causeList.get(i);
            }
        }
        return logMsg;
    }

    public void log(String logMsg)
    {
        LOGGER.log(Level.SEVERE, logMsg);
    }

}
