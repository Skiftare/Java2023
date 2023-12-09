package edu.project3;

import edu.project3.analyzer.InputFileLogsParser;
import edu.project3.generators.reports.Report;
import edu.project3.generators.reports.ReportGenerator;
import edu.project3.generators.reports.ReportPrinter;
import edu.project3.systeminteraction.DataClass;
import edu.project3.systeminteraction.FileAndPathManager;
import static edu.project3.systeminteraction.ArgumentsData.processArgs;

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
        ReportGenerator gen = new ReportGenerator();
        Report report = gen.generateReport();

        ReportPrinter.printReport(report);
    }

}
