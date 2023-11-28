package edu.project3;

import edu.project3.utility.UtilityClass;
import static edu.project3.ArgumentsData.processArgs;

@SuppressWarnings("HideUtilityClassConstructor")
public class LogAnalyzer {
    public static void reset(){
        DataClass.resetStaticVariables();
        UtilityClass.setFileFormat(null);
        UtilityClass.setFileName(null);
    }
    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        processArgs(args);

        InputFileLogsParser.parseInputFile();

        FileAndPathManager.initFileForLogOutput();

        Report report = ReportGenerator.generateReport();

        ReportPrinter.printReport(report);

    }

}
