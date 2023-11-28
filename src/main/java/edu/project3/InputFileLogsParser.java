package edu.project3;

import edu.project3.utility.UtilityClass;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import static edu.project3.DataClass.addReadedLog;
import static edu.project3.DataClass.putParsedLogToDataClass;
import static edu.project3.FileAndPathManager.createReaderForLogParser;
import static edu.project3.FileAndPathManager.findLogFiles;

@SuppressWarnings("HideUtilityClassConstructor")
public class InputFileLogsParser {

    private static boolean isCurrInRange(Date curr) {
        if (ArgumentsData.getFrom() == null && ArgumentsData.getTo() == null) {
            return true;
        } else if (ArgumentsData.getFrom() == null) {
            return curr.compareTo(ArgumentsData.getTo()) <= 0;
        } else if (ArgumentsData.getTo() == null) {
            return (curr.compareTo(ArgumentsData.getFrom()) >= 0);
        } else {
            return (curr.compareTo(ArgumentsData.getFrom()) >= 0
                && curr.compareTo(ArgumentsData.getTo()) <= 0);
        }
    }

    private static void parseOneLog(String line) {
        LogEntry log = LogParser.parse(line);
        if (isCurrInRange(log.getTimeLocal())) {
            putParsedLogToDataClass(log);
        }
    }

    private static void parseWebFile() {
        try {
            String line;
            URL url = URI.create(ArgumentsData.getPath()).toURL();
            addReadedLog(String.valueOf(url));
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = reader.readLine()) != null) {
                parseOneLog(line);
            }

            reader.close();
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
    }

    private static void parseLocalFile() {
        try {
            List<Path> paths = findLogFiles(ArgumentsData.getPath());
            String line;
            for (Path logPath : paths) {
                addReadedLog(logPath.toString());

                try (BufferedReader reader = createReaderForLogParser(logPath)) {
                    while ((line = reader.readLine()) != null) {
                        parseOneLog(line);
                    }
                } catch (Exception e) {
                    ErrorLogger.createLogError(e.getMessage());
                }
            }
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
    }

    public static void parseInputFile() {
        if (ArgumentsData.getPath().startsWith(UtilityClass.getHttpString())) {
            parseWebFile();
        } else {
            parseLocalFile();
        }
    }
}
