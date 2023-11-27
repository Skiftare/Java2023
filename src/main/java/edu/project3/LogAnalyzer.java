package edu.project3;

import static edu.project3.ArgumentsData.processArgs;

@SuppressWarnings("HideUtilityClassConstructor")
public class LogAnalyzer {
    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        processArgs(args);

        InputFileLogsParser.parseInputFile();

        FileAndPathManager.initFileForLogOutput();

        Report report = ReportGenerator.generateReport();

        ReportPrinter.printReport(report);
    }

}
