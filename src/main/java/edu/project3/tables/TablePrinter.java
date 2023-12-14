package edu.project3.tables;

import java.util.List;
import static edu.project3.utility.UtilityTableClass.ENDL_CHAR;

abstract class TablePrinter {
    protected StringBuilder sb = new StringBuilder();
    protected String nameOfTable;
    protected List<String> headers;
    protected List<List<String>> data;

    void printTable(String nameOfTable, List<String> headers, List<List<String>> data) {
        this.nameOfTable = nameOfTable;
        this.headers = headers;
        this.data = data;

        printTableHeader();

        for (List<String> row : data) {
            printTableRow(row);
        }

        sb.append(ENDL_CHAR);
    }

    protected abstract void printTableHeader();

    protected abstract void printTableRow(List<String> row);

    String printAsString() {
        return sb.toString();
    }
}
