package edu.project3;

import static edu.project3.FileAndPathManager.writeToFile;

public class ReportPrinter {
    public static void printReport(Report report){
        for(Table table: report.getTablesInReport()){
            table.printAsString();
            writeToFile(table);
        }
    }
}
