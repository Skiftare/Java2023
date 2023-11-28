package edu.project3;

import static edu.project3.ArgumentsData.processArgs;

@SuppressWarnings("HideUtilityClassConstructor")
public class LogAnalyzer {
    public static void reset() {
        DataClass.resetStaticVariables();
        FileAndPathManager.setFileFormat(null);
        FileAndPathManager.setFileName(null);
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
