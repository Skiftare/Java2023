package edu.project3.reports;

import edu.project3.tables.Table;
import static edu.project3.systeminteraction.FileAndPathManager.writeToFile;

@SuppressWarnings("HideUtilityClassConstructor")
public class ReportPrinter {
    public static void printReport(Report report) {
        for (Table table : report.getTablesInReport()) {
            table.printAsString();
            writeToFile(table);
        }
    }
}
